import edu.duke.FileResource;
import edu.duke.StorageResource;

import edu.duke.*;
import java.io.File;


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
        
        if (minIndex >= dna.length() - 4){
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
    // NEW update in Assignment 3
    public StorageResource getAllGenes(String dna){
        
        //create a new storage list
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        
            //define current printing gene

        while (true) {
            
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()){
                break;

            }
            //add this element into list
            geneList.add(currentGene);
            
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        
        return geneList;
    }
    
    public double cgRatio(String dna){

        double countC = 0.0;
        double countG = 0.0;
        int indexC = dna.indexOf("C");
        int indexG = dna.indexOf("G");
        while (indexC != -1){
            countC += 1;
            
            indexC = dna.indexOf("C", indexC +1);

        }
        
        while (indexG != -1){
            countG += 1;
            
            indexG = dna.indexOf("G", indexG +1);

        }
        
        double Ratio = (countC + countG) / dna.length();
        
        return Ratio;

    }
    
    public int countCTG(String dna){
        
        int count = 0;
        int indexctg = dna.indexOf("CTG");
        
        while (indexctg != -1){
            count += 1;
            
            indexctg = dna.indexOf("CTG", indexctg + 3);

    }
    
        return count;
    }
    
    public void processGenes (StorageResource sr){
        
        int count = 0;
        int countcg = 0;
        int longest = 0;
        for (String g: sr.data()){
            
            if (g.length() > 60){
                count += 1;
                //print the name of long string
                System.out.println("Longer than 9 strings are: " + g);
                
            }
            
            if (cgRatio(g) >0.35){
                countcg += 1;
                System.out.println("high c-g ratio strings are: " + g);
                
            }
            
            if (g.length() > longest){
                
                longest = g.length();
                
            }
        }
        //print the number of long strings
        System.out.println("We have long strings: " + count);    
        System.out.println("We high c-g ratio cases: " + countcg);  
        System.out.println("Our longest cases have a length of: " + longest);  
    }
    
    public void testProcessGenes(){
        
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();

        StorageResource geneList = getAllGenes(dna);
        int count = 0;
        for (String g: geneList.data()){
            count += 1;
        }
        System.out.println("Total count is " + count);
        
        System.out.println("how many CTG: " + countCTG(dna));
        // sr.add("CTCTCTCTATGATTATATTTTGA");
        // sr.add("CTCTC");
        // sr.add("CGCGCCGCGCGCGCGCGATGTTA");
        // sr.add("ATATTATATTATCGATATTATTTA");
        // sr.add("CTCTCTCTATGATTATATTTTGAAAGATAGA");
        
        System.out.println(geneList);
        processGenes(geneList);
        
        
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
    // This could be used to test storage!
    public void testOn(){
        String dna = "CGATGGTTGATAAGCCTAAGCTATAA";
        System.out.println("Testing DNA is " + dna);
        StorageResource genes = getAllGenes(dna);
        for (String g: genes.data()){
            System.out.println("Gene found is " + g);
        }
        
        
        dna = "";
        System.out.println("Testing DNA is " + dna);
        genes = getAllGenes(dna);
        for (String g: genes.data()){
            System.out.println("Gene found is " + g);
        }
        
        dna = "ATGATTATGTATGGGTAATATATGATAATATGA";
        System.out.println("Testing DNA is " + dna);
        genes = getAllGenes(dna);
        for (String g: genes.data()){
            System.out.println("Gene found is " + g);
        }
        
        
        
    }
}

