/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package counselormgmtsystem;

/**
 *
 * @author tzhen
 */

public class Counselor extends User {
    String specialization;
    String contactNumber;
    String email;


    public Counselor(String ID, String username, String password, String fullName,
                     String specialization, String contactNumber, String email) {
        super(ID, username, password, fullName);
        this.specialization = specialization;
        this.contactNumber = contactNumber;
        this.email = email;
    }


    public Counselor(String ID, String username, String password, String fullName) {
        super(ID, username, password, fullName);
        this.specialization = "General Counseling";
        this.contactNumber = "Not Set";
        this.email = "Not Set";
    }

  
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
        System.out.println("Specialization: " + specialization);
        System.out.println("Contact Number: " + contactNumber);
        System.out.println("Email: " + email);
    }


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
