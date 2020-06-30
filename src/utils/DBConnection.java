package utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

public class DBConnection<T> {
    private String urlConnection = "jdbc:sqlserver://localhost:1433;databaseName=QLBT;user=sa;password=123456";
    /// lay so dong
    public int getRowCount(String query) {
        try (Connection con = DriverManager.getConnection(urlConnection)) {
            int row = 0;
            String queryIn = query.substring(8);
            String queryOut = "Select count(*) "+queryIn;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(queryOut);
            while (rs.next()) {
                row = rs.getInt(1);
            }
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public boolean check(String query){
        try (Connection con = DriverManager.getConnection(urlConnection)){ ;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next())
            {
                return true;
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updatePassword(String username, String password)
    { try(Connection con = DriverManager.getConnection(urlConnection))
    {
        String query = "update employee set password = '"+password+"' where username ='"+username+"'";
        Statement stmt = con.createStatement();
        int check = stmt.executeUpdate(query);
        if ( check!= 0)
        {
            return  true;
        }
        else
        {
            return false;
        }
    }catch (SQLException e )
    {
        e.printStackTrace();
    }return false;
    }
    public boolean Create(T item) {
        try (Connection con = DriverManager.getConnection(urlConnection)) {
            Class<?> classInfo = item.getClass();
            String className = classInfo.getName();
            int lastIndxDot = className.lastIndexOf(".");
            String tableName = className.substring(lastIndxDot + 1);
            String query = "INSERT INTO " + tableName + "(";
            Field[] fields = classInfo.getFields();
            for (Field columnName : fields
            ) {
                query += columnName.getName() + ",";
            }
            query = query.substring(0, query.length() - 1);
            query += ") VALUES(";
            for (Field fieldItem : fields
            ) {
                if (fieldItem.getType().equals(String.class)) {
                    query += "N'" + fieldItem.get(item) + "',";

                } else if (fieldItem.getType().equals(Timestamp.class)) {
                    query += "'" + fieldItem.get(item) + "',";
                } else if (fieldItem.getType().equals(int.class)) {
                    query += fieldItem.get(item) + ",";
                }

            }
            query = query.substring(0, query.length() - 1);
            query += ")";

            PreparedStatement pstmt = con.prepareStatement(query);
            int check = pstmt.executeUpdate();
            if (check == 1) {
                return true;
            }


        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }
    // lay du lieu
    public ArrayList<Object> getAllData(String query) {
        try (Connection con = DriverManager.getConnection(urlConnection)) {
            ArrayList<Object> arr = new ArrayList<>();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    arr.add(rs.getObject(i));
                }
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
