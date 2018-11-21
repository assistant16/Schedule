package repository;

import util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SQLHelper {

    public static String addLike(List<String> params,String field,String value,String operator){
        String sql = "";
        if(!StringUtil.isEmpty(value)){
            sql = field + "LIKE ? " + operator;
            params.add(value + "%");
        }
        return sql;
    }

    public static void setParams(PreparedStatement statement,List<String> params) throws SQLException {
        for (int i=0;i<params.size();i++){
            statement.setString(i+1,params.get(i) + "%");
        }
    }

    public static String setId(String str) throws SQLException {
        if (str == null)
            return "0";
        return str;
    }
}
