/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package counselormgmtsystem;

/**
 *
 * @author tzhen
 */
// Counselor is a child class of User.
// Username, password, full name and status are inherited from User.
public class Counselor extends User {
    String specialization;
    String contactNumber;
    String email;

    // Constructor used by FileHandler.
    // Data comes from users.txt and counselor.txt.
    public Counselor(String ID, String username, String password, String fullName, String status,
                     String specialization, String contactNumber, String email) {
        super(ID, username, password, fullName, status);
        this.specialization = specialization;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    // Constructor overloading.
    // This is useful if counselor extra details are not available yet.
    public Counselor(String ID, String username, String password, String fullName, String status) {
        super(ID, username, password, fullName, status);
        this.specialization = "General Counseling";
        this.contactNumber = "Not Set";
        this.email = "Not Set";
    }

    // Method overriding from User class.
    @Override
    public void displayMenu() {
        System.out.println("===== Counselor Menu =====");
        System.out.println("1. View Profile");
        System.out.println("2. View Assigned Appointments");
        System.out.println("3. Add Consultation Record");
        System.out.println("4. View Consultation Records");
        System.out.println("5. Logout");
    }

    public void viewProfile() {
        System.out.println("Counselor ID: " + ID);
        System.out.println("Name: " + fullName);
        System.out.println("Username: " + username);
        System.out.println("Status: " + status);
        System.out.println("Specialization: " + specialization);
        System.out.println("Contact Number: " + contactNumber);
        System.out.println("Email: " + email);
    }

    // This method uses an array, which matches the assignment requirement.
    public void viewAssignedAppointments(String[] appointments) {
        System.out.println("===== Assigned Appointments =====");

        if (appointments.length == 0) {
            System.out.println("No appointment assigned.");
        } else {
            for (int i = 0; i < appointments.length; i++) {
                System.out.println((i + 1) + ". " + appointments[i]);
            }
        }
    }

    // This follows counselor.txt format:
    // counselorID|specialization|contactNumber|email
    public String toFileLine() {
        return ID + "|" + specialization + "|" + contactNumber + "|" + email;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
