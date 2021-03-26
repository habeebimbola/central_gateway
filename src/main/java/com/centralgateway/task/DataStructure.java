package com.centralgateway.task;

import java.util.Arrays;

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
    public static void main(String[] args)
    {
        DataStructure dataStructure = new DataStructure();
        int[] A = {1,2,3};
        int[] Z = {1,1,2,3,4,6};
        int[] K = {1,2,3,4,5};
//        dataStructure.minInteger(A);
        dataStructure.circularArray(K,4);
    }
}
