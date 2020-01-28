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
        Band[] bands = new Band[0];
        try {
            bands = getBands(new File("bands.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found, exiting program...");
            System.exit(2);
        }

        // because the band list is static, I sort these right away
        // if they weren't, you could dynamically do so
        // by passing the sort methods as arguments to search methods.


        // program control
        Scanner stdin = new Scanner(System.in);
        while (true) {
            System.out.println("Search by Band Name (1) or Set List (2):");
            int choice = stdin.nextInt();

            switch (choice) {
                case 1:
                    Band[] bandsByName = sortByName(bands);
                    break;
                case 2:
                    Band[] bandsBySetTime = sortBySetTime(bands);
                    break;
                case 99:
                    System.out.println("-------------\nExiting program...");
                    System.exit(1);
            }
        }
    }

    private static Band[] getBands(File bandFile) throws FileNotFoundException {
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

        Band[] bandsArray = new Band[bands.size()];

        for (int i = 0; i < bands.size(); i++) {
            bandsArray[i] = bands.get(i);
        }
        return bandsArray;
    }

    private static Band[] sortByName(Band[] bands) {
        int count = bands.length;
        Band temp;
        // loop through each band in list
        // basically, push lesser (alphabetically) names to the end of the line
        // loop through each element and eventually it will be sorted
        // complexity: O(n^2)
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                if (bands[i].getBandName().compareTo(bands[j].getBandName()) > 0) {
                    temp = bands[i];
                    bands[i] = bands[j];
                    bands[j] = temp;
                }
            }
        }
        return bands;
    }

    private static Band[] sortBySetTime(Band[] bands) {
        int count = bands.length;
        Band temp;
        // same concept as sorting by band name
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                if (bands[i].getSetTime() > bands[j].getSetTime()) {
                    temp = bands[i];
                    bands[i] = bands[j];
                    bands[j] = temp;
                }
            }
        }

        return bands;
    }

    private static boolean searchBands(ArrayList<Band> sortedBands, String query) {
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
