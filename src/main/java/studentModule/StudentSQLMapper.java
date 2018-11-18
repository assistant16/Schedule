package studentModule;

import Repository.SQLMapper;
import filter.StudentFilter;
import groupModule.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentSQLMapper implements SQLMapper<Integer,Student, StudentFilter> {

    @Override
    public Integer getKey(Student item) {
        return item.getId();
    }

    @Override
    public Integer setKey(Student item, int size) {
        int id = size + 1;
        item.setId(id);
        return id;
    }

    @Override
    public List<Student> getData(Connection connection, StudentFilter studentFilter) throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT schedule.student.ID, " +
                "schedule.student.firstName, " +
                "schedule.student.lastName, " +
                "schedule.student.AVG_MARK AS 'STUDENT_AVG', " +
                "schedule.student.GROUP_groupNumber, " +
                "schedule.group.AVG_MARK  AS 'GROUP_AVG' " +
                "FROM BEGANSS.STUDENT AS ST JOIN BEGANSS.GROUP AS GR ON ST.GROUP_NUMBER = GR.GROUP_NUMBER " +
                "WHERE ST.FIRST_NAME LIKE ? " +
                "AND ST.SECOND_NAME LIKE  ? AND GR.GROUP_NUMBER LIKE ?;";
        PreparedStatement statement =connection.prepareStatement(sql);
        statement.setString(1,studentFilter.getName() + "%" );
        statement.setString(2,studentFilter.getSurname() + "%");
        statement.setString(3, studentFilter.getGroupNumber() + "%");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Student student = fillStudent(resultSet);
            students.add(student);
        }

        return students;
    }




    @Override
    public void createData(Connection connection, Student item) throws SQLException {

    }

    @Override
    public Student findOne(Connection connection, Integer id) throws Exception {
        return null;
    }

    private Student fillStudent(ResultSet resultSet) throws SQLException{
        Student student = null;
        Group group = new Group(resultSet.getString("groupNumber"));
        group.setAvgMark(resultSet.getString("GROUP_AVG"));
        student = new Student(resultSet.getString("firstName"),resultSet.getString("lastName"),group);
        student.setAvgMark(resultSet.getString("STUDENT_AVG"));
        student.setId(resultSet.getInt("ID"));
        return student;
    }
}
