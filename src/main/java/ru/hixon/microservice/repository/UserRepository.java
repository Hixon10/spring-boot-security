package ru.hixon.microservice.repository;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hixon.microservice.Tables;
import ru.hixon.microservice.entity.Role;
import ru.hixon.microservice.entity.User;
import ru.hixon.microservice.tables.records.UsersRecord;

import java.util.List;

@Service
public class UserRepository {

    private DSLContext dsl;

    @Autowired
    public void setDsl(DSLContext dsl) {
        this.dsl = dsl;
    }

    public boolean existsByUsername(String username) {
        return findByUsername(username) != null;
    }

    public User findByUsername(String username) {
        UsersRecord usersRecord = dsl.selectFrom(Tables.USERS)
                .where(Tables.USERS.USERNAME.eq(username))
                .fetchAny();

        if (usersRecord == null) {
            return null;
        }

        User user = new User();
        user.setId(usersRecord.getId());
        user.setPassword(usersRecord.getPassword());
        user.setUsername(usersRecord.getUsername());
        user.setRoles(List.of(Enum.valueOf(Role.class, usersRecord.getRole())));

        return user;
    }

    public void deleteByUsername(String username) {
        dsl.deleteFrom(Tables.USERS)
                .where(Tables.USERS.USERNAME.eq(username))
                .execute();
    }

    public void save(User user) {
        dsl.insertInto(Tables.USERS)
                .set(Tables.USERS.ID, user.getId())
                .set(Tables.USERS.PASSWORD, user.getPassword())
                .set(Tables.USERS.ROLE, user.getRoles().get(0).getAuthority())
                .set(Tables.USERS.USERNAME, user.getUsername())
                .execute();
    }
}
