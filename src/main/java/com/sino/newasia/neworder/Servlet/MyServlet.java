package com.sino.newasia.neworder.Servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MyServlet", urlPatterns = {"/myServlet"})
public class MyServlet extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        logger.info("username ="+username);
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();
        try {
            printWriter.write("<head>");
            printWriter.write("<title>MyFirstServlet</title>");
            printWriter.write("</head>");
            printWriter.write("<body>");
            printWriter.write("<h2>Servlet MyFirstServlet at </h2>");
            printWriter.write("<h3> Welcome " +username +" come in servlet <h3/>");
            printWriter.write("</body>");
            printWriter.write("</html>");
        }finally {
            printWriter.close();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }
    @Override
    public String getServletInfo() {
        return "MyServlet";
    }

}
