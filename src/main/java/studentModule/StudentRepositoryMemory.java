package studentModule;

import groupModule.GroupRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class StudentRepositoryMemory implements StudentRepository{

    private final Map<String,Student> STUDENTS = new LinkedHashMap<>();
    private GroupRepository groupRepository;


    public StudentRepositoryMemory(List<Student> students){
        if(students != null){
            for (Student st : students){
                this.STUDENTS.put(st.getId(),st);
            }
        }
    }

    public List<Student> findAll(){
        return new ArrayList<>(STUDENTS.values());
    }

//    public List<Student> findAllFilter(){
//
//        return new ArrayList<>(STUDENTS.values());
//    }

    @Override
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
