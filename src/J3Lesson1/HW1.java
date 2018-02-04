package J3Lesson1;

/**
 * Java. Level 3. Lesson 1
 *
 * @author Dmitriy Semenov
 * @version 0.1 dated Feb 02, 2018
 * @link https://github.com/sdimka/Java3
 *
 */

import java.util.ArrayList;
import java.util.stream.DoubleStream;


//    -------------   Task 3 -----------------
class Fruit {
    protected int id;
    protected float weight;

    public Fruit(int id, float weight) {
        this.id = id;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public float getWeight() {
        return weight;
    }
}

class Apple extends Fruit {

    public Apple(int id, float weight) {
        super(id, weight);
    }
}

class Orange extends Fruit {

    public Orange(int id, float weight) {
        super(id, weight);
    }
}

class Box<T extends Fruit>{
    private ArrayList<T> arr = new ArrayList<>();

    public Box() {
    }

    public void add (T obj){
        this.arr.add(obj);
    }

    public ArrayList<T> getArr() {
        return arr;
    }

    public void print (){
        arr.forEach(a -> System.out.println(a.getClass().getName() + " id: " + a.getId() + " w: " + a.getWeight()));
    }

    public float getWeight(){
        double sum = arr.stream().mapToDouble(a -> DoubleStream.of(a.getWeight()).sum()).sum();
       // System.out.println((float)sum);
        return (float)sum;
    }

    public boolean compare(Box<?> b){
        return Float.compare(getWeight(), b.getWeight()) == 0;
    }

    public void merge(Box<? extends T> obj ){
        obj.arr.forEach(a -> arr.add(a));
        obj.arr.clear();
    }
}

public class HW1 {
    //    -------------   Task 1 -----------------
    static <T> void exchangeArr (T[] arr, int a, int b){
        T temp;
        if (a < arr.length & b < arr.length){
            temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
    }
    //    -------------   Task 2 -----------------

    static <T , V extends T > void arrToArrList (ArrayList<T> x , V[] y ){
        for (int i = 0; i < y.length; i++){
            x.add(y[i]);
        }
    }

    public static void printArr (Object[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        System.out.println("Arrays before reshuffle:");
        String i[] = {"A","B","C","D","E"};
        Integer in[] = {1,2,3,4,5,6};
        printArr(i);
        printArr(in);
        exchangeArr(i, 2, 4);
        exchangeArr(in, 0,5);
        System.out.println("Arrays after reshuffle:");
        printArr(i);
        printArr(in);

        String str[] = {"A","B","C","D","E"};
        ArrayList<String> strAL = new ArrayList<>();
        arrToArrList(strAL, str);
        System.out.println("-----------  Task 2 (content of ArrayList)  ----------------");
        strAL.forEach(a -> System.out.print(a + " "));
        System.out.println("");


        System.out.println("-----------  Task 3 (extendet)   ---------------------");
        Apple a1 = new Apple(1, 2f);
        Apple a2 = new Apple(2, 2.2f);
        Orange o1 = new Orange(3, 2f);
        Orange o2 = new Orange(4, 2.3f);
        Box<Apple> b1 = new Box<>();
        Box<Apple> b3 = new Box<>();
        Box<Orange> b2 = new Box<>();
        b1.add(a1);
        b1.add(a2);
        System.out.println("Content of Box1:");
        b1.print();
        //System.out.println(b1.getWeight());

        b2.add(o1);
        b2.add(o2);
        System.out.println("Content of Box2:");
        b2.print();
        //System.out.println(b2.getWeight());

        System.out.println("Box1 weight compare Box2: " + b1.compare(b2));
        //b3.add(a1);
        b3.merge(b1);
        System.out.println("Content of Box3 after merge:");
        b3.print();
        System.out.println("Content of the first box after merge:");
        b1.print();
    }

}
