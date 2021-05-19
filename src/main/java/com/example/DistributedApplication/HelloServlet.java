package com.example.DistributedApplication;

import com.a20da10.service.ejb.MyfirstBean;

import java.io.*;
import javax.ejb.EJB;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    @EJB
    MyfirstBean bean;
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        //out.println("<ul>");
        //for(StudentEntity s: bean.findStudentName("James")){
            //out.println("<li>" + s.getFirstName() + "</li>");
        //}

        //out.println("</ul>");
        out.println("<p>The result of 15 + 32 = " + bean.doOperation(15,32) + ".</p>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}