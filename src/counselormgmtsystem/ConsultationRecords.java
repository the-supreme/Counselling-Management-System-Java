public class ConsultationRecords {
    private String recordID;
    private String appointmentID;
    private String studentID;
    private String counsellorID;
    private String consultationDate;
    private String notes;
    private String recommendation;


    public ConsultationRecords(String recordID, String appointmentID, String studentID,
                               String counsellorID, String consultationDate,
                               String notes, String recommendation) {
        this.recordID = recordID;
        this.appointmentID = appointmentID;
        this.studentID = studentID;
        this.counsellorID = counsellorID;
        this.consultationDate = consultationDate;
        this.notes = notes;
        this.recommendation = recommendation;
    }

  
    public void displayRecord() {
        System.out.println("===== Consultation Record =====");
        System.out.println("Record ID: " + recordID);
        System.out.println("Appointment ID: " + appointmentID);
        System.out.println("Student ID: " + studentID);
        System.out.println("Counsellor ID: " + counsellorID);
        System.out.println("Date: " + consultationDate);
        System.out.println("Notes: " + notes);
        System.out.println("Recommendation: " + recommendation);
    }

    
    public String toFileLine() {
        return recordID + "|" + appointmentID + "|" + studentID + "|" + counsellorID + "|"
                + consultationDate + "|" + notes + "|" + recommendation;
    }

  
    public void saveRecordToFile(String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(toFileLine());
            writer.newLine();
            writer.close();
            System.out.println("Consultation record saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving consultation record.");
        }
    }

    
    public static void viewAllRecordsFromFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            System.out.println("===== All Consultation Records =====");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading consultation records.");
        }
    }

   
    public static void viewRecordsByCounsellor(String fileName, String counsellorID) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            System.out.println("===== Consultation Records for " + counsellorID + " =====");
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");

                if (data.length == 7 && data[3].equals(counsellorID)) {
                    System.out.println("Record ID: " + data[0]);
                    System.out.println("Appointment ID: " + data[1]);
                    System.out.println("Student ID: " + data[2]);
                    System.out.println("Date: " + data[4]);
                    System.out.println("Notes: " + data[5]);
                    System.out.println("Recommendation: " + data[6]);
                    System.out.println("------------------------------");
                }
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading consultation records.");
        }
    }

   
    public String getRecordID() {
        return recordID;
    }

    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getCounsellorID() {
        return counsellorID;
    }

    public void setCounsellorID(String counsellorID) {
        this.counsellorID = counsellorID;
    }

    public String getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(String consultationDate) {
        this.consultationDate = consultationDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}
