package sahan.abr.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CRUDRepository<T> {

    T getById(Integer id) throws SQLException;

    List<T> getAll() throws SQLException;

    int save(T data) throws SQLException;

    boolean update(T data) throws SQLException;

    boolean deleteById(Integer id) throws SQLException;

    default int executeId(PreparedStatement statement) throws SQLException {
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating failed, no rows affected.");
        }

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Creating failed, no ID obtained.");
            }
        }
    }

    default boolean executeDML(PreparedStatement statement) throws SQLException {
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating failed, no rows affected.");
        } else {
            return true;
        }
    }
}
