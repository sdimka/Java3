package J3Lesson1;

import java.util.ArrayList;
import java.util.stream.DoubleStream;



class Fruit {
    private int id;
    private float weight;

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

    public void print (){
        arr.forEach(a -> System.out.println(a.getClass().getName() + " id: " + a.getId() + " w: " + a.getWeight()));
    }

    public float getWeight(){
        double sum = arr.stream().mapToDouble(a -> DoubleStream.of(a.getWeight()).sum()).sum();
       // System.out.println((float)sum);
        return (float)sum;
    }

    public boolean compare(Box<?> b){
        return getWeight() == b.getWeight();
    }

    public void merge(Box<? extends T> obj ){
        obj.arr.forEach(a -> arr.add(a));
    }

    public ArrayList<T> getArr() {
        return arr;
    }
}

public class HW1 {

    public static void main(String[] args) {
        Apple a1 = new Apple(1, 2f);
        Apple a2 = new Apple(2, 2.2f);
        Orange o1 = new Orange(3, 2f);
        Orange o2 = new Orange(4, 2.3f);
        Box<Apple> b1 = new Box<>();
        Box<Apple> b3 = new Box<>();
        Box<Orange> b2 = new Box<>();

        b1.add(a1);
        b1.add(a2);
        b1.print();
        System.out.println(b1.getWeight());

        b2.add(o1);
        b2.add(o2);
        b2.print();
        System.out.println(b2.getWeight());

        System.out.println(b1.compare(b2));
        b3.add(a1);
        b3.merge(b1);
        System.out.println(b3.getWeight());
    }

}
