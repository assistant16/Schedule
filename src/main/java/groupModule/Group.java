package groupModule;

public class Group {

    private double groupNumber;
    private double avhMark;

    public Group(double groupNumber, double avhMark) {
        this.groupNumber = groupNumber;
        this.avhMark = avhMark;
    }

    public double getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(double groupNumber) {
        this.groupNumber = groupNumber;
    }

    public double getAvhMark() {
        return avhMark;
    }

    public void setAvhMark(double avhMark) {
        this.avhMark = avhMark;
    }
}
