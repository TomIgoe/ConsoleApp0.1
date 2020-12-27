package features;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class FeatureBo {





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
        ArrayList<BusinessManager> businessList = new ArrayList<>(); //ArrayList called BusinessList
        Set<String> localAuthSet = new HashSet<>(); //HashSet for Local Authorities
        Set<String> busNameSet = new HashSet<>(); //HashSet for Business Names

        try {
            var files = new File(fileDirectory).listFiles(x -> x.isFile() && x.getName().endsWith(".csv"));


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


                    boolean a = true;
                    // Get the value from the row
                    // If the value is null or is a string of length 0
                    //        set the value to 0.0
                    // else
                    ////// use the value

                    String localAuth = row.get("LocalAuthorityName");
                    localAuthSet.add(localAuth);
                    // get localAuthorityName and add it to localAuthSet

                    String busName = row.get("BusinessName");
                    busNameSet.add(busName);
                    // get busName and add it to busNameSet



                    var business = new BusinessManager(
                            localAuth,
                            busName,
                            row.get("BusinessType"),
                            row.get("AddressLine1"),
                            (
                                    row.get("Geocode/Latitude") != null
                                            && !row.get("Geocode/Latitude").isEmpty()
                            )
                                    ? Float.parseFloat(row.get("Geocode/Latitude")) : 0.0f,
                            (row.get("Geocode/Longitude") == null && row.get("Geocode/Longitude").isEmpty()) ? Float.parseFloat(row.get("Geocode/Longitude")) : 0.0f,
                            (row.get("RatingValue") == null && row.get("RatingValue").isEmpty()) ? Byte.parseByte(row.get("RatingValue")) : 0,
                            rating.equals(EXEMPT),
                            rating.equals(AWAITING),
                            row.get("RatingDate"),
                            (short) ((row.get("Scores/Hygiene") == null && row.get("Scores/Hygiene").isEmpty()) ? Byte.parseByte(row.get("Scores/Hygiene")) :  0.0f),
                            (short) ((row.get("Scores/Structural") == null && row.get("Scores/Structural").isEmpty()) ? Byte.parseByte(row.get("Scores/Structural")) :  0.0f),
                            (short) ((row.get("Scores/ConfidenceInManagement") == null && row.get("Scores/ConfidenceInManagement").isEmpty()) ? Byte.parseByte(row.get("Scores/ConfidenceInManagement")) :  0.0f)

                    );
                    //Putting the data into businessList. Parsing to the correct data type, handling where null values or where cells are empty.
                    businessList.add(business);



                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
            e.printStackTrace();

            //if files are not found - print - "FileNotFoundException"

        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }

        //Standard call for catching IO Exceptions and print StackTrace

      //  System.out.println(localAuthSet);
        //Print out LocalAuthSet - This will print only unique values.




        System.out.println(busNameSet);
        // for printing busNameSet

        //System.out.println(localAuthSet.size());
        return businessList;
    }
}



