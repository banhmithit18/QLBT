package utils;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection<T> {
    private String urlConnection = "jdbc:sqlserver://localhost:1433;databaseName=QLBT;user=sa;password=123456";
    /// lay so dong
    public int getRowCount(String table) {
        try (Connection con = DriverManager.getConnection(urlConnection)) {
            int row = 0;
            String query = "select count(*) from " + table;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                row = rs.getInt(1);
            }
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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
