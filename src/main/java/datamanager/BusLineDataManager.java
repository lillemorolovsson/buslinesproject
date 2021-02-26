package datamanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import dataUtilisers.BusLineDataIf;
import dataprovider.BusLineDataJSONParser;
import dataprovider.BusLineDataProvider;
import dataprovider.BusLineDataProvider.BusLineInformation;
import dataprovider.TrafikLabbComm;

public final class BusLineDataManager implements BusLineDataIf {

	private List<BusLineInformation> allBusLineData = new ArrayList<>();
	private Map<String, BusLineInformation> allBusLineInfoPerLine;
	private boolean isInitialised;

	private BusLineDataProvider dataProvider;
	private int totalNumberOfBusLines;

	public BusLineDataManager() {
		this.dataProvider = new BusLineDataJSONParser();
	}

	public BusLineDataManager(TrafikLabbComm externalCaller) {
		this.dataProvider = new BusLineDataJSONParser(externalCaller);
	}

	public void initAndFetchData() {
		if (!isInitialised || dataProvider.isTimeToUpdate()) {

			if (allBusLineInfoPerLine != null) {
				allBusLineInfoPerLine.clear();
			}

			allBusLineInfoPerLine = dataProvider.getData();
			if (allBusLineInfoPerLine != null && !allBusLineInfoPerLine.isEmpty()) {
				allBusLineData.clear();
				allBusLineData.addAll(allBusLineInfoPerLine.values());
				allBusLineData.sort(new SortNumberOfPatternPointNumbersDesc());

				totalNumberOfBusLines = allBusLineData.size();
				isInitialised = true;
			}
			else {
				allBusLineInfoPerLine = new HashMap<>();
			}
		}
	}

	private List<BusLineInformation> getSubListOfTopLines(int numberOfItemsToReturn) {
		if (numberOfItemsToReturn <  0) {
			return new ArrayList<>();
		}

		if (totalNumberOfBusLines <= numberOfItemsToReturn) {
			return allBusLineData.subList(0, totalNumberOfBusLines);
		}
		else {
			return allBusLineData.subList(0, numberOfItemsToReturn);
		}
	}

	private void lateInit() {
		if (!isInitialised) {
			initAndFetchData();
		}
	}

	@Override
	public List<String> getTop10BusLineWithMostStops() {

		lateInit();

		List<String> topTenLineNumbers = new ArrayList<>();
		List<BusLineInformation> topTenList = getSubListOfTopLines(10);
		if (topTenList == null) {
			return new ArrayList<>();
		}
		topTenList.stream().forEach(p -> topTenLineNumbers.add(p.lineNumber));
		return topTenLineNumbers;
	}

	@Override
	public List<BusLineInfo> getBusLineWithMostStops(int pNumberOfItems) {
		lateInit();

		List<BusLineInformation> topList = getSubListOfTopLines(pNumberOfItems);
		if (topList == null) {
			return new ArrayList<>();
		}

		List<BusLineInfo> info = topList.stream()
			.map(p -> createBusLineInfo(p))
			.collect(Collectors.toList());

		return info;
	}

	private BusLineInfo createBusLineInfo(BusLineInformation busLine) {
		lateInit();

		BusLineInfo info = new BusLineInfo();
		info.lineNumber = busLine.lineNumber;

		info.numberOfStops = busLine.numberOfBusStops;
		info.direction = busLine.busStopIdsDirection1.size() == info.numberOfStops ?
				"1" : "2";
		info.busStops = info.direction.equals("1") ?
				busLine.busStopIdsDirection1 : busLine.busStopIdsDirection2;
		return info;
	}

	@Override
	public List<String> getAllBusStopsForBusLine(String lineNumber) {

		//try catch here or in caller method??
		lateInit();

		BusLineInformation lineInfo = allBusLineInfoPerLine.get(lineNumber);
		if (lineInfo == null) {
			return new ArrayList<>();
		}

		Set<String> tAllBusStops = new HashSet<>();
		tAllBusStops.addAll(lineInfo.busStopIdsDirection1);
		tAllBusStops.addAll(lineInfo.busStopIdsDirection2);

		return new ArrayList<>(tAllBusStops);
	}

	@Override
	public List<String> getAllBusStopsForBusLine(String lineNumber, String direction) {
		lateInit();

		BusLineInformation lineInfo = allBusLineInfoPerLine.get(lineNumber);
		if (lineInfo == null) {
			return new ArrayList<>();
		}

		Set<String> tAllBusStops = new HashSet<>();
		if (direction.equals(BusLineDataJSONParser.cDirectionCode1)) {
			tAllBusStops.addAll(lineInfo.busStopIdsDirection1);
		}
		else {
			tAllBusStops.addAll(lineInfo.busStopIdsDirection2);
		}

		return new ArrayList<>(tAllBusStops);
	}

	@Override
	public long getTotalNumberOfBusLines() {
		return totalNumberOfBusLines;
	}



}
