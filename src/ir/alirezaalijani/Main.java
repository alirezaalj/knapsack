package ir.alirezaalijani;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 12/24/2022
 */
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Number of Items:");
        int size = scanner.nextInt();

        System.out.print("Enter Size of PackBack:");
        int packBackSize=scanner.nextInt();

        int[] rW = new int[size];
        int[] rV = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            // from 10 to 100
            rW[i] = random.nextInt(100 - 10) + 10;
            // from 100 to 1000
            rV[i] = random.nextInt(1000 - 100) + 100;
        }

        System.out.println(Arrays.stream(rW)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(",","{","}")));
        System.out.println(Arrays.stream(rV)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(",","{","}")));


        long startTime = System.currentTimeMillis();
        int maxVal= Greedy.packBack(packBackSize,rW,rV,size);
        long endTime = System.currentTimeMillis();

        System.out.printf("Max Value: %d%n",maxVal);
        System.out.printf("Greedy answer find in %dms%n", endTime - startTime);


        System.out.println("---------------------------");
        startTime = System.currentTimeMillis();
        maxVal= Dynamic.packBack(packBackSize,rW, rV,size);
        endTime = System.currentTimeMillis();

        System.out.printf("Max Value: %d%n",maxVal);
        System.out.printf("Dynamic answer find in %dms%n", endTime - startTime);

        System.out.println("---------------------------");
        int[] rAns= new int[size];
        int[] rAnsFinal= new int[size];
        startTime = System.currentTimeMillis();
        BackTrack.knapsack(rW, rV, packBackSize, 0, rAns, rAnsFinal);
        endTime = System.currentTimeMillis();

        int totalW=0;
        for (int i = 0; i < rW.length; i++) {
            if (rAnsFinal[i] == 1) {
                totalW += rW[i];
            }
        }
        System.out.printf("Max Weight: %d%n",totalW);
        System.out.printf("BackTrack answer find in %dms%n", endTime - startTime);

        System.out.print(Arrays.stream(rAnsFinal)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(",","{","}")));


    }
}
