package com.mode.init;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mode.dao.AccountDAO;
import com.mode.dao.ProfileDAO;
import com.mode.dao.UserDeviceDAO;
import com.mode.dao.UserLoginLogDAO;

/**
 * Created by Lei on 9/28/15.
 *
 * Add mockup beans for DAOs
 */
@Configuration
public class TestDAOConfig {

    @Bean
    public AccountDAO accountDAO(){
        return Mockito.mock(AccountDAO.class);
    }

    @Bean
    public ProfileDAO profileDAO(){
        return Mockito.mock(ProfileDAO.class);
    }

    @Bean
    public UserLoginLogDAO userLoginLogDAO(){
        return Mockito.mock(UserLoginLogDAO.class);
    }

    @Bean
    public UserDeviceDAO userDeviceDAO(){
        return Mockito.mock(UserDeviceDAO.class);
    }
}
