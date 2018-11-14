package studentModule;

import groupModule.Group;

public class Student {

    private String id;
    private String firstName;
    private String secondName;
    private Group group;

    public Student(){
        super();
    }

    public Student(String firstName, String secondName) {
        super();
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getSecondName() {
        return secondName;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
