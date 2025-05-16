import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet{

    private int low; //for iterative binary search
    private int middle; //for iterative binary search
    private int high; //for iterative binary search
    private ArrayList<MyClass> arr;
    private int width = 600; //canvas
    private int height = 600; //canvas
    private String note; //message
    private boolean sort; //is it sorted?
    private int count; //keyPress count

    public static void main(String[] args){
        PApplet.main("Main");
    }

    public void settings(){
        size(width, height);

    }

    public void setup(){
        arr = new ArrayList<MyClass>();
        for (int i = 0; i < 30; i++){ //initialize the arraylist
            arr.add(new MyClass(30-i));
        }
        low = 0;
        high = arr.size()-1;
    }

    public void draw(){
        String starting;
        background(255);
        for (int i = 0; i < arr.size(); i++){
            fill(205,242,122);
            rect(width/arr.size() * i, height/2, width/arr.size(), height/arr.size());
        }
        starting = "Instructions: \n 1. CLICK ANY KEY TO SORT \n 2. Enter a number between 0 & 29: \n 3. Click enter to start binary search";
        textSize(25);
        fill(137, 137, 245);
        text(starting, 50,100);
        fill(205,242,122);
        textSize(50);
        if (sort){
            background(255); //clear canvas
            for (int i = 0; i < arr.size(); i++){ //redraw squares
                fill(205,242,122);
                rect(width/arr.size() * i, height/2, width/arr.size(), height/arr.size());
            }
            fill(137, 137, 245);
            text(note, 150,100); //message
            fill(205,242,122);
            if (count > 1){ //recolor square
                fill(137, 137, 245);
                rect(width/arr.size() * middle, height/2, width/arr.size(), height/arr.size());
                fill(205,242,122);
            }
        }
    }

    public void keyPressed(){
        count++;
        if(sort) {
            if (binarySearchIterative(arr, 9) == -1) {
                if (low == middle && middle == high) {
                    note = "DOESN'T EXIST";
                } else {
                    note = "NOT FOUND YET";
                }
            } else {
                note = "FOUND AT INDEX " + middle;
            }
        }else{ // if it isn't sorted
            selectionSort(arr); //first press should sort
            note = "SORTED";
        }
    }

    private int binarySearchIterative(ArrayList<MyClass> arr, int target){ //iterative binary
        middle = (low+high)/2;
        if (arr.get(middle).getiD() != target){
            if (low == middle && middle == high){
                return -1;
            }
            if (arr.get(middle).getiD() > target){
                high = middle -1;
                return -1;
            }else{
                low = middle + 1;
                return -1;
            }
        }
        return middle;
    }

    private void selectionSort(ArrayList<MyClass> arr) {
        int min;
        for (int c = 0; c < arr.size() - 1; c++) {
            min = c;
            for (int i = min + 1; i < arr.size(); i++) {
                if (arr.get(i).compareTo(arr.get(min)) == -1) {
                    min = i;
                }
            }
            MyClass temp = arr.get(c);
            arr.set(c, arr.get(min));
            arr.set(min, temp);
        }
        sort = true;
    }



    /*private int binarysearchRecursive(ArrayList<MyClass> arr, int low, int high, int target){ //*frankenstein voice* IT'S ALIVE! IT'S ALIVE!
        middle = (low+high)/2;
        boolean one = (low == middle) && (middle == high);
        if (arr.get(middle).getiD() == target){ //base case 1
            return middle;
        } else if(one && arr.get(middle).getiD() != target) { //base case 2
            return -1;
        } else if(arr.get(middle).getiD() > target){
            return binarysearchRecursive(arr, low, middle-1, target);
        }else{
            return binarysearchRecursive(arr, middle + 1, high, target);
        }

    }*/
}