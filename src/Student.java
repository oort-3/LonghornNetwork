import java.util.*;

public abstract class Student {
    protected String name;
    protected int age;
    protected String gender;
    protected int year;
    protected String major;
    protected double gpa;
    protected List<String> roommatePreferences;
    protected List<String> previousInternships;

    /** Return the connection strength to another Student based on their attributes */
    public abstract int calculateConnectionStrength(Student other);
    public String toString(){
        return "Name: " + name + "\nAge:" +age+ "\nGender: "+ gender+ "\nYear: "+year + "\nMajor: " + major + "\nGPA: " +gpa + "\nRoommatePref: " +roommatePreferences+"\nPrevIntern: " + previousInternships;
    }
}
