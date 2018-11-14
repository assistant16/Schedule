package groupModule;

public class Group {

    private String id;
    private int groupNumber;

    public Group(int groupNumber){
        this.groupNumber=groupNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }
}
