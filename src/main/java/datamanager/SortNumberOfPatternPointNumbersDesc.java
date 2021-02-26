package datamanager;

import java.util.Comparator;

import dataprovider.BusLineDataProvider.BusLineInformation;

public class SortNumberOfPatternPointNumbersDesc implements Comparator<BusLineInformation>  {

	@Override
	public int compare(BusLineInformation arg0, BusLineInformation arg1) {

		if (arg0.numberOfBusStops < arg1.numberOfBusStops) {
			return 1;
		}

		if (arg0.numberOfBusStops > arg1.numberOfBusStops) {
			return -1;
		}

		return (arg0.lineNumber.compareTo(arg1.lineNumber));
	}


}
