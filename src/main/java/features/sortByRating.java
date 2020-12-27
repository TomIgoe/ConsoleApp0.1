package features;

import java.util.Comparator;

class sortByRating implements Comparator<BusinessManager> {
    // Used for sorting in ascending order of
    // roll number
    public int compare(BusinessManager a, BusinessManager b) {

        return a.getRatings().compareTo(b.getRatings()) * -1;

    }
}
