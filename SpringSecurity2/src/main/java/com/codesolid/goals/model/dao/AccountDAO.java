package com.codesolid.goals.model.dao;

import com.codesolid.goals.model.dto.Account;
import com.codesolid.goals.security.SiteUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class AccountDAO
 * Description:
 */
public class AccountDAO {

    private JdbcTemplate jdbcTemplate;

    public AccountDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public Account loadAccountById(int id) {
        String query = "SELECT id, subdomain_name from accounts where id = ?";
        return jdbcTemplate.queryForObject(query, new Object[] {id}, new AccountRowMapper());
    }

    @Transactional
    public Account createAccount(Account account) {
        String query = "INSERT into accounts(subdomain_name) values (?)";
        jdbcTemplate.update(query, account.getDomainName());
        return loadAccountBySubdomainName(account.getDomainName());
    }

    @Transactional
    public Account loadAccountBySubdomainName(String subdomain) {
        String query = "SELECT id, subdomain_name from accounts where subdomain_name = ?";
        return jdbcTemplate.queryForObject(query, new Object[] {subdomain}, new AccountRowMapper());
    }

}

class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setDomainName(rs.getString("subdomain_name"));
        return account;
    }
}

