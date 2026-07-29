package counselormgmtsystem;
import counselormgmtsystem.adminApptStats;
import counselormgmtsystem.adminGenerateReports;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;


public class Admin extends User{
    String contactNumber;
    String email;
    String officeRoom;

    Admin(String ID, String username, String password, String fullName, String status, String contactNumber, String email, String officeRoom) {
        super(ID, username, password, fullName, status);
        this.contactNumber = contactNumber;
        this.email = email;
        this.officeRoom = officeRoom; 
    }

    // ---------- Getters / Setters ----------

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

    public String getOfficeRoom() {
        return officeRoom;
    }

    public void setOfficeRoom(String officeRoom) {
        this.officeRoom = officeRoom;
    }

    public String generateNextReceptionistID() {
        int max = 0;
        for (User u : FileHandler.userList) {
            if (u instanceof Receptionist && u.ID != null && u.ID.startsWith("REC")) {
                try {
                    int num = Integer.parseInt(u.ID.substring(3));
                    if (num > max) {
                        max = num;
                    }
                } catch (NumberFormatException ignored) {
                    // skip malformed IDs
                }
            }
        }
        return String.format("REC%03d", max + 1);
    }
    
    public String generateNextCounselorID() {
        int max = 0;
        for (User u : FileHandler.userList) {
            if (u instanceof Counselor && u.ID != null && u.ID.startsWith("COU")) {
                try {
                    int num = Integer.parseInt(u.ID.substring(3));
                    if (num > max) {
                        max = num;
                    }
                } catch (NumberFormatException ignored) {
                    // skip malformed IDs
                }
            }
        }
        return String.format("COU%03d", max + 1);
    }
    
    public void manageUserAccounts(ArrayList<User> userList, User targetUser, String action) {
        if (userList == null || targetUser == null || action == null) {
            System.out.println("Error: invalid arguments supplied to manageUserAccounts().");
            return;
        }

        switch (action.trim().toUpperCase()) {
            case "ADD":
                for (User u : userList) {
                    if (u.ID.equals(targetUser.ID)) {
                        System.out.println("Error: a user with ID " + targetUser.ID + " already exists.");
                        return;
                    }
                }
                userList.add(targetUser);
                System.out.println("--- Account " + targetUser.ID + " successfully added ---");
                break;

            case "UPDATE":
                for (int i = 0; i < userList.size(); i++) {
                    if (userList.get(i).ID.equals(targetUser.ID)) {
                        userList.set(i, targetUser);
                        System.out.println("--- Account " + targetUser.ID + " successfully updated ---");
                        return;
                    }
                }
                System.out.println("Error: user ID " + targetUser.ID + " not found.");
                break;

            case "DELETE":
                for (int i = 0; i < userList.size(); i++) {
                    if (userList.get(i).ID.equals(targetUser.ID)) {
                        userList.remove(i);
                        System.out.println("--- Account " + targetUser.ID + " successfully deleted ---");
                        return;
                    }
                }
                System.out.println("Error: user ID " + targetUser.ID + " not found.");
                break;

            default:
                System.out.println("Error: unsupported action \"" + action + "\". Use ADD, UPDATE or DELETE.");
        }
    
    }
    
    public String validateUser(String name, String contact, String email, String password, boolean isNewUser, String currentUserId) {
        //check if empty
        if (name.trim().isEmpty() || contact.trim().isEmpty() || email.trim().isEmpty()) {
            return "Full Name, Contact Number and Email are required.";
        }

        //check for format
        if (!email.trim().matches("^[\\w.+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            return "Please enter a valid email address.";
        }
        if (!contact.trim().matches("^[0-9+\\-\\s]{7,15}$")) {
            return "Please enter a valid contact number.";
        }

        //check 
        if (isNewUser || (password != null && !password.trim().isEmpty())) {
            if (password.length() < 8) {
                return "Password must be at least 8 characters long.";
            }
            if (!password.matches(".*\\d.*")) {
                return "Password must contain at least one number.";
            }
        }

        for (User u : FileHandler.userList) {
            
            // Skip the user we are currently editing
            if (currentUserId != null && u.ID.equals(currentUserId)) {
                continue; 
            }

            String existingEmail = "";
            String existingContact = "";

            // Safely cast to the specific user type to access their unique variables
            if (u instanceof Counselor c) {
                existingEmail = c.email;            // Use .getEmail() if private
                existingContact = c.contactNumber;  // Use .getContactNumber() if private
            } 
            else if (u instanceof Receptionist r) {
                existingEmail = r.email;
                existingContact = r.contactNumber;
            }
            // Note: If Students also have emails/contacts in your system, 
            // add another 'else if (u instanceof Student s)' right here!

            // Perform the duplicate checks using the extracted data
            if (!existingEmail.isEmpty() && existingEmail.equalsIgnoreCase(email.trim())) {
                return "A user with this email address already exists.";
            }
            if (!existingContact.isEmpty() && existingContact.equals(contact.trim())) {
                return "A user with this contact number already exists.";
            }
        }

        return null; // Null means valid!
    }

    public String validateRoster(String counselorId, String dateStr, String startStr, String endStr, String currentRosterId) {
        // 1. Empty Checks
        if (counselorId.trim().isEmpty() || dateStr.trim().isEmpty() || startStr.trim().isEmpty() || endStr.trim().isEmpty()) {
            return "Counselor ID, Date, Start Time and End Time are required.";
        }

        // --- NEW: 2. Counselor Existence Check ---
        boolean counselorExists = false;
        for (User u : FileHandler.userList) {
            // Assuming your ID variable is public (u.ID). If it's private, use u.getID()
            if (u instanceof Counselor && u.ID.equals(counselorId.trim())) {
                counselorExists = true;
                break; // We found them, no need to keep looping!
            }
        }
        if (!counselorExists) {
            return "The provided Counselor ID does not exist in the system.";
        }
        // -----------------------------------------

        LocalDate rosterDate;
        LocalTime startTime, endTime;

        // 3. Formatting Checks
        try {
            rosterDate = LocalDate.parse(dateStr.trim());
        } catch (DateTimeParseException e) {
            return "Date must be in yyyy-MM-dd format.";
        }

        try {
            startTime = LocalTime.parse(startStr.trim());
            endTime = LocalTime.parse(endStr.trim());
        } catch (DateTimeParseException e) {
            return "Times must be in HH:mm 24-hour format (e.g. 14:30).";
        }

        // 4. Logical Time Check
        if (!endTime.isAfter(startTime)) {
            return "End Time must be after Start Time.";
        }

        // 5. Working Hours Check
        LocalTime shiftStartLimit = LocalTime.of(8, 0);  // 08:00
        LocalTime shiftEndLimit = LocalTime.of(17, 0); // 17:00

        if (startTime.isBefore(shiftStartLimit) || endTime.isAfter(shiftEndLimit)) {
            return "Roster times must be within working hours (08:00 to 17:00).";
        }

        // 6. Reasonable Date Limits
        LocalDate today = LocalDate.now();
        if (rosterDate.isBefore(today)) {
            return "You cannot schedule a roster for a past date.";
        }
        if (rosterDate.isAfter(today.plusMonths(3))) {
            return "You cannot schedule a roster more than 3 months in advance.";
        }

        // 7. Overlap/Duplicate Check
        for (Roster r : FileHandler.rosterList) {
            if (currentRosterId != null && r.rosterID.equals(currentRosterId)) {
                continue; 
            }
            if (r.counselorID.equals(counselorId.trim()) && r.date.equals(dateStr.trim())) {
                LocalTime existingStart = LocalTime.parse(r.startTime);
                LocalTime existingEnd = LocalTime.parse(r.endTime);

                if (startTime.isBefore(existingEnd) && endTime.isAfter(existingStart)) {
                    return "This counselor already has an overlapping shift on this date (" + r.startTime + " - " + r.endTime + ").";
                }
            }
        }

        return null; 
    }
    
    //calculate appointment stats 
   public void updateApptStats(String dateFilter, adminApptStats frame) {
        int total = 0, completed = 0, cancelled = 0, pending = 0;
        int morningCount = 0, afternoonCount = 0, onlineCount = 0, walkInCount = 0;

        // Parallel lists for counselor stats
        java.util.ArrayList<String> counselorIds = new java.util.ArrayList<>();
        java.util.ArrayList<Integer> counts = new java.util.ArrayList<>();

        for (Appointment appt : FileHandler.apptList) {
            if (dateFilter != null && !dateFilter.trim().isEmpty()) {
                if (!appt.getDate().startsWith(dateFilter.trim())) continue;
            }

            total++;
            String status = appt.getStatus();
            if (status.equalsIgnoreCase("Completed")) completed++;
            else if (status.equalsIgnoreCase("Cancelled")) cancelled++;
            else pending++;

            if (appt.getTime().compareTo("12:00") < 0) morningCount++;
            else afternoonCount++;

            String type = appt.getBookingType();
            if (type.equalsIgnoreCase("Online")) onlineCount++;
            else if (type.toLowerCase().contains("walk")) walkInCount++;

            if (!status.equalsIgnoreCase("Cancelled")) {
                String cID = appt.getCounselorID();
                int index = counselorIds.indexOf(cID);
                if (index == -1) {
                    counselorIds.add(cID);
                    counts.add(1);
                } else {
                    counts.set(index, counts.get(index) + 1);
                }
            }
        }

        // Directly update the GUI fields using the frame reference
        frame.getApptBookedTf().setText(String.valueOf(total));
        frame.getApptCompletedTf().setText(String.valueOf(completed));
        frame.getApptCancelledTf().setText(String.valueOf(cancelled));
        frame.getApptPendingTf().setText(String.valueOf(pending));

        double rate = (completed + cancelled > 0) ? ((double) completed / (completed + cancelled)) * 100 : 0.0;
        frame.getCompletionRateTf().setText(String.format("%.1f%%", rate));

        frame.getMorningTf().setText(String.valueOf(morningCount));
        frame.getAfternoonTf().setText(String.valueOf(afternoonCount));
        frame.getOnlineTf().setText(String.valueOf(onlineCount));
        frame.getWalkInTf().setText(String.valueOf(walkInCount));

        // Update table
        frame.getModel().setRowCount(0);
        for (int i = 0; i < counselorIds.size(); i++) {
            frame.getModel().addRow(new Object[]{counselorIds.get(i), counts.get(i)});
        }
    }

   // --- Logic for Adding Roster ---
    public void manageRosters(ArrayList<Roster> rosterList, Roster targetRoster, String action) {
        if (rosterList == null || targetRoster == null || action == null) {
            System.out.println("Error: invalid arguments supplied to manageRosters().");
            return;
        }

        switch (action.trim().toUpperCase()) {
            case "ADD":
                // Logic to generate ID and add
                int max = 0;
                for (Roster r : rosterList) {
                    try {
                        int num = Integer.parseInt(r.getRosterID().substring(3));
                        if (num > max) max = num;
                    } catch (NumberFormatException ignored) {}
                }
                targetRoster.setRosterID(String.format("ROS%03d", max + 1));
                rosterList.add(targetRoster);
                System.out.println("--- Roster " + targetRoster.getRosterID() + " successfully added ---");
                break;

            case "UPDATE":
                for (int i = 0; i < rosterList.size(); i++) {
                    if (rosterList.get(i).getRosterID().equals(targetRoster.getRosterID())) {
                        rosterList.set(i, targetRoster);
                        System.out.println("--- Roster " + targetRoster.getRosterID() + " successfully updated ---");
                        return;
                    }
                }
                System.out.println("Error: Roster ID " + targetRoster.getRosterID() + " not found.");
                break;

            case "DELETE":
                for (int i = 0; i < rosterList.size(); i++) {
                    if (rosterList.get(i).getRosterID().equals(targetRoster.getRosterID())) {
                        rosterList.remove(i);
                        System.out.println("--- Roster " + targetRoster.getRosterID() + " successfully deleted ---");
                        return;
                    }
                }
                System.out.println("Error: Roster ID " + targetRoster.getRosterID() + " not found.");
                break;

            default:
                System.out.println("Error: unsupported action \"" + action + "\". Use ADD, UPDATE, or DELETE.");
        }
        
        // Save to file after any change
        new FileHandler().saveDataToFiles();
    }
    
// --- Logic for Report Generation ---
    public void generateReport(String category, String timeframe, String yearStr, String dateStr, adminGenerateReports frame) {
        String filterMatch = "";
        int targetQuarter = 0;
        
        try {
            if (yearStr.isEmpty()) throw new Exception("Please enter a valid year.");
            int year = Integer.parseInt(yearStr);
            if (year < 2024 || year > 2026) throw new Exception("Year must be between 2024 and 2026.");
            filterMatch = String.valueOf(year);
            
            if (!timeframe.equals("Yearly")) {
                if (dateStr.isEmpty()) throw new Exception("Please enter a value in the Month/Date field.");
                if (timeframe.equals("Monthly")) {
                    int month = Integer.parseInt(dateStr);
                    if (month < 1 || month > 12) throw new Exception("Month must be 1-12.");
                    filterMatch += "-" + String.format("%02d", month);
                } else if (timeframe.equals("Quarterly")) {
                    targetQuarter = Integer.parseInt(dateStr.toUpperCase().replace("Q", ""));
                    if (targetQuarter < 1 || targetQuarter > 4) throw new Exception("Quarter must be 1-4.");
                } else if (timeframe.equals("Daily")) {
                    String[] parts = dateStr.split("-");
                    filterMatch += "-" + String.format("%02d", Integer.parseInt(parts[0])) + "-" + String.format("%02d", Integer.parseInt(parts[1]));
                }
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(frame, e.getMessage(), "Input Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

// --- DATA GENERATION ---
        frame.getModel().setRowCount(0); // Use getModel()

        if (category.equals("Appointments")) {
            int total = 0, completed = 0, pending = 0, cancelled = 0;
            for (Appointment appt : FileHandler.apptList) {
                if (checkDateMatch(appt.getDate(), filterMatch, timeframe, targetQuarter)) {
                    total++;
                    if (appt.getStatus().equalsIgnoreCase("Completed")) completed++;
                    else if (appt.getStatus().equalsIgnoreCase("Cancelled")) cancelled++;
                    else pending++;
                    
                    frame.getModel().addRow(new Object[]{appt.getAppointmentID(), appt.getStudentID(), appt.getCounselorID(), appt.getDate(), appt.getTime(), appt.getBookingType(), appt.getQueueNumber(), appt.getStatus()});
                }
            }
            // Use the getters for the text fields!
            frame.getTotalTf().setText(String.valueOf(total));
            frame.getCompletedTf().setText(String.valueOf(completed));
            frame.getPendingTf().setText(String.valueOf(pending));
            frame.getCancelledTf().setText(String.valueOf(cancelled));
            
        } else if (category.equals("Counselor Workload")) {
            java.util.ArrayList<String> counselorIds = new java.util.ArrayList<>();
            java.util.ArrayList<Integer> counts = new java.util.ArrayList<>();

            for (Appointment appt : FileHandler.apptList) {
                if (checkDateMatch(appt.getDate(), filterMatch, timeframe, targetQuarter) && !appt.getStatus().equalsIgnoreCase("Cancelled")) {
                    String cID = appt.getCounselorID();
                    int index = counselorIds.indexOf(cID);
                    if (index == -1) {
                        counselorIds.add(cID);
                        counts.add(1);
                    } else {
                        counts.set(index, counts.get(index) + 1);
                    }
                }
            }
            
            for (int i = 0; i < counselorIds.size(); i++) {
                frame.getModel().addRow(new Object[]{counselorIds.get(i), counts.get(i)});
            }
            frame.getTotalTf().setText(String.valueOf(FileHandler.userList.size())); 
            frame.getCompletedTf().setText(String.valueOf(counselorIds.size()));
            
        } else if (category.equals("Consultation Records")) {
            int total = 0;
            java.util.ArrayList<String> uniqueStudents = new java.util.ArrayList<>();

            for (ConsultationRecords record : FileHandler.consultList) {
                if (checkDateMatch(record.getDate(), filterMatch, timeframe, targetQuarter)) {
                    total++;
                    if (!uniqueStudents.contains(record.getStudentID())) {
                        uniqueStudents.add(record.getStudentID());
                    }
                    frame.getModel().addRow(new Object[]{record.getRecordID(), record.getAppointmentID(), record.getStudentID(), record.getCounselorID(), record.getDate(), record.getNotes()});
                }
            }
            frame.getTotalTf().setText(String.valueOf(total));
            frame.getCompletedTf().setText(String.valueOf(uniqueStudents.size()));
        }
        
        if (frame.getModel().getRowCount() == 0) {
            javax.swing.JOptionPane.showMessageDialog(frame, "No records found for this timeframe.", "Report Result", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Helper method for date filtering
    public boolean checkDateMatch(String recordDate, String filterMatch, String timeframe, int targetQuarter) {
        if (timeframe.equals("Quarterly")) {
            if (!recordDate.startsWith(filterMatch)) return false;
            try {
                int month = Integer.parseInt(recordDate.split("-")[1]);
                int recordQuarter = (month - 1) / 3 + 1; 
                return recordQuarter == targetQuarter;
            } catch (Exception e) {
                return false;
            }
        } else {
            return recordDate.startsWith(filterMatch);
        }
    }
    
    // --- Logic for Cancelling Appointments ---
    public String cancelAppointment(Appointment appt) {
        if (appt == null) {
            return "No appointment selected.";
        }
        
        if (appt.getStatus().equalsIgnoreCase("Cancelled")) {
            return "This appointment is already cancelled.";
        }

        // 1. Cancel the appointment
        appt.setStatus("Cancelled");

        // 2. Free up the corresponding roster slot
        for (Roster r : FileHandler.rosterList) {
            if (r.getCounselorID().equals(appt.getCounselorID()) &&
                r.getDate().equals(appt.getDate()) &&
                r.getStartTime().equals(appt.getTime())) {
                
                r.setStatus("Available");
                break; // Found it, no need to keep looping
            }
        }

        // 3. Save changes
        new FileHandler().saveDataToFiles();
        
        return null; // Return null to indicate success
    }
    @Override
    public String toString() {
        return "User: " + this.ID
         + " " + this.username + " " + this.password + " " + this.fullName + " " + this.status + " " + this.contactNumber + " " + this.email + " " + this.officeRoom ;
    }

}
