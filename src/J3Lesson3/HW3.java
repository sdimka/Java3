package J3Lesson3;

import java.io.*;

public class HW3 {

    private static void makeByteArray(){
        byte bw[] = {10,20,30,40,50};

        try (FileOutputStream fos = new FileOutputStream("data.bin")){
            for (byte a : bw) {
                fos.write(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void readByteArray(){


        try (FileInputStream fis = new FileInputStream("data.bin");
             InputStreamReader isr = new InputStreamReader(fis);
      //  ByteArrayInputStream is = new ByteArrayInputStream(isr);
        ){

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        makeByteArray();
       // readByteArray();
    }
}
