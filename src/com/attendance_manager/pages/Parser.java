package com.attendance_manager.pages;

import org.json.JSONArray;
import org.json.JSONObject;

public class Parser {

    public static void parse(String response) {
        //System.out.println(response);
        //PublicHolidays hols=new PublicHolidays();
        String return_string="";
        JSONObject holidays_object = new JSONObject(response);

        JSONArray holidays = holidays_object.getJSONArray("holidays");


        for (int i = 0; i < holidays.length(); i++) {
            JSONObject holiday = holidays.getJSONObject(i);

            String name = holiday.getString("name");
            String date = holiday.getString("date");

            //code to format date to our convenience
            date=date.substring(8,10)+"-"+date.substring(5,7)+"-"+date.substring(0,4);

            //parsing weekday
            JSONObject weekday = holiday.getJSONObject("weekday");
            //parsing day
            JSONObject day = weekday.getJSONObject("date");
            String day_name = day.getString("name");


            //System.out.println( date + " - " + day_name+" - "+name );
            String holidayInfo=date + " - " + day_name+" - "+name+"\n";
            System.out.print(holidayInfo);


        }
        //return return_string;
    }
}
