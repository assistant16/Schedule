package groupModule;

import repository.SQLMapper;
import filter.GroupFilter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupSQLMapper implements SQLMapper<String,Group, GroupFilter>{

    @Override
    public String getKey(Group item) {
        return item.getGroupNumber();
    }

    @Override
    public String setKey(Group item, int size) {
        return item.getGroupNumber();
    }

    @Override
    public List<Group> getData(Connection connection, GroupFilter groupFilter) throws SQLException {
        List<Group> groups = new ArrayList<>();
        String sql = "SELECT " +
                "groupNumber, " +
                "AVG_MARK " +
                "FROM schedule.group " +
                "WHERE groupNumber LIKE ? AND AVG_MARK LIKE ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,groupFilter.getGroupNumber() + "%");
        statement.setString(2,groupFilter.getAvg_mark() + "%");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Group group = new Group(resultSet.getString("groupNumber"));
            group.setAvgMark(resultSet.getString("AVG_MARK"));
            groups.add(group);
        }
        return groups;
    }

    @Override
    public void createData(Connection connection, Group group) throws SQLException {
        String sql = "INSERT " +
                "INTO schedule.group(groupNumber, AVG_MARK)" +
                " VALUES(?,?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,group.getGroupNumber());
        statement.setString(2,group.getAvgMark());
        statement.executeQuery();
    }

    @Override
    public Group findOne(Connection connection, String id) throws Exception {
        Group group = null;

        String sql = "SELECT " +
                "groupNumber, " +
                "AVG_MARK " +
                "FROM schedule.group " +
                "WHERE groupNumber =?;";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            group = new Group(resultSet.getString("groupNumber"));
            group.setAvgMark(resultSet.getString("AVG_MARK"));
        }
        return group;
    }
}
