
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon (String dna, int startIndex, String stopCodon){
        //find the index of stopcodon
        int firstOccur = dna.indexOf(stopCodon, startIndex);
        
        while (firstOccur != -1){
        if ((firstOccur - startIndex) % 3 == 0){
            //length should be a multiple of 3
            return firstOccur;
        }
        else{
            firstOccur = dna.indexOf(stopCodon, firstOccur + 1);
        }
        }
        //no strand found, return length of dna
        return dna.length();
        
        
    }
    
    public String findGene (String dna, int somewhere){
        
        String startCodon = "ATG";
        
        int firstatg = dna.indexOf(startCodon, somewhere);
        //if no atg, empty string
        if (firstatg == -1){
            return "";
        }
        
        String stopCodon = "TAA";
        int startIndex = firstatg;
        //find stop codon after start codon
        int indexTAA = findStopCodon(dna, startIndex, stopCodon);
        //find tag
        stopCodon = "TAG";
        int indexTAG = findStopCodon(dna, startIndex, stopCodon);
        //find tga
        stopCodon = "TGA";
        int indexTGA = findStopCodon(dna, startIndex, stopCodon);
        int minIndex = 0;
        //if TAA not exist or both exist and TAG shorter than TAA, assign min to TAG
        if (indexTAA == -1 ||
            indexTAG != -1 && indexTAG < indexTAA){
                minIndex = indexTAG;
            }
        else {
            minIndex = indexTAA;
        }
        //if min not exist or both exist and tga < min, assign tga.
        if (minIndex == -1 ||
            indexTGA != -1 && indexTGA < minIndex){
                minIndex = indexTGA;
            }
        // if no gene exist, minIndex not exist, return empty
        if (minIndex == -1){
            return "";
        }
        
        return dna.substring(startIndex, minIndex+3);
            
        
    }
    
    public void printAllGenes(String dna){
        
        int startIndex = 0;
            //define current printing gene

        while (true) {
            
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()){
                break;

            }
            System.out.println("Found gene is " + currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
    
    
    public void testFindStopCodon(){
        String dna = "CGATGGTTGATAAGCCTAAGCTATAA";
        int startIndex = 0;
        String stopCodon = "TAA";
        int result = findStopCodon(dna, startIndex, stopCodon);
        System.out.println("DNA strand is " + result);
        
        dna = "ATGATTTACGCTGCTGATAATAATAA";
        startIndex = 0;
        stopCodon = "TAA";
        result = findStopCodon(dna, startIndex, stopCodon);
        System.out.println("DNA strand is " + result);
        
        
        
        
    }
    
    public void testFindGene(){
        String dna = "AATGCTAACTAGCTGACTAAT";
        int start = 0;
        String result = findGene(dna,start);
        System.out.println("DNA strand is " + dna);
        System.out.println("gene is " + result);
        
        dna = "CGACGACGACGA";
        result = findGene(dna,start);
        System.out.println("DNA strand is " + dna);
        System.out.println("gene is " + result);
        
        dna = "ATGATCTAG";
        result = findGene(dna,start);
        System.out.println("DNA strand is " + dna);
        System.out.println("gene is " + result);
        
        dna = "ATGATCTGATAGTAA";
        result = findGene(dna,start);
        System.out.println("DNA strand is " + dna);
        System.out.println("gene is " + result);
    }
    
    public void testOn(){
        String dna = "CGATGGTTGATAAGCCTAAGCTATAA";
        System.out.println("Testing DNA is " + dna);
        printAllGenes(dna);
        
        dna = "";
        System.out.println("Testing DNA is " + dna);
        printAllGenes(dna);
        
        dna = "ATGATTATGTATGGGTAATATATGATAATATGA";
        System.out.println("Testing DNA is " + dna);
        printAllGenes(dna);
        
        
        
    }
}
