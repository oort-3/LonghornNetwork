import java.util.*;

public class GaleShapley {
    /**
     * Assign roommates for each student in a list of UniversityStudents
     * @param students - list of students to assign roommates for
     */
    public static void assignRoommates(List<UniversityStudent> students) {
        Map<UniversityStudent, Queue<String>> proposals = new HashMap<>();
        Queue<UniversityStudent> unmatched = new LinkedList<>();
        Map<String, UniversityStudent> studentMap = new HashMap<>();
        for(UniversityStudent student: students){
            unmatched.add(student);
            studentMap.put(student.getName(), student);
            proposals.put(student, new LinkedList<>(student.getRoommatePreferences()));
        }

        while(!unmatched.isEmpty()){
            UniversityStudent current = unmatched.poll();
            Queue<String> myPreferences = proposals.get(current);
            if(myPreferences.isEmpty()) continue;
            if(current.getRoommate() != null) continue;
            while( !myPreferences.isEmpty()){
                UniversityStudent roommate = studentMap.get(myPreferences.poll());
                if(roommate.getRoommate()==null){
                    current.setRoommate(roommate);
                    roommate.setRoommate(current);
                    break;
                }
                else{
                    UniversityStudent competitor = roommate.getRoommate();
                    if(roommate.getRoommatePreferences().contains(current.getName())) {
                        if (roommate.getRoommatePreferences().indexOf(current.getName()) < roommate.getRoommatePreferences().indexOf(competitor.getName())) {
                            roommate.setRoommate(current);
                            current.setRoommate(roommate);
                            competitor.setRoommate(null);
                            unmatched.add(competitor);
                            break;
                        }
                    }
                }
            }
            if(current.getRoommate() == null) unmatched.add(current);
        }
    }
}
