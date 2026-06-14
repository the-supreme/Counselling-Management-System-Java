package counselormgmtsystem;

public class Receptionist extends User {
    String contactNumber;
    String email;

    public Receptionist(String ID, String username, String password, String fullName, String status, String contactNumber, String email) {
        super(ID, username, password, fullName, status);
        this.email = email;
        this.contactNumber = contactNumber; 
    }

    @Override
    public String toString() {
        return "User: " + this.ID
        
         + " " + this.username + " " + this.password + " " + this.fullName + " " + this.status + " " + this.contactNumber + " " + this.email + " ";
    }

}

