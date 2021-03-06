package dataprovider;

import static dataprovider.TrafikLabbComm.DIRECTION_CODE;
import static dataprovider.TrafikLabbComm.EXECUTION_TIME;
import static dataprovider.TrafikLabbComm.JOUR_POINT_NUMBER;
import static dataprovider.TrafikLabbComm.LINE_NUMBER;
import static dataprovider.TrafikLabbComm.RESPONSE_DATA;
import static dataprovider.TrafikLabbComm.RESULT;
import static dataprovider.TrafikLabbComm.TYPE;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import dataprovider.TrafikLabbComm.DataModelType;

public final class BusLineDataJSONParser implements BusLineDataProvider {

	/**
	 * The external data is updated between 00.00 and 02.00 each day, we want to make sure that
	 * we update after that. We can have old data for an extra hour, but not for a whole day.
	 */
	private static final int HOUR_AFTER_WHICH_TO_FETCH_FRESH_DATA = 3;

	private LocalDateTime wasLastUpdated;
	private final TrafikLabbComm externalDataProvider;


	public BusLineDataJSONParser(TrafikLabbComm externalProvider) {
		externalDataProvider = externalProvider;
	}

	public BusLineDataJSONParser() {
		externalDataProvider = new TrafikLabbCaller();
	}

	@Override
	public Map<String, BusLineInformation> getData() {

		try {
			System.out.println("Fetching of data started " + LocalDateTime.now());

			JSONObject fetchedData = externalDataProvider.fetchJourneyPatterns();
			logExecutionTime(fetchedData);
			return mapToBusLineInformation(fetchedData);
		}
		catch (Exception e) {
			System.out.println("Failed to fetch data from external data Provider");
			e.printStackTrace();
			return new HashMap<>();
		}

	}

	private Map<String, BusLineInformation> mapToBusLineInformation(JSONObject fetchedData) {

		Map<String, BusLineInformation> mapData = new HashMap<>();

		System.out.println("Mapping of fetched data started " + LocalDateTime.now());
		if (fetchedData == null || fetchedData.optJSONObject(RESPONSE_DATA) == null) {
			return mapData;
		}

		try {
			JSONObject responseData =  fetchedData.getJSONObject(RESPONSE_DATA);
			String modelType = responseData.getString(TYPE);
			switch (modelType) {
				case DataModelType.JOUR_POINT_ON_LINE:
					mapData = mapJourneyPattern(responseData);
					break;
				case DataModelType.LINE:
				case DataModelType.SITE:
				case DataModelType.STOP_POINT:
				case DataModelType.TRANSPORT_MODE:
				default:
					System.out.println("Unsupported datamodel was returned: " + modelType);
					break;
			}

			System.out.println("Mapping of fetched data finished" + LocalDateTime.now());

			enrichBusLineInformation(mapData);
			wasLastUpdated = LocalDateTime.now();

			System.out.println("Enriching fetched data finished" + LocalDateTime.now());
		}
		catch (RuntimeException e ) {
			System.out.println("Failed to parse from JSON to java objects");
			e.printStackTrace();
		}

		return mapData;
	}

	private void enrichBusLineInformation(Map<String, BusLineInformation> mapData) {
		if (mapData == null) {
			return;
		}

		mapData.values().stream()
			.forEach(p-> setNumberofPatternPointNumbers(p));
	}

	private void setNumberofPatternPointNumbers(BusLineInformation busLineInformation) {
		busLineInformation.numberOfBusStops = Math.max(
				busLineInformation.busStopIdsDirection1.size(),
				busLineInformation.busStopIdsDirection2.size());
	}

	private void logExecutionTime(JSONObject fetchedData) {
		int execTime = fetchedData.optInt(EXECUTION_TIME);
		System.out.println(LocalDateTime.now() + "Execution time for request was " + execTime);
	}

	private Map<String, BusLineInformation> mapJourneyPattern(JSONObject responseData) {

		Map<String, BusLineInformation> mapData = new HashMap<>();

		JSONArray result = responseData.getJSONArray(RESULT);

		for (int i = 0; i < result.length(); i++) {
			try {
				JSONObject item = result.getJSONObject(i);
				String lineNumber = item.getString(LINE_NUMBER);

				BusLineInformation info = mapData.computeIfAbsent(lineNumber, missing -> new BusLineInformation(lineNumber));
				String directionCode = item.getString(DIRECTION_CODE);
				if (directionCode.equals(TrafikLabbComm.DIRECTION_CODE_1)) {
					info.busStopIdsDirection1.add(item.getString(JOUR_POINT_NUMBER));
				}
				else {
					info.busStopIdsDirection2.add(item.getString(JOUR_POINT_NUMBER));
				}
			}
			catch (RuntimeException e ) {
				System.out.println("Failed to parse from JSON to java objects");
				e.printStackTrace();
			}
		}

		return mapData;
	}

	public LocalDateTime getCurrentTime() {
		return LocalDateTime.now();
	}


	@Override
	public boolean isTimeToUpdate() {

		if (wasLastUpdated == null) {
			return true;
		}

		LocalDateTime today = getCurrentTime().truncatedTo(ChronoUnit.DAYS);
		LocalDateTime wasUpdatedDate = wasLastUpdated.truncatedTo(ChronoUnit.DAYS);

		//if it was last updated previous day -- update if current time is later than 03.00
		if (wasUpdatedDate.compareTo(today) < 0 &&
			getCurrentTime().getHour() > HOUR_AFTER_WHICH_TO_FETCH_FRESH_DATA) {
			return true;
		}
		return false;
	}

}
