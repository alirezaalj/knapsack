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
public class GreedyApp {

    public static int max(int a, int b) {
        return Math.max(a, b);
    }

    public static int packBack(int W, int[] wt, int[] val, int n){
        if (n == 0 || W == 0)
            return 0;

        if (wt[n-1] > W)
            return packBack(W, wt, val, n-1);

        else {
            int first=val[n - 1] + packBack(W - wt[n - 1], wt, val, n - 1);
            int second= packBack(W, wt, val, n - 1);
            return max(first,second);
        }
    }

    public static void main(String[] args)
    {
        int[] val = new int[]{60, 100, 120};
        int[] wt = new int[]{10, 20, 30};
        int  W = 50;
        int n = val.length;
        System.out.println(packBack(W, wt, val, n));

        System.out.println();
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

        System.out.println("[" + Arrays.stream(rW)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(",")) + "]");
        System.out.println("[" + Arrays.stream(rV)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(",")) + "]");

        long startR = System.currentTimeMillis();
        int maxVal= packBack(packBackSize,rW, rV,size);
        long endR = System.currentTimeMillis();

        System.out.printf("Max Value: %d%n",maxVal);
        System.out.printf("Answer find in %dms", endR - startR);
    }
}
