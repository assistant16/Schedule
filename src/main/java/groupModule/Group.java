package groupModule;

public class Group {

    private String groupNumber;
    private double avgMark;

    public Group(String groupNumber, double avgMark) {
        this.groupNumber = groupNumber;
        this.avgMark = avgMark;
    }

    public String getGroupNumber() {
        return groupNumber;
    }
    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }
    public double getAvgMark() {
        return avgMark;
    }
    public void setAvgMark(double avgMark) {
        this.avgMark = avgMark;
    }
}
