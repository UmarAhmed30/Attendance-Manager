package com.attendance_manager.pages.holidaysAPI;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parser {

    private ArrayList<ArrayList<String>> extractedHolidays=new ArrayList<>();

    public ArrayList<ArrayList<String>> parse(String response) {

        JSONObject holidays_object = new JSONObject(response);
        JSONArray holidays = holidays_object.getJSONArray("holidays");

        for (int i = 0; i < holidays.length(); i++) {

            JSONObject holiday = holidays.getJSONObject(i);

            String name = holiday.getString("name");
            String date = holiday.getString("date");

            date=date.substring(8,10)+"-"+date.substring(5,7)+"-"+date.substring(0,4);

            JSONObject weekday = holiday.getJSONObject("weekday");
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
