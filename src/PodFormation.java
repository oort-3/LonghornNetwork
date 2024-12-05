import java.util.*;

public class PodFormation {
    StudentGraph graph;
    /** Create a PodFormation object given a StudentGraph */
    public PodFormation(StudentGraph graph) {
        this.graph = graph;
    }

    /** Place students in pods of size podSize */
    public void formPods(int podSize) {
        // Method signature only
        List<UniversityStudent> podMembers = new ArrayList<>();
        int currentsize = 1;
        List<UniversityStudent> availableStudents = new ArrayList<>();

        for(UniversityStudent s: graph.getStudentsList()){
            availableStudents.add(s);
        }

        while(!availableStudents.isEmpty()){
            UniversityStudent initialStudent = availableStudents.get(0);
            podMembers = new ArrayList<>();
            podMembers.add(initialStudent);       //initial in pod
            availableStudents.remove(initialStudent);
            List<Node> neighbors = graph.getNeighbors(initialStudent);

            while(podMembers.size() < podSize && !availableStudents.isEmpty()) {
                int maxConnection = 0;
                Node nextNode = null;
                for (Node n : neighbors) {
                    if(availableStudents.contains(n.getStudent())) {
                        if (n.getConnectionStrength() > maxConnection) {
                            maxConnection = n.getConnectionStrength();
                            nextNode = n;
                        }
                    }

                }
                if (nextNode != null) {
                    podMembers.add(nextNode.getStudent());
                    availableStudents.remove(nextNode.getStudent());
                    for (Node n : graph.getNeighbors(nextNode.getStudent())) {
                        if (!neighbors.contains(n)) {
                            neighbors.add(n);
                        }
                    }
                }
                if(maxConnection == 0 ){
                    break;
                }
            }
            for(UniversityStudent s: podMembers){
                s.setPodMembers(podMembers);
            }

        }



    }
}
