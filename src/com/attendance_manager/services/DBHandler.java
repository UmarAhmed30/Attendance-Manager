package com.attendance_manager.services;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;

public class DBHandler {

    String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    String DB_URL = "jdbc:mysql://localhost/javaproject";

    //  Database credentials
    String USER = "root";
    String PASS = "ahmed3633";
    Connection conn = null;
    Statement stmt = null;

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
        return false;
    }

    public void insertToDB(String email,String password) {

        String hashedPwd=BCrypt.hashpw(password,BCrypt.gensalt(5));


        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //check for existing user
            PreparedStatement checkExisting=conn.prepareStatement("select * from user where email= ?" ,ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            checkExisting.setString(1,email);
            ResultSet rs=checkExisting.executeQuery();
            //System.out.println(rs);
            if(rs.absolute(1)) {
                System.out.println("ALREADY EXISTING USER");
                conn.close();
                return;
            }




            //STEP 4: Execute a query
            String query = " insert into user"
                    + " values (?, ?)";
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, email);
            preparedStmt.setString (2, hashedPwd);
            // execute the preparedstatement
            preparedStmt.execute();

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }

    public boolean addSubject(String inputCode,String inputSubName,String inputFaculty){

        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            System.out.println("DEBUG");
            //check for existing user
            PreparedStatement checkExisting=conn.prepareStatement("select * from subject where subjectCode= ? and staff= ?" ,ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            checkExisting.setString(1,inputCode);
            checkExisting.setString(2,inputFaculty);
            ResultSet rs=checkExisting.executeQuery();
            //System.out.println(rs);
            if(rs.absolute(1)) {
                System.out.println("ALREADY EXISTING SUBJECT");
                return false;

            }
            else{
                System.out.println("NEW SUBJECT DETECTED !");
                //STEP 4: Execute a query
                String query = " insert into subject"
                        + " values (?,?,?)";
                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString (1, inputCode);
                preparedStmt.setString (2, inputFaculty);
                preparedStmt.setString (3, inputSubName);
                preparedStmt.execute();

                String query2=" insert into registers"
                        + " values (?,?,?,?,?,?)";
                PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
                preparedStmt2.setString (1, "akash@gmail.com");
                preparedStmt2.setString (2,"0" );
                preparedStmt2.setString (3, "0");
                preparedStmt2.setString (4, inputSubName);
                preparedStmt2.setString (5, inputCode);
                preparedStmt2.setString (6, inputFaculty);

                preparedStmt2.execute();
                return true;


            }


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");


        return true;
    }


    public void deleteSubject(String inputSubName) {

        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //check if existing subject
            PreparedStatement checkExisting=conn.prepareStatement("select * from registers where subjectName= ? " ,ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            checkExisting.setString(1,inputSubName);

            ResultSet rs=checkExisting.executeQuery();
            //System.out.println(rs);
            if(rs.absolute(1)) {

                //UNREGISTER
                System.out.println("REMOVING SUBJECT");
                String query = " delete from registers where subjectName=?";


                // create the mysql delete preparedstatement
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString (1, inputSubName);
                preparedStmt.execute();

                //remove subject

                String query2 = " delete from subject where subjectName=?";


                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
                preparedStmt2.setString (1, inputSubName);
                preparedStmt2.execute();

            }
            else{

                System.out.println("WRONG SUBJECT NAME");

            }


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");

    }

    public ArrayList<String> fetchSubjects(){
        return null;
    }
}
