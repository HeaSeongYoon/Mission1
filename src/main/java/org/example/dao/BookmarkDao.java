package org.example.dao;

import org.example.domain.Bookmark;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BookmarkDao {

    private final String dbUrl = "jdbc:sqlite:C:/Users/STEALTH/DataGripProjects/M1/identifier.sqlite";
    private final String insertSql = "INSERT INTO bookmark (HIST_NO, MAIN_NM, name, create_date) VALUES (?, ?, ?, datetime('now'))";

    public void add(Bookmark bookmark) {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement pstmt = connection.prepareStatement(insertSql)) {

            pstmt.setInt(1, bookmark.getHistNo());
            pstmt.setString(2, bookmark.getMainNm());
            pstmt.setString(3, bookmark.getName());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
