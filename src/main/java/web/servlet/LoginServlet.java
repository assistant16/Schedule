package web.servlet;


import userModeule.User;
import userModeule.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        ServletContext sc = getServletContext();
        this.userRepository = (UserRepository) sc.getAttribute("userRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {
        String name = req.getParameter("name");
        String pass = req.getParameter("pass");
        UserRepository userRepository = new UserRepository();
        User user = this.userRepository.LogIn(name,pass);
        if(user!=null){
            HttpSession session = req.getSession();
            session.setAttribute("user",user);
            resp.sendRedirect("/student");
        } else {
            doGet(req,resp);
        }
    }

}
