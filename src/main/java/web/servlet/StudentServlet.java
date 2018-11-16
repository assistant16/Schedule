package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import groupModule.GroupRepository;
import studentModule.Student;
import studentModule.StudentRepositoryMemory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class StudentServlet extends HttpServlet{

    private static final long serialVersionUID = 6345194112526801506L;
    private StudentRepositoryMemory studentRepositoryMemory;
    private GroupRepository groupRepository;
    private final static List<Student> STUDENTS = new ArrayList<Student>();
    private ObjectMapper mapper;

    @Override
    public void init(){
        ServletContext servletContext = getServletContext();
        studentRepositoryMemory = (StudentRepositoryMemory) servletContext.getAttribute("studentRepositoryMemory");
        this.mapper = (ObjectMapper) servletContext.getAttribute("objectMapper");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        List<Student> students = studentRepositoryMemory.findAll();

        PrintWriter pw = resp.getWriter();
        pw.println(mapper.writeValueAsString(students));
        pw.close();


       // req.setAttribute("students",this.studentRepositoryMemory.findAll());

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/student.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String groupNumber = req.getParameter("groupNumber")
        Student student = new Student(firstName, secondName,groupRepository.findGroupById(groupNumber));
        studentRepositoryMemory.create(student);
        doGet(req,resp);
    }

    //    private static String toJson(Student student){
//        String json = "{" +
//                "\"id\": \"" + student.getId() + "\", " +
//                "\"firstName\": \"" + student.getFirstName()+ "\", " +
//                "\"secondName\": \"" + student.getLastName()+ "\" " +
//       "}";
//        return json;
//    }
//
//    private static String toJson(List<Student> students){
//        String json = "[" ;
//            if(students != null) {
//                boolean firstItem = true;
//                for (Student student: students){
//                    if (firstItem){
//                        firstItem = false;
//                    } else{
//                        json += ",";
//                    }
//                    json += toJson(student);
//                }
//            }
//          json += "]";
//        return json;
//    }

    //
    //    }
    //    @Override
    //    public void destroy() {
}
