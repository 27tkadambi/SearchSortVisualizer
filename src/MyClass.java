public class MyClass implements Comparable<MyClass>{
    private int iD;
    private int sepalLength;
    private int sepalWidth;
    private int petalLength;
    private int petalWidth;

    public MyClass(int iD){
        this.iD = iD;
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
