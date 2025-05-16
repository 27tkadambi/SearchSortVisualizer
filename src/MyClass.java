public class MyClass implements Comparable<MyClass>{
    private int iD;
    private int sepalLength;
    private int sepalWidth;
    private int petalLength;
    private int petalWidth;
    private String species;

    public MyClass(int iD, int sepalLength, int sepalWidth, int petalLength, int petalWidth, String species){

        this.iD = iD;
        this.sepalLength = sepalLength;
        this.sepalWidth = sepalWidth;
        this.petalLength = petalLength;
        this.petalWidth = petalWidth;
        this.species = species;
    }

    public int compareTo(MyClass another){
        if (this.iD == another.iD) {
            return 0;
        }else if(this.iD > another.iD){
            return 1;
        }else {
            return -1;
        }
    }

    public int getiD(){
        return iD;
    }
}
