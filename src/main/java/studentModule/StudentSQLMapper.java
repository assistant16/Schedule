package studentModule;

import Repository.SQLHelper;
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
        List<String> params = new ArrayList<>();

        String sql = "SELECT ST.ID, " +
                "ST.firstName, " +
                "ST.lastName, " +
                "ST.AVG_MARK AS 'STUDENT_AVG', " +
                "ST.GROUP_groupNumber, " +
                "GR.AVG_MARK  AS 'GROUP_AVG' " +
                "FROM schedule.student AS ST JOIN schedule.group AS GR ON ST.GROUP_groupNumber = GR.groupNumber " +
                "WHERE " +
                SQLHelper.addLike(params, "ST.firstName", studentFilter.getName(), " AND ") +
                SQLHelper.addLike(params, "ST.lastName", studentFilter.getSurname(), " AND ") +
                SQLHelper.addLike(params, "GR.groupNumber", studentFilter.getGroupNumber(), " AND ") +
                "1=1";
//
        PreparedStatement statement = connection.prepareStatement(sql);

        SQLHelper.setParams(statement,params);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Student student = fillStudent(resultSet);
            students.add(student);
        }

        return students;
    }




    @Override
    public void createData(Connection connection, Student item) throws SQLException {
        String sql = "INSERT INTO schedule.student(firstName, lastName, AVG_MARK, GROUP_groupNumber) VALUES (?,?,?,?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, item.getFirstName());
        statement.setString(2, item.getLastName());
        statement.setString(3, item.getAvgMark());
        statement.setString(4, String.valueOf(item.getGroup()));
        statement.executeUpdate();
        sql = "SELECT MAX(schedule.student.ID) AS 'STUDENT_ID' FROM schedule.student;";
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
            item.setId(rs.getInt("STUDENT_ID"));
        }
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
