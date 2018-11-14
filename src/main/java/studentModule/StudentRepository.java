package studentModule;

import groupModule.GroupRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class StudentRepository {

    private final Map<String,Student> STUDENTS = new LinkedHashMap<>();
    private GroupRepository groupRepository;

    public StudentRepository(List<Student> students){
        if(students != null){
            for (Student st : students){
                this.STUDENTS.put(st.getId(),st);
            }
        }
    }

    public List<Student> findAll(){
        return new ArrayList<>(STUDENTS.values());
    }

    public Student create(Student student){
        String id = UUID.randomUUID().toString();
        student.setId(id);
        STUDENTS.put(id,student);
        return student;
    }

    public Student update(Student student){
        STUDENTS.put(student.getId(),student);
        return student;
    }

    public void delete(String id){
        STUDENTS.remove(id);
    }

}
