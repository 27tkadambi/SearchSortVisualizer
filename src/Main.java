import processing.core.PApplet;
import java.util.ArrayList;
import processing.data.Table;
import processing.data.TableRow;

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
    int target;
    String targetString;
    boolean complete; //is target string complete?
    String found; //knowledge of found iris

    public static void main(String[] args){
        PApplet.main("Main");
    }

    public void settings(){
        size(width, height);

    }

    public void setup(){
        Table table = loadTable("iris.csv", "header");
        arr = new ArrayList<MyClass>();

        for (TableRow row : table.rows()){ //initialize the arraylist
            int iD = row.getInt("Id");
            int sepalLength = row.getInt("SepalLengthCm");
            int sepalWidth = row.getInt("SepalWidthCm");
            int petalLength = row.getInt("PetalLengthCm");
            int petalWidth = row.getInt("PetalWidthCm");
            String species = row.getString("Species");
            arr.add(new MyClass(iD, sepalLength, sepalWidth, petalLength, petalWidth, species));
        }
        low = 0;
        high = arr.size()-1;
        targetString = "";
        found = "";
    }

    public void draw(){
        String starting;
        background(255);
        for (int i = 0; i < arr.size(); i++){
            fill(205,242,122);
            rect(width/arr.size() * i, height/2, width/arr.size(), height/arr.size());
        }

        starting = "Instructions: \n 1. CLICK ANY KEY TO SORT \n 2. Enter a number between 0 & 29. \n 3. Click enter to start binary search";
        textSize(25);

        fill(137, 137, 245);
        text(starting, 50,100);
        rect(450,400,140,75); //reset button
        text("Number: " + targetString, 400,  50); //visualize?????
        fill(205,242,122);

        textSize(50);

        fill(0);
        text("RESET", 450, 450);
        fill(205,242,122);

        if (sort){
            background(255); //clear canvas

            fill(137, 137, 245);
            rect(450,400,140,75);
            fill(0);
            text("RESET", 450, 450);

            for (int i = 0; i < arr.size(); i++){ //redraw squares
                fill(205,242,122);
                rect(width/arr.size() * i, height/2, width/arr.size(), height/arr.size());
            }

            fill(137, 137, 245);
            text(note, 120,120); //message
            textSize(25);
            text("Number: " + targetString, 400,  50); //visualize?????
            text(found, 100, 400);
            textSize(50);
            fill(205,242,122);

            if (count > 1 && complete){ //recolor square
                fill(137, 137, 245);
                rect(width/arr.size() * middle, height/2, width/arr.size(), height/arr.size());
                fill(205,242,122);
            }
        }
    }

    public void keyPressed(){
        count++;
        String digits = "0123456789";
        if(sort) {
            if(digits.indexOf(key) != -1){
                targetString += key;
                target = Integer.parseInt(targetString);
            }
            if (target > 29){
                note = "TRY AGAIN, BAD INPUT";
            }
            if (digits.indexOf(key) == -1) {
                complete = true;
                if (binarySearchIterative(arr, target) == -1) {
                    if (low == middle && middle == high) {
                        note = "DOESN'T EXIST";
                    } else {
                        note = "NOT FOUND YET";
                    }
                } else {
                    note = "FOUND AT INDEX " + middle;
                    found = arr.get(middle).toString();
                }
            }
        }else{ // if it isn't sorted
            selectionSort(arr); //first press should sort
            note = "SORTED";
        }
    }

    public void mouseClicked(){
        if ((mouseX >= 450 && mouseX <= 590) || (mouseY >= 400 && mouseY <= 475)) {
            reset();
        }
    }

    public void reset(){
        background(0);
        sort = false;
        complete = false;
        count = 0;
        note = "";
        setup();
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