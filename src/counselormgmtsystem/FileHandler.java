package counselormgmtsystem;
import ArrayList;
import Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class FileHandler {
    public static ArrayList<User> userList = new ArrayList<>();
    public static ArrayList<Appointment> apptList = new ArrayList<>();
    public static ArrayList<Roster> rosterList = new ArrayList<>();
    public static ArrayList<ConsultationRecord> recordList = new ArrayList<>();

    // methods
    public void loadDataFromFiles(String fileName) {
         try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // for (String data: line) {

                // }

            }
         }
         catch (IOException e) {
            System.out.println("File can't be accessed.");
         }
    }

    Static saveDataToFiles(String fileName) {

    }

    checkLogin(String username, String password) {

    }
}
