# parking-lot

This is a sample parking lot which allows the following features :
* Park a vehicle
* Leave the parking
* parking slot numbers of all vehicles with certain driver age
* Parking slot number for vehicle with given registration number
* Registration numbers of vehicles with certain age

## How to build the application

* Run the below command to build the project
```bash
mvn clean install
````

## How to run the application

There are 2 ways to run the application:

* Run the below command after you have successfully built the jar
```bash
java -jar parking-lot-1.0.jar
```
OR 

* If you wish to run the app in an IDE, make sure you have imported the project properly and simply run the main class i.e ParkingLotApp

<br />
    
`Note`: It requires an input txt file which contains parking instructions. There are 2 ways to provide the input file.
* pass the file path as program argument e.g. 
```bash
java -jar parking-lot-1.0.jar /Users/vivekthusu/Documents/FIRE/input.txt
```
OR
* mention the file path in the properties file in src/main/resources ([application.properties](https://github.com/VivekThusu/parking-lot/blob/master/src/main/resources/application.properties))

* If no input is provided, then the program reads a sample file ([input.txt](https://github.com/VivekThusu/parking-lot/blob/master/src/main/resources/input.txt)) present in the resources directory
    
