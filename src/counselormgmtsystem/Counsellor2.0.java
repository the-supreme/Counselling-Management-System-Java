package counselormgmtsystem;


public class Counsellor extends User {
    private String specialization;
    private String phoneNumber;
    private String email;


    public Counsellor(String ID, String username, String password, String fullName,
                      String status, String specialization, String phoneNumber, String email) {
        super(ID, username, password, fullName, status);
        this.specialization = specialization;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


    public Counsellor(String ID, String username, String password, String fullName, String status) {
        super(ID, username, password, fullName, status);
        this.specialization = "General Counselling";
        this.phoneNumber = "Not Set";
        this.email = "Not Set";
    }


    @Override
    public void displayMenu() {
        System.out.println("===== Counsellor Menu =====");
        System.out.println("1. View Profile");
        System.out.println("2. View Assigned Appointments");
        System.out.println("3. Add Consultation Record");
        System.out.println("4. View Consultation Records");
        System.out.println("5. Logout");
    }

    public void viewProfile() {
        System.out.println("Counsellor ID: " + ID);
        System.out.println("Name: " + fullName);
        System.out.println("Username: " + username);
        System.out.println("Status: " + status);
        System.out.println("Specialization: " + specialization);
        System.out.println("Phone Number: " + phoneNumber);
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
        return ID + "|" + specialization + "|" + phoneNumber + "|" + email;
    }

    public void saveCounsellorToFile(String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(toFileLine());
            writer.newLine();
            writer.close();
            System.out.println("Counsellor details saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving counsellor details.");
        }
    }

    public static void viewAllCounsellorsFromFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            System.out.println("===== All Counsellors =====");
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");

                if (data.length == 4) {
                    System.out.println("Counsellor ID: " + data[0]);
                    System.out.println("Specialization: " + data[1]);
                    System.out.println("Phone Number: " + data[2]);
                    System.out.println("Email: " + data[3]);
                    System.out.println("------------------------------");
                }
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading counsellor details.");
        }
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
