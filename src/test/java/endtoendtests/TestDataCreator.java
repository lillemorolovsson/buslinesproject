package endtoendtests;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import dataprovider.TrafikLabbComm;

public class TestDataCreator {

	public static class EmptyTestData implements TrafikLabbComm {

		@Override
		public JSONObject fetchJourneyPatterns() {
			return new JSONObject();
		}

	}

	public static class FetchExternalTestData implements TrafikLabbComm {
		private JSONObject mDataObject;

		public static String externalTestData =
				"{\"ExecutionTime\":411,\"Message\":null,\"ResponseData\":{\"Type\":\"JourneyPatternPointOnLine\",\"Version\":\"2021-02-24 00:14\",\"Result\":[{\"ExistsFromDate\":\"2018-02-16 00:00:00.000\",\"JourneyPatternPointNumber\":\"10008\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2018-02-16 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2020-09-28 00:00:00.000\",\"JourneyPatternPointNumber\":\"10012\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2020-09-28 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2018-08-20 00:00:00.000\",\"JourneyPatternPointNumber\":\"10014\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2018-08-20 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2013-12-19 00:00:00.000\",\"JourneyPatternPointNumber\":\"10016\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2013-12-19 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2021-01-03 00:00:00.000\",\"JourneyPatternPointNumber\":\"10024\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2021-01-03 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10034\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2014-05-06 00:00:00.000\",\"JourneyPatternPointNumber\":\"10038\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2014-05-06 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10042\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2020-07-02 00:00:00.000\",\"JourneyPatternPointNumber\":\"10044\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2020-07-02 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10053\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2013-12-19 00:00:00.000\",\"JourneyPatternPointNumber\":\"10055\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2013-12-19 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10057\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10059\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10061\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2018-09-04 00:00:00.000\",\"JourneyPatternPointNumber\":\"10062\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2018-09-04 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10066\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2015-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10100\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2015-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10154\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2018-09-04 00:00:00.000\",\"JourneyPatternPointNumber\":\"10156\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2018-09-04 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10449\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2013-12-19 00:00:00.000\",\"JourneyPatternPointNumber\":\"10451\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2013-12-19 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10453\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2015-12-03 00:00:00.000\",\"JourneyPatternPointNumber\":\"10506\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2015-12-03 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10643\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2013-12-19 00:00:00.000\",\"JourneyPatternPointNumber\":\"10727\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2013-12-19 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10729\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2021-02-16 00:00:00.000\",\"JourneyPatternPointNumber\":\"10731\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2021-02-16 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10733\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10743\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10745\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10754\",\"DirectionCode\":\"1\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2018-02-16 00:00:00.000\",\"JourneyPatternPointNumber\":\"10009\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2018-02-16 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2018-08-20 00:00:00.000\",\"JourneyPatternPointNumber\":\"10015\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2018-08-20 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2015-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10019\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2015-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2021-01-03 00:00:00.000\",\"JourneyPatternPointNumber\":\"10025\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2021-01-03 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10031\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10033\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10035\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2014-05-06 00:00:00.000\",\"JourneyPatternPointNumber\":\"10037\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2014-05-06 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10043\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2020-07-02 00:00:00.000\",\"JourneyPatternPointNumber\":\"10045\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2020-07-02 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10052\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2013-12-19 00:00:00.000\",\"JourneyPatternPointNumber\":\"10054\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2013-12-19 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10056\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10058\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10060\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2020-09-28 00:00:00.000\",\"JourneyPatternPointNumber\":\"10065\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2020-09-28 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10127\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10153\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2018-09-04 00:00:00.000\",\"JourneyPatternPointNumber\":\"10155\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2018-09-04 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2018-09-04 00:00:00.000\",\"JourneyPatternPointNumber\":\"10157\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2018-09-04 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10450\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2013-12-19 00:00:00.000\",\"JourneyPatternPointNumber\":\"10452\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2013-12-19 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10454\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2021-02-16 00:00:00.000\",\"JourneyPatternPointNumber\":\"10456\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2021-02-16 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10462\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2015-12-03 00:00:00.000\",\"JourneyPatternPointNumber\":\"10505\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2015-12-03 00:00:00.000\",\"LineNumber\":\"1\"},{\"ExistsFromDate\":\"2012-06-23 00:00:00.000\",\"JourneyPatternPointNumber\":\"10644\",\"DirectionCode\":\"2\",\"LastModifiedUtcDateTime\":\"2012-06-23 00:00:00.000\",\"LineNumber\":\"1\"}]}}";


		@Override
		public JSONObject fetchJourneyPatterns() {
			String data = externalTestData;
			try {
				JSONParser parser = new JSONParser();
				mDataObject = (JSONObject) parser.parse(data);
			} catch (Exception e) {
				System.out.println("Parsing of external test data failed");
				e.printStackTrace();
			}
			return mDataObject;
		}

		public JSONObject fillExternalTestData() {

			String data = externalTestData;
			try {
				JSONParser parser = new JSONParser();
				mDataObject = (JSONObject) parser.parse(data);
			} catch (Exception e) {
				System.out.println("Parsing of external test data failed");
				e.printStackTrace();
			}
			return mDataObject;
		}
	}

	public static class FetchTestData implements TrafikLabbComm {

		private static Map<String, List<String>> testLines = new HashMap<>();
		private JSONObject mDataObject;

		@Override
		public JSONObject fetchJourneyPatterns() {
				return fillTestData();
		}

		public static Map<String, List<String>> getTestLines() {
			return testLines;
		}

		private JSONObject getJsonObject(
				int statusCode, Long execTime, ResponseData response) {
			JSONObject obj = new JSONObject();

			obj.put(TrafikLabbComm.STATUS_CODE, statusCode + "");
			obj.put(TrafikLabbComm.EXECUTION_TIME, execTime);
			obj.put(TrafikLabbComm.RESPONSE_DATA, getJsonResponseObject(response));

			return obj;
		}

		private JSONObject getJsonResponseObject(ResponseData response) {
			JSONObject obj = new JSONObject();

			obj.put(TrafikLabbComm.VERSION, response.version);
			obj.put(TrafikLabbComm.TYPE, response.type);
			obj.put(TrafikLabbComm.RESULT, getResponseResultAsJsonObject(response.result));

			return obj;
		}

		private JSONArray getResponseResultAsJsonObject(
				List<JournyPatternPointOnLine> responseResult) {

			JSONArray tReturnList = new JSONArray();

			for (JournyPatternPointOnLine result : responseResult) {

				JSONObject obj = new JSONObject();

				obj.put(TrafikLabbComm.LINE_NUMBER, result.lineNumber + "");
				obj.put(TrafikLabbComm.DIRECTION_CODE, result.directionCode + "");
				obj.put(TrafikLabbComm.JOUR_POINT_NUMBER, result.journeyPatternPointNumber + "");
				obj.put(TrafikLabbComm.LAST_MODIFIED, result.lastModified);
				obj.put(TrafikLabbComm.EXISTS_FROM_DATE, result.existsFromDate);
				tReturnList.add(obj);
			}

			return tReturnList;
		}

		private JournyPatternPointOnLine createPointOnLine(int lineNumber, int directioncode, int pointNumber) {

			JournyPatternPointOnLine pointOnLine = new JournyPatternPointOnLine();
			pointOnLine.lineNumber = lineNumber;
			pointOnLine.directionCode = directioncode;
			pointOnLine.journeyPatternPointNumber = pointNumber;
			pointOnLine.lastModified = LocalDateTime.now();
			pointOnLine.existsFromDate = LocalDateTime.of(2021, 1, 1, 13, 30);

			return pointOnLine;
		}

		public JSONObject fillTestData() {
			List<JournyPatternPointOnLine> tList = new ArrayList<>() ;

			JournyPatternPointOnLine[] tArray = createTestData();
			tList.addAll(Arrays.asList(tArray));

			ResponseData tData = new ResponseData();
			tData.version = LocalDate.now().toString();
			tData.type = DataModelType.JOUR_POINT_ON_LINE;
			tData.result = tList;

			mDataObject = getJsonObject(0, 3932L, tData);
			return mDataObject;
		}

		public JSONObject fillLineData() {
			return null;
		}


		private JournyPatternPointOnLine[] createTestData() {
			return new JournyPatternPointOnLine[] {

					createPointOnLine(55, 2, 1),
					createPointOnLine(55, 2, 2),
					createPointOnLine(55, 2, 3),
					createPointOnLine(55, 2, 4),
					createPointOnLine(55, 2, 5),
					createPointOnLine(55, 2, 6),
					createPointOnLine(55, 2, 7),
					createPointOnLine(55, 2, 8),
					createPointOnLine(55, 1, 1),
					createPointOnLine(55, 1, 2),
					createPointOnLine(55, 1, 12),
					createPointOnLine(55, 1, 5),
					createPointOnLine(55, 1, 6),
					createPointOnLine(55, 1, 10),

					createPointOnLine(95, 1, 1),
					createPointOnLine(95, 1, 2),
					createPointOnLine(95, 1, 3),
					createPointOnLine(95, 1, 5),
					createPointOnLine(95, 1, 6),
					createPointOnLine(95, 1, 8),
					createPointOnLine(95, 2, 11),
					createPointOnLine(95, 2, 22),
					createPointOnLine(95, 2, 23),
					createPointOnLine(95, 2, 25),
					createPointOnLine(95, 2, 26),
					createPointOnLine(95, 2, 28),

					createPointOnLine(66, 1, 2),
					createPointOnLine(66, 1, 3),
					createPointOnLine(66, 1, 14),
					createPointOnLine(66, 1, 56),
					createPointOnLine(66, 2, 3),
					createPointOnLine(66, 2, 14),
					createPointOnLine(66, 2, 56),

					createPointOnLine(75, 1, 2),
					createPointOnLine(75, 1, 3),
					createPointOnLine(75, 1, 5),
					createPointOnLine(75, 1, 6),

					createPointOnLine(23, 1, 2),
					createPointOnLine(23, 1, 3),
					createPointOnLine(23, 1, 5),
					createPointOnLine(23, 1, 6),

					createPointOnLine(25, 1, 2),
					createPointOnLine(25, 1, 3),
					createPointOnLine(25, 1, 5),

					createPointOnLine(35, 1, 2),
					createPointOnLine(35, 1, 3),
					createPointOnLine(35, 1, 5),

					createPointOnLine(45, 1, 2),
					createPointOnLine(45, 1, 3),
					createPointOnLine(45, 1, 5),

					createPointOnLine(65, 1, 3),
					createPointOnLine(65, 1, 5),

					createPointOnLine(555, 1, 2),
					createPointOnLine(555, 1, 3),
					createPointOnLine(555, 1, 5),

					createPointOnLine(155, 1, 3),
					createPointOnLine(155, 1, 5),

					createPointOnLine(355, 1, 2),
					createPointOnLine(355, 1, 3),
					createPointOnLine(355, 1, 5),

					createPointOnLine(955, 1, 2),
					createPointOnLine(955, 1, 5),

					createPointOnLine(655, 1, 2),
					createPointOnLine(655, 1, 3),
					createPointOnLine(655, 1, 5),
					createPointOnLine(655, 1, 15),
					createPointOnLine(655, 2, 22),
					createPointOnLine(655, 2, 35),
					createPointOnLine(655, 2, 5),
					createPointOnLine(655, 2, 15),

					createPointOnLine(255, 1, 2),
					createPointOnLine(255, 1, 3),

					createPointOnLine(455, 1, 2),
					createPointOnLine(455, 1, 3),
					createPointOnLine(455, 1, 5),

			};
		}
	}
}
