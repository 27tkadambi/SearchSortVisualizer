import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet{

    private int low; //break glass in case of non-recursive binary search
    private int middle;
    private int high; //do not tap on the glass it makes him scared
    //private int[] arr = new int[]{1,2,3,4,7}; //test
    private ArrayList<MyClass>  arr;
    private int width = 600;
    private int height = 600;
    private String note;
    private boolean sort;
    private int count;

    public static void main(String[] args){
        PApplet.main("Main");
    }

    public void settings(){
        size(width, height);

    }

    public void setup(){
        arr = new ArrayList<MyClass>();
        for (int i = 0; i < 10; i++){
            arr.add(new MyClass(10-i));
        }
        low = 0;
        high = arr.size()-1;
    }

    public void draw(){
        background(255);
        textSize(50);
        for (int i = 0; i < arr.size(); i++){
            fill(205,242,122);
            rect(width/arr.size() * i, height/2, width/arr.size(), height/arr.size());
            text(arr.get(i).getiD(), width/arr.size() * i, height/2);
        }

        if (sort){
            background(255);
            for (int i = 0; i < arr.size(); i++){
                fill(205,242,122);
                rect(width/arr.size() * i, height/2, width/arr.size(), height/arr.size());
                text(arr.get(i).getiD(), width/arr.size() * i, height/2);
            }
            text(note, 50,50);
            if (count > 1){
            fill(12,244,32);
            rect(width/arr.size() * middle, height/2, width/arr.size(), height/arr.size());
            fill(205,242,122);
            }
        }else{
            fill(205,242,122);
            text("Click any key to sort", 50,50);
        }

        //System.out.println(binarysearchIterative(arr, 11)); //test
    }

    public void keyPressed(){
        count++;
        if(sort) {
            if (binarysearchIterative(arr, 9) == -1) {
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

    private int binarysearchIterative(ArrayList<MyClass> arr, int target){
        middle = (low+high)/2;
        if (arr.get(middle).getiD() != target){
            if (low == middle && middle == high){
                return -1;
            }
            if (arr.get(middle).getiD() > target){
                high = middle -1;
                middle = (low+high)/2;
                return -1;
            }else {
                low = middle + 1;
                middle = (low + high) / 2;
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