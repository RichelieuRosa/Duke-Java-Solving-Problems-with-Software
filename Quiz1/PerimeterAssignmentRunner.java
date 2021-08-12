import edu.duke.*;
import java.io.File;

//Assignment 1 and 2 should be seperately ran! 
//Comment testPerimeter or testMultiple (2 functions) to run quiz1-4





public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int totalNumPoints = 0;
        //Start with first point
        Point prevPt = s.getLastPoint();
        //loop all points
        for (Point currPt: s.getPoints()){
            totalNumPoints +=1;
            
            prevPt = currPt;
        }
        
        return totalNumPoints;
    }

    public double getAverageLength(Shape s) {
        // average length = perimeter/number of side
        double Sum = getPerimeter(s);
        int Numofside = getNumPoints(s);
        
        double AvgSide = Sum / Numofside;
        
        return AvgSide;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double LargestSide = 0.0;
        // Start with prevPt
        Point prevPt = s.getLastPoint();
        // loop to find the longest
        for (Point currPt: s.getPoints()){
            // find distance from prevPt to currPt
            double currDist = prevPt.distance(currPt);
            
            if (currDist > LargestSide){
                //update largest each loop
                LargestSide = currDist;
                
            }
            //Update prevPt to currPt
            prevPt = currPt;
        }
       
        
        
        return LargestSide;
    }

    public double getLargestX(Shape s) {
                
        double LargestX = 0.0;
        // Start with prevPt
        Point prevPt = s.getLastPoint();
        // loop to find the longest
        for (Point currPt: s.getPoints()){
            // find distance from prevPt to currPt
            double currX = prevPt.getX();
            
            if (currX > LargestX){
                //update largest each loop
                LargestX = currX;
                
            }
            //Update prevPt to currPt
            prevPt = currPt;
        }
        return LargestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Create a directory selector
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0.0;
        File largestFile = null;
        
        for (File f: dr.selectedFiles()){
            FileResource file = new FileResource(f);
            Shape sh = new Shape(file);
            double Perim = getPerimeter(sh);
            
            if (Perim>largestPerim){
                largestPerim = Perim;
            }

        }

        
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0.0;
        File largestFile = null;
        
        for (File f: dr.selectedFiles()){
            FileResource file = new FileResource(f);
            Shape sh = new Shape(file);
            double Perim = getPerimeter(sh);
            
            if (Perim > largestPerim){
                largestPerim = Perim;
                largestFile = f;
            }

        }

        
        return largestFile.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        System.out.println("*** A New Running ***");
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int NumPoint = getNumPoints(s);
        System.out.println("Number of points = " + NumPoint);
        double Avglength = getAverageLength(s);
        System.out.println("Average side length = " + Avglength);
        double LongestSide = getLargestSide(s);
        System.out.println("Largest side = " + LongestSide);
        double largestX = getLargestX(s);
        System.out.println("Largest X = " + largestX);
        
        testPerimeterMultipleFiles();
        testFileWithLargestPerimeter();
    }
    
    public void testPerimeterMultipleFiles() {
        //
        
        double LargestP = getLargestPerimeterMultipleFiles();
        System.out.println("Largest perimeter = " + LargestP);
        
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        
        String LargeFile = getFileWithLargestPerimeter();
        System.out.println("Largest file name = " + LargeFile);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        // pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
