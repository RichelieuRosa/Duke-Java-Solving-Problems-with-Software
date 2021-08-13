


public class Part2 {
    

    public String findSimpleGene (String dna, String startCodon, String stopCodon){
    //set result as none
    String results = "";
    //Capitalize all letters
    String bigDNA = dna.toUpperCase();
    //find the start index
    
    int StartIndex = bigDNA.indexOf(startCodon);

    if (StartIndex == -1){
        // no ATG find, return none
        return "";
    }
    //find the last index
    int StopIndex = bigDNA.indexOf(stopCodon);
    if (StopIndex == -1){
        return "";
    }
    //find if the gene has a multiple of 3
    if ((StopIndex - StartIndex) %3 !=0){
        return "Not a real gene";
    }
    //record result
    if (dna.substring(StartIndex,StartIndex+3) == "atg"){
        results = results.toLowerCase();
    }
    results = dna.substring(StartIndex,StopIndex+3); //from start to end (end length =3)

    return results;    
    }
   
    
    public void testSimpleGene (){
        String startCodon = "ATG";
        String stopCodon = "TTA";
        
        String dna = "AATGCTGCTGTTA";
        System.out.println("DNA strand is " + dna);
        String gene = findSimpleGene(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene);
        
        dna = "ATCCTGCACTCATGTTA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene);
        
        dna = "ATGAATGTGTATATTTCCGTTA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene);
        
        dna = "ATGATGATGATGAAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene);
        
        dna = "ATTTTTGGGGTGTGTTA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene);
        
        dna = "atgaaatta";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene);
        
    }
    
    
    
    
}

