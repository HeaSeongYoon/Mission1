

package org.example;

import javax.servlet.http.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


@WebServlet(name = "helloServlet", value = "/")
public class MainServlet extends HttpServlet {

    public void init() {
        System.out.println("init!");
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("doGet!");
        response.setContentType("type!");
    }


    public void destroy() {
        System.out.println("destroy!!");
    }

}


