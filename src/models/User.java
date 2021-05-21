package models;

public class User {
    private String email;
    private String password;
    private BioData bio;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BioData getBio() {
        return bio;
    }

    public void setBio(BioData bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", bio=" + bio +
                '}';
    }

}
