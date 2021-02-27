package datamanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import dataprovider.BusLineDataJSONParser;
import dataprovider.BusLineDataProvider;
import dataprovider.BusLineDataProvider.BusLineInformation;
import dataprovider.TrafikLabbComm;

public final class BusLineDataManager implements BusLineDataIf {

	private List<BusLineInformation> allLineInformation = new ArrayList<>();
	private Map<String, BusLineInformation> informationByLineNumber;
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

			if (informationByLineNumber != null) {
				informationByLineNumber.clear();
			}

			informationByLineNumber = dataProvider.getData();
			if (dataWasFound()) {
				mapToInternalStrutures();
				isInitialised = true;
			}
			else {
				informationByLineNumber = new HashMap<>();
			}
		}
	}

	private void mapToInternalStrutures() {
		allLineInformation.clear();
		allLineInformation.addAll(informationByLineNumber.values());
		allLineInformation.sort(new SortNumberOfPatternPointNumbersDesc());

		totalNumberOfBusLines = allLineInformation.size();
	}

	private boolean dataWasFound() {

		return informationByLineNumber != null && !informationByLineNumber.isEmpty();
	}

	private void lateInit() {
		if (!isInitialised) {
			initAndFetchData();
		}
	}

	@Override
	public List<String> getTop10BusLineWithMostStops() {
		return getBusLineWithMostStops(10)
				.stream()
				.map(line -> line.lineNumber)
				.collect(Collectors.toList());
	}

	@Override
	public List<BusLineInfo> getBusLineWithMostStops(int pNumberOfItems) {
		lateInit();

		List<BusLineInformation> topList = getSubListOfTopLines(pNumberOfItems);
		return topList.stream()
				.map(info -> createBusLineInfo(info))
				.collect(Collectors.toList());
	}

	private List<BusLineInformation> getSubListOfTopLines(int numberOfItemsToReturn) {
		return allLineInformation.stream()
				.limit(Math.max(0, numberOfItemsToReturn))
				.collect(Collectors.toList());
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

        lateInit();

        List<String> returnList = new ArrayList<>();

        BusLineInformation lineInfo = informationByLineNumber.get(lineNumber);
        if (lineInfo == null) {
            return returnList;
        }

        Set<String> tAllBusStops = new HashSet<>();
        tAllBusStops.addAll(lineInfo.busStopIdsDirection1);
        tAllBusStops.addAll(lineInfo.busStopIdsDirection2);
        returnList.addAll(tAllBusStops);

        return returnList;
    }

    @Override
    public List<String> getAllBusStopsForBusLine(String lineNumber, String direction) {
        lateInit();

        List<String> returnList = new ArrayList<>();
        BusLineInformation lineInfo = informationByLineNumber.get(lineNumber);
        if (lineInfo == null) {
            return returnList;
        }

        returnList.addAll(BusLineDataProvider.DIRECTION1.equals(direction) ?
        		lineInfo.busStopIdsDirection1 : lineInfo.busStopIdsDirection2);

        return returnList;
    }

	@Override
	public long getTotalNumberOfBusLines() {
		return totalNumberOfBusLines;
	}

}
