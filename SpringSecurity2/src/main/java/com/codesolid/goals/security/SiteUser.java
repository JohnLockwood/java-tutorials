package com.codesolid.goals.security;

import com.codesolid.goals.model.dto.Account;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.ArrayList;
import java.util.Collection;


/**
 * Class SiteUser
 * Description:
 */
public class SiteUser extends User  {

    private boolean enabled;

    public SiteUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public SiteUser() {
        this("guest@guestaculous.net", "5950fdef2ec5e081d21b23e61f14340c006c9905da7038c109f9364deb3c6442121965af32ecfaf4", true, true, true, true, new ArrayList<GrantedAuthority>(1));

    }

    @NotEmpty(message = "Email address may not be empty")
    @Email(message = "Not a valid email address")
    private String email;       /* email and username are equivalent */

    @NotEmpty(message = "Password may not be empty")
    @Length(min=8, max=12, message="Password must contain between eight and twelve characters")
    private String password;

    private long id;
    private Account account;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail()  {
        return email;
    }

    public void setUsername(String username) {
        setEmail(username);
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
