package MainPack;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by V on 30/11/2014.
 */
class FileHandler {



    public void addToAutoCompleteList(String torrentName) {
        try {
            File file = new File("Data.dat");
            if (!wordExists(torrentName)) {
                FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(torrentName + "\n");
                bw.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetAll() {
        
    }

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

    private boolean wordExists(String torrentName) {
        try {
            Scanner in = new Scanner(new FileReader("Data.dat"));
            while (in.hasNextLine()) {
                if (in.nextLine().equalsIgnoreCase(torrentName)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }


    public ArrayList<String> getTorrentList() {
        try {
            File file = new File("Data.dat");
            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            ArrayList<String> torrentNameList = new ArrayList<>();
            Scanner in = new Scanner(new FileReader("Data.dat"));

            while (in.hasNextLine()) {
                torrentNameList.add(in.nextLine());
            }
            return torrentNameList;
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
