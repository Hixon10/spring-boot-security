/*
 * This file is generated by jOOQ.
 */
package ru.hixon.microservice;


import javax.annotation.Generated;

import org.jooq.UniqueKey;
import org.jooq.impl.Internal;

import ru.hixon.microservice.tables.FlywaySchemaHistory;
import ru.hixon.microservice.tables.Users;
import ru.hixon.microservice.tables.records.FlywaySchemaHistoryRecord;
import ru.hixon.microservice.tables.records.UsersRecord;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<FlywaySchemaHistoryRecord> FLYWAY_SCHEMA_HISTORY_PK = UniqueKeys0.FLYWAY_SCHEMA_HISTORY_PK;
    public static final UniqueKey<UsersRecord> USERS_PKEY = UniqueKeys0.USERS_PKEY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class UniqueKeys0 {
        public static final UniqueKey<FlywaySchemaHistoryRecord> FLYWAY_SCHEMA_HISTORY_PK = Internal.createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, "flyway_schema_history_pk", FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK);
        public static final UniqueKey<UsersRecord> USERS_PKEY = Internal.createUniqueKey(Users.USERS, "users_pkey", Users.USERS.ID);
    }
}
