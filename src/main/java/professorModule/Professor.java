package professorModule;

public class Professor {

    private String firstName;
    private String secondName;
    private String fatherName;
    private double birthDate;
    private int avgMark;

    public Professor(String firstName, String secondName, String fatherName, double birthDate, int avgMark) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.fatherName = fatherName;
        this.birthDate = birthDate;
        this.avgMark = avgMark;
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

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public double getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(double birthDate) {
        this.birthDate = birthDate;
    }

    public int getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(int avgMark) {
        this.avgMark = avgMark;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", birthDate=" + birthDate +
                ", avgMark=" + avgMark +
                '}';
    }
}
