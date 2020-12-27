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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//import org.apache.commons.csv.*;


public class CsvReaderuouo {




    //basic CSV READER Using Apache Commons
    public static void main(String[] args) {

        String filesPath = System.getProperty("user.home") + "\\Downloads\\datasets-foodsafety\\datasets-foodsafety";

        Path filesDirectory = Paths.get(filesPath);

        String filename = ""; // will use to find files in the directory

        Path csvPath = filesDirectory.resolve(filename);


        try {
            ArrayList<String> localAuthorities = parseFiles(csvPath.toString());
            //create ArrayList called Local Authorities where we are storing the parsed files from our directory.

            //businessManagers.stream().map(data -> data.getLocalAuthorityName()).distinct().toArray()
      //      System.out.print("Results : " + Arrays.toString(localAuthorities.toArray()));


        } catch (IOException e) {
            System.out.print("ERROR");
            e.printStackTrace();
        }
    }

    //
    private static final String EXEMPT = "Exempt";
    private static final String AWAITING = "AwaitingInspection";
// strings to be used later for handling rating data.

//
    public static ArrayList<String> parseFiles(String fileDirectory) throws IOException {
        ArrayList<BusinessManager> businessList = new ArrayList<>(); //ArrayList called BusinessList
   //     Set<String> localAuthSet = new HashSet<>();//HashSet called LocalAuthSet
        Set<String> busNameSet = new HashSet<>();//HashSet called busNameSet
        var uniqueAuthorityNameList = new ArrayList<String>(); //ArrayList called uniqueAuthorityNameList
        var uniqueAuthorityNameList2 = new ArrayList<String>(); //ArrayList called uniqueAuthorityNameList

        var original = new ArrayList<String>();
        var result = new ArrayList<String>();

        try {
            var files = new File(fileDirectory).listFiles(x -> x.isFile() && x.getName().endsWith(".csv"));
            var businessNameList = new ArrayList<String>();

            for (var file : files) {
                var reader = Files.newBufferedReader(file.toPath());
                var parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
                //find any files where the filename ends with .csv within the directory
                // parse each of the files
                //   System.out.println(file.getName());
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
               //     localAuthSet.add(localAuth);


                    String busName = row.get("BusinessName");
                    busNameSet.add(busName);

                    //if authorityNameList does not contain localAuth
                    //then add to authorityNameList
                    if (!uniqueAuthorityNameList.contains(localAuth)) {
                        uniqueAuthorityNameList.add(localAuth);
                        //if unique Authority name list does not contain what
                        // its already stored in Local Auth (i.e its unique to what its seen in Local Auth), them add it to uniqueAuthorityNameList.

              //          System.out.println(uniqueAuthorityNameList);
              //          System.out.println(localAuth);


                    }


                    result = (ArrayList<String>) original.stream()
                            .distinct()
                            .collect(Collectors.toList());



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

     //   System.out.println(localAuthSet);
        System.out.println(uniqueAuthorityNameList);
   //     System.out.println(uniqueAuthorityNameList2);



        //    System.out.print(businessList);

        //       System.out.println(busNameSet);

        //System.out.println(localAuthSet.size());
        return uniqueAuthorityNameList;
    }
}

