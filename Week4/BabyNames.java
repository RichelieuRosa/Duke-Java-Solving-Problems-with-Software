


import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;


public class BabyNames {
    // Method1 : count total birth
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int boyname = 0;
        int girlname = 0;
        
        
        for (CSVRecord rec: fr.getCSVParser(false)){
            int numBirth = Integer.parseInt(rec.get(2));
            totalBirths += numBirth;            
            if (rec.get(1).equals("M")){                
                totalBoys += numBirth;
                boyname ++;
            }
            else{
                totalGirls += numBirth;
                girlname++;
            }       
        }
        System.out.println("total births in the year " + totalBirths);
        System.out.println("total boys in the year " + totalBoys);
        System.out.println("total girls in the year " + totalGirls);
        System.out.println("total boys name " + boyname);
        System.out.println("total girl name " + girlname);
    }
    //Test 1 count total birth
    public void testtotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
        
    }
    //Method 2 rank the name for given gender
    public int getRank (int year, String name, String gender){
        String filename = "yob" + year + ".csv";
        FileResource fr = new FileResource(filename);
        //initialize rank
        int rank = 0;
        //test exist or not
        int exist = 0;
        for (CSVRecord rec: fr.getCSVParser(false)){
            
            if (rec.get(1).equals(gender)){
                rank = rank +1;
                if (rec.get(0).equals(name)){
                    exist = 1;
                    break;
                }
            }

        }
        if (exist != 1){
            return -1;
        }
        else{
            return rank;
        }                        
    }
    //Test 2 rank with given factors
    public void testgetRank (){
        
        System.out.println(getRank(1971, "Frank", "M"));

    }
    //Method 3 get person at this rank
    public String getName  (int year, int rank, String gender){
        String filename = "yob" + year + ".csv";
        FileResource fr = new FileResource(filename);
        //initialize rank
        String name = "";
        //test exist or not
        int currentRow = 0;
        int exist = 0;
        
        
        for (CSVRecord rec: fr.getCSVParser(false)){
            
            if (rec.get(1).equals(gender)){
                currentRow ++;
                if (currentRow == rank){
                    exist = 1;
                    name = rec.get(0);
                    break;
                }
            }

        }
        if (exist == 1){
            return name;
        }
        else{
            return "NO NAME";
        }                        
                        
    }
    //Test 3 get name
    public void testgetName(){
        System.out.println(getName (1982, 450, "M"));   
    }
    //Method 4 Examine name in new year
    public String whatIsNameInYear (String name, int year, int newYear, String gender){
        String filename = "yob" + year + ".csv";
        FileResource fr = new FileResource(filename);
        
        int rank = getRank(year, name, gender);
        String newName = "";
        String gen = "";
        if (gender == "M"){
            gen = "he";
        }
        else{
            gen = "she";
        }
        
        if (rank != -1){
            newName = getName(newYear, rank, gender);
            return (name + " born in " + year + " would be " + newName + 
                " if " + gen + " was born in " + newYear);
        }
        else{
            return "No such name is found in this year";
        }
        
        
    }
    
    //Test 4 new name in new year
    public void testwhatIsNameInYear(){
        System.out.println(whatIsNameInYear("Owen",1974,2014,"M"));
    }
    
    //Method 5 highest year rank
    public String yearOfHighestRank (String name, String gender){
        DirectoryResource dr = new DirectoryResource();
               
        
        int highestSoFar = 0;
        String highfile = "";
        int existInAll = 0;
        
        for (File f: dr.selectedFiles()){
            //read file in the directory 
            int rank = 0;
            int exist = 0;
            
            String fname = f.getName();
            FileResource fr = new FileResource(fname);
            
            for (CSVRecord rec: fr.getCSVParser(false)){
                if (rec.get(1).equals(gender)){
                    rank ++;
                    if (rec.get(0).equals(name)){
                        exist = 1;
                        if (highestSoFar == 0){
                                highestSoFar = rank;
                                highfile = fname;
                            }
                        else if (rank < highestSoFar){
                                highestSoFar = rank;
                                highfile = fname;
                            }
                        break;
                    }
                }
                
            }
            if (exist == 1){
                existInAll = 1;
                
            }
                    
                
        }
        if (existInAll !=1){
            return "NOT FOUND";
        }
        else{
            return highfile;
        }
        
    }
    
    //Test5 highest rank of a name in files
    
    public void testyearOfHighestRank(){
        
        System.out.println(yearOfHighestRank("Mich","M"));
    }
    
    //Method 6 average rank of a name
    
    public double getAverageRank  (String name, String gender){
        DirectoryResource dr = new DirectoryResource();
               
        double totalRank = 0;
        int countFiles = 0;
        int existInAll = 0;
        
        for (File f: dr.selectedFiles()){
            //read file in the directory 
            //IMPORTANT: renew numbers after a file
            int rank = 0;
            int exist = 0;
            
            
            
            String fname = f.getName();
            FileResource fr = new FileResource(fname);
            
            for (CSVRecord rec: fr.getCSVParser(false)){
                if (rec.get(1).equals(gender)){
                    rank ++;
                    if (rec.get(0).equals(name)){
                        countFiles ++;
                        totalRank = totalRank + rank;
                        System.out.println(totalRank + " rank "+ rank);
                        exist = 1;
                        break;
                    }
                }
                
            }
            if (exist == 1){
                existInAll = 1;
                
            }
                    
                
        }
        if (existInAll !=1){
            return -1;
        }
        else{
            
            return totalRank/countFiles;
        }
        
    }
    
    //Test 6 average rank of a name
    public void testgetAverageRank(){
        
        System.out.println(getAverageRank("Robert","M"));
        
    }
    
    //Method 7 find all people rank higher than target
    public int getTotalBirthsRankedHigher (int year, String name, String gender){
        String filename = "yob" + year + ".csv";
        FileResource fr = new FileResource(filename);
        int exist = 0;
        int sum = 0;
        for (CSVRecord csr: fr.getCSVParser(false)){
            if (csr.get(1).equals(gender)){
                if(csr.get(0).equals(name)){
                    exist = 1;
                    break;
                }
                sum = sum + Integer.parseInt(csr.get(2));
            }
            
        }
        if (exist == 1){
           return sum;
        }
        else{
            return -1;
        }
       
    }
    //Test last get higher rank than target
    public void testgetTotalBirthsRankedHigher(){
        System.out.println(getTotalBirthsRankedHigher(1990,"Emily","F"));
    }
}



