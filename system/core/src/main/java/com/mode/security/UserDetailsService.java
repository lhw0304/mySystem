package com.mode.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.mode.base.Message;
import com.mode.dao.AccountDAO;
import com.mode.entity.Account;
import com.mode.exception.ModeException;


@Component
public class UserDetailsService
        implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private AccountDAO accountDAO;

    @Override
    public UserDetails loadUserByUsername(final String username) {

        String login = username.toLowerCase();

        Account account = new Account();
        account.setUsername(login);

        account = accountDAO.getAccount(account);

        if (account == null) {
            throw new UsernameNotFoundException("User " + login + " was not found in the database");
        } else if (account.getStatus() == 1) {
            throw new UserNotActivatedException("User " + login + " is not activated");
        }

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (account.getRole() != null) {
            String[] authorities = account.getRole().split(",");
            for (String role : authorities) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
                grantedAuthorities.add(grantedAuthority);
            }
        } else {
            throw new ModeException(Message.UNAUTHORIZED);
        }

        return new AuthenticationUser(account.getUsername(), account.getPassword(),
                grantedAuthorities, account.getId());

    }

}