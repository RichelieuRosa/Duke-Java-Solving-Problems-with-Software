
//remember to import

import edu.duke.*;
import org.apache.commons.csv.*;



public class CSVexportData {
    
    //open the target file and call all functions
    public void tester(){
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        // parser = fr.getCSVParser();
        // Test 1
        // String country = "Nauru";
        // countryInfo(parser, country);
        
        // Test 2
        // String exportItem1 = "cotton";
        // String exportItem2 = "flowers";
        // listExportersTwoProducts(parser, exportItem1, exportItem2);
        
        // Test 3
        // String exportItem = "cocoa";
        // numberOfExporters(parser, exportItem);
        
        // Test 4
        String amount = "$999,999,999,999";
        bigExporters(parser, amount);
    }
    // find certain country
    public void countryInfo(CSVParser parser, String country){

        boolean result = false;
        
        for (CSVRecord record : parser){
            
            String nation = record.get("Country");
            
            //test if the target country exists first
            if (nation.contains(country)){

                String export = record.get("Exports");
            
                String value = record.get("Value (dollars)");
                
                result = true;
                
                System.out.println(country + ": " + export + ": " + value);
            
            } 

        }
        
        if (result == false){
                System.out.println("NOT FOUND");
        }

    }
    // find a country with two target products same
    public void listExportersTwoProducts (CSVParser parser, String exportItem1 , String exportItem2){
        
        for (CSVRecord record : parser){
            String export = record.get("Exports");
            //fulfill two statements at same time
            if (export.contains(exportItem1) && export.contains(exportItem2)){
                
                System.out.println(record.get("Country"));
                

            }

        }
    
    }
    
    
    public void numberOfExporters (CSVParser parser, String exportItem){
        
        int countCountry = 0;
        
        for (CSVRecord record : parser){
            
            String export = record.get("Exports");
            
            if (export.contains(exportItem)){
                
                countCountry ++;
                
            }
        
        
        }
        
        System.out.println(countCountry);
    }
    
    
    public void bigExporters (CSVParser parser, String amount){
        
        for (CSVRecord record : parser){
            
            String value = record.get("Value (dollars)");
            
            if (value.length() > amount.length()){
                
                System.out.println(record.get("Country") + " : " + value);
                
            }

        }
        
    }
}
