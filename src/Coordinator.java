/*
  Adam Maser
  CSC 450
  Assignment 2

  1.26.2020
 */

/*
 -------
  Program Outline
 -------
  1) Load data from file
       a) store each band into Band object
  2) Sort Bands by name and set time (2 ArrayLists?)
       a) Tower of Hanoi algorithm?
  3) Method for binary Band Name search (searchBands())
       a) recursively search
  4) Method for linear Set Time search (searchSetTime())

 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Coordinator {

    public static void main(String[] args) {
        ArrayList<Band> bands;
        try {
            bands = getBands(new File("bands.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found, exiting program...");
            System.exit(2);
        }


    }

    public static boolean searchBands(ArrayList<Band> sortedBands, String query) {
        if (sortedBands.size() == 0) {
            return false;
        }
        if (sortedBands.get(sortedBands.size() / 2).getBandName().equals(query)) {
            return true;
        } else {
            // searchBands()
        }
        return true;
    }

    private static ArrayList<Band> getBands(File bandFile) throws FileNotFoundException {
        // init band ArrayList
        ArrayList<Band> bands = new ArrayList<>();

        // get Scanner
        Scanner readFile = new Scanner(bandFile);

        // loop through file and add to ArrayList
        while (readFile.hasNextLine()) {
            // split string into name and set time
            String[] bandInfo = readFile.nextLine().split("\\|");
            // create Band object and store in ArrayList
            bands.add(new Band(bandInfo[0], Float.parseFloat(bandInfo[1])));
        }

        return bands;
    }
}

class Band {
    private String bandName;
    private float setTime;

    public Band(String bandName, float setTime) {
        this.bandName = bandName;
        this.setTime = setTime;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public float getSetTime() {
        return setTime;
    }

    public void setSetTime(float setTime) {
        this.setTime = setTime;
    }
}
