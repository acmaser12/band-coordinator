/*
  Adam Maser
  CSC 450
  Assignment 2

  1.26.2020
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Coordinator {

    public static void main(String[] args) {

        // read bands from file and store into Band array
        Band[] bands = new Band[0];
        try {
            bands = getBands(new File("bands.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found, exiting program...");
            System.exit(2);
        }

        // output program info
        System.out.println("Submitted by: Adam Maser - masera1@csp.edu");
        System.out.println("I certify that this is my own work");

        // program control
        Scanner stdin = new Scanner(System.in);
        while (true) {
            System.out.println("\nSearch by Band Name (1) or Set List (2):");
            int choice = Integer.parseInt(stdin.nextLine());

            switch (choice) {
                case 1:
                    // sort bands from file by band name
                    Band[] bandsByName = sortByName(bands);
                    // prompt user and gather input
                    System.out.println("Enter Band Name you are looking for:");
                    String query = stdin.nextLine();
                    // call search method
                    searchByBandName(bandsByName, query);
                    break;
                case 2:
                    // sort bands from file by set time
                    Band[] bandsBySetTime = sortBySetTime(bands);
                    // prompt user and gather input
                    System.out.println("Enter the Set time you are looking for:");
                    float setQuery = Float.parseFloat(stdin.nextLine());
                    // call search method
                    searchBySetTime(bandsBySetTime, setQuery);
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
        // complexity: O(n^2) -- (kinda slow)
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

    private static void searchByBandName(Band[] sortedBands, String query) {
        if (sortedBands.length > 1) {
            // get middle index
            int mid = sortedBands.length / 2;

            // string to string comparison and store result
            int comparison = query.compareToIgnoreCase(sortedBands[mid].getBandName());

            // if equal, return index of match
            if (comparison == 0) {
                // store result
                Band result = sortedBands[mid];
                //output to user
                System.out.println("Bandname is: " + result.getBandName().toLowerCase());
                System.out.println("Band found is: " + result.getBandName() + " has a set time of " +
                        result.getSetTime() + " minutes");
            } else if (comparison > 0) {
                // if greater than 0, recursively call method with larger half of array
                searchByBandName(Arrays.copyOfRange(sortedBands, mid, sortedBands.length), query);
            } else {
                // else, recursively call method with smaller half of array
                searchByBandName(Arrays.copyOfRange(sortedBands, 0, mid), query);
            }
        } else {
            // if no match, output to user
            System.out.println("Bandname is: " + query);
            System.out.println("Band [" + query + "] was not found");
        }
    }

    private static void searchBySetTime(Band[] sortedBands, float query) {
        for (int i = 1; i <= sortedBands.length; i++) {
            // iterate through bands until query is less than current band
            // then, test if it is closer to element behind or current
            if (query < sortedBands[i].getSetTime()) {
                float lastDiff = query - sortedBands[i-1].getSetTime();
                float nextDiff = sortedBands[i].getSetTime() - query;
                if (lastDiff < nextDiff) {
                    System.out.println("Band with the closest time is: " + sortedBands[i-1].getBandName() +
                            " has a set time of " + sortedBands[i-1].getSetTime() + " minutes");
                } else {
                    System.out.println("Band with the closest time is: " + sortedBands[i].getBandName() +
                            " has a set time of " + sortedBands[i].getSetTime() + " minutes");
                }
                break;
            }
        }
    }
}

class Band {
    private String bandName;
    private float setTime;

    Band(String bandName, float setTime) {
        this.bandName = bandName;
        this.setTime = setTime;
    }

    String getBandName() {
        return bandName;
    }

    float getSetTime() {
        return setTime;
    }
}
