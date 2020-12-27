package features;

import java.util.Comparator;
import java.util.function.BiConsumer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class BusinessManager {//implements Comparable<BusinessManager> {

	private String _localAuthorityName;
	private String _businessName;
	private String _businessType;
	private String _businessAddress;
	
	private Float _businessLatitude;
	private Float _businessLongitude;
	
	private Byte _businessRatingValue;
	
	private Boolean _businessAwaitingRating;
	private Boolean _businessExemptRating;

	private String _businessRatingDate;
	
	private Short _businessHygineRating;
	private Short _businessStructuralRating;
	private Short _businessConfidenceRating;

	private static final String EXEMPT = "Exempt";
	private static final String AWAITING = "AwaitingInspection";
	
	
	public BusinessManager(String localAuthorityName, String businessName, String businessType,
						   String businessAddress, Float businessLatitude, Float businessLongitude, Byte businessRatingValue,
						    Boolean businessAwaitingRating, Boolean businessExemptRating, String businessRatingDate,
						   Short businessHygineRating, Short businessStructuralRating, Short businessConfidenceRating) {
		
		
		 _localAuthorityName = localAuthorityName;
		 _businessName = businessName;
		 _businessType = businessType;
		 _businessAddress = businessAddress;
		
		 _businessLatitude = businessLatitude;
		 _businessLongitude = businessLongitude;
		
		 _businessRatingValue = businessRatingValue;
		 _businessAwaitingRating = businessAwaitingRating;
		 _businessExemptRating = businessExemptRating;

		 _businessRatingDate = businessRatingDate;
		
		 _businessHygineRating = businessHygineRating;
		 _businessStructuralRating = businessStructuralRating;
		 _businessConfidenceRating = businessConfidenceRating;

		
	}

	public BusinessManager (CSVRecord row) {


		var rating = row.get("RatingValue");

		String dataFromSheet = row.get("Geocode/Latitude");
//             System.out.println(dataFromSheet);



		float realValue = 0.0f;
		if (dataFromSheet != null && dataFromSheet.length() != 0) {
			realValue = Float.parseFloat(dataFromSheet);
		}


		boolean a = true;

		float b = a ? 1.0f : 2.0f;

		String localAuth = row.get("LocalAuthorityName");

		String busName = row.get("BusinessName");

		String ratingValue = row.get("RatingValue");

		_localAuthorityName = localAuth;
		_businessName = busName;
		_businessType = row.get("BusinessType");
		_businessAddress = row.get("AddressLine1");

		_businessLatitude = (row.get("Geocode/Latitude") == null && row.get("Geocode/Latitude").isEmpty()) ? Float.parseFloat(row.get("Geocode/Latitude")): 0.0f ;
		_businessLongitude = (row.get("Geocode/Latitude") == null && row.get("Geocode/Latitude").isEmpty()) ? Float.parseFloat(row.get("Geocode/Latitude")): 0.0f ;

		_businessRatingValue = (row.get("RatingValue") != null
				&& !row.get("RatingValue").isEmpty()
				&&ratingValue.matches("\\d")
		) ? Byte.parseByte(row.get("RatingValue")) : 0;
		_businessAwaitingRating = rating.equals(EXEMPT);
		_businessExemptRating = rating.equals(AWAITING);

		_businessRatingDate = row.get("RatingDate");

		_businessHygineRating = (short) ((row.get("Scores/Hygiene") != null && !row.get("Scores/Hygiene").isEmpty()) ? Byte.parseByte(row.get("Scores/Hygiene")) : 0.0f);
		_businessStructuralRating = (short) ((row.get("Scores/Structural") != null && !row.get("Scores/Structural").isEmpty()) ? Byte.parseByte(row.get("Scores/Structural")) : 0.0f);
		_businessConfidenceRating = (short) ((row.get("Scores/ConfidenceInManagement") != null && !row.get("Scores/ConfidenceInManagement").isEmpty()) ? Byte.parseByte(row.get("Scores/ConfidenceInManagement")) : 0.0f);

	}

	public String getDetails() {
		return String.format("%s: %-60s %s: %-70s %s %s: %-15s %s: %-10s %s: %-10s",
					"name", _businessName,
					"Type", _businessType,
					"Address", _businessAddress,
					"Latitude", _businessLatitude,
					"Longitude", _businessLongitude);
	}

	public Float getLatitude() {
		return _businessLatitude;

	}

	public String getLocalAuthorityName() {
		return _localAuthorityName;
		
	}

	public Byte getRatingValue() {
		return _businessRatingValue;

	}



	
	public String getBusinessName() {
		return _businessName;
		
	}



	public String getBusinessType() {
		return _businessType;
	}
	
	public String getBusinessRatingDate() {
		return _businessRatingDate;
	}


	public Short getBusinessHygineRating() {
		return _businessHygineRating;
	}
	public Short getBusinessStructuralRating() {
		return _businessStructuralRating;
	}
	public Short getBusinessConfidenceRating() {
		return _businessConfidenceRating;
	}






	public String getRatings() {
		int stringLength = 20;
		return String.format("%s: %-" + stringLength + "s %s: %-" + stringLength + "s %s: %-" + stringLength + "s %s: %-" + stringLength + "s %s: %-" + stringLength + "s",
				"RatingValue", _businessRatingValue,
				"RatingDate", _businessRatingDate,
				"Scores/Hygiene", _businessHygineRating,
				"Scores/Structural",  _businessStructuralRating,
				"Scores/ConfidenceInManagement", _businessConfidenceRating);
	}

	public boolean exempt() {
		return _businessExemptRating;
	}

	public boolean pending() {
		return _businessAwaitingRating;
	}
}

