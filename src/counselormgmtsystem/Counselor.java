package counselormgmtsystem;

public class Counselor extends User{
    String specialization;
    String contactNumber;
    String email;

    public Counselor(String ID, String username, String password, String fullName, String specialization, String contactNumber, String email) {
        super(ID, username, password, fullName);
        this.specialization = specialization;
        this.email = email;
        this.contactNumber = contactNumber; 
    }

    @Override
    public String toString() {
        return "User: " + this.ID
         + " " + this.username + " " + this.password + " " + this.fullName + " " + " " + this.contactNumber + " " + this.email + " " + this.specialization ;
    }

}
