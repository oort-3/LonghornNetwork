import java.util.*;

public class UniversityStudent extends Student {
    // TODO: Constructor and additional methods to be implemented
    private List<UniversityStudent> podMembers;

    /** Creates a UniversityStudent object with all specified attributes */
    public UniversityStudent(String name, int age, String gender,
                             int year, String major, double gpa,
                             List<String> roommatePreferences,
                             List<String> previousInternships)
    {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.year = year;
        this.major = major;
        this.gpa = gpa;
        this.roommatePreferences = roommatePreferences;
        this.previousInternships = previousInternships;
    }

    /** Return true if student is preferred as a roommate for this UniversityStudent */
    public boolean prefersRoommate(UniversityStudent student){
    }
}

