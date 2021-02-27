# Buslinesproject

Test program for retrieving data from TrafikLabb's _SL Hållplatser och Linjer 2_ API. 

Go to https://www.trafiklab.se/ and register as a user. Create a project that uses _SL Hållplatser och Linjer 2_, 
and create a key for the project. Add that key to _TrafikLabbCaller.JOUR_KEY_.

Create a new instance of class _BusLineDataManager_ and call it's methods. 
An empty constructor call will access TrafikLabbs API, but it is also possible to send in one's own 
implementation of _TrafikLabComm_ to run towards alternativ data, for e.g. test purposes. 

Each instance of _BusLineDataManager_ will fetch data from TrafikLabb once per day, using cached 
data for subsequent calls. There is an _initAndFetchData()_ that can be called to choose when to 
initiate the cache. If no call is made, there will a late initialisation upon first request for data. 

There is a JUnit test class added where all tests run towards test data, and also a testClient which runs
towards TrafikLabb and prints some output. 

## Note
There is a limit to the number of calls allowed to TrafikLabb per hour and month for the supplied KEY. It is
therefore advicable to use the same instance of _BusLineDataManager_ for multiple calls, instead of 
creating a new one for each call.

 