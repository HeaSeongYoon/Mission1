package org.example.service;

import org.example.dao.BookmarkGroupDao;
import org.example.domain.BookmarkGroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class BookmarkGroupService {

    public boolean createGroup(BookmarkGroup group) {
        try {
            Connection conn = getConnection();

            Date now = new Date();
            group.setCreateDate(now);
            group.setModifiedDate(now);

            BookmarkGroupDao dao = new BookmarkGroupDao(conn);
            return dao.insert(group);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateGroup(BookmarkGroup group) {
        try {
            Connection conn = getConnection();

            Date now = new Date();
            group.setModifiedDate(now);

            BookmarkGroupDao dao = new BookmarkGroupDao(conn);
            return dao.update(group);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteGroup(BookmarkGroup group) {
        try {
            Connection conn = getConnection();

            BookmarkGroupDao dao = new BookmarkGroupDao(conn);
            return dao.delete(group);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<BookmarkGroup> listAllGroups() {
        try {
            Connection conn = getConnection();

            BookmarkGroupDao dao = new BookmarkGroupDao(conn);
            return dao.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection("jdbc:sqlite:C:/Users/STEALTH/DataGripProjects/M1/identifier.sqlite");
    }

    public BookmarkGroup getGroupById(int id) {
        try {
            Connection conn = getConnection();
            BookmarkGroupDao dao = new BookmarkGroupDao(conn);
            return dao.findById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
