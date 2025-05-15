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
            text(note, 50,50);
            fill(12,244,32);
            rect(width/arr.size() * middle, height/2, width/arr.size(), height/arr.size());
            fill(205,242,122);
        }

        //System.out.println(binarysearchIterative(arr, 11)); //test
    }

    public void keyPressed(){
        /*if (!sort){
            selectionSort(arr);
        }*/
        selectionSort(arr);
        if (binarysearchIterative(arr, 1) == -1){
            if (low == middle && middle == high){
                note = "DOESN'T EXIST";
            }else{
                note = "NOT FOUND YET";
            }
        }else{
            note = "FOUND AT INDEX " + middle;
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
        for (int curIndex = 0; curIndex < arr.size() - 1; curIndex++) {
            int minIndex = findMin(arr, curIndex);
            swap(arr, curIndex, minIndex);
            sort = true;
        }
    }
    private int findMin(ArrayList<MyClass> arr, int startingIndex) {
        int minIndex = startingIndex;
        for (int i = minIndex + 1; i < arr.size(); i++) {
            if (arr.get(i).compareTo(arr.get(minIndex)) == -1) {
                minIndex = i;
            }
        }
        return minIndex;
    }
    private void swap(ArrayList<MyClass> arr, int x, int y) {
        MyClass temp = arr.get(x);
        arr.set(x,(arr.get(y)));
        arr.set(y,temp);
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