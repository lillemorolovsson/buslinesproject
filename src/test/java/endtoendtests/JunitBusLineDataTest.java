package endtoendtests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import dataUtilisers.BusLineDataIf.BusLineInfo;
import datamanager.BusLineDataManager;
import dataprovider.TrafikLabbComm;
import endtoendtests.TestDataCreator.EmptyTestData;
import endtoendtests.TestDataCreator.FetchExternalTestData;
import endtoendtests.TestDataCreator.FetchTestData;


public class JunitBusLineDataTest {

	BusLineDataManager manager;

	public void setUpTestData() {
		setUpTestData(new FetchTestData());
	}

	private void setUpProdTestData() {
		manager = new BusLineDataManager();
		manager.initAndFetchData();
	}

	public void setUpTestData(TrafikLabbComm externalProvider) {
		manager = new BusLineDataManager(externalProvider);
		manager.initAndFetchData();
	}

	@Test
	public void verifyWithIllegalInput() {

		setUpTestData();

		List<String> list = manager.getAllBusStopsForBusLine("X");
		Assert.assertNotNull("Null was not expected", list);
		Assert.assertEquals("Unexpected number of lines returned", 0, list.size());

		List<BusLineInfo> lines = manager.getBusLineWithMostStops(-1);
		Assert.assertNotNull("Null was not expected", lines);
		Assert.assertEquals("Unexpected number of lines returned", 0, lines.size());

		lines = manager.getBusLineWithMostStops(0);
		Assert.assertNotNull("Null was not expected", lines);
		Assert.assertEquals("Unexpected number of lines returned", 0, lines.size());

		list = manager.getAllBusStopsForBusLine("TEST", "WRONG");
		Assert.assertNotNull("Null was not expected", lines);
		Assert.assertEquals("Unexpected number of lines returned", 0, list.size());


	}

	@Test
	public void verifyWithNoBusLineData() {

		setUpTestData(new EmptyTestData());

		long numberOfLines = manager.getTotalNumberOfBusLines();
		Assert.assertEquals("Unexpected number of lines returned", 0, numberOfLines);

		List<BusLineInfo> lines = manager.getBusLineWithMostStops(12);
		Assert.assertNotNull("Null was not expected", lines);
		Assert.assertEquals("Unexpected number of lines returned", 0, lines.size());

		List<String> list = manager.getAllBusStopsForBusLine("1");
		Assert.assertNotNull("Null was not expected", lines);
		Assert.assertEquals("Unexpected number of lines returned", 0, list.size());

		list = manager.getTop10BusLineWithMostStops();
		Assert.assertNotNull("Null was not expected", lines);
		Assert.assertEquals("Unexpected number of lines returned", 0, list.size());

		list = manager.getAllBusStopsForBusLine("1", "1");
		Assert.assertNotNull("Null was not expected", lines);
		Assert.assertEquals("Unexpected number of lines returned", 0, list.size());
	}

	@Test
	public void verifyReFetchingOfDataWorks() {
		setUpTestData();

		manager.initAndFetchData();
	}

	@Test
	public void verifyBusLinesWithMostStops() {
		setUpTestData(new FetchTestData());

		Integer[][] expectedTopLines = new Integer[][] {
				new Integer[] {55, 8},
				new Integer[] {95, 6},
				new Integer[] {23, 4},
				new Integer[] {655, 4},
				new Integer[] {66, 4},
				new Integer[] {75, 4},
				new Integer[] {25, 3},
				new Integer[] {35, 3},
				new Integer[] {355, 3},
				new Integer[] {45, 3},
				new Integer[] {455, 3}};

		List<BusLineInfo> lines = manager.getBusLineWithMostStops(11);
		Assert.assertEquals("Unexpected number of lines returned", expectedTopLines.length, lines.size());

		int i = 0;
		for (BusLineInfo line : lines) {

			Assert.assertEquals("Unexpected line",expectedTopLines[i][0] + "", line.lineNumber);
			Assert.assertEquals("Unexpected number of lines",
					expectedTopLines[i][1].intValue(), line.numberOfStops);
			i++;
		}
	}

	@Test
	public void verifyTotalNumberOfBusLines() {

		setUpTestData();

		long expectedNumberOfLines = 16;
		long numberOfLines = manager.getTotalNumberOfBusLines();
		Assert.assertEquals("Unexpected number of lines returned", expectedNumberOfLines, numberOfLines);
	}

	@Test
	public void verifyBusLinesWithMostStopsExternalTestData() {
		setUpTestData(new FetchExternalTestData());

		Integer[][] expectedTopLines = new Integer[][] {
				new Integer[] {1, 31}};

		List<BusLineInfo> lines = manager.getBusLineWithMostStops(11);
		Assert.assertEquals("Unexpected number of lines returned", expectedTopLines.length, lines.size());


		int i = 0;
		for (BusLineInfo line : lines) {

			Assert.assertEquals("Unexpected line",expectedTopLines[i][0] + "", line.lineNumber);
			Assert.assertEquals("Unexpected number of busstops",
					expectedTopLines[i][1].intValue(), line.numberOfStops);
			Assert.assertEquals("Unexpected direction for the line", line.direction, "1");
			i++;
		}
	}

	@Test
    @Ignore("Ignored by default: Only run toward live data when you really want to")
	public void verifyWithFullExternalData() {

		setUpProdTestData();

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

	@Test
	public void verifyNumberOfBusStops() {

		setUpTestData(new FetchTestData());

		// List.of()

		Map<String, List<String>> expectedRows = new HashMap<>();

		expectedRows.put("55", List.of("1", "2", "3", "4","5", "6", "7", "8", "10", "12"));
		expectedRows.put("95", List.of("1", "2", "3", "5", "6", "8", "11", "22", "23", "25", "26", "28"));
		expectedRows.put("66",  List.of("2", "3", "14", "56"));
		expectedRows.put("75", List.of("2", "3", "5", "6"));
		expectedRows.put("23",  List.of("2", "3", "5", "6"));
		expectedRows.put("25",  List.of("2", "3", "5"));
		expectedRows.put("35",  List.of("2", "3", "5"));
		expectedRows.put("45",  List.of("2", "3", "5"));
		expectedRows.put("65",  List.of("3", "5"));
		expectedRows.put("555",  List.of("2", "3", "5"));
		expectedRows.put("955",  List.of("2", "5"));
		expectedRows.put("255",  List.of("2", "3"));
		expectedRows.put("655",  List.of("2", "3", "5", "15", "22", "35"));
		expectedRows.put("455",  List.of("2", "3", "5"));


		for (Entry<String, List<String>> expectedStops : expectedRows.entrySet()) {

			List<String> busStops = manager.getAllBusStopsForBusLine(expectedStops.getKey());

			Assert.assertEquals("Unexpected number of stops", expectedStops.getValue().size(), busStops.size());

			busStops.removeAll(expectedStops.getValue());
			Assert.assertTrue("Unexpected stops in list", busStops.isEmpty());
		}
	}

	@Test
	public void verifyTop10BusLinesWithMostStops() {
		setUpTestData(new FetchTestData());

		//95, 55, 655, 75, 66, 23, 25, 35, 45, 355
		List<String> expectedTopLines = new ArrayList<>( List.of("55", "95", "23", "655", "66", "75", "25", "35","355", "45"));

		List<String> lines = manager.getTop10BusLineWithMostStops();
		Assert.assertEquals("Unexpected number of lines returned", expectedTopLines.size(), lines.size());

		Assert.assertTrue("Unexpected order of stops " + lines, lines.equals(expectedTopLines));

	}
}
