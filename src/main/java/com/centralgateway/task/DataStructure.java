package com.centralgateway.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataStructure {

    public int minInteger(int[] N){

        int size = N.length;
        int smallest = 1;

        if(size < 1)  {
            System.out.println("Minimum "+smallest);

        return smallest;
        }

        Arrays.sort(N);

        if( N[size - 1] < 1  )
        {
            System.out.println("Minimum "+smallest);

            return smallest;
        }

        if(N[size - 1]  > 1 && size == 1)
        {
            System.out.println("Minimum "+smallest);

            return smallest;
        }

        if( N[0] > 1 )
        {
            System.out.println("Minimum "+smallest);

            return smallest;
        }

        int counter = 1;

        for(int index = 0; index < size; index++)
        {

            if( counter == N[index] )
            {
                counter++;;
            }

        }
        System.out.println("Minimum After Looping "+counter);
        return smallest;
    }

    public void circularArray(int[] n, int passes){

        int size = n.length;

        while ( passes > 0)
        {
        for(int index = 0; index < size - 1; index++)
        {
            if(index == 0 )
            {
                int temp = n[index];
                n[index] = n[size - 1];
                n[size - 1 ] = temp;
            }
            else
                {
                int temp = n[index - 1];

                n[index - 1] = n[index];
                n[index ] = temp;
            }

        }
            passes--;
        }
        for(int index=0; index < size; index++)
        {
            System.out.printf("%d ", n[index]);
        }
    }

    private int[] reverseArray(int[] n)
    {
        int size = n.length;
        int[] K =new int[size];

        for(int index = 0; index < size; index++)
        {
            if(index == 0 )
            {
                int temp = n[index] ;
                K[index] = n[size - 1 ] ;
                K[size - 1 ] = temp;
            }
            if( index > 0 && index < size - 1)
            {
                int temp = n[index];
                K[index] = n[(size - 1) - index];
                K[(size - 1) - index] = temp;
            }
        }
        return K;
    }
    public int reverseArr(int[] arr){

        int size = arr.length;
        int smallest = 1;

        if(size < 1){
            return smallest;
        }

        if( size == 1 && arr[size - 1] > smallest)
        {
            return smallest;
        }

        Arrays.sort(arr);

        if( arr[0] > 1 || arr[size - 1] < 1)
        {
            return smallest;
        }

        for(int index = 1; index <= size; index++)
        {
            if(arr[index - 1] == index || smallest < arr[index - 1])
            {
                smallest++;
            }
        }

        return smallest;
    }
    public static void main(String[] args)
    {
        DataStructure dataStructure = new DataStructure();
        int[] A = {-1,-2,-3};
        int[] Z = {1,1,2,3,4,6};
        int[] K = {1,2,3,4,5};
        int[] X = {1,4,3,2};
//        dataStructure.minInteger(A);
        System.out.println( dataStructure.reverseArr(A));
        System.out.println( Integer.reverse(32));
        System.out.println();

        int [] T = dataStructure.reverseArray(X);
        for (int t: T){
            System.out.printf("%d ", t);
        }
    }
}
