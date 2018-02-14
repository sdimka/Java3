package J3Lesson3;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class HW3 {

    private static void makeByteArrayFile(int FileSize, String FileName){
        byte bw[] = new byte[FileSize];

        for (int i = 0; i  < FileSize; i++){
            bw[i] = (byte)(i + 1);
        }

        try (FileOutputStream fos = new FileOutputStream(FileName)){
            for (byte a : bw) {
                fos.write(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void readByteArrayFile(){
        byte bw[] = new byte[50];

        try (FileInputStream fis = new FileInputStream("data.bin"))
        {
            int data = fis.read(bw);
            System.out.println(Arrays.toString(bw));
            System.out.println(data);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void joinFile(){

//        try {
//            Files.list(Paths.get("./")).filter(f -> f.getFileName().toString().contains("spl")).forEach(a -> System.out.println(a));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try(FileOutputStream fos = new FileOutputStream("joint.bin")) {
            List<InputStream> iss = Files.list(Paths.get("./"))
                    .filter(f -> f.getFileName().toString().contains("spl"))
                    .map(f -> {
                        try {
                            return new FileInputStream(f.toString());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }).collect(Collectors.toList());
//            ArrayList<InputStream> iss = new ArrayList<>();
//            for (int i = 0; i < 5; i++){
//                iss.add(new FileInputStream(i + "spl.bin"));
//            }
            //Enumeration<InputStream> e = Collections.enumeration(al);
            SequenceInputStream seq = new SequenceInputStream(Collections.enumeration(iss));

            int rb = seq.read();
            while (rb != -1){
                fos.write(rb);
                rb = seq.read();
            }

            seq.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        makeByteArrayFile(50, "data.bin");
        readByteArrayFile();

        for (int i = 0; i < 5; i++){
            makeByteArrayFile(100, i + "spl.bin");
        }

        joinFile();
    }
}
