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
            conn = Db.getConnection();

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

}
