package dev.jlkesh.dao;

import java.sql.SQLException;

public interface DAO<D,ID> {
    D save(D d) throws SQLException;
}
