package com.mode.util;

import com.mode.base.Message;
import com.mode.exception.ModeException;

/**
 * Created by Lei on 3/22/16.
 */
public class LoginValidation {

    /**
     * Check the type of the login string
     *
     * @param login
     * @return
     */
    public static String validate(String login) {
        // Only contain numbers
        final String phonePattern = "[0-9]+";
        // Only contain numbers and letters
        final String usernamePattern = "^(?=.*[A-Z])(?=.*[0-9])[A-Z0-9]+$";
        // Email validation
        final String emailPattern =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (login.matches(phonePattern) && login.length() > 0) {
            return "phone";
        } else if (login.matches(emailPattern) && login.length() > 0) {
            return "email";
        } else if(login.matches(usernamePattern) && login.length() > 0){
            return "username";
        } else {
            throw new ModeException(Message.NOT_VALID_USERNAME);
        }

    }
}
