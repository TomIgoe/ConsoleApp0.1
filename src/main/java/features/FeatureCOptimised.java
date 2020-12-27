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


public class FeatureCOptimised {

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

    //
    private static final String EXEMPT = "Exempt";
    private static final String AWAITING = "AwaitingInspection";
    //private static final String filesDirectory = "C:\\Users\\harry\\OneDrive\\Desktop\\Data And algorythems CW\\datasets-foodsafety";
//
    public static ArrayList<BusinessManager> parseFiles (String fileDirectory) throws IOException {
        //ArrayList<BusinessManager> businessList = new ArrayList<>();
        Set<String> localAuthSet = new HashSet<>();
        //Set<String> busNameSet = new HashSet<>();
        Map<String, ArrayList<BusinessManager>> businessMap = new TreeMap<String, ArrayList<BusinessManager>>();
        try {
            var files = new File(fileDirectory).listFiles(x -> x.isFile() && x.getName().endsWith(".csv"));
            var businessNameList = new ArrayList<String>();
            var authorityNameList = new ArrayList<String>();

            for (var file : files) {
                var reader = Files.newBufferedReader(file.toPath());
                var parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

                //   System.out.println(file.getName());
                for (var row : parser) {

                    var rating = row.get("RatingValue");

                    String dataFromSheet = row.get("Geocode/Latitude");
//             System.out.println(dataFromSheet);


                    String dataFromSheet2 = row.get("Scores/ConfidenceInManagement");
//             System.out.println(dataFromSheet2);


                    float realValue = 0.0f;
                    if (dataFromSheet != null && dataFromSheet.length() != 0) {
                        realValue = Float.parseFloat(dataFromSheet);
                    }


                    boolean a = true;

                    float b = a ? 1.0f : 2.0f;


                    // Get the value from the row
                    // If the value is null or is a string of length 0
                    //        set the value to 0.0
                    // else
                    ////// use the value

                    String localAuth = row.get("LocalAuthorityName");
                    localAuthSet.add(localAuth);

                    //sanitizing the index
                    String busName = row.get("BusinessName").toLowerCase().trim();

                    /*
                    busNameSet.add(busName);

                    busNameSet.add(ratingValue);
                    */
                    String ratingValue = row.get("RatingValue");
                    // create array list for a business if it does not exist already
                    if (businessMap.get(busName) == null) {
                        businessMap.put(busName, new ArrayList<BusinessManager>());
                    }
                        businessMap.get(busName).add(new BusinessManager(
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
                                (row.get("Scores/Hygiene") != null && !row.get("Scores/Hygiene").isEmpty()) ? Byte.parseByte(row.get("Scores/Hygiene")) : (short) 0.0f,
                                (row.get("Scores/Structural") != null && !row.get("Scores/Structural").isEmpty()) ? Byte.parseByte(row.get("Scores/Structural")) : (short) 0.0f,
                                (row.get("Scores/ConfidenceInManagement") != null && !row.get("Scores/ConfidenceInManagement").isEmpty()) ? Byte.parseByte(row.get("Scores/ConfidenceInManagement")) : (short) 0.0f

                        ));




                    //businessList.add(business);


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

        //    System.out.println(localAuthSet);
      //  System.out.println(busNameSet);

// businessList.sort();

                Scanner myObj = new Scanner(System.in);  // Create a Scanner object
                System.out.println("Enter business name");
                String busName2 = (myObj.nextLine());
            //    Set<String> busName = Collections.singleton(myObj.nextLine());  // Read user input
         //       System.out.println("Username is: " + busName + busNameSet);  // Output user input

        if (businessMap.get(busName2) != null) {
            System.out.println("worked:" + busName2);
            System.out.println(businessMap.size());
            Collections.sort(businessMap.get(busName2), new sortByRatingAndDate());

            for (BusinessManager business : businessMap.get(busName2)) {
                System.out.println(
                             business.getRatings()
                            );
                }
            }

        else {
            System.out.println("not found:" + busName2);
        }





        //System.out.println(localAuthSet.size());
        return businessMap.get(busName2);
    }
}
