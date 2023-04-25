
# NBP API backend 

This project is a task for Dynatrace Internship 2023. This Java Server built with Spring downloads data from NBP API (http://api.nbp.pl), processes it and returns requested values.

### Starting the server

To start the server download nbpservice.jar file and run it with this command
```
java -jar nbpservice.jar
```
### Parameters
**{code}** - 3 letter string representing currency code from table A of NBP API (https://nbp.pl/en/statistic-and-financial-reporting/rates/table-a/) e.g. EUR  
**{count}** - positive integer <= 255 representing number of last quotations e.g. 20  
**{date}** - date formatted YYYY-MM-DD e.g. 2023-07-14  
### Operation 1
To query operation 1 run this command
```
curl http://localhost:8080/exchange-rate/{date}/{code}
```
or insert above URL into the browser. {date} and {code} parameters should be replaced with correct values per Parameters paragraph.   
This operation returns a single value of exchange rate formatted as double.  

### Operation 2
To query operation 2 run this command
```
curl http://localhost:8080/min-max/{code}/{count}
```
or insert above URL into the browser. {code} and {count} parameters should be replaced with correct values per Parameters paragraph.   
This operation returns a two element array of double containing consecutively minimal and maximal value of the exchange rate.  

### Operation 3
To query operation 3 run this command
```
curl http://localhost:8080/rate-difference/{code}/{count}
```
or insert above URL into the browser. {code} and {count} parameters should be replaced with correct values per Parameters paragraph.   
This operation returns a single value of the the major difference between the buy and ask rate formatted as double.   

### Examples
#### Operation 1
```
curl http://localhost:8080/exchange-rate/2023-01-02/gbp
```
will return 5.2768.

#### Operation 2
```
curl http://localhost:8080/min-max/eur/10
```
can return different value each day, e.g. 2023-04-25: [4.598, 4.666].

#### Operation 3
```
curl http://localhost:8080/rate-difference/aud/10
```
can return different value each day, e.g. 2023-04-25: 0.057.

