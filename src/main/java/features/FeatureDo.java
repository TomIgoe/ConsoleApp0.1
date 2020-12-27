package features;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.csv.*;


public class FeatureDo {

// private Set<String> localAuthSet = new HashSet<>();

    //basic CSV READER Using Apache Commons
    public static void main(String[] args) {

        String filesPath = System.getProperty("user.home") + "\\Downloads\\datasets-foodsafety\\datasets-foodsafety";

        Path filesDirectory = Paths.get(filesPath);

        String filename = "";// "liverpool.csv";

        Path csvPath = filesDirectory.resolve(filename);


        //  System.out.print(csvPath);
        try {
            ArrayList<BusinessManager> businessManagers = parseFiles(csvPath.toString());
            //System.out.print(businessManagers.get(1).toString());

            for (var item : businessManagers) {
                // System.out.print(item.toString());

            }


        } catch (IOException e) {
            System.out.print("ERROR");
            e.printStackTrace();
        }
    }

    public static ArrayList<BusinessManager> parseFiles(String fileDirectory) throws IOException {
        ArrayList<BusinessManager> businessList = new ArrayList<>();

        try {
            var files = new File(fileDirectory).listFiles(x -> x.isFile() && x.getName().endsWith(".csv"));

            for (var file : files) {
                var reader = Files.newBufferedReader(file.toPath());
                var parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

                //   System.out.println(file.getName());
                for (CSVRecord row : parser) {
                    BusinessManager business = new BusinessManager(row);
                    businessList.add(business);

                }


            }

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }


        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        System.out.println("Select filter by number");
        System.out.println("1 Ratings equal to or higher");
        System.out.println("2 Ratings equal to or lower");
        System.out.println("3 Between to rating");
        System.out.println("4 Special value");
        int selection = Integer.parseInt(myObj.nextLine());
        ArrayList<BusinessManager> shortList = new ArrayList<BusinessManager>();

        // select filter

        if (selection == 1) {// 1 higher than
            System.out.println("Select a rating");

            float lowestRating = Float.parseFloat(myObj.nextLine());

            System.out.println("Ratings equal to or higher than " + lowestRating);
            //Collections.sort(businessList, new sortByRating());
            for (BusinessManager business : businessList) {
                float rate = (float) business.getRatingValue();
                if (rate >= lowestRating) {
                    shortList.add(business);
                }
            }
        }
         else if (selection == 2) {// 2 lower than


            System.out.println("Select a rating");

            float highestRating = Float.parseFloat(myObj.nextLine());

            System.out.println("Ratings equal to or higher than " + highestRating);
            //Collections.sort(businessList, new sortByRating());
            for (BusinessManager business : businessList) {
                float rate = (float) business.getRatingValue();
                if (rate <= highestRating) {
                    shortList.add(business);
                }
            }
        } else if (selection == 3) { // 3 in between
            System.out.println("Select a lowest");
            float lowerRating = Float.parseFloat(myObj.nextLine());
            System.out.println("Select a highest");
            float higherRating = Float.parseFloat(myObj.nextLine());
            for (BusinessManager business : businessList) {
                float rate = (float) business.getRatingValue();
                if (rate >= lowerRating && rate <= higherRating) {
                    shortList.add(business);
                }
            }
        } else if (selection == 4) { // 4 special value
            boolean success = false;
            while (!success) {
                System.out.println("Print values that are Exempt or Pending. Type e for Exempt, p for pending.");
                char input = (myObj.nextLine().toLowerCase().charAt(0));

                if (input == 'e') {
                    success = true;
                    for (BusinessManager business : businessList) {
                        if (business.exempt()) {
                            shortList.add(business);
                        }
                    }
                } else if (input == 'p') {
                    success = true;
                    for (BusinessManager business : businessList) {
                        if (business.pending()) {
                            shortList.add(business);
                        }
                    }
                } else {
                    System.out.println("please enter e for exempt, p for pending...");
                }
            }
        }
       //


        Collections.sort(shortList, new sortByRating());
        for (BusinessManager business : shortList) {
            System.out.println(
                    business.getRatingValue() + " " + business.getBusinessName()
            );
        }

        return businessList;
    }
}
