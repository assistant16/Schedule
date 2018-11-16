package web.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import groupModule.GroupRepository;
import studentModule.Student;
import studentModule.StudentReader;
import studentModule.StudentRepositoryMemory;
import studentModule.StudentWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class ApplicationListener implements ServletContextListener {

    private StudentRepositoryMemory studentRepositoryMemory;
    private GroupRepository groupRepository;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Properties properties = getCurrProperties();
        try{
            ServletContext servletContext = servletContextEvent.getServletContext();
            String studentPath = properties.getProperty("student.file.path");
            List<Student> students = new StudentReader(studentPath).read();
            this.studentRepositoryMemory = new StudentRepositoryMemory(students);
            servletContext.setAttribute("studentRepositoryMemory",studentRepositoryMemory);
            servletContext.setAttribute("objectMapper",new ObjectMapper());

        } catch (IOException e) {
            throw new RuntimeException("Can't read students",e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Properties properties = getCurrProperties();
        try{
            ServletContext servletContext = servletContextEvent.getServletContext();
            String studentPath = properties.getProperty("student.file.path");
            List<Student> students = studentRepositoryMemory.findAll();
            new StudentWriter(studentPath).write(students);
        } catch (IOException e) {
            System.out.println("can't save file");
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
