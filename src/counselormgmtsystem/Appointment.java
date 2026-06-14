package counselormgmtsystem;

public class Appointment {
    String appointmentID;
    String studentID;
    String counselorID;
    String date;
    String time;
    String bookingType;
    String queueNumber;
    String status;

    public Appointment(String appointmentID, String studentID, String counselorID, String date, String time, String bookingType, String queueNumber, String status) {
        this.appointmentID = appointmentID;
        this.studentID = studentID;
        this.counselorID = counselorID;
        this.date = date;
        this.time = time;
        this.bookingType = bookingType;
        this.queueNumber = queueNumber;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment: " + " " + this.appointmentID + " " + this.studentID + " " + this.counselorID + " " + this.date + " " + this.time + " " + this.bookingType + " " + this.queueNumber + " " + this.status;
    }

}
