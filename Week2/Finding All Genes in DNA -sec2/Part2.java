
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {

    public int howMany (String stringa, String stringb){
        
        int startInd = 0;
        int count = 0;
        //find first stringa position
        int indexA = stringb.indexOf(stringa, startInd);
        
        while (indexA != -1){
            //if succeed on finding stringa, count+1
            count +=1;
            //update index, find stringa after **previous finding**
            indexA = stringb.indexOf(stringa, indexA + stringa.length());

        }
        
        return count;
    }
    
    
    public void testHowMany(){
        String stringa = "GAA";
        String stringb = "ATGAACGAATTGAATC";
        System.out.println(howMany(stringa,stringb));
        
        
        stringa = "AA";
        stringb = "ATAAAA";
        System.out.println(howMany(stringa,stringb));
        
        stringa = "BB";
        stringb = "BBBBB";
        System.out.println(howMany(stringa,stringb));
        
        
        
        
    }
    
    
    
    
    
}
