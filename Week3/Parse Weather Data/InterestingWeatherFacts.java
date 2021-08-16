
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class InterestingWeatherFacts {
    //Test1 in a file find coldest time
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord lowestRow = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temp was " + lowestRow.get("TemperatureF") + " at  " 
        + lowestRow.get("DateUTC"));
        
    }
    //Test 2 in multiple files find a coldest time
    public void testFileWithColdestTemperature(){
        
        String filename = fileWithColdestTemperature();
	FileResource fr = new FileResource(filename);
	CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
	
        //we only output the file name!
        int fnameIndex = filename.lastIndexOf("\\");
        System.out.println("Coldest day was in file is " + filename.substring(fnameIndex+1));
        System.out.println("Coldest temp on that day was " + 
        smallest.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were:");
        
        //print all lines in the csv file
        for (CSVRecord current: fr.getCSVParser()){
            //print date + real temp
            System.out.println(current.get("DateUTC") + ": " + current.get("TemperatureF"));

        }
        
        
    }
    //Test 3 humidity
    public void testLowestHumidityInFile(){
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at "
            + csv.get("DateUTC"));
        
        
    }
    //Test 4 many humidity
    public void testLowestHumidityInManyFiles(){
        
        CSVRecord smallest = lowestHumidityInManyFiles();
        
        System.out.println("Lowest Humidity was " + smallest.get("Humidity") +
            " at " + smallest.get("DateUTC"));
        
    }
    
    //Test5 1 average
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double csv = averageTemperatureInFile(parser);
        
        
        System.out.println("Average temperature in file is " + csv);
        
        
    }
    //Test6 average with high humidity
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int value = 80;
        averageTemperatureWithHighHumidityInFile (parser,value);
        
    }
    //coldest 1
    public CSVRecord coldestHourInFile (CSVParser parser){
        
        CSVRecord lowestRow = null;
        
        for (CSVRecord currentRow: parser){
            
            lowestRow = getSmallestOfTwo(currentRow, lowestRow);

        }
        
        return lowestRow;
    }
    //coldest many
    public String fileWithColdestTemperature(){
        //iterate all 
        CSVRecord lowestRow = null;
        File filename = null;

        DirectoryResource dr = new DirectoryResource();

        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            
            CSVRecord currentRecord = coldestHourInFile(fr.getCSVParser());
            
            if (lowestRow == null){
                lowestRow = currentRecord;
            }
            else{
            double currentTemp = Double.parseDouble(currentRecord.get("TemperatureF"));
            double lowestTemp = Double.parseDouble(lowestRow.get("TemperatureF"));
                //exclude some bogus temp (due to lack of data)
                
            if (currentTemp < lowestTemp && currentTemp != -9999){
                lowestRow = currentRecord;
                filename = f;
            }
        }
            
            
            
        }
         
        return filename.getPath();    
    }
    //humidty 1
    public CSVRecord lowestHumidityInFile (CSVParser parser){
        
        CSVRecord lowestRow = null;
        
        for (CSVRecord currentRow: parser){
            
            lowestRow = getSmallestOfHumidity(currentRow, lowestRow);

        }
        
        return lowestRow;
    }
    //humidity many
    public CSVRecord lowestHumidityInManyFiles (){
        
        DirectoryResource dr = new DirectoryResource();
        //iterate all 
        CSVRecord lowestRow = null;
        
        
        
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());

            lowestRow = getSmallestOfHumidity(currentRow, lowestRow);

        }
            
        return lowestRow;
        
    }
    //average in 1
    public double averageTemperatureInFile (CSVParser parser){
        
        int countcell = 0;
        double totalTemp = 0;
        
        for (CSVRecord currentRow: parser){
            
            countcell ++;
            
            totalTemp = totalTemp + Double.parseDouble(currentRow.get("TemperatureF"));
            
            
        }
        
        return (totalTemp/countcell);
    }
    
    //average with high humidity
    public void averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        
        int countcell = 0;
        double totalTemp = 0;
        
        for (CSVRecord currentRow: parser){
            double currentHum = Double.parseDouble(currentRow.get("Humidity"));
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if (currentHum >= value){
                countcell ++;
            
                totalTemp = totalTemp + Double.parseDouble(currentRow.get("TemperatureF"));
            
            
        }
    }
        if (totalTemp == 0){
            System.out.println("No temperatures with that humidity");
        }
        else{
        System.out.println("Average Temp when high Humidity is " + (totalTemp/countcell));
        }          
            
            
        
        
        
        
    }
    
    //find a common code temp
    public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord lowestRow){

        if (lowestRow == null){
                lowestRow = currentRow;
        }
        else{
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double lowestTemp = Double.parseDouble(lowestRow.get("TemperatureF"));
                //exclude some bogus temp (due to lack of data)
                
            if (currentTemp < lowestTemp && currentTemp != -9999){
                lowestRow = currentRow;
            }
        }
        return lowestRow;
    }
    
    // find a common pattern for humidty
    public CSVRecord getSmallestOfHumidity(CSVRecord currentRow, CSVRecord lowestRow){
        
        if (lowestRow == null){
                lowestRow = currentRow;
        }
        else{
            if (currentRow.get("Humidity") != "N/A"){
                double currentHum = Double.parseDouble(currentRow.get("Humidity"));
                double lowestHum = Double.parseDouble(lowestRow.get("Humidity"));
            
                //exclude some bogus temp (due to lack of data)
                
                if (currentHum < lowestHum){
                    lowestRow = currentRow;
                }
            }
        }
        return lowestRow;

    }

   
 }
        
        
        
        
            
            
        
    
    
    
    
    
    
    
 