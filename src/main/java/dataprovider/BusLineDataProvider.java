package dataprovider;

import java.util.List;
import java.util.Map;

public interface BusLineDataProvider {
	public Map<String, BusLineInformation>  getData();

	public boolean isTimeToUpdate();

	public class BusLineInformation {

		public String lineNumber;
		public String busStopId;
		public List<String> busStopIdsDirection1;
		public List<String> busStopIdsDirection2;

		/**
		 * Contains the number of busStops in the busStopList that contains most
		 * bus stops
		 */
		public int	numberOfBusStops;

	}
}
