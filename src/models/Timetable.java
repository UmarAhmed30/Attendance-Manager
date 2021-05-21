package models;

import java.util.ArrayList;

public class Timetable {
    private ArrayList<ArrayList<String>> timeTable;

    public ArrayList<ArrayList<String>> getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(ArrayList<ArrayList<String>> timeTable) {
        this.timeTable = timeTable;
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "timeTable=" + timeTable +
                '}';
    }
}
