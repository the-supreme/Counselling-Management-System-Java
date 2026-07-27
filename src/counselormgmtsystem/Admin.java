package counselormgmtsystem;

public class Admin extends User{
    String contactNumber;
    String email;
    String officeRoom;

    Admin(String ID, String username, String password, String fullName, String contactNumber, String email, String officeRoom) {
        super(ID, username, password, fullName);
        this.contactNumber = contactNumber;
        this.email = email;
        this.officeRoom = officeRoom; 
    }

    @Override
    public String toString() {
        return "User: " + this.ID
         + " " + this.username + " " + this.password + " " + this.fullName + " " + " " + this.contactNumber + " " + this.email + " " + this.officeRoom ;
    }
}
