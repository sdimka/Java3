package J3Lesson7t;

/**
 * Java. Level 3. Lesson 7
 * Task 2, "extended"
 *
 *
 * @author Dmitriy Semenov
 * @version 0.1 dated Mar 16, 2018
 * @link https://github.com/sdimka/Java3
 *
 */

public class MyTest {

    @Test(priority = 1)
    public void test1() {
        System.out.println("Running test # 1");
    }

    @Test(priority = 3)
    public void test4() {
        System.out.println("Running test # 4, same priority with 3");
    }

    @Test(priority = 2)
    public void test2() {
        System.out.println("Running test # 2");
    }

    @Test(priority = 3)
    public void test3() {
        System.out.println("Running test # 3, same priority with 4");
    }

    @Test
    public void test5() {
        System.out.println("Running test # 5, no priority");
    }

    @BeforeSuite
    public void before() {
        System.out.println("Running Before");
    }

    @AfterSuite
    public void after() {
        System.out.println("Running After");
    }

//    @AfterSuite
//    public void afterSecond() {
//        System.out.println("Running After");
//    }

//    @BeforeSuite
//    public void beforeSecond() {
//        System.out.println("Running Before");
//    }

}
