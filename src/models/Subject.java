package models;

public class Subject {
    private String subjectCode;
    private String subjectName;
    private String faculty;
    private int totalClasses;
    private int attendedClasses;
    private int missedClasses;
    private  float attendancePercentage;
    private int safebunks;

    public Subject(String subjectCode, String subjectName, String faculty) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.faculty = faculty;
    }



    public Subject(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getFaculty() {
        return faculty;
    }

    public int getSafebunks() {
        return safebunks;
    }

    public void setSafebunks(int safebunks) {
        this.safebunks = safebunks;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getTotalClasses() {
        return totalClasses;
    }

    public void setTotalClasses(int totalClasses) {
        this.totalClasses = totalClasses;
    }

    public int getAttendedClasses() {
        return attendedClasses;
    }

    public void setAttendedClasses(int attendedClasses) {
        this.attendedClasses = attendedClasses;
    }

    public int getMissedClasses() {
        return missedClasses;
    }

    public void setMissedClasses(int missedClasses) {
        this.missedClasses = missedClasses;
    }

    public float getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(float attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectCode='" + subjectCode + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", faculty='" + faculty + '\'' +
                ", totalClasses=" + totalClasses +
                ", attendedClasses=" + attendedClasses +
                ", missedClasses=" + missedClasses +
                ", attendancePercentage=" + attendancePercentage +
                '}';
    }
}
