package studentModule;

import groupModule.Group;

public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private double avgMark;
    private Group group;

    public Student(){}

    public Student(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student(String firstName, String secondName, Group group) {
        super();
        this.firstName = firstName;
        this.lastName = secondName;
        this.avgMark=0;
        this.group = group;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }
    public String  getAvgMark() {
        return avgMark+"";
    }
    public void setAvgMark(String avgMark) {
        this.avgMark = Double.parseDouble(avgMark);
    }
}
