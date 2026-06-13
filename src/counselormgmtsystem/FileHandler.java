package counselormgmtsystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class FileHandler {
    public static ArrayList<User> userList = new ArrayList<>();

    // public static ArrayList<Appointment> apptList = new ArrayList<>();
    // public static ArrayList<Roster> rosterList = new ArrayList<>();
    // public static ArrayList<ConsultationRecord> recordList = new ArrayList<>();

    // methods

    // in this method, we load all the data from user file, admin file, student file, and counselor file
    // then we load data from other appointment, rosters, consultationrecords

    public void loadDataFromFiles() {
         try (BufferedReader uReader = new BufferedReader(new FileReader("dataFiles/users.txt"))) {
            System.out.println("Users File successfully opened!");
            String line;
            while ((line = uReader.readLine()) != null) {
                String[] userData = line.trim().split("\\|");

                // if ADMIN DATA
                if (userData[0].startsWith("ADM")) {
                    try (BufferedReader adminReader = new BufferedReader(new FileReader("dataFiles/admin.txt"))) {
                        String adminLine;
                        while ((adminLine = adminReader.readLine()) != null) {
                            String[] adminData = adminLine.trim().split("\\|");
                            if (adminData[0].equals(userData[0])) {
                                Admin admin = new Admin(userData[0], userData[1], userData[2], userData[3], userData[4], adminData[1], adminData[2], adminData[3]);
                                userList.add(admin);
                                break;
                            }
                        }
                    }
                    catch (IOException e) {
                        System.out.println("Admin File can't be accessed.");
                    }

                }

                // if STUDENT DATA
                if (userData[0].startsWith("STD")) {
                    try (BufferedReader studentReader = new BufferedReader(new FileReader("dataFiles/student.txt"))) {
                        String studentLine;
                        while ((studentLine = studentReader.readLine()) != null) {
                            String[] studentData = studentLine.trim().split("\\|");
                            if (studentData[0].equals(userData[0])) {
                                Student student = new Student(userData[0], userData[1], userData[2], userData[3], userData[4], studentData[1], studentData[2], studentData[3]);
                                userList.add(student);
                                break;
                            }
                        }
                    }
                    catch (IOException e) {
                        System.out.println("Student File can't be accessed.");
                    }

                }

                if (userData[0].startsWith("REC")) {
                    try (BufferedReader recepReader = new BufferedReader(new FileReader("dataFiles/receptionist.txt"))) {
                        String recepLine;
                        while ((recepLine = recepReader.readLine()) != null) {
                            String[] recepData = recepLine.trim().split("\\|");
                            if (recepData[0].equals(recepData[0])) {
                                Receptionist receptionist = new Receptionist(userData[0], userData[1], userData[2], userData[3], userData[4], recepData[1], recepData[2]);
                                userList.add(receptionist);
                                break;
                            }
                        }
                    }
                    catch (IOException e) {
                        System.out.println("Receptionist File can't be accessed.");
                    }

                }

                if (userData[0].startsWith("CNS")) {
                    try (BufferedReader counsReader = new BufferedReader(new FileReader("dataFiles/counselor.txt"))) {
                        String counsLine;
                        while ((counsLine = counsReader.readLine()) != null) {
                            String[] counsData = counsLine.trim().split("\\|");
                            if (counsData[0].equals(counsData[0])) {
                                Counselor counselor = new Counselor(userData[0], userData[1], userData[2], userData[3], userData[4], counsData[1], counsData[2], counsData[3]);
                                userList.add(counselor);
                                break;
                            }
                        }
                    }
                    catch (IOException e) {
                        System.out.println("Receptionist File can't be accessed.");
                    }

                }

            }
        }
        catch (IOException e) {
            System.out.println("Counselor File can't be accessed.");
        }

        for (User user: userList) {
            System.out.println(user);
        }
    }

    // Static saveDataToFiles(String fileName) {

    // }

    // checkLogin(String username, String password) {

    // }
}
