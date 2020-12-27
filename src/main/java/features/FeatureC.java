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


        import java.util.ArrayList;
        import java.util.HashSet;
        import java.util.Set;



public class FeatureC {


    //basic CSV READER Using Apache Commons
    public static void main(String[] args) {

        String filesPath = System.getProperty("user.home") + "\\Downloads\\datasets-foodsafety\\datasets-foodsafety";

        Path filesDirectory = Paths.get(filesPath);

        String filename = "";// will use to find files in the directory

        Path csvPath = filesDirectory.resolve(filename);







        try {
            ArrayList<BusinessManager> businessManagers = parseFiles(csvPath.toString());
            //create ArrayList called Business Managers where we are storing the parsed files from our directory.




        } catch (IOException e) {
            System.out.print("ERROR");
            e.printStackTrace();
        }
    }

    //
    private static final String EXEMPT = "Exempt";
    private static final String AWAITING = "AwaitingInspection";
// strings to be used later for handling rating data.

    public static ArrayList<BusinessManager> parseFiles (String fileDirectory) throws IOException {
        ArrayList<BusinessManager> businessList = new ArrayList<>();//ArrayList called BusinessList
        Set<String> localAuthSet = new HashSet<>();//HashSet called LocalAuthSet
        Set<String> busNameSet = new HashSet<>();//HashSet named busNameSet

        try {
            var files = new File(fileDirectory).listFiles(x -> x.isFile() && x.getName().endsWith(".csv"));
            var businessNameList = new ArrayList<String>();
            var authorityNameList = new ArrayList<String>();

            for (var file : files) {
                var reader = Files.newBufferedReader(file.toPath());
                var parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            //find any files where the filename ends with .csv within the directory
                // parse each of the files
                for (var row : parser) {

                    var rating = row.get("RatingValue");

                    String dataFromSheet = row.get("Geocode/Latitude");


                    float realValue = 0.0f;
                    if (dataFromSheet != null && dataFromSheet.length() != 0) {
                        realValue = Float.parseFloat(dataFromSheet);
                    }


                    // Get the value from the row
                    // If the value is null or is a string of length 0
                    //        set the value to 0.0
                    // else
                    ////// use the value

                    String localAuth = row.get("LocalAuthorityName");
                    localAuthSet.add(localAuth);

                    String busName = row.get("BusinessName");
                    busNameSet.add(busName);

                    String ratingValue = row.get("RatingValue");
                    busNameSet.add(ratingValue);

                    var business = new BusinessManager(
                            localAuth,
                            busName,
                            row.get("BusinessType"),
                            row.get("AddressLine1"),
                            (row.get("Geocode/Latitude") == null && row.get("Geocode/Latitude").isEmpty()) ? Float.parseFloat(row.get("Geocode/Latitude")): 0.0f ,
                            (row.get("Geocode/Longitude") == null && row.get("Geocode/Longitude").isEmpty()) ? Float.parseFloat(row.get("Geocode/Longitude")) : 0.0f,
                            (row.get("RatingValue") != null
                                    && !row.get("RatingValue").isEmpty()
                            &&ratingValue.matches("\\d")
                            ) ? Byte.parseByte(row.get("RatingValue")) : 0,
                            rating.equals(EXEMPT),
                            rating.equals(AWAITING),
                            row.get("RatingDate"),
                            (short) ((row.get("Scores/Hygiene") != null && !row.get("Scores/Hygiene").isEmpty()) ? Byte.parseByte(row.get("Scores/Hygiene")) : (short) 0.0f),
                            (short) ((row.get("Scores/Structural") != null && !row.get("Scores/Structural").isEmpty()) ? Byte.parseByte(row.get("Scores/Structural")) : (short) 0.0f),
                            (short) ((row.get("Scores/ConfidenceInManagement") != null && !row.get("Scores/ConfidenceInManagement").isEmpty()) ? Byte.parseByte(row.get("Scores/ConfidenceInManagement")) : (short) 0.0f)

                    );
                    //Putting the data into businessList. Parsing to the correct data type, handling where null values or where cells are empty.

                    businessList.add(business);


                    //           if (!businessNameList.contains(business.getBusinessName()))
                    //               businessNameList.add(business.getBusinessName());

                    //           if (!authorityNameList.contains(business.getLocalAuthorityName()))
                    //               authorityNameList.add(business.getLocalAuthorityName());
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
                //Read busName

        if (busNameSet.contains(busName2)) {
            System.out.println("worked:" + busName2); //Return business name works if finds match
            Collections.sort(businessList, new sortByRatingAndDate()); //sort businessList by SortByRatingAndDate Class
            for (BusinessManager business : businessList) {

                if (business.getBusinessName().equalsIgnoreCase(busName2)) { //if its equal to what the user typed
                    System.out.println(
                             business.getRatings()
                            //print out getRatings from Business - which uses BusinessManager Class.
                            //This contains Rating Value, Rating Date, Scores/Hygiene, Scores/Structural, Scores/ConfidenceInManagement
                            //returning those in Date order and Rating
                            // +
                            //business.getRatings()
                            );
                }
            }

        }
        else {
            System.out.println("not found:" + busName2);
            //Print error message - cannot find busName
        }

        //System.out.println(localAuthSet.size());
        return businessList;
    }
}
