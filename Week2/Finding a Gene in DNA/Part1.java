

public class Part1 {
    public String findSimpleGene (String dna){
    //set result as none
    String results = "";
    //find the start index
    int StartIndex = dna.indexOf("ATG");
    if (StartIndex == -1){
        // no ATG find, return none
        return "";
    }
    int StopIndex = dna.indexOf("TAA", StartIndex + 3);  //find index after three characters of startindex
    if (StopIndex == -1){
        return "";
    }
    //find if the gene has a multiple of 3
    if ((StopIndex - StartIndex) %3 !=0){
        return "Not a real gene";
    }
    //record result
    results = dna.substring(StartIndex,StopIndex+3); //from start to end (end length =3)

    return results;    
    }
    
    
    public void testSimpleGene (){
        String dna = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("DNA strand is " + dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "ATCCTGCACTCATGTTA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "ATGAATGTGTATATTTCCGTTA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "ATGATGATGATGAAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "ATTTTTGGGGTGTGTTA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        
    }
    
    
    
    
}
