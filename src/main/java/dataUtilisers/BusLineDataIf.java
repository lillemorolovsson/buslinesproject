package dataUtilisers;

import java.util.List;

public interface BusLineDataIf {

		public static class BusLineInfo {

			/**
			 * Unique id of the busline.
			 * */
			public String lineNumber;

			/**
			 * The direction of the line, 1 or 2 */
			public String direction;

			public List<String> busStops;

			/** Number of busstops on this line. */
			public int numberOfStops;
		}


		/** Returns a list with the 10 busLines with most stops. Only the
		 * line direction with most stops is counted, each busline can thus only
		 * be in the list once.
		 * Will never return more than 10 lines.
		 * Secondary sorting is on the lineNumber in ascending order.
		 *
		 * @return non-null List<>
		 */
		List<String> getTop10BusLineWithMostStops();

		/** Returns a list with the busLines with the most stops.
		 * Only the line direction with most stops is counted, each bus line can thus only
		 * be in the list once.
		 *
		 * It will never return more than input number of busLines.
		 * Secondary sorting is on the lineNumber in ascending order.
		 *
		 * @param numberofItemsToReturn  The number of items to return from the top of the list.
		 * 						e.g. 10 will thus return the top 10 bus lines.
		 *
		 * @return non-null List<>
		 */
		List<BusLineInfo> getBusLineWithMostStops(int numberofItemsToReturn);

		/**
		 * Returns the id of all bus stops for the input bus line, i.e. all unique
		 * bus stops for both directions.
		 *
		 * @param lineNumber		The id of the busline in question.
		 * @return non-null List
		 */
		List<String> getAllBusStopsForBusLine(String lineNumber);


		/**
		 * Returns the id of all bus stops for the input bus line and direction.
		 *
		 * @param lineNumber			The Id of the busLine
		 * @param direction				Refers to the direction of the line. "1" or "2"
		 */
		List<String> getAllBusStopsForBusLine(String lineNumber, String direction);

		/**
		 * @return 	the total number of bus lines. Zero if no buslines are found.
		 */
		long getTotalNumberOfBusLines();

}
