package com.mode.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Created by Lei on 1/8/16.
 */
public class AuthenticationUser extends User{

    private final int userId;

    public AuthenticationUser(String username, String password, boolean enabled, boolean
            accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
                              Collection<? extends GrantedAuthority> authorities, int userId) {
        super(username, password, enabled, accountNonExpired,  credentialsNonExpired,
                accountNonLocked, authorities);
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}
