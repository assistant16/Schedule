package userModeule;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserRepository {

    private DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public UserRepository() {
    }

    public User LogIn(String name,String pass){
        try(Connection connection = dataSource.getConnection()){
            String sql = "SELECT "
                    + "username, "
                    + "pass, "
                    + "role "
                    + "FROM schedule.user";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                if (name.equals(rs.getString("username")) && pass.equals(rs.getString("pass"))){
                    User user = new User(name, pass, rs.getString("role"));
                    return user;

                }
            }
        } catch (Throwable ex){
            throw new RuntimeException(ex);
        }
        return null;
    }

}
