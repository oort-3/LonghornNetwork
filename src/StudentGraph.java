import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Node {
    UniversityStudent student;
    int connectionStrength;

    public Node(UniversityStudent student, int connectionStrength) {
        this.connectionStrength = connectionStrength;
        this.student = student;
    }

    public UniversityStudent getStudent() {
        return student;
    }
    public int getConnectionStrength() {
        return connectionStrength;
    }
}

public class StudentGraph {
    private Map<UniversityStudent, List<Node>> adjacencyList;
    private List<UniversityStudent> studentsList;
    public StudentGraph(List<UniversityStudent> students){
        adjacencyList = new HashMap<>();
        studentsList = students;
        int current = 0;
        while(current < students.size()) {
            LinkedList<Node> nodeList = new LinkedList<>();
            UniversityStudent thisStudent = students.get(current);
            for (int i = 0; i < students.size(); i++) {
                if(current == i) continue;
                UniversityStudent otherStudent = students.get(i);
                int connectionStrength = thisStudent.calculateConnectionStrength(otherStudent);
                if(connectionStrength != 0){
                    nodeList.add(new Node(otherStudent, connectionStrength));
                }

            }
            current++;
            adjacencyList.put(thisStudent, nodeList);
        }
    }
    public List<Node> getNeighbors ( UniversityStudent student){
        return adjacencyList.get(student);
    }

    public List<UniversityStudent> getStudentsList() {
        return studentsList;
    }
}
