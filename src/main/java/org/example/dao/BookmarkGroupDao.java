package org.example.dao;

import org.example.domain.BookmarkGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookmarkGroupDao {
    private Connection conn;

    public BookmarkGroupDao(Connection conn) {
        this.conn = conn;
    }

    public boolean insert(BookmarkGroup group) throws SQLException {
        String sql = "INSERT INTO bookmark_groups (name, sort_order, create_date, modified_date) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);

        try {
            stmt.setString(1, group.getName());
            stmt.setInt(2, group.getOrder());
            stmt.setTimestamp(3, new Timestamp(group.getCreateDate().getTime()));
            stmt.setTimestamp(4, new Timestamp(group.getModifiedDate().getTime()));

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public boolean update(BookmarkGroup group) throws SQLException {
        String sql = "UPDATE bookmark_groups SET name = ?, sort_order = ?, modified_date = ? WHERE id = ?";


        PreparedStatement stmt = conn.prepareStatement(sql);

        try {
            stmt.setString(1, group.getName());
            stmt.setInt(2, group.getOrder());
            stmt.setTimestamp(3, new Timestamp(group.getModifiedDate().getTime()));
            stmt.setInt(4, group.getId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public boolean delete(BookmarkGroup group) throws SQLException {
        String sql = "DELETE FROM bookmark_groups WHERE id = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);

        try {
            stmt.setInt(1, group.getId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public List<BookmarkGroup> findAll() throws SQLException {
        String sql = "SELECT * FROM bookmark_groups";

        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet resultSet = stmt.executeQuery();

        List<BookmarkGroup> result = new ArrayList<>();
        while (resultSet.next()) {
            BookmarkGroup group = new BookmarkGroup();
            group.setId(resultSet.getInt("id"));
            group.setName(resultSet.getString("name"));
            group.setOrder(resultSet.getInt("sort_order"));
            group.setCreateDate(resultSet.getTimestamp("create_date"));
            group.setModifiedDate(resultSet.getTimestamp("modified_date"));
            result.add(group);
        }

        resultSet.close();
        stmt.close();

        return result;
    }


    public BookmarkGroup findById(int id) throws SQLException {
        String sql = "SELECT * FROM bookmark_groups WHERE id = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            BookmarkGroup group = new BookmarkGroup();
            group.setId(resultSet.getInt("id"));
            group.setName(resultSet.getString("name"));
            group.setOrder(resultSet.getInt("sort_order"));
            group.setCreateDate(resultSet.getTimestamp("create_date"));
            group.setModifiedDate(resultSet.getTimestamp("modified_date"));
            return group;
        }

        resultSet.close();
        stmt.close();

        return null;
    }

}
