package counselormgmtsystem;

import counselormgmtsystem.FileHandler;

public class Receptionist extends User {
    protected String contactNumber;
    protected String email;

    public Receptionist(String ID, String username, String password, String fullName, String contactNumber, String email) {
        super(ID, username, password, fullName);
        this.email = email;
        this.contactNumber = contactNumber; 
    }

    public String getReceptionistEmail() {
        return this.email;
    }

    public void setReceptionistEmail(String email) {
        this.email = email;
    }

    public String getReceptionistNumber() {
        return this.contactNumber;
    }

    public void setReceptionistNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    // manage student accounts

    public void createStudentAccount(String username, String password, String fullName, String intakeCode, String email, String contactNumber, String emergencyContact) {
        String newStudentID = FileHandler.generateUserID("STD", "student.txt");
        Student newStudent = new Student(newStudentID, username, password, fullName, intakeCode, email, contactNumber, emergencyContact);  
        FileHandler.userList.add(newStudent);
    }


    @Override
    public String toString() {
        return "User: " + this.ID
        
         + " " + this.username + " " + this.password + " " + this.fullName + " " + " " + this.contactNumber + " " + this.email + " ";
    }

}

