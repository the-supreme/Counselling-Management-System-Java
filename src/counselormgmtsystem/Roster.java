package counselormgmtsystem;

import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Roster {
    protected String rosterID;
    protected String counselorID;
    protected String date;
    protected String startTime;
    protected String endTime;
    protected String status;

    public Roster(String rosterID, String counselorID, String date, String startTime, String endTime, String status) {
        this.rosterID = rosterID;
        this.counselorID = counselorID;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }
    
    //getters
    public String getRosterID() { 
        return rosterID; 
    }
    
    public String getCounselorID() { 
        return counselorID; 
    }
    
    public String getDate() { 
        return date; 
    }
    
    public String getStartTime() { 
        return startTime; 
    }
    
    public String getEndTime() { 
        return endTime; 
    }
    
    public String getStatus() { 
        return status; 
    }

    //setters
    public void setRosterID(String rosterID) { 
        this.rosterID = rosterID; 
    }
    
    public void setCounselorID(String counselorID) { 
        this.counselorID = counselorID; 
    }
    
    public void setDate(String date) { 
        this.date = date; 
    }
    
    public void setStartTime(String startTime) { 
        this.startTime = startTime;
    }
    
    public void setEndTime(String endTime) {
        this.endTime = endTime; 
    }
    
    public void setStatus(String status) { 
        this.status = status; 
    }
 
    public static void addRoster(String counselorID, String date, String start, String end) {
        int max = 0;
        for (Roster r : FileHandler.rosterList) {
            try {
                int num = Integer.parseInt(r.rosterID.substring(3));
                if (num > max) max = num;
            } catch (NumberFormatException ignored) {}
        }
        String newID = String.format("ROS%03d", max + 1);
 
        Roster newRoster = new Roster(newID, counselorID, date, start, end, "Available");
        FileHandler.rosterList.add(newRoster);
 
        new FileHandler().saveDataToFiles();
        System.out.println("--- Roster slot successfully added ---");
    }
 
    public static void updateRoster(String rosterID, String date, String start, String end, String status) {
        for (Roster r : FileHandler.rosterList) {
            if (r.rosterID.equals(rosterID)) {
                r.date      = date;
                r.startTime = start;
                r.endTime   = end;
                r.status    = status;
 
                new FileHandler().saveDataToFiles();
                return;
            }
        }
        System.out.println("Error: Roster ID " + rosterID + " not found.");
    }
 
    public static void deleteRoster(String rosterID) {
        for (int i = 0; i < FileHandler.rosterList.size(); i++) {
            if (FileHandler.rosterList.get(i).rosterID.equals(rosterID)) {
                FileHandler.rosterList.remove(i);
 
                new FileHandler().saveDataToFiles();
                System.out.println("--- Roster " + rosterID + " successfully deleted ---");
                return;
            }
        }
        System.out.println("Error: Roster ID " + rosterID + " not found.");
    }
 
}
