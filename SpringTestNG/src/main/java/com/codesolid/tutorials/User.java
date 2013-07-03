package com.codesolid.tutorials;

/**
 * Class User
 * Description: An example of a bean dependency.  Story is dependent on User.
 */
public class User {
    private String role;

    public User() {
        setRole("User");
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
