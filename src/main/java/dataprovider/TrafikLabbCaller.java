package dataprovider;

import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

class TrafikLabbCaller implements TrafikLabbComm {

	private static final String JOUR_KEY = "ad4d2aaeb67b4f5b836bac7d0ca34dc9";
	private static final String JOUR_URL = "https://api.sl.se/api2/LineData.json?"
	 		+ "model=JourneyPatternPointOnLine&"
	  		+ "key=" + JOUR_KEY
	  		+ "&DefaultTransportModeCode=BUS";


	private String fetchData() throws Exception {
		StringBuilder result = new StringBuilder();
		URLConnection conn = new URL(JOUR_URL).openConnection();

		try (Scanner scanner = new Scanner(conn.getInputStream())) {
			while (scanner.hasNextLine()) {
				result.append(scanner.nextLine());
			}
		}

		return result.toString();
	}

	@Override
	public JSONObject fetchJourneyPatterns() {
		try {
			String response = fetchData();
			JSONParser parser = new JSONParser();
			JSONObject object = (JSONObject) parser.parse(response);
			return object;
		}
		catch (Exception e) {
			System.out.println("Failed to fetch journey patterns");
			e.printStackTrace();
			return new JSONObject();
		}
	}

}
