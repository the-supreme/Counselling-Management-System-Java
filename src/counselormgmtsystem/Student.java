package counselormgmtsystem;

public class Student extends User{
    String intakeCode;
    String contactNumber;
    String emergencyContact;

    public Student(String ID, String username, String password, String fullName, String status, String intakeCode, String contactNumber, String emergencyContact) {
        super(ID, username, password, fullName, status);
        this.intakeCode = intakeCode;
        this.contactNumber = contactNumber;
        this.emergencyContact = emergencyContact; 
    }

    @Override
    public String toString() {
        return "User: " + this.ID
         + " " + this.username + " " + this.password + " " + this.fullName + " " + this.status + " " + this.contactNumber + " " + this.intakeCode + " " + this.emergencyContact ;
    }
}
