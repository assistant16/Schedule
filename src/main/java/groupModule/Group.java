package groupModule;

public class Group {

    private String groupNumber;
    private double avgMark;

    public Group(String groupNumber) {
        super();
        this.groupNumber = groupNumber;
        this.avgMark = 0 ;
    }

    public String getGroupNumber() {
        return groupNumber;
    }
    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }
    public String getAvgMark() {
        return avgMark+"";
    }
    public void setAvgMark(String avgMark) {
        this.avgMark = Double.parseDouble(avgMark);
    }
}
