package com.lambdaschool.zoos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleid;

    @Column(nullable = false,
            unique = true)
    private String name;

    // map over 'role' to userRoles
    @OneToMany(mappedBy = "role",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("role")
    private List<UserRoles> userRoles = new ArrayList<>();

    // default constructor
    public Role()
    {
    }

    // constructor
    public Role(String name)
    {
        this.name = name.toUpperCase();
    }

    // Getters and Settters
    public long getRoleid()
    {
        return roleid;
    }

    public void setRoleid(long roleid)
    {
        this.roleid = roleid;
    }

    public String getName()
    {
        if (name == null)
        {
            return null;
        } else
        {
            return name.toUpperCase();
        }
    }

    public void setName(String name)
    {
        this.name = name.toUpperCase();
    }

    public List<UserRoles> getUserRoles()
    {
        return userRoles;
    }

    public void setUserRoles(List<UserRoles> userRoles)
    {
        this.userRoles = userRoles;
    }
}