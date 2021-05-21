package com.attendance_manager.pages;
import com.attendance_manager.pages.userLogin.Login;
import com.attendance_manager.pages.userLogin.NewUser;
import com.attendance_manager.services.DBHandler;

public class Main {

    public static void main(String[] args) {

        DBHandler db = new DBHandler();
        if (db.userExists()) {
            new NewUser();
        } else {
            new Login("");
        }

//       new NewUser();
    }
}