//
//Yanbo Liang
//Remember to import duke to use URLResource

import edu.duke.*;
import java.io.*;


public class Part4 {
    public void findweblinks(){
        URLResource web = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        
        for (String keyword: web.words()){
            
            String wordLow = keyword.toLowerCase();
            //Start with find youtube string
            int pos = wordLow.indexOf("youtube.com");
            
            if (wordLow.contains("youtube.com")){
                //verify if it is a link (contain http)
                if (wordLow.contains("http://")){
                    //find start and end using index
                    int StartQuote = keyword.lastIndexOf("\"",pos);
                    int EndQuote = keyword.indexOf("\"",pos+1);
                    //extract address from start to end
                    //NOTE: remember to add 1 (after quote mark)
                    String webAddress = keyword.substring(StartQuote+1,EndQuote);
                    
                    System.out.println(webAddress);
                }
            }
            

        }
        
        
        
    }
}
