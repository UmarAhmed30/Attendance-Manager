package com.attendance_manager.pages;

import com.attendance_manager.components.FileChooser;
import com.attendance_manager.services.DBHandler;

public class Main {

    public static void main(String[] args) {

        DBHandler db = new DBHandler();

        if (db.userExists()) {
            new NewUser();
        } else {
            new Login();
        }
//        new NewUser();
//        new Login();
//        new PublicHolidays();
//        new Homepage();
//        new AddSubject();
//        new RemoveSubject();
//        new AddAttendance();
//        new PublicHolidays();
//        new FileChooser().triggerDBox();
//        new Bio();
        //new History();
    }
}
