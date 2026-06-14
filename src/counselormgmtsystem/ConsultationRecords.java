package counselormgmtsystem;

public class ConsultationRecords {

    String recordID;
    String appointmentID;
    String studentID;
    String counselorID;
    String date;
    String notes;
    String recommendations;

    public ConsultationRecords(String recordID, String appointmentID, String studentID, String counselorID, String date, String notes, String recommendations) {
        this.recordID = recordID;
        this.appointmentID = appointmentID;
        this.studentID = studentID;
        this.date = date;
        this.counselorID = counselorID;
        this.notes = notes;
        this.recommendations = recommendations;
    }

    @Override
    public String toString() {
        return "ConsultationRecords: " + " " + this.recordID + " " + this.appointmentID + " " + this.studentID + " " + this.date + " " + this.counselorID + " " + this.notes + " " + this.recommendations;
    }
}
