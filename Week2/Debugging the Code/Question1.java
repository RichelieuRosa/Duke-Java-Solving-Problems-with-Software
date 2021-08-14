
/**
 * Write a description of Question1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Question1 {
    public void findAbc(String input) {
    int index = input.indexOf("abc");
    while (true) {
        // System.out.println("index " + index);
        if (index == -1) {
            break;
        }
        // when index+4 > length, we have to stop it
        if (index >= input.length() - 3){
            break;
        }
        
        String found = input.substring(index+1, index+4);
        
        System.out.println(found);
        
        index = input.indexOf("abc", index+3);
        // System.out.println("index after updating " + index);
    }
}
   public void test() {
    //findAbc("abcd");
    //       0123456
    findAbc("abcabcabcabca");
    //       01234567890123456789012345678901234567890123456
    // findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
    
    
    
}
}
