package dev.jlkesh.dao;

import dev.jlkesh.configs.DBUtils;
import dev.jlkesh.dao.mappers.DBUserMapper;
import dev.jlkesh.domains.DBUser;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.User;

import javax.swing.text.html.Option;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDAO implements DAO<DBUser, String> {

    @Getter
    private final static UserDAO instance = new UserDAO();

    public static final String INSERT_USER_QUERY = "insert into users(chatid, firstname, status, language) values(?, ?, ?, ?);";

    @Override
    public DBUser save(DBUser dbUser) throws SQLException {
        var connection = DBUtils.getConnection();
        var ps = connection.prepareStatement(INSERT_USER_QUERY);
        ps.setString(1, dbUser.getChatId());
        ps.setString(2, dbUser.getFirstName());
        ps.setString(3, dbUser.getStatus().name());
        ps.setString(4, dbUser.getLanguage());
        ps.execute();
        return dbUser;
    }


    public Optional<DBUser> findById(String userID) throws SQLException {
        var con = DBUtils.getConnection();
        var ps = con.prepareStatement("select * from users where chatid = ?");
        ps.setString(1, userID);
        var rs = ps.executeQuery();
        return rs.next()
                ? Optional.of(new DBUserMapper().mapTo(rs))
                : Optional.empty();
    }
}