package com.attendance_manager.services;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;

public class DBHandler {

    String DB_URL = "jdbc:mysql://localhost/javaproject";
    String USER = "root";
    String PASS = "ahmed3633";
    Connection conn = null;
    Statement stmt = null;


    public boolean userExists() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to a selected database...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            PreparedStatement checkExisting = conn.prepareStatement("select * from user ", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = checkExisting.executeQuery();

            if (rs.absolute(1)) {
                System.out.println("Already Existing User !");
                conn.close();
                return false;
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    conn.close();
            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return true;
    }

    public boolean validateUser(String email, String password) {

        String hashedPwd = BCrypt.hashpw(password, BCrypt.gensalt(5));
        System.out.println(hashedPwd);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to a selected database...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            String query = "select password from user where email= ?";
            PreparedStatement checkExisting = conn.prepareStatement(query);
            checkExisting.setString(1, email);
            ResultSet rs = checkExisting.executeQuery();

            while (rs.next()) {
                if (BCrypt.checkpw(password, rs.getString("password"))) {

                    conn.close();
                    return true;
                }
            }
            return false;

        } catch (Exception se) {
            se.printStackTrace();
        }
        finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
        return false;
    }

    public void insertToDB(String email, String password) {

        String hashedPwd = BCrypt.hashpw(password, BCrypt.gensalt(5));


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            PreparedStatement checkExisting = conn.prepareStatement("select * from user where email= ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            checkExisting.setString(1, email);
            ResultSet rs = checkExisting.executeQuery();

            if (rs.absolute(1)) {
                System.out.println("Already existing user !");
                conn.close();
                return;
            }

            String query = " insert into user"
                    + " values (?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, email);
            preparedStmt.setString(2, hashedPwd);
            preparedStmt.execute();

        } catch (Exception se) {
            se.printStackTrace();
        }
        finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }

    public boolean addSubject(String inputCode, String inputSubName, String inputFaculty) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to a selected database...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            PreparedStatement checkExisting = conn.prepareStatement("select * from subject where subjectCode= ? and staff= ?", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            checkExisting.setString(1, inputCode);
            checkExisting.setString(2, inputFaculty);
            ResultSet rs = checkExisting.executeQuery();

            if (rs.absolute(1)) {
                System.out.println("Alreadt existing subject !");
                return false;

            } else {
                System.out.println("New subject detected !");
                String query = " insert into subject"
                        + " values (?,?,?)";

                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, inputCode);
                preparedStmt.setString(2, inputFaculty);
                preparedStmt.setString(3, inputSubName);
                preparedStmt.execute();

                String userQuery="select email from user";
                PreparedStatement prep=conn.prepareStatement(userQuery);
                ResultSet resultSet=prep.executeQuery();
                String userName=null;

                while (resultSet.next()){
                    userName=resultSet.getString("email");

                }
                System.out.println(userName);

                String query2 = " insert into registers"
                        + " values (?,?,?,?,?,?)";

                PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
                preparedStmt2.setString(1, userName);
                preparedStmt2.setInt(2, 0);
                preparedStmt2.setInt(3, 0);
                preparedStmt2.setString(4, inputSubName);
                preparedStmt2.setString(5, inputCode);
                preparedStmt2.setString(6, inputFaculty);

                preparedStmt2.execute();
                return true;

            }
        } catch (Exception se) {
            se.printStackTrace();
        }
        finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");


        return true;
    }


    public void deleteSubject(String inputSubName) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to a selected database...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            PreparedStatement checkExisting = conn.prepareStatement("select * from registers where subjectName= ? ", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            checkExisting.setString(1, inputSubName);

            ResultSet rs = checkExisting.executeQuery();

            if (rs.absolute(1)) {
                System.out.println("Removing Subject !");
                String query = " delete from registers where subjectName=?";

                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, inputSubName);
                preparedStmt.execute();

                String query2 = " delete from subject where subjectName=?";

                PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
                preparedStmt2.setString(1, inputSubName);
                preparedStmt2.execute();

            } else {
                System.out.println("Wrong subject name !");
            }


        } catch (Exception se) {
            se.printStackTrace();
        }
        finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }

    public ArrayList<String> fetchSubjects() {

        ArrayList<String> returnList = new ArrayList<String>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to a selected database...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            String query = "select * from registers";
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            ResultSet rs = preparedStmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("subjectName"));
                returnList.add(rs.getString("subjectName"));
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return returnList;

    }


    public void updateAttendance(String subjectName, int flag) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to a selected database...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            String query = "select * from registers where subjectName=?";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, subjectName);
            preparedStmt.execute();

            ResultSet rs = preparedStmt.executeQuery();
            int currentVal = 0;

            while (rs.next()) {
                if (flag == 0) {
                    currentVal = rs.getInt("missedClasses");
                } else {
                    currentVal = rs.getInt("attendedClasses");
                }
            }
            String insertQuery = null;
            if (flag == 0) {
                insertQuery = "update registers set missedClasses=? where subjectName= ?";

            } else {

                insertQuery = "update registers set attendedClasses=? where subjectName= ?";
            }

            PreparedStatement inspreparedStmt = conn.prepareStatement(insertQuery);

            inspreparedStmt.setInt(1, currentVal + 1);
            inspreparedStmt.setString(2, subjectName);
            inspreparedStmt.execute();
            System.out.println("You missed a class !");


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

    public void updateHistory(String subjectName, int flag) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to a selected database...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            String query = "insert into history values(?,?)";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, subjectName);

            if (flag == 0) {
                preparedStmt.setString(2, "ABSENT");
            } else {
                preparedStmt.setString(2, "PRESENT");
            }

            preparedStmt.execute();

            System.out.println("Updated history !");


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<ArrayList<String>> fetchHistory() {

        ArrayList<ArrayList<String>> extractedAttendance = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to a selected database...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            String query = "select * from history";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                ArrayList<String> attRow = new ArrayList<String>();
                attRow.add(rs.getString("subjectName"));
                attRow.add(rs.getString("attendance"));
                extractedAttendance.add(attRow);
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return extractedAttendance;

    }

    public void clearHistory() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to a selected database...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            String query = "truncate history";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.executeQuery();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getStats() {
        ArrayList<Integer> stats = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to a selected database...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            String selectMissed = "select missedClasses from registers";
            PreparedStatement selectMissedpreparedStmt = conn.prepareStatement(selectMissed);
            ResultSet rs1 = selectMissedpreparedStmt.executeQuery();

            int missedClasses = 0;

            while (rs1.next()) {
                missedClasses += rs1.getInt("missedClasses");

            }

            String selectAttended = "select attendedClasses from registers";
            PreparedStatement selectAttendedpreparedStmt = conn.prepareStatement(selectAttended);
            ResultSet rs2 = selectAttendedpreparedStmt.executeQuery();

            int attendedClasses = 0;

            while (rs2.next()) {
                attendedClasses += rs2.getInt("attendedClasses");
            }

            int totalClasses = missedClasses + attendedClasses;

            int safeBunks = calculateSafeBunks(attendedClasses, totalClasses);

            stats.add(totalClasses);
            stats.add(attendedClasses);
            stats.add(safeBunks);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return stats;
    }

    public ArrayList<Integer> getStatsForSub(String sub) {
        ArrayList<Integer> stats = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            String selectMissed = "select missedClasses from registers where subjectName='" + sub + "'";
            PreparedStatement selectMissedpreparedStmt = conn.prepareStatement(selectMissed);
            ResultSet rs1 = selectMissedpreparedStmt.executeQuery();

            int missedClasses = 0;

            while (rs1.next()) {
                missedClasses += rs1.getInt("missedClasses");
            }

            String selectAttended = "select attendedClasses from registers where subjectName='" + sub + "'";
            PreparedStatement selectAttendedpreparedStmt = conn.prepareStatement(selectAttended);
            ResultSet rs2 = selectAttendedpreparedStmt.executeQuery();

            int attendedClasses = 0;

            while (rs2.next()) {
                attendedClasses += rs2.getInt("attendedClasses");
            }

            int totalClasses = missedClasses + attendedClasses;

            int safeBunks = calculateSafeBunks(attendedClasses, totalClasses);

            stats.add(totalClasses);
            stats.add(attendedClasses);
            stats.add(safeBunks);


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return stats;
    }

    public int calculateSafeBunks(int attendedClasses, int totalClasses) {

        int safeBunks;

        if (totalClasses == 0) {
            return 0;
        }

        int i = 0;

        if (((float) attendedClasses / totalClasses) > 0.75) {
            for (i = 0; ; i++) {
                int newTotalClass = totalClasses + i;
                float newPercentage = ((float) attendedClasses) / newTotalClass;
                if (newPercentage <= 0.75) {
                    break;
                }

            }
            safeBunks = i - 1;
            System.out.println(i - 1 + " Classes can be bunked !");
            return safeBunks;
        } else {
            for (i = 0; ; i++) {
                int newTotalClass = totalClasses + i;
                int newAttendedClasses = attendedClasses + i;
                float newPercentage = ((float) newAttendedClasses) / newTotalClass;
                if (newPercentage > 0.75) {
                    break;
                }
            }
            safeBunks = (i + 1);
            System.out.println(i + 1 + " Classes need to be attended !");
            return safeBunks * (-1);
        }
    }

    public String[] getBio() {
        String[] bio = new String[4];


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to a selected database...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            String query = "select * from bio";

            PreparedStatement preparedStmt = conn.prepareStatement(query);

            ResultSet rs = preparedStmt.executeQuery();

            while (rs.next()) {
                bio[0] = rs.getString("name");
                bio[1] = rs.getString("year");
                bio[2] = rs.getString("college");
                bio[3]=rs.getString("filepath");
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return bio;
    }

    public void updateBio(String inputName, String inputYear, String inputCollege, String inputFilePath) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to a selected database...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            String query = " delete from bio";
            PreparedStatement d = conn.prepareStatement(query);
            d.execute();

            String query1 = "insert into bio values(?,?,?,?)";
            PreparedStatement updateBio = conn.prepareStatement(query1);
            updateBio.setString(1, inputName);
            updateBio.setString(2, inputYear);
            updateBio.setString(3, inputCollege);
            updateBio.setString(4, inputFilePath);
            updateBio.execute();

        } catch (Exception classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException ignored) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");

    }
}



