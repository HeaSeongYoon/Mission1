package org.example;

import org.example.common.Db;
import org.example.service.HistService;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.example.common.Db.getConnection;

@WebServlet("/deletehist")
public class DeleteHistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("Deleting record with ID: " + id); // 콘솔에 출력할 내용 추가
        HistService histService = new HistService();
        histService.delete(id);
        response.sendRedirect("history.jsp");
    }

    public void delete(int histNo) {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            conn = getConnection();

            String sql = "DELETE FROM HIST WHERE HIST_NO= ?";
            stat = conn.prepareStatement(sql);
            stat.setInt(1, histNo);
            int affected = stat.executeUpdate();

            if (affected < 0) {
                System.out.println("삭제 실패");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null && !stat.isClosed()) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean addBookmark(String groupId, String wifiId) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        String sql = "INSERT INTO bookmark (HIST_NO, MAIN_NM, name, create_date) (SELECT ROWID, MAIN_NM, MGR_NO, strftime('%Y-%m-%d %H:%M:%f', 'now') FROM PUB_WIFI WHERE MGR_NO = ?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, wifiId);

        int rowsAffected = pstmt.executeUpdate();
        pstmt.close();
        conn.close();

        return rowsAffected > 0;
    }
}
