package features;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.Float.NEGATIVE_INFINITY;
import static java.lang.Float.POSITIVE_INFINITY;


public class FeatureF {

// private Set<String> localAuthSet = new HashSet<>();

    //basic CSV READER Using Apache Commons
    public static void main(String[] args) {

        String filesPath = System.getProperty("user.home") + "\\Downloads\\datasets-foodsafety\\datasets-foodsafety";

        Path filesDirectory = Paths.get(filesPath);

        String filename = "";// "liverpool.csv";

        Path csvPath = filesDirectory.resolve(filename);

        try {
            ArrayList<BusinessManager> businessManagers = parseFiles(csvPath.toString());
        } catch (IOException e) {
            System.out.print("ERROR");
            e.printStackTrace();
        }
    }

    public static ArrayList<BusinessManager> parseFiles(String fileDirectory) throws IOException {
        Map<String, ArrayList<BusinessManager>> businessMap = new TreeMap<String, ArrayList<BusinessManager>>();
        try {
            var files = new File(fileDirectory).listFiles(x -> x.isFile() && x.getName().endsWith(".csv"));

            for (var file : files) {
                var reader = Files.newBufferedReader(file.toPath());
                var parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

                //   System.out.println(file.getName());
                for (CSVRecord row : parser) {

                    String busName = row.get("BusinessName").toLowerCase().trim();

                    // create array list for a business if it does not exist already
                    if (businessMap.get(busName) == null) {
                        businessMap.put(busName, new ArrayList<BusinessManager>());
                    }
                    businessMap.get(busName).add(new BusinessManager(row));
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
        System.out.println("Enter business name");
        String busName2 = (myObj.nextLine());

        if (businessMap.get(busName2) != null) {
            System.out.println("worked:" + busName2);
            System.out.println(businessMap.size());
            //Collections.sort(businessMap.get(busName2), new sortByRatingAndDate());
            int noOfReviews = 0;
            float lowestRating = POSITIVE_INFINITY;
            float higestRating = NEGATIVE_INFINITY;
            float total = 0;

            for (BusinessManager business : businessMap.get(busName2)) {
                if (business.exempt() || business.pending()) {
                    continue;
                }
                ++noOfReviews;

                float rating = (float) business.getRatingValue();
                lowestRating = rating < lowestRating ? rating : lowestRating;
                higestRating = rating > higestRating ? rating : higestRating;
                total += rating;
            }
            System.out.println("noOfReviews " + noOfReviews + " Lowest " + lowestRating + " highest " + higestRating + " average " + ((noOfReviews != 0) ? total / noOfReviews : "N/A"));
        }

        return businessMap.get(busName2);
    }
}
