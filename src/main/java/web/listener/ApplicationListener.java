package web.listener;

import groupModule.GroupRepository;
import studentModule.Student;
import studentModule.StudentReader;
import studentModule.StudentRepository;
import studentModule.StudentWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class ApplicationListener implements ServletContextListener {

    private StudentRepository studentRepository;
    private GroupRepository groupRepository;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Properties properties = getCurrProperties();
        try{
            ServletContext servletContext = servletContextEvent.getServletContext();
            String studentPath = properties.getProperty("student.file.path");
            List<Student> students = new StudentReader(studentPath).read();
            this.studentRepository = new StudentRepository(students);
           servletContext.setAttribute("studentRepository",studentRepository);

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
            List<Student> students = studentRepository.findAll();
            new StudentWriter(studentPath).write(students);
        } catch (IOException e) {
            System.out.println("can't save file");
        }

    }

    public Properties getCurrProperties() {
        Properties properties = new Properties();
        try{
            try (FileInputStream fileInputStream = new FileInputStream("C://blekb//Schedule//src//main//resources//config.properties")) {
                properties.load(fileInputStream);
            }
        } catch (IOException e) {
            System.err.println("Error : file not found ");
        }
        return properties;
    }
}
