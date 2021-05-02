package com.attendance_manager.components;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parser {
    private ArrayList<ArrayList<String>> extractedHolidays=new ArrayList<>();




    public ArrayList<ArrayList<String>> parse(String response) {

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


            ArrayList<String> extractedHoliday=new ArrayList<>();
            extractedHoliday.add(date);
            extractedHoliday.add(day_name);
            extractedHoliday.add(name);
            extractedHolidays.add(extractedHoliday);


        }
        return extractedHolidays;
    }
}
