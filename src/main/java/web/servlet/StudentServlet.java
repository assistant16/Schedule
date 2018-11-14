package web.servlet;

import studentModule.Student;
import studentModule.StudentRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentServlet extends HttpServlet{

    private static final long serialVersionUID = 6345194112526801506L;
    private StudentRepository studentRepository;
    private final static List<Student> STUDENTS = new ArrayList<Student>();

//    @Override
//    public void init(){
//        ServletContext servletContext = getServletContext();
//        this.studentRepository = (StudentRepository) servletContext.getAttribute("studentRepository");
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students",STUDENTS);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/student.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        Student student = new Student(firstName, secondName);
        this.studentRepository.create(student);
        doGet(req,resp);
    }

//    @Override
//    public void destroy() {
//
//    }
}
