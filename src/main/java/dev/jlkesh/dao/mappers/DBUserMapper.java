package dev.jlkesh.dao.mappers;

import dev.jlkesh.domains.DBUser;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUserMapper implements RowMapper<DBUser> {
    @Override
    public DBUser mapTo(ResultSet rs) throws SQLException {
        return DBUser
                .builder()
                .firstName(rs.getString("firstname"))
                .status(DBUser.Status.valueOf(rs.getString("status")))
                .language(rs.getString("language"))
                .build();
    }
}
