package J3Lesson3;

import java.io.*;

public class HW3 {

    static void makeByteArray(){
        byte bw[] = {10,20,30,40,50};
        File file = new File("data.bin");
        FileInputStream fis = null;
        ByteArrayInputStream is =new ByteArrayInputStream(bw);
        try (FileOutputStream fos = new FileOutputStream(file)){

            for (byte a : bw) {
                fos.write(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null){
                    fos.close();
                }
            }catch (IOException e){

            }
        }
    }

    public static void main(String[] args) {
        makeByteArray();
        readByteArray();
    }
}
