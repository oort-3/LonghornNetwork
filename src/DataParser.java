import java.io.*;
import java.util.*;

public class DataParser {
    /**
     * Create UniversityStudent objects from data written in a file
     * @param filename - input file containing the information of students to be added
     * @return A list of UniversityStudent objects created as defined in the file
     * @throws IOException
     */
    public static List<UniversityStudent> parseStudents(String filename) throws IOException {
        List<UniversityStudent> students = new ArrayList<>();
        Scanner file = new Scanner(new FileReader(filename));
        while(file.hasNextLine()){
            String name, gender, major;
            int age, year;
            double gpa;
            List<String> roommatePreferences = new ArrayList<>();
            List<String> previousInternships = new ArrayList<>();
            String nextLine = file.nextLine();
            while(nextLine.isEmpty()){
                nextLine = file.nextLine();
            }

            if(nextLine.equals("Student:") && file.next().equals("Name:")){
                name = file.next();
                if(file.next().equals("Age:")) {
                    age = Integer.parseInt(file.next());

                    if(file.next().equals("Gender:")) {
                        gender = file.next();

                        if(file.next().equals("Year:")) {
                            year = Integer.parseInt(file.next());

                            if(file.next().equals("Major:")) {
                                major = file.nextLine();

                                if(file.next().equals("GPA:")) {
                                    gpa = Double.parseDouble(file.next());

                                    if(file.next().equals("RoommatePreferences:")){
                                        parseList(file.nextLine(), roommatePreferences);

                                        if(file.next().equals("PreviousInternships:")){
                                            parseList(file.nextLine(), previousInternships);

                                            UniversityStudent student = new UniversityStudent(name, age, gender, year, major, gpa, roommatePreferences, previousInternships);
                                            students.add(student);
                                            //System.out.println(student + "\n");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return students;
    }

    /** Parse a line containing a list and place elements into a given List of strings */
    private static void parseList(String list, List<String> stringList){

        String[] names = list.split(" ");
        for( String word: names){
            if(!word.isEmpty()) {
                if(word.endsWith(",")) word = word.substring(0, word.length()-1);
                stringList.add(word);
            }
        }
    }
}
