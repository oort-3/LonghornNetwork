import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide the input file name as a command-line argument.");
            return;
        }
        String inputFile = args[0];
        try {
            List<UniversityStudent> students = DataParser.parseStudents(inputFile);

            // Roommate matching
            GaleShapley.assignRoommates(students);
            for(UniversityStudent s : students){
                if(s.getRoommate() == null) System.out.println(s.getName() +" has no roommate");
                else System.out.println(s.getName() +" is roommates with "+s.getRoommate().getName());
            }
            // Pod formation
            StudentGraph graph = new StudentGraph(students);
            PodFormation podFormation = new PodFormation(graph);
            podFormation.formPods(4);
            for(UniversityStudent s: students){
                System.out.println(s.getPodMembers());
            }
            // Referral path finding
            ReferralPathFinder pathFinder = new ReferralPathFinder(graph);
            // TODO: Implement user interaction for specifying a target company
            List<UniversityStudent> s = pathFinder.findReferralPath(graph.getStudent("Timmy"), "FindMe");
            if(null== s) System.out.println("no referral");
            else System.out.println(s);

            s = pathFinder.findReferralPath(graph.getStudent("Issac"), "FindMe");
            if(null==s) System.out.println("no referral");
            else System.out.println(s);

            s = pathFinder.findReferralPath(graph.getStudent("Timmy"), "IDontExist");
            if(null==s ) System.out.println("no referral");
            else System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
