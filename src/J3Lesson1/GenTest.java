package J3Lesson1;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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
        GenTest<String> strTT = new GenTest<>("Bla-bla");
        System.out.println(strTT.showType() + " value:" + strTT.GetObj());

        String s = "2018-02-18 15:50:49";
        Instant d = Instant.now();
        LocalDate ld = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss").withLocale(Locale.ENGLISH);;
        LocalDateTime ldt = LocalDateTime.parse(s, dtf);
        System.out.println(ldt.toLocalDate());


    }
}
