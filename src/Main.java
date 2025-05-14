import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet{

    //private int low; //break glass in case of non-recursive binary search
    private int middle;
    //private int high; //do not tap on the glass it makes him scared
    //private int[] arr = new int[]{1,2,3,4,7}; //test
    private ArrayList<MyClass>  arr;
    public static void main(String[] args){
        PApplet.main("Main");
    }

    public void settings(){
        size(600,600);

    }

    public void setup(){
        arr = new ArrayList<MyClass>();
        for (int i = 0; i < 10; i++){
            arr.add(new MyClass(i));
        }
    }

    public void draw(){
        background(255);
        System.out.println(binarysearchRecursive(arr, 0, 4, 11)); //test
    }

    /*private int binarysearchNotRecursive(ArrayList<MyClass> arr, int target){ //it's not recursive dude, what's wrong with you?
        low = 0;
        high = arr.size()-1;
        middle = (low+high)/2;
        while (arr.get(middle).getiD() != target){
            if (arr.get(middle).getiD() > target){
                high = middle -1;
                middle = (low+high)/2;
            }else{
                low = middle +1;
                middle = (low+high)/2;
            }
            if (low == middle && middle == high){
                return -1;
            }
        }
        return middle;

    }*/

    private int binarysearchRecursive(ArrayList<MyClass> arr, int low, int high, int target){ //*frankenstein voice* IT'S ALIVE! IT'S ALIVE!
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

    }
}