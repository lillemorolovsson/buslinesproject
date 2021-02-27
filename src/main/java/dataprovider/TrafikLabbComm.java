package dataprovider;

import java.time.LocalDateTime;
import java.util.List;

import org.json.JSONObject;

public interface TrafikLabbComm {

	public static final String DIRECTION_CODE_1 = "1";
	public static final String DIRECTION_CODE_2 = "2";

	public static class DataModelType {
		public static final String SITE = "site";
		public static final String STOP_POINT = "stop";
		public static final String LINE = "line";
		public static final String JOUR_POINT_ON_LINE = "JourneyPatternPointOnLine";
		public static final String TRANSPORT_MODE = "tran";
	}

	public static final String STATUS_CODE = "StatusCode";
	public static final String EXECUTION_TIME = "ExecutionTime";
	public static final String RESPONSE_DATA = "ResponseData";
	public static final String VERSION = "Version";
	public static final String TYPE = 		"Type";
	public static final String RESULT = "Result";
	public static final String LINE_NUMBER = "LineNumber";
	public static final String DIRECTION_CODE = "DirectionCode";
	public static final String JOUR_POINT_NUMBER = "JourneyPatternPointNumber";
	public static final String LAST_MODIFIED = "LastModified";
	public static final String EXISTS_FROM_DATE = "ExistsFromDate";

	/**
	 * Structure of the JSON object received back.
	 */
	public static class ResponseRecord {
		/** Number of milliseconds */
		public  long excecutionTime;

		/** Container-object that contains typed data, to be cast to actual data */
		public ResponseData responseData;

	}

	public static class ResponseData {
			/** Last modified date, usually once a day  : 2014-06-27 14:03:39.103 */
			public String version;

			/** The DataModelType of the data model of the response:
			 * Site, StopPoint, Line, JourneyPatternPointOfLine, TransportMode ...
			 */
			public String type;

			/** Container-object that contains typed data, to be cast to actual data */
			public List<JournyPatternPointOnLine> result;
	}

	public static class JournyPatternPointOnLine {
		public int lineNumber;
		public int directionCode;
		public int journeyPatternPointNumber;
		public LocalDateTime lastModified;
		public LocalDateTime existsFromDate;
	}

	public JSONObject fetchJourneyPatterns();

}
