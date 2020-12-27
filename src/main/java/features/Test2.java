package features;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.awt.print.Book;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


/**
     * returns a string which is comma separate column values for matching row otherwise null.
     *
     * @param searchColumnIndex
     * @param searchString
     * @return
     * @throws IOException
     */

   import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

    public class Test2 {

/*
        public static void main(String[] args) {

            List<String> filenames = new LinkedList<String>();
            public void listFilesForFolder(final File folder) {
                for (final File fileEntry : folder.listFiles()) {
                    if (fileEntry.isDirectory()) {
                        listFilesForFolder(fileEntry);
                    } else {
                        if(fileEntry.getName().contains(".csv"))
                            filenames.add(fileEntry.getName())
                    }
                }
            }

            final File folder = new File("/home/you/Desktop");
            listFilesForFolder(folder);


}

            String path1 = "C:\\Users\\harry\\OneDrive\\Desktop\\Data And algorythems CW\\datasets-foodsafety\\liverpool.csv";
            String path2 = "C:\\Users\\harry\\OneDrive\\Desktop\\Data And algorythems CW\\datasets-foodsafety\\knowsley.csv";
            String path3 = "C:\\Users\\harry\\OneDrive\\Desktop\\Data And algorythems CW\\datasets-foodsafety\\sefton.csv";
            String path4 = "C:\\Users\\harry\\OneDrive\\Desktop\\Data And algorythems CW\\datasets-foodsafety\\sthelens.csv";
            String path5 = "C:\\Users\\harry\\OneDrive\\Desktop\\Data And algorythems CW\\datasets-foodsafety\\wigan.csv";
            String path6 = "C:\\Users\\harry\\OneDrive\\Desktop\\Data And algorythems CW\\datasets-foodsafety\\wirral.csv";
            String line = "";

            List<Book> books = new ArrayList<>();
            try {
                BufferedReader br1 = new BufferedReader(new FileReader(path1));
                //	BufferedReader br2 = new BufferedReader(new FileReader(path2));
                //	BufferedReader br3 = new BufferedReader(new FileReader(path3));
                //	BufferedReader br4 = new BufferedReader(new FileReader(path4));
                //	BufferedReader br5 = new BufferedReader(new FileReader(path5));
                //	BufferedReader br6 = new BufferedReader(new FileReader(path6));

            try {
                br1.readLine(); //exclude first column where column titles
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            while (true) {
                try {
                    if (!((line = br1.readLine()) != null)) break;
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                String[] values = line.split(",");

                    //	br2.readLine(); //exclude first column where column titles
                    //		while ((line = br1.readLine()) != null) {
                    //			String[] values2 = line.split(",");



                    String FHRSIDs = values[0]; //Setting FHRSIDs
                    int FHRSID = Integer.parseInt(FHRSIDs); //parsing string FHRSIDs to int FHRSID

                    String LocalAuthorityBusinessID = values [1];


                    String BusinessName = values [2];

                    String BusinessType = values [3];
                    String LocalAuthorityName = values [13];
                    String LocalAuthorityNamei = LocalAuthorityName.replaceAll("[^A-Za-z]", LocalAuthorityName);
                    //boolean doesItContain = br.contains("Liverpool");

			/*	String BusinessTypeIDs = values [4];
				int BusinessTypeID = Integer.parseInt(BusinessTypeIDs);//parsing string to int

				String AddressLine2 = values [5];
				String AddressLine3 = values [6];
				String PostCode = values [7];
				String RatingValues = values [8];
				int RatingValue = Integer.parseInt(RatingValues); //parsing string to int
				String RatingKey = values [9];
				String RatingDate = values [10];
				String LocalAuthorityName = values [11];
				String LocalAuthorityWebSite = values [12];
				String LocalAuthorityEmailAddress = values [13];
				String ScoresnHygieness = values [14];
				int ScoresnHygienes = Integer.parseInt(ScoresnHygieness);//parsing string int
				String ScoresnStructurals = values [15];
				int ScoresnStructural = Integer.parseInt(ScoresnStructurals);//parsing string to int
				String SchemeTypes = values [16];
				int SchemeType = Integer.parseInt(SchemeTypes);//parsing string to int
				String NewRatingPendings = values [17];
				boolean NewRatingPending = Boolean.parseBoolean(NewRatingPendings);//parsing string to boolean
				String GeocodenLongitudes = values [18];
				float GeocodenLongitude = Float.parseFloat(GeocodenLongitudes);//parsing string to float
				String GeocodenLatitudes = values [19];
				float GeocodenLatitude = Float.parseFloat(GeocodenLatitudes);//parsing string to float






                    System.out.println("LocalAuthorityName: " + LocalAuthorityNamei.replaceAll("[^A-Za-z]","")); //Local Authority Name


				System.out.println("FHRSID: " + FHRSID + " " + "LocalAuthorityBusinessID: "
				 +  LocalAuthorityBusinessID + " "+ "BusinesName: "+ BusinessName + " " + "BusinessType: "
						+ BusinessType

						);







                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
*/
    }
