/**
 * Class Actor
 * Description:  A user of the system, the subject of a user story.  We use
 *               actor instead of user as table name to avoid any conflict
 *               with the database concept of user
 */
package com.codesolid.tutorials.model.entities;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Table( name = "ACTORS" )
@Entity
public class Actor {
    public static final String DEFAULT_ROLE = "Standard User";

    public Actor() {
        setRole(Actor.DEFAULT_ROLE);
    }

    private Long id;
    private String role;


    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name="user_role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}


