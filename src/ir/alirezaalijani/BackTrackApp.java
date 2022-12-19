package ir.alirezaalijani;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 12/16/2022
 */
public class BackTrackApp {

    public static int maxValue = 0;

    public static void main(String[] args) {

        int[] w = {10, 20, 30};
        int[] val = {60, 100, 120};
        int[] ans = new int[w.length];
        int[] finalAns = new int[w.length];
        int max = 50;

        long start = System.currentTimeMillis();
        knapsack(w, val, max, 0, ans, finalAns);
        long end = System.currentTimeMillis();

        System.out.printf("Answer find in %dms", end - start);
        System.out.println("[" + Arrays.stream(finalAns)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(",")) + "]");

        maxValue =0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Number of Items:");
        int size = scanner.nextInt();

        System.out.print("Enter Size of PackBack:");
        int packBackSize=scanner.nextInt();

        int[] rW = new int[size];
        int[] rV = new int[size];
        int[] rAns= new int[size];
        int[] rAnsFinal= new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            // from 10 to 100
            rW[i] = random.nextInt(100 - 10) + 10;
            // from 100 to 1000
            rV[i] = random.nextInt(1000 - 100) + 100;
        }

        System.out.println("[" + Arrays.stream(rW)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(",")) + "]");
        System.out.println("[" + Arrays.stream(rV)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(",")) + "]");

        long startR = System.currentTimeMillis();
        knapsack(rW, rV, packBackSize, 0, rAns, rAnsFinal);
        long endR = System.currentTimeMillis();

        System.out.printf("Answer find in %dms", endR - startR);
        System.out.print("[" + Arrays.stream(rAnsFinal)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(",")) + "]");

        System.out.print("-> total weight:");
        int totalW=0;
        for (int i = 0; i < rW.length; i++) {
            if (rAnsFinal[i] == 1) {
                totalW += rW[i];
            }
        }
        System.out.println(totalW);
    }

    public static void knapsack(int[] w, int[] val, int max, int index, int[] sol, int[] finalSol) {
        sol[index] = -1;
        int n = w.length;
        while (sol[index] < 1) {
            sol[index] = sol[index] + 1;
            int valSum = promising(sol, w, val, max, finalSol);
            if (valSum != -1) {
                update(sol, finalSol, valSum);
            }
            if (index < n - 1) {
                knapsack(w, val, max, index + 1, sol, finalSol);
            }
        }

    }

    public static void update(int[] ans, int[] finalAns, int valSum) {
        if (valSum > maxValue) {
            maxValue = valSum;
            System.arraycopy(ans, 0, finalAns, 0, ans.length);
        }
    }

    public static int promising(int[] ans, int[] w, int[] val, int max, int[] finalAns) {
        int wSum = 0;
        int valSum = 0;
        for (int i = 0; i < w.length; i++) {
            if (ans[i] == 1) {
                wSum += w[i];
                valSum += val[i];
            }
        }
        if (wSum <= max) {
            return valSum;
        } else {
            return -1;
        }
    }
}
