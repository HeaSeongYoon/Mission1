package org.example;

import org.example.service.HistService;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/deletehist")
public class DeleteHistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("Deleting record with ID: " + id); // 콘솔에 출력할 내용 추가
        HistService histService = new HistService();
        histService.delete(id);
        response.sendRedirect("history.jsp");
    }

}
