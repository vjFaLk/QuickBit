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

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

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

    public void setAutoSettings() {

    }

    private void readSettings() {
        try {
            Scanner in = new Scanner(new FileReader("DeleteMeNot.dat"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean wordExists(String torrentName) {
        try {
            Scanner in = new Scanner(new FileReader("Data.dat"));
            while (in.hasNext()) {
                if (in.next().equalsIgnoreCase(torrentName)) {
                    System.out.println("IT HAS ITS");
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
            ArrayList<String> torrentNameList = new ArrayList<>();
            Scanner in = new Scanner(new FileReader("Data.dat"));

            while (in.hasNext()) {
                torrentNameList.add(in.next());
            }
            return torrentNameList;
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        return null;
    }

}
