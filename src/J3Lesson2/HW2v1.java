package J3Lesson2;

/**
 * Java. Level 3. Lesson 2
 *
 * @author Dmitriy Semenov
 * @version 0.2 dated Feb 08, 2018
 * @link https://github.com/sdimka/Java3
 *
 */

import java.util.*;
import java.sql.*;

class HW2v1 implements IConstants {

    final String NAME_TABLE = "products";
    final String SQL_CREATE_TABLE =
            "DROP TABLE IF EXISTS " + NAME_TABLE + ";" +
                    "CREATE TABLE " + NAME_TABLE +
                    "(id  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " title CHAR(25) NOT NULL," +
                    " cost INT);";
    final String SQL_SELECT = "SELECT * FROM " + NAME_TABLE + ";";
    final String DB_CREATED = "Darabase created.";
    final String RECORD_ADDED = "Table Initialized";
    final String RECORD_DELETED = "Record deleted.";
    final String RECORD_UPDATED = "Record updated.";
    final String UNKNOWN_COMMAND = "use [-create,-init,-r,-d,-getprice,-setprice, -list] only.";
    final String LOGIN_COL = "login";

    Connection connect;
    Statement stmt;
    ResultSet rs;
    String sql;

    public static void main(String[] args) {
        new HW2v1(args);
    }

    HW2v1(String[] args) {
        if (args.length == 0) {
            System.out.println("Please " + UNKNOWN_COMMAND);
            Scanner sc = new Scanner(System.in);
            System.out.print("> ");
            String line = sc.nextLine();
            args = line.split(" ");
        }

        switch (args[0]) {
            case "-create":
                openDBFile(SQLITE_DB)
                        .createTable(SQL_CREATE_TABLE);
                System.out.println(DB_CREATED);
                break;
            case "-init":
                openDBFile(SQLITE_DB)
                        .init();
                System.out.println(RECORD_ADDED);
                break;
            case "-r":
                openDBFile(SQLITE_DB)
                        .list();
                break;
            case "-d":
                openDBFile(SQLITE_DB)
                        .delete(args[1]);
                System.out.println(RECORD_DELETED);
                break;
            case "-getprice":
                if (args.length > 1) {
                    openDBFile(SQLITE_DB)
                            .getPrice(args[1]);
                }else System.out.println("Please, enter product name");
                break;
            case "-setprice":
                if (args.length > 2) {
                    openDBFile(SQLITE_DB)
                            .setPrice(args[1], Integer.parseInt(args[2]));
                }else System.out.println("Please, enter product name and new price");
                break;
            case "-list":
                if (args.length > 2) {
                    openDBFile(SQLITE_DB)
                            .listRange(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                }else System.out.println("Please, enter price range");
                break;
            default:
                System.out.println("Unknown command," + UNKNOWN_COMMAND);
        }
    }

    private HW2v1 openDBFile(String dbName) { // open/create database
        try {
            Class.forName(DRIVER_NAME);
            connect = DriverManager.getConnection(dbName);
            stmt = connect.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    private void createTable(String sqlCreateTable) { // create table
        try {
            stmt.executeUpdate(sqlCreateTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() { // add record
        try {
            stmt.executeUpdate ("DELETE FROM " + NAME_TABLE);

            PreparedStatement ps = connect.prepareStatement("INSERT INTO " +
                    NAME_TABLE +"(title, cost) VALUES(?, ?);");
            for (int i = 1; i < 15; i++){
                ps.setString(1,"Товар" + i);
                ps.setInt(2, i*10);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void delete(String title) { // delete record by title
        try {
            stmt.executeUpdate("DELETE from " + NAME_TABLE +
                    " where title='" + title + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void list() { // show all records
        try {
            System.out.println("ID\t\tPRD NAME\t\tPRICE");
            rs = stmt.executeQuery(SQL_SELECT);
            displayRow(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getPrice(String prd){
        try {
            String sql = "SELECT cost FROM " + NAME_TABLE + " WHERE title = '" + prd +"';" ;
            rs = stmt.executeQuery(sql);
            if (rs != null) {
                displayRow(rs);
            } else System.out.println(prd + " not found");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setPrice(String prd, int price){
        try {
            String sql = "UPDATE " + NAME_TABLE + " SET cost=" + price +
                    " WHERE title = '"  + prd + "';";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void listRange(int lowPrice, int hiPrice){
        try {
            String sql = "SELECT * FROM " + NAME_TABLE + " WHERE cost >=" + lowPrice +
                    " AND cost <="  + hiPrice + ";";
            rs = stmt.executeQuery(sql);
            displayRow(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void displayRow(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                for (int i = 1; i < rsmd.getColumnCount() + 1; i++){
                System.out.print(rs.getString(i) + "\t\t");
                }
                System.out.println(" ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
