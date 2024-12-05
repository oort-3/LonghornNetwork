import java.util.*;

public class ReferralPathFinder {
    /** Create a ReferralPathFinder object given a StudentGraph */
    StudentGraph graph;


    public ReferralPathFinder(StudentGraph graph) {
        // Constructor
        this.graph = graph;
    }

    /** Find a path to a target company from a student */
    public List<UniversityStudent> findReferralPath(UniversityStudent start, String targetCompany) {
        // Method signature only
        List<UniversityStudent> students = new ArrayList<>();
        Map<UniversityStudent, Integer> distances = new HashMap<>();

        for(UniversityStudent s: graph.getStudentsList()){
            students.add(s);
            distances.put(s, 0);
        }

        List <UniversityStudent> path = new ArrayList<>();
        List<Node> neighbors = graph.getNeighbors(start);
        distances.put(start, 0);


        while(!students.isEmpty()) {
            for (Node neighbor : neighbors) {
                UniversityStudent neighborStudent = neighbor.getStudent();
                int neighborConnection = neighbor.getConnectionStrength();
                if (neighborConnection > distances.get(neighborStudent)) {
                    distances.put(neighbor.getStudent(), neighbor.getConnectionStrength());

                }
            }
            students.remove(start);
            if(!students.isEmpty()){
                int strongestConnection = 0;
                UniversityStudent strongestNeighbor = students.get(0);
                for(UniversityStudent s: students){
                    int newConnection = distances.get(start) + distances.get(s);
                    if(newConnection > strongestConnection) {
                        strongestConnection = newConnection;
                        strongestNeighbor = s;
                    }
                }
                start = strongestNeighbor;
            }
        }
        int connectionValue = 0;
        UniversityStudent connection = null;
        for(UniversityStudent s: graph.getStudentsList()){
            if(s.previousInternships.contains(targetCompany)){
                connectionValue = distances.get(s);
                connection = s;
            }
        }
        path.add(connection);
        if(connectionValue  == 0 )return null;
        else return path;
    }


}
