package com.codesolid.goals.security;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.codesolid.goals.model.dao.AccountDAO;
import com.codesolid.goals.model.dto.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class SiteUserDetailsService
 * Description:
 */
public class SiteUserDetailsService extends JdbcUserDetailsManager {
    /*
        TODO: At this point we need to use our jdbcTemplate member variable
        using not RowMapper but the more custom one?  Or see below
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        String sql = "select id, username, password, enabled, account_id from users where username = ?";
        SiteUserRowMapper mapper = new SiteUserRowMapper();
        mapper.setJdbcTemplate((getJdbcTemplate()));
        SiteUser user = (SiteUser) getJdbcTemplate().queryForObject(sql, new Object[] {username}, mapper);
        return user;
        //return super.loadUserByUsername(username);
    }

    // TODO Unique constraint may be violated on the account, or on userName -- test!
    @Override
    @Transactional
    public void createUser(UserDetails userDetails){
        SiteUser siteUser = (SiteUser) userDetails;
        if (siteUser.getId() != 0)
        {
            throw new UnsupportedOperationException("SiteUserDetailsService.createUser: user with id " + new Long(siteUser.getId()).toString() + " has already been created!  CreateUser not supported, use updateUser instead.");
        }

        Account account = siteUser.getAccount();
        if (account == null) {
            throw new UnsupportedOperationException("SiteUserDetailsService.createUser: cannot create a user without an associated account");
        }

        // Make sure our account_id is valid.  If not, create the account now too.
        if(account.getId() == 0) {
            AccountDAO dao = new AccountDAO(getJdbcTemplate());
            Account saved = dao.createAccount(account);
            siteUser.setAccount(saved);;
        }

        String sql = "INSERT INTO users(username, password, enabled, account_id) values(?, ?, ?, ?)";
        getJdbcTemplate().update(sql, siteUser.getUsername(), siteUser.getPassword(), siteUser.isEnabled(), siteUser.getAccount().getId());
    }

    @Override
    @Transactional
    public void updateUser(UserDetails details) {
        super.updateUser(details);
    }

}

class SiteUserRowMapper implements RowMapper <SiteUser>
{
    JdbcTemplate jdbcTemplate;  // To use AccountDao to retrive nested object

    public void setJdbcTemplate(JdbcTemplate template) {
        jdbcTemplate = template;
    }


    @Override
    public SiteUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        SiteUser user = new SiteUser();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setEnabled(rs.getBoolean("enabled"));
        user.setPassword(rs.getString("password"));

        // Load and set the account object
        AccountDAO dao = new AccountDAO(jdbcTemplate);
        Account account = dao.loadAccountById(rs.getInt("account_id"));
        user.setAccount(account);

        return user;
            /* example fragment...
            user.setId
            user.setEmail(rs.getString("EMAIL"));
            customer.setName(rs.getString("NAME"));
            customer.setAge(rs.getInt("AGE"));
            return customer;
            */
    }


}

