package web.listener;


import Repository.RepositoryMain;
import com.fasterxml.jackson.databind.ObjectMapper;
import filter.GroupFilter;
import filter.StudentFilter;
import groupModule.Group;
import groupModule.GroupRepository;
import studentModule.Student;

import studentModule.StudentRepositoryMemory;
import studentModule.StudentSQLMapper;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationListener implements ServletContextListener {

    private RepositoryMain<Integer,Student, StudentFilter> studentRepository;
    private RepositoryMain<String, Group, GroupFilter> groupRepository;

    private StudentRepositoryMemory studentRepositoryMemory;
    private GroupRepository groupRepositoryMemory;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DataSource dataSource = dataSource();
        ServletContext servletContext = servletContextEvent.getServletContext();
        this.studentRepository = new RepositoryMain<Integer, Student, StudentFilter>(new StudentSQLMapper(),dataSource);
        servletContext.setAttribute("studentRepository",studentRepository);
//            ServletContext servletContext = servletContextEvent.getServletContext();
//            String studentPath = properties.getProperty("student.file.path");
//            List<Student> students = new StudentReader(studentPath).read();
//            this.studentRepositoryMemory = new StudentRepositoryMemory(students);
//            servletContext.setAttribute("studentRepositoryMemory",studentRepositoryMemory);

        servletContext.setAttribute("objectMapper",new ObjectMapper());


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) { }

    private DataSource dataSource(){
        try {
            Context initialContext = new InitialContext();
            Context eContext = (Context) initialContext.lookup("java:/comp/env");
            DataSource dataSource = (DataSource) eContext.lookup("jdbc/Schedule");
            return dataSource;
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public Properties getCurrProperties() {
        Properties properties = new Properties();
        try{
            try (FileInputStream fileInputStream = new FileInputStream("C:/Users/blekb/Schedule/src/main/resources/config.properties")) {
                properties.load(fileInputStream);
            }
        } catch (IOException e) {
            System.err.println("Error : file not found ");
        }
        return properties;
    }
}
