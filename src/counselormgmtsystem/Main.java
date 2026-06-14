package counselormgmtsystem;

import java.util.Arrays;


public class Main {
    public static void main(String[] args) {

        // read the files and initialize
        FileHandler fh = new FileHandler();
        fh.loadDataFromFiles();
        Admin admin1 = new Admin("ADM005", "admin_abu", "@bu@li", "Abu Ali", "Active", "012-23413131", "abu.jenkins@apu.edu.my", "Block D, Level 4, Room 401");
        FileHandler.userList.add(admin1);
        for (User user: FileHandler.userList) {
            System.out.println(user);
        }

        fh.saveDataToFiles();

    }
}
