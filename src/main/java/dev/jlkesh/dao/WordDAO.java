package dev.jlkesh.dao;

import dev.jlkesh.configs.DBUtils;
import dev.jlkesh.domains.Word;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WordDAO implements DAO<Word, Long> {

    public static final String INSERT_WORD_QUERY = """
            insert into word(word, translations, definitions,examples, chat_id) values(?,?,?,?,?) returning id, created_at ;""";

    public Word save(Word word) throws SQLException {
        var connection = DBUtils.getConnection();
        var ps = connection.prepareStatement(INSERT_WORD_QUERY);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            word.setId(rs.getLong("id"));
            word.setCreatedAt(rs.getDate("created_at"));
        } else throw new SQLException("Could not extract ResultSet");
        return word;
    }
}
