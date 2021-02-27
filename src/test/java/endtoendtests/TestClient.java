package endtoendtests;

import java.util.List;

import datamanager.BusLineDataManager;
import datamanager.BusLineDataIf.BusLineInfo;

public class TestClient {

	public static void main(String... args) {
		BusLineDataManager manager = new BusLineDataManager();
		manager.initAndFetchData();

		System.out.println("TOTAL NUMBER OF LINES");
		long numberOfLines = manager.getTotalNumberOfBusLines();
		System.out.println(" == " + numberOfLines);
		System.out.println();
		System.out.println("___________________________________________________________");

		int xLines = 20;
		List<BusLineInfo> topElevenLines = manager.getBusLineWithMostStops(xLines);

		System.out.println("TOP X LINES");
		for (BusLineInfo line : topElevenLines) {
			System.out.println(String.format("%20.20s %10d %2.2s",
					line.lineNumber, line.numberOfStops, line.direction));
		}
		System.out.println("___________________________________________________________");

		List<String> top10Lines = manager.getTop10BusLineWithMostStops();

		System.out.println("TOP TEN LINES");
		for (String line : top10Lines) {
			System.out.print(line + " | ");
		}
		System.out.println();
		System.out.println("___________________________________________________________");


		System.out.println("BUS STOPS PER LINE AND DIRECTION");
		for (BusLineInfo line : topElevenLines) {
			List<String> busStops = manager.getAllBusStopsForBusLine(line.lineNumber, line.direction);
			System.out.print(line.lineNumber + "-> " +  busStops.size() + ": ");
			for (String stop : busStops) {
				System.out.print(String.format("%7.7s |", stop));
			}
			System.out.println();
		}
		System.out.println("___________________________________________________________");


		System.out.println("ALL BUS STOPS PER LINE");
		for (BusLineInfo line : topElevenLines) {
			List<String> busStops = manager.getAllBusStopsForBusLine(line.lineNumber);
			System.out.print(line.lineNumber + "-> " +  busStops.size() + ": ");
			for (String stop : busStops) {
				System.out.print(String.format("%7.7s |", stop));
			}
			System.out.println();
		}
		System.out.println("___________________________________________________________");
	}

}
