package J3Lesson3;

/**
 * Java. Level 3. Lesson 3
 * Task extra - write-read to file using serializable
 *
 * @author Dmitriy Semenov
 * @version 0.2 dated Feb 13, 2018
 * @link https://github.com/sdimka/Java3
 *
 */

import java.io.*;
import java.util.ArrayList;


public class HW3Serialization {


    public static void main(String[] args) {

        ArrayList<Employee> emp1 = new ArrayList<>();
        ArrayList<Employee> emp2 = new ArrayList<>();

        emp1.add(new Employee("Петр", "Алексеевич", "Романов", "Главный", "ar@mail.ru",
                78978978, 300000, 50));
        emp1.add(new Employee("Алексей", "Николаевич", "Толстой", "Помладше", "an@mail.ru",
                78978978, 200000, 45));
        emp1.add(new Employee("Али", "Алибабаевич", "Алибабаев", "Средненький", "aa@mail.ru",
                78978978, 150000, 41));
        emp1.add(new Employee("Максим", "Александрович", "Фадеев", "Помошник средненького", "ma@mail.ru",
                78978978, 100000, 30));
        emp1.add(new Employee("Гадя", "Петрович", "Хренова", "Секретарь", "gp@mail.ru",
                78978978, 30000, 25));

        writeObjectToFile(emp1);

        readObjectFromFile(emp2);

        emp2.forEach(a -> a.print());

    }

    static void writeObjectToFile (ArrayList<?> arr){
        try (ObjectOutputStream ou = new ObjectOutputStream(new FileOutputStream( "empl.out"))){
            ou.writeObject(arr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void readObjectFromFile (ArrayList<Employee> arrZ){
        try{
        ObjectInputStream is = new ObjectInputStream(new FileInputStream( "empl.out"));
            try {
                arrZ.addAll((ArrayList<Employee>)is.readObject());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Employee implements Serializable {
    //  ФИО, должность, email, телефон, зарплата, возраст
    private String firstName;
    private String secondName;
    private String familyName;
    private String position;
    private String email;
    private int phoneNum;
    private int salary;
    private int age;

    public Employee(String firstName) {
        this.firstName = firstName;
    }

    public Employee(String _firstName, String _secondName, String _familyName, String _position,
                    String _email, int _phoneNum, int _salary, int _age) {
        firstName = _firstName;
        secondName = _secondName;
        familyName = _familyName;
        position = _position;
        email = _email;
        phoneNum = _phoneNum;
        salary = _salary;
        age = _age;
    }

    public Employee() {
        firstName = "Имя";
        secondName = "Отчество";
        familyName = "Фамелия";
        position = "Сотрудник";
        email = "xx@mail.com";
        phoneNum = 892112345;
        salary = 35000;
        age = 30;
    }

    public int getAge() {
        return age;
    }

    public void print(){
        System.out.printf("Фамелия Имя Отчество: %s %s %s \n" +
                        "Должность: %s " +
                        "e-mail: %s , телефон: %d \n" +
                        "Зарплата: %d " +
                        "Возраст: %d \n"
                ,firstName, secondName, familyName, position, email, phoneNum, salary, age);

    }

}
