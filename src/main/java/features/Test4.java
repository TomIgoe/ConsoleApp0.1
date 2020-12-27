package features;

import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Test4 {


    public static void main(String[] args) {






        String path1 = "C:\\Users\\Tom\\eclipse-workspace\\testProj\\datasets-foodsafety\\liverpool.csv";

        String line = "";

        List<Book> books = new ArrayList<>();
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(path1));
            //	BufferedReader br2 = new BufferedReader(new FileReader(path2));
            //	BufferedReader br3 = new BufferedReader(new FileReader(path3));
            //	BufferedReader br4 = new BufferedReader(new FileReader(path4));
            //	BufferedReader br5 = new BufferedReader(new FileReader(path5));
            //	BufferedReader br6 = new BufferedReader(new FileReader(path6));

            br1.readLine(); //exclude first column where column titles

            List<String[]> lines = new ArrayList<String[]>();
            while ((line = br1.readLine()) != null) {
                lines.add(line.split(","));

            }

            // convert our list to a String array.
            String[][] array = new String[lines.size()][0];
            lines.toArray(array);

            String target = "4 Nations Food Company Ltd";
            int coloumn = 2;
            List<Integer> list = searchFunction(target,coloumn, array);

//            System.out.println(list);

            for(int i = 0; i < array[1].length; i++){ // search columns
          //      System.out.print(array[1][i] + ",");
            }

            System.out.println("----------------------------------");

            //array.length

            //search function
            // create list int 'foundIndexs'
      //      String target = "3 Wheeler Trailor";

            // return foundindexs
            // [2,5,80]

            // passs the returned array to printMany function
            //printMany loops throughthe passed array and for each one calls printArray passing ararry value parameter



            for(int i = 0; i < array[1].length; i++){
                for(int j = 0; j < array.length; j ++){
                    System.out.println(array[j][i]);
                }
            }
            System.out.println(array[4][5]);

           /*
            while ((line = br1.readLine()) != null) {
                String[] values = line.split(",");

                //	br2.readLine(); //exclude first column where column titles
                //		while ((line = br1.readLine()) != null) {
                //			String[] values2 = line.split(",");


/*
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


              //  System.out.println(books);



           //     System.out.println("LocalAuthorityName: " + LocalAuthorityNamei.replaceAll("[^A-Za-z]","")); //Local Authority Name


				System.out.println("FHRSID: " + FHRSID + " " + "LocalAuthorityBusinessID: "
				 +  LocalAuthorityBusinessID + " "+ "BusinessName: "+ BusinessName + " " + "BusinessType: "
						+ BusinessType

						);




*/





        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static List<Integer> searchFunction(String target, int coloumn, String[][] array) {
        List<Integer> list=new ArrayList<Integer>();
        System.out.println(array[13][coloumn]);
        for (int j = 0; j < array.length; j++) { // searches through rows

            if (target == array[j][coloumn]) {
                System.out.println("match");
                list.add(j);
            }
        }
        return list;
    }

}

