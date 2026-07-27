package counselormgmtsystem;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class FileHandler {
    public static ArrayList<User> userList = new ArrayList<>();
    public static ArrayList<Appointment> apptList = new ArrayList<>();
    public static ArrayList<Roster> rosterList = new ArrayList<>();
    public static ArrayList<ConsultationRecords> consultList = new ArrayList<>();

    public void loadDataFromFiles() {
        userList.clear();
        apptList.clear();
        rosterList.clear();
        consultList.clear();

        // Read Users File
        try (BufferedReader uReader = new BufferedReader(new FileReader("dataFiles/users.txt"))) {
            String line;
            while ((line = uReader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue; // Skip blank lines

                String[] userData = line.split("\\|");
                if (userData.length < 4) continue;

                // Read Admin File and Add to UserList
                if (userData[0].startsWith("ADM")) {
                    try (BufferedReader adminReader = new BufferedReader(new FileReader("dataFiles/admin.txt"))) {
                        String adminLine;
                        while ((adminLine = adminReader.readLine()) != null) {
                            String[] adminData = adminLine.trim().split("\\|");
                            if (adminData.length >= 4 && adminData[0].equals(userData[0])) {
                                Admin admin = new Admin(userData[0], userData[1], userData[2], userData[3], adminData[1], adminData[2], adminData[3]);
                                userList.add(admin);
                                break;
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Admin File can't be accessed.");
                    }
                }

                // Read Student File and Add to UserList
                else if (userData[0].startsWith("STD")) {
                    try (BufferedReader studentReader = new BufferedReader(new FileReader("dataFiles/student.txt"))) {
                        String studentLine;
                        while ((studentLine = studentReader.readLine()) != null) {
                            String[] studentData = studentLine.trim().split("\\|");
                            if (studentData.length >= 5 && studentData[0].equals(userData[0])) {
                                Student student = new Student(userData[0], userData[1], userData[2], userData[3], studentData[1], studentData[2], studentData[3], studentData[4]);
                                userList.add(student);
                                break;
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Student File can't be accessed.");
                    }
                }

                // Read Receptionist File and Add to UserList
                else if (userData[0].startsWith("REC")) {
                    try (BufferedReader recepReader = new BufferedReader(new FileReader("dataFiles/receptionist.txt"))) {
                        String recepLine;
                        while ((recepLine = recepReader.readLine()) != null) {
                            String[] recepData = recepLine.trim().split("\\|");
                            // FIXED: recepData[0].equals(userData[0]) instead of recepData[0]
                            if (recepData.length >= 3 && recepData[0].equals(userData[0])) {
                                Receptionist receptionist = new Receptionist(userData[0], userData[1], userData[2], userData[3], recepData[1], recepData[2]);
                                userList.add(receptionist);
                                break;
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Receptionist File can't be accessed.");
                    }
                }

                // Read Counselor File and Add to UserList
                else if (userData[0].startsWith("CNS")) {
                    try (BufferedReader counsReader = new BufferedReader(new FileReader("dataFiles/counselor.txt"))) {
                        String counsLine;
                        while ((counsLine = counsReader.readLine()) != null) {
                            String[] counsData = counsLine.trim().split("\\|");
                            // FIXED: counsData[0].equals(userData[0]) instead of counsData[0]
                            if (counsData.length >= 4 && counsData[0].equals(userData[0])) {
                                Counselor counselor = new Counselor(userData[0], userData[1], userData[2], userData[3], counsData[1], counsData[2], counsData[3]);
                                userList.add(counselor);
                                break;
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Counselor File can't be accessed.");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Users File can't be accessed.");
        }

        // Read Appointments File safely
        try (BufferedReader apptReader = new BufferedReader(new FileReader("dataFiles/appointments.txt"))) {
            String apptline;
            while ((apptline = apptReader.readLine()) != null) {
                apptline = apptline.trim();
                if (apptline.isEmpty()) continue;

                String[] appointmentData = apptline.split("\\|");
                // GUARD: Check length before accessing index 7
                if (appointmentData.length >= 8) {
                    Appointment appt = new Appointment(appointmentData[0], appointmentData[1], appointmentData[2], appointmentData[3], appointmentData[4], appointmentData[5], appointmentData[6], appointmentData[7]);
                    apptList.add(appt);
                } else {
                    System.out.println("Skipping invalid appointment row: " + apptline);
                }
            }
        } catch (IOException e) {
            System.out.println("Cant open the Appointment File");
        }

        // Read Rosters File safely
        try (BufferedReader rosterReader = new BufferedReader(new FileReader("dataFiles/rosters.txt"))) {
            String rosterLine;
            while ((rosterLine = rosterReader.readLine()) != null) {
                rosterLine = rosterLine.trim();
                if (rosterLine.isEmpty()) continue;

                String[] rosterData = rosterLine.split("\\|");
                if (rosterData.length >= 6) {
                    Roster roster = new Roster(rosterData[0], rosterData[1], rosterData[2], rosterData[3], rosterData[4], rosterData[5]);
                    rosterList.add(roster);
                }
            }
        } catch (IOException e) {
            System.out.println("Cant open the Roster File");
        }

        // Read Consultation Records File safely
        try (BufferedReader consultReader = new BufferedReader(new FileReader("dataFiles/consultationRecords.txt"))) {
            String consultLine;
            while ((consultLine = consultReader.readLine()) != null) {
                consultLine = consultLine.trim();
                if (consultLine.isEmpty()) continue;

                String[] consultData = consultLine.split("\\|");
                if (consultData.length >= 7) {
                    ConsultationRecords consult = new ConsultationRecords(consultData[0], consultData[1], consultData[2], consultData[3], consultData[4], consultData[5], consultData[6]);
                    consultList.add(consult);
                }
            }
        } catch (IOException e) {
            System.out.println("Cant open the ConsultationRecords File");
        }
    }

    public void saveDataToFiles() {
        ArrayList<String> adminList = new ArrayList<>(); 
        ArrayList<String> studentList = new ArrayList<>(); 
        ArrayList<String> counselorList = new ArrayList<>(); 
        ArrayList<String> receptionistList = new ArrayList<>(); 
        ArrayList<String> userDataList = new ArrayList<>();

        for (User user : userList) {
            String userText = user.ID + "|" + user.username + "|" + user.password + "|" + user.fullName;
            userDataList.add(userText);

            if (user instanceof Admin admin) {
                String adminText = admin.ID + "|" + admin.contactNumber + "|" + admin.email + "|" + admin.officeRoom; 
                adminList.add(adminText);
            } else if (user instanceof Student student) {
                String studentText = student.ID + "|" + student.intakeCode + "|" + student.contactNumber + "|" + student.email + "|" + student.emergencyContact; 
                studentList.add(studentText);
            } else if (user instanceof Counselor counselor) {
                String counselorText = counselor.ID + "|" + counselor.specialization + "|" + counselor.contactNumber + "|" + counselor.email; 
                counselorList.add(counselorText);
            } else if (user instanceof Receptionist receptionist) {
                String receptionistText = receptionist.ID + "|" + receptionist.contactNumber + "|" + receptionist.email; 
                receptionistList.add(receptionistText);
            }
        }

        // write into users file
        try (BufferedWriter userWriter = new BufferedWriter(new FileWriter("dataFiles/users.txt"))) {
            for (String user : userDataList) {
                userWriter.write(user);
                userWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Cannot open the users file");
        }

        try (BufferedWriter adminWriter = new BufferedWriter(new FileWriter("dataFiles/admin.txt"))) {
            for (String admin : adminList) {
                adminWriter.write(admin);
                adminWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Cannot open the admin file");
        }

        try (BufferedWriter counselorWriter = new BufferedWriter(new FileWriter("dataFiles/counselor.txt"))) {
            for (String counselor : counselorList) {
                counselorWriter.write(counselor);
                counselorWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Cannot open the counselor file");
        }

        try (BufferedWriter studentWriter = new BufferedWriter(new FileWriter("dataFiles/student.txt"))) {
            for (String student : studentList) {
                studentWriter.write(student);
                studentWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Cannot open the student file");
        }

        try (BufferedWriter receptionistWriter = new BufferedWriter(new FileWriter("dataFiles/receptionist.txt"))) {
            for (String receptionist : receptionistList) {
                receptionistWriter.write(receptionist);
                receptionistWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Cannot open the receptionist file");
        }

        // write into rosters
        ArrayList<String> listOfRosterText = new ArrayList<>(); 
        for (Roster roster : rosterList) {
            String rosterText = roster.rosterID + "|" + roster.counselorID + "|" + roster.date + "|" + roster.startTime + "|" + roster.endTime + "|" + roster.status;
            listOfRosterText.add(rosterText);
        }

        try (BufferedWriter rosterWriter = new BufferedWriter(new FileWriter("dataFiles/rosters.txt"))) {
            for (String roster : listOfRosterText) {
                rosterWriter.write(roster);
                rosterWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Cannot open the Roster file");
        }

        // write into appointments
        ArrayList<String> listOfApptText = new ArrayList<>(); 
        for (Appointment appt : apptList) {
            String apptText = appt.appointmentID + "|" + appt.studentID + "|" + appt.counselorID + "|" + appt.date + "|" + appt.time + "|" + appt.queueNumber + "|" + appt.bookingType + "|" + appt.status;
            listOfApptText.add(apptText);
        }

        try (BufferedWriter apptWriter = new BufferedWriter(new FileWriter("dataFiles/appointments.txt"))) {
            for (String appt : listOfApptText) {
                apptWriter.write(appt);
                apptWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Cannot open the Appointments file");
        }

        // write into consultation records
        ArrayList<String> listOfRecords = new ArrayList<>(); 
        for (ConsultationRecords record : consultList) {
            String consultText = record.recordID + "|" + record.appointmentID + "|" + record.studentID + "|" + record.counselorID + "|" + record.date + "|" + record.notes + "|" + record.recommendations;
            listOfRecords.add(consultText);
        }

        try (BufferedWriter recordWriter = new BufferedWriter(new FileWriter("dataFiles/consultationRecords.txt"))) {
            for (String record : listOfRecords) {
                recordWriter.write(record);
                recordWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Cannot open the ConsultationRecords file");
        }
    }

    public boolean checkLogin(String username, String password) {
        for (User user : userList) {
            if (user.username.equals(username) && user.password.equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static String generateUserID(String prefix, String filename) {
        int max_id = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] data = line.split("\\|"); 
                if (data.length > 0 && !data[0].isEmpty()) {
                    String fullId = data[0].trim();
                    String numericPart = fullId.replaceAll("\\D+", ""); 
                    if (!numericPart.isEmpty()) {
                        int current_id_num = Integer.parseInt(numericPart);
                        if (current_id_num > max_id) {
                            max_id = current_id_num;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, creating initial ID.");
        } catch (IOException e) {
            return "Error: Cannot read file";
        }

        int new_num = max_id + 1;
        return prefix + String.format("%02d", new_num);
    }    
}