package dataprovider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface BusLineDataProvider {
	public Map<String, BusLineInformation>  getData();

	public boolean isTimeToUpdate();

	public static final String DIRECTION1 = TrafikLabbComm.DIRECTION_CODE_1;
	public static final String DIRECTION2 = TrafikLabbComm.DIRECTION_CODE_2;

	public class BusLineInformation {


		public BusLineInformation(String lineNumber) {
			this.lineNumber = lineNumber;
		}

		public String lineNumber;
		public String busStopId;
		public List<String> busStopIdsDirection1 = new ArrayList<>();
		public List<String> busStopIdsDirection2 = new ArrayList<>();

		/**
		 * Contains the number of busStops in the busStopList that contains most
		 * bus stops
		 */
		public int numberOfBusStops;

	}
}
