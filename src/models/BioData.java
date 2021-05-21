package models;

public class BioData {
    private String name;
    private String year;
    private String college;
    private String imagePath;

    public BioData(String name, String year, String college, String imagePath) {
        this.name = name;
        this.year = year;
        this.college = college;
        this.imagePath = imagePath;
    }

    public BioData() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "BioData{" +
                "name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", college='" + college + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
