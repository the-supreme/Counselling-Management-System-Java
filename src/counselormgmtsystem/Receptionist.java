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

    public boolean createStudentAccount(String username, String password, String fullName, String intakeCode, String email, String contactNumber, String emergencyContact) {
        
        String newStudentID = FileHandler.generateUserID("STD", FileHandler.userList, u -> u.getID());
        Student newStudent = new Student(newStudentID, username, password, fullName, intakeCode, email, contactNumber, emergencyContact);  
        FileHandler.userList.add(newStudent);
        return true;
    }

    public boolean updateStudentAccount(String studentID, String username, String password, String fullName, String intakeCode, String email, String contactNumber, String emergencyContact) {
        for (User u : FileHandler.userList) {
            if (u.getID().equals(studentID) && u instanceof Student s) {
                s.setUsername(username);
                s.setpassword(password);
                s.setFullName(fullName);       // Adjust casing to match your Student getters/setters
                s.setintakeCode(intakeCode);
                s.setStudentEmail(email);
                s.setContactNumber(contactNumber);
                s.getEmergencyContact();      // Setters as appropriate
                return true;
            }
        }
        return false; // Student ID not found
    }
    
    public boolean deleteStudentAccount(String studentID) {
        return FileHandler.userList.removeIf(u -> u.getID().equals(studentID));
    }

    @Override
    public String toString() {
        return "User: " + this.ID
        
         + " " + this.username + " " + this.password + " " + this.fullName + " " + " " + this.contactNumber + " " + this.email + " ";
    }

}

