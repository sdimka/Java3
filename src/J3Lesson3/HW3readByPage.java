package J3Lesson3;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class HW3readByPage {

    private static void GenerateFile() {
        char chArr[] = new char[1800];
        char chLor[] = new char[1800];
        Random random = new Random();

        try (BufferedReader br = new BufferedReader(new FileReader("lorem.txt"))) {
            br.read(chLor, 0, 1800);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("text.txt"))) {

            for (int i = 0; i < 5827; i++) {

                char inToChar[] = String.valueOf(i + " page ").toCharArray();
                for (int k = 0; k < inToChar.length; k++) {
                    chArr[k] = inToChar[k];
                }
                for (int k = inToChar.length; k < 1800; k++) {
                    chArr[k] = chLor[k - inToChar.length];
                }

                bw.write(chArr);
            }
            System.out.println("Complete!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void readPage(int pNum) {
        long startTime = System.nanoTime();
        char chArr[] = new char[1800];
        int pCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("text.txt"))) {

            while (pCount != pNum +1) {
                br.read(chArr, 0, 1800);
                pCount++;
            }
            System.out.println(chArr);

            long stopTime = System.nanoTime();
            System.out.println("Search time: " + (stopTime - startTime)/1000000 + " milliseconds");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readPageSeek(int pNum){
        long startTime = System.nanoTime();
        char chArr[] = new char[1800];

        try(RandomAccessFile file = new RandomAccessFile("text.txt", "r")){
            file.seek(pNum*1800);
            for(int i = 0; i < chArr.length; i++){
               chArr[i] = (char)file.read();
            }
            System.out.println(chArr);
            long stopTime = System.nanoTime();
            System.out.println("Search time: " + (stopTime - startTime)/1000000 + " milliseconds");
        }catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        //GenerateFile();
        //readPage(4500);
        System.out.println("c - create big file, s num - search page by num v1, ss num - search page by num v2, ex - exit");
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("> ");
            String line = sc.nextLine();
            args = line.split(" ");

            switch (args[0]) {
                case "c":
                    GenerateFile();
                    break;
                case "s":
                    readPage(Integer.parseInt(args[1]));
                    break;
                case "ss":
                    readPageSeek(Integer.parseInt(args[1]));
                    break;
                case "ex":
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Unknown command");
            }
        } while (!args[0].equals("ex"));
    }
}
