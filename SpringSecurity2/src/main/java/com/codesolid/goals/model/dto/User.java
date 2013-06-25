package com.codesolid.goals.model.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Author: John Lockwood
 * Date: 6/21/13
 * Time: 12:54 PM
 */
public class User /* extends org.springframework.security.core.userdetails.User */ {
    public User() {

    }

    @NotEmpty(message = "Email address may not be empty")
    @Email(message = "Not a valid email address")
    private String email;       /* email and username are equivalent */

    @NotEmpty(message = "Password may not be empty")
    @Length(min=8, max=12, message="Password must contain between eight and twelve characters")
    private String password;


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail()  {
        return email;
    }

    public void setUsername(String username) {
        setEmail(username);
    }

    //@Override
    public String getUsername() {
        return getEmail();
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }
}
