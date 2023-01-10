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
public class Dynamic {

    public static int max(int a, int b) {
        return Math.max(a, b);
    }

    public static int packBack(int W, int[] wt, int[] val, int n) {
        int i, w;
        int[][] mat = new int[n + 1][W + 1];

        for (i = 0; i <= n; i++) {
            for (w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    mat[i][w] = 0;
                else if (wt[i - 1] <= w)
                    mat[i][w] = max(val[i - 1] + mat[i - 1][w - wt[i - 1]], mat[i - 1][w]);
                else
                    mat[i][w] = mat[i - 1][w];
            }
        }

        printMat(mat);

        int[] items=new int[n];
        int sol=W;
        for (i=n;i>0;i--){
            if (mat[i][sol]!=mat[i-1][sol]){
                System.out.printf("item %d is used%n",i-1);
                items[i-1]=1;
                sol = sol - wt[i-1];
            }else {
                System.out.printf("item %d not used%n",i-1);
                items[i-1]=0;
            }
        }

        printArray(items);

        int totalW=0;
        for (int a = 0; a < items.length; a++) {
            if (items[a] == 1) {
                totalW += wt[a];
            }
        }
        System.out.println("total weight:"+totalW);

        return mat[n][W];
    }

    public static void main(String[] args) {
        int[] val = new int[]{60, 50 , 70 ,30};
        int[] wt = new int[]{5, 3 ,4 ,2};
        int W = 5;
        int n = val.length;
        System.out.printf("Max Value: %d",packBack(W, wt, val, n));

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

    public static void printArray(int[] arr) {
        System.out.println("["+Arrays.stream(arr)
                .mapToObj(String::valueOf)
                        .map(s -> {
                            if (s.length()<=1){
                                return s.concat("  ");
                            } else if (s.length() == 2) {
                                return s.concat(" ");
                            }
                            return s;
                        })
                .collect(Collectors.joining(","))+"]");
    }
    public static void printMat(int[][] mat){
        System.out.println("Mat:");
        for (int[] ints : mat) {
            printArray(ints);
        }
        System.out.println("--------------");
    }
}
