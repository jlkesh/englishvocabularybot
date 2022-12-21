package dev.jlkesh.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<E> {
    E mapTo(ResultSet rs) throws SQLException;
}
