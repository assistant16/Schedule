package web.servlet;

import repository.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import filter.GroupFilter;
import filter.StudentFilter;
import groupModule.Group;
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
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class StudentServlet extends HttpServlet{

    private static final long serialVersionUID = 6345194112526801506L;

    private Repository<Integer,Student, StudentFilter> studentRepository;
    private Repository<String, Group, GroupFilter> groupGroupRepository;

    private StudentRepositoryMemory studentRepositoryMemory;
    private GroupRepository groupRepository;
    private final static List<Student> STUDENTS = new ArrayList<Student>();
    private ObjectMapper mapper;

    @Override
    public void init(){
        ServletContext servletContext = getServletContext();
        this.studentRepository = (Repository<Integer,Student, StudentFilter>) servletContext.getAttribute("studentRepository");
        this.groupGroupRepository = (Repository<String, Group, GroupFilter>) servletContext.getAttribute("groupGroupRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //  List<Student> students = studentRepositoryMemory.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String sortByName = req.getParameter("sortByName");
        String sortBySurname = req.getParameter("sortBySurname");
        String sortByGroup = req.getParameter("sortByGroup");
        StudentFilter studentFilter = new StudentFilter();
        if (sortByName != null)
            studentFilter.setName(sortByName);
        if (sortBySurname != null)
            studentFilter.setSurname(sortBySurname);
        if (sortByGroup != null)
            studentFilter.setGroupNumber(sortByGroup);
        req.setAttribute("students", this.studentRepository.findAll(studentFilter));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/student.jsp");
        dispatcher.forward(req, resp);
       // req.setAttribute("students", studentRepositoryMemory.findAll());

//        if("".equals(req.getParameter("json"))) {
//            resp.setContentType("application/json");
//
//            PrintWriter pw = resp.getWriter();
//            pw.print(toJson(students));
//            pw.close();
//        } else {
//            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/student.jsp");
//            dispatcher.forward(req, resp);
//        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = (StringBuilder) req.getAttribute("strPost");
        JSONObject jsonObject = new JSONObject(sb.toString());
        Student st = new Student(jsonObject.getString("firstName"),
                jsonObject.getString("secondName"),
                groupRepository.findGroupById(jsonObject.getString("groupNumber")));
        this.studentRepository.create(st);

//        String firstName = req.getParameter("firstName");
////        String secondName = req.getParameter("lastName");
////        String groupNumber = req.getParameter("groupNumber");
////        Student student = new Student(firstName, secondName);
////        studentRepositoryMemory.create(student);
//        resp.sendRedirect(req.getContextPath() + "/student");
//
        resp.sendRedirect(req.getContextPath() + "/student");
        doGet(req,resp);
    }

    private static String toJson(Student student){
    String json = "{" +
            "\"id\": \"" + student.getId() + "\", " +
            "\"firstName\": \"" + student.getFirstName()+ "\", " +
            "\"lastName\": \"" + student.getLastName()+ "\" " +
   "}";
    return json;
    }

    private static String toJson(List<Student> students){
        String json = "[" ;
            if(students != null) {
                boolean firstItem = true;
                for (Student student: students){
                    if (firstItem){
                        firstItem = false;
                    } else{
                        json += ",";
                    }
                    json += toJson(student);
                }
            }
          json += "]";
        return json;
    }

    @Override
    public void destroy() {}
}
