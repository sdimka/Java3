package J3Lesson1;

public class GenTest<T> {
    T obj;

    GenTest (T obj){
        this.obj = obj;
    }

    public T GetObj(){
        return obj;
    }
}
