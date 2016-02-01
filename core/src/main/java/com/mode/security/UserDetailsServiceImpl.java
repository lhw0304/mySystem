package com.mode.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mode.dao.AccountDAO;
import com.mode.entity.Account;

/**
 * Implements Spring Security {@link UserDetailsService} that is injected into
 * authentication provider in configuration XML. It interacts with domain, loads
 * user details and wraps it into {@link UserContext} which implements Spring
 * Security {@link UserDetails}.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountDAO accountDAO;

    /**
     * This will be called from
     * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider#retrieveUser(java.lang.String, org.springframework.security.authentication.UsernamePasswordAuthenticationToken)}
     * when
     * {@link AuthenticationService#authenticate(java.lang.String, java.lang.String)}
     * calls
     * {@link AuthenticationManager#authenticate(org.springframework.security.core.Authentication)}
     * . Easy.
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountDAO.getAccountByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("User " + username + " not found.");
        }

        String[] roles = account.getRole().split(",");
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if (roles != null && roles.length > 0) {
            for(String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }

        boolean accountNonLocked = true;

        if(account.getLocked() == 1){
            accountNonLocked = false;
        }

        return new AuthenticationUser(username, account.getPassword(), true, true, true, accountNonLocked,
                authorities, account.getUserId());
    }
}