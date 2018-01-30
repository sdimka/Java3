package J3Lesson1;

public class GenTest<T> {
    T obj;

    GenTest(T obj) {
        this.obj = obj;
    }

    public T GetObj() {
        return obj;
    }

    public String showType() {
        return "Object is" + obj.getClass().getName();
    }

    public static void main(String[] args) {
        GenTest<Integer> inTT = new GenTest<>(678);
        System.out.println(inTT.showType() + " value:" + inTT.GetObj());
        // TODO: 30.01.2018 write string example
        GenTest<String> strTT = new GenTest<>("Bla-bla");
        System.out.println(strTT.showType() + " value:" + strTT.GetObj());
    }
}
