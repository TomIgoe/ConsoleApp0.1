package features;

import java.util.Comparator;

class sortByRatingAndDate implements Comparator<BusinessManager> {
    // Used for sorting in ascending order of
    // roll number
    //int comparisions = 1;
    public int compare(BusinessManager a, BusinessManager b) {
        int order = 0;
        int date = 0;
        int rating = 0;
        //System.out.println(comparisions++);
        date = a.getBusinessRatingDate().compareTo(b.getBusinessRatingDate());

        rating = a.getRatings().compareTo(b.getRatings());

        if (date == 0) {
            order = rating;
        } else {
            order = date;
        }

        return order * -1;
    }
}

