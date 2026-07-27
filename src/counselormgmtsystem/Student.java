package counselormgmtsystem;

public class Student extends User{
    String intakeCode;
    String email;
    String contactNumber;
    String emergencyContact;

    public Student(String ID, String username, String password, String fullName, String intakeCode, String email, String contactNumber, String emergencyContact) {
        super(ID, username, password, fullName);
        this.intakeCode = intakeCode;
        this.email = email;
        this.contactNumber = contactNumber;
        this.emergencyContact = emergencyContact; 
    }
    
    public String getStudentEmail() {
        return this.email;
    }

    public void setStudentEmail(String email) {
        this.email = email;
    }
    
    public String getintakeCode() {
        return this.intakeCode;
    }

    public void setintakeCode(String email) {
        this.intakeCode = intakeCode;
    }
    
    public String getContactNumber() {
        return this.contactNumber;
    }

    public void setContactNumber(String email) {
        this.contactNumber = contactNumber;
    }
    
    public String getEmergencyContact() {
        return this.emergencyContact;
    }

    public void setEmergencyContact(String email) {
        this.emergencyContact = emergencyContact;
    }
    
    @Override
    public String toString() {
        return "User: " + this.ID
         + " " + this.username + " " + this.password + " " + this.fullName + " " + " " + this.contactNumber + " " + this.intakeCode + " " + this.emergencyContact ;
    }
}
