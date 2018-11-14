package web.servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentModule.Student;

public class TestServlet extends HttpServlet {

    private static final long serialVersionUID = 6345194112526801506L;


    private final static List<Student> STUDENTS = new ArrayList<Student>();

    public static void main(String[] args) {
        //Student students = new Student[0];
        //for(Student students : )
    }




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("studentCount", STUDENTS.size());
        req.setAttribute("students", STUDENTS);

        //req.setAttribute("firstName", STUDENTS.get(STUDENTS.size()-1));

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/test.jsp");
        dispatcher.forward(req, resp);




        //=--=-=-=-=-=- 1-s way  =--=-=-=-=-=-
		/*

	 	PrintWriter pW = resp.getWriter();
		resp.setContentType("text/html");

		pW.write("<form>");
		pW.print("<input type=\"text\" name=\"name\">");
		pW.print("<input type=\"submit\">");
		pW.print("</form>");
		pW.close();
		*/

    }


//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {
//        String id = req.getParameter("id");
//        String firstName = req.getParameter("firstName");
//
//        String secondName = req.getParameter("secondName");
//        //System.out.print(String.format("First name is : %s , Second name is : %s ",firstName,secondName));
//        STUDENTS.add(new Student(firstName,secondName,group));
//        doGet(req,resp);
//    }

}
