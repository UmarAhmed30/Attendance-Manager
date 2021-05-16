package com.attendance_manager.services;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;

public class DBHandler {

    String DB_URL = "jdbc:mysql://localhost/javaproject";

    //  Database credentials
    String USER = "root";
    String PASS = "ahmed3633";
    Connection conn = null;
    Statement stmt = null;


    public boolean userExists(){
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //check for existing user
            PreparedStatement checkExisting = conn.prepareStatement("select * from user ", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = checkExisting.executeQuery();
            //System.out.println(rs);
            if (rs.absolute(1)) {
                System.out.println("ALREADY EXISTING USER");
                conn.close();
                return false;
            }




        }  catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } // do nothing
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return true;


    }

    public boolean validateUser(String email, String password) {


        String hashedPwd = BCrypt.hashpw(password, BCrypt.gensalt(5));
        System.out.println(hashedPwd);
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            String query = "select password from user where email= ?";
            PreparedStatement checkExisting = conn.prepareStatement(query);
            checkExisting.setString(1, email);
            ResultSet rs = checkExisting.executeQuery();
            //System.out.println(rs);
            while (rs.next()) {
                if (BCrypt.checkpw(password, rs.getString("password"))) {

                    conn.close();
                    return true;
                }
            }
            return false;

        } catch (Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally {
            //finally block used to close resources
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
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return false;
    }

    public void insertToDB(String email, String password) {

        String hashedPwd = BCrypt.hashpw(password, BCrypt.gensalt(5));


        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //check for existing user
            PreparedStatement checkExisting = conn.prepareStatement("select * from user where email= ?", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            checkExisting.setString(1, email);
            ResultSet rs = checkExisting.executeQuery();
            //System.out.println(rs);
            if (rs.absolute(1)) {
                System.out.println("ALREADY EXISTING USER");
                conn.close();
                return;
            }


            //STEP 4: Execute a query
            String query = " insert into user"
                    + " values (?, ?)";
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, email);
            preparedStmt.setString(2, hashedPwd);
            // execute the preparedstatement
            preparedStmt.execute();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }

    public boolean addSubject(String inputCode, String inputSubName, String inputFaculty) {

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            System.out.println("DEBUG");
            //check for existing user
            PreparedStatement checkExisting = conn.prepareStatement("select * from subject where subjectCode= ? and staff= ?", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            checkExisting.setString(1, inputCode);
            checkExisting.setString(2, inputFaculty);
            ResultSet rs = checkExisting.executeQuery();
            //System.out.println(rs);
            if (rs.absolute(1)) {
                System.out.println("ALREADY EXISTING SUBJECT");
                return false;

            } else {
                System.out.println("NEW SUBJECT DETECTED !");
                //STEP 4: Execute a query
                String query = " insert into subject"
                        + " values (?,?,?)";
                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, inputCode);
                preparedStmt.setString(2, inputFaculty);
                preparedStmt.setString(3, inputSubName);
                preparedStmt.execute();

                String query2 = " insert into registers"
                        + " values (?,?,?,?,?,?)";
                PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
                preparedStmt2.setString(1, "akash@gmail.com");
                preparedStmt2.setInt(2, 0);
                preparedStmt2.setInt(3, 0);
                preparedStmt2.setString(4, inputSubName);
                preparedStmt2.setString(5, inputCode);
                preparedStmt2.setString(6, inputFaculty);

                preparedStmt2.execute();
                return true;


            }


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");


        return true;
    }


    public void deleteSubject(String inputSubName) {

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //check if existing subject
            PreparedStatement checkExisting = conn.prepareStatement("select * from registers where subjectName= ? ", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            checkExisting.setString(1, inputSubName);

            ResultSet rs = checkExisting.executeQuery();
            //System.out.println(rs);
            if (rs.absolute(1)) {

                //UNREGISTER
                System.out.println("REMOVING SUBJECT");
                String query = " delete from registers where subjectName=?";


                // create the mysql delete preparedstatement
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, inputSubName);
                preparedStmt.execute();

                //remove subject

                String query2 = " delete from subject where subjectName=?";


                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
                preparedStmt2.setString(1, inputSubName);
                preparedStmt2.execute();

            } else {

                System.out.println("WRONG SUBJECT NAME");

            }


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");

    }

    public ArrayList<String> fetchSubjects() {

        ArrayList<String> returnList = new ArrayList<String>();

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //Query
            String query = "select * from registers";


            // create the  preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            ResultSet rs = preparedStmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("subjectName"));
                returnList.add(rs.getString("subjectName"));
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return returnList;

    }


    public void updateAttendance(String subjectName, int flag) {

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");


            String query = "select * from registers where subjectName=?";


            // create the  preparedstatement
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
            //Query
            if (flag == 0) {
                insertQuery = "update registers set missedClasses=? where subjectName= ?";

            } else {

                insertQuery = "update registers set attendedClasses=? where subjectName= ?";
            }
            // create the  preparedstatement
            PreparedStatement inspreparedStmt = conn.prepareStatement(insertQuery);


            inspreparedStmt.setInt(1, currentVal + 1);
            inspreparedStmt.setString(2, subjectName);
            inspreparedStmt.execute();
            System.out.println("YOU MISSED A CLASS !");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void updateHistory(String subjectName, int flag) {

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");


            String query = "insert into history values(?,?)";


            // create the  preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, subjectName);

            if (flag == 0) {
                preparedStmt.setString(2, "ABSENT");
            } else {
                preparedStmt.setString(2, "PRESENT");
            }

            preparedStmt.execute();

            System.out.println("updated history");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


    public ArrayList<ArrayList<String>> fetchHistory() {

        ArrayList<ArrayList<String>> extractedAttendance = new ArrayList<>();

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 3: Open a connection
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
                //System.out.println(rs.getString("subjectName")+" "+rs.getString("attendance"));
                extractedAttendance.add(attRow);
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return extractedAttendance;

    }

    public ArrayList<Integer> getStats() {
        ArrayList<Integer> stats = new ArrayList<>();

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //Query to find number of missed classes
            String selectMissed = "select missedClasses from registers";
            PreparedStatement selectMissedpreparedStmt = conn.prepareStatement(selectMissed);
            ResultSet rs1 = selectMissedpreparedStmt.executeQuery();
            int missedClasses = 0;
            while (rs1.next()) {
                missedClasses += rs1.getInt("missedClasses");

            }
//            System.out.println(missedClasses);


            //Query to find number of attended classes

            String selectAttended = "select attendedClasses from registers";
            PreparedStatement selectAttendedpreparedStmt = conn.prepareStatement(selectAttended);
            ResultSet rs2 = selectAttendedpreparedStmt.executeQuery();
            int attendedClasses = 0;
            while (rs2.next()) {
                attendedClasses += rs2.getInt("attendedClasses");

            }


            int totalClasses = missedClasses + attendedClasses;


            int safeBunks = calculateSafeBunks(attendedClasses, totalClasses);

            //append to arraylist for frontend
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

    public int calculateSafeBunks(int attendedClasses, int totalClasses) {
        int safeBunks;
        if(totalClasses==0){
            return 0;
        }

        int i=0;
        if (((float)attendedClasses / totalClasses) > 0.75) {
            for (i = 0; ; i++) {
                int newTotalClass = totalClasses + i;
                float newPercentage = ((float) attendedClasses) / newTotalClass;
                if (newPercentage <= 0.75) {
                    break;
                }

            }
            safeBunks = i - 1;
            System.out.println(i-1+ " classes can be bunked");
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
            System.out.println(i+1+ " classes need to be attended");
            return safeBunks * (-1);

        }


    }

}
