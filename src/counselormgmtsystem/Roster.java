package counselormgmtsystem;

public class Roster {
    String rosterID;
    String counselorID;
    String date;
    String startTime;
    String endTime;
    String status;

    public Roster(String rosterID, String counselorID, String date, String startTime, String endTime, String status) {
        this.rosterID = rosterID;
        this.counselorID = counselorID;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Roster: " + " " + this.rosterID + " " + this.counselorID + " " + this.date + " " + this.startTime + " " + this.endTime + " " + this.status;
    }


    
}
