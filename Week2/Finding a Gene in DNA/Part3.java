
/**
 * Yanbo Liang 
 * 2021.08.13
 */

public class Part3 {
    
   public boolean twoOccurrences(String stringa, String stringb){
   
   //index of first appearance
   int appear1 = stringb.indexOf(stringa);
   //index of second appearance
   int appear2 = stringb.indexOf(stringa, appear1 + stringa.length());
   
   if (appear1 == -1){
       return false;
   } else if (appear2 == -1){
       return false;
   }else {
       return true;
   }

       
   }
    
   public void testing(){
       
   System.out.println("***THIS IS A NEW TEST***");
       
   String stringa = "by";
   String stringb = "A story by Abby Long";
   boolean answer = twoOccurrences(stringa, stringb);
   System.out.println("string a = " + stringa + "string b = " + stringb);
   System.out.println(answer);
  
   
   stringa = "a";
   stringb = "banana";
   answer = twoOccurrences(stringa, stringb);
   System.out.println("string a = " + stringa + "string b = " + stringb);
   System.out.println(answer);
   
   stringa = "atg";
   stringb = "ctgtatgta";
   answer = twoOccurrences(stringa, stringb);
   System.out.println("string a = " + stringa + "string b = " + stringb);
   System.out.println(answer);

       
   }
   
   public String lastPart(String stringa, String stringb){
       
   int afterA = stringb.indexOf(stringa);
   
   if (afterA == -1){
       //if no string a found, return full string b
       return stringb;
   } else{
       //if string a found, obtain the letter after a to the end
       //NOTE: substring NOT include the last point (stringb.length())
       String lastB = stringb.substring(afterA + stringa.length(), stringb.length());
       return lastB;
       
    }
       
       
   }
   
   public void testinglast(){
   
   String stringa = "an";
   String stringb = "banana";
   String last = lastPart(stringa, stringb);
   //System.out.println(stringb.length());
   System.out.println(last);
   
   stringa = "zoo";
   stringb = "forest";
   last = lastPart(stringa, stringb);
   System.out.println(last);
    
    
    
    
    }

}
