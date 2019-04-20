/*
 * This file is generated by jOOQ.
 */
package ru.hixon.microservice.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Users implements Serializable {

    private static final long serialVersionUID = 1830698625;

    private final String userId;
    private final String role;

    public Users(Users value) {
        this.userId = value.userId;
        this.role = value.role;
    }

    public Users(
        String userId,
        String role
    ) {
        this.userId = userId;
        this.role = role;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getRole() {
        return this.role;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Users (");

        sb.append(userId);
        sb.append(", ").append(role);

        sb.append(")");
        return sb.toString();
    }
}