package MainPack;

import java.io.*;
import java.util.Scanner;

/**
 * Created by V on 30/11/2014.
 */
class FileHandler {


    public void setAutoSettings(boolean toggleOption) {
        File file = new File("config.cfg");
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.valueOf(toggleOption));
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createConfigFile() {
        File file = new File("config.cfg");
        if (!file.exists()) {
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(String.valueOf("true"));
                bw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean getAutoSetting() {
        createConfigFile();
        try {
            Scanner in = new Scanner(new FileReader("config.cfg"));
            if (in.next().equals("false")) {
                return false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return true;
    }

}
