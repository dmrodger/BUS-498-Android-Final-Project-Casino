package com.example.thecasino;

import java.util.*;


public class Roulette
{
    private int[] black = {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};
    private int[] red = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
    private int[] row1 = {3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36};
    private int[] row2 = {2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35};
    private int[] row3 = {1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34};
    
    // returns value from -1 to 37, -1 is 00.
    public int spin()
    {
        Random random = new Random();
        int randomInt = random.nextInt(38) - 1;
        return randomInt;
    }
    
    // For 00, give -1 to this function.
    public int payout(int field, int betOnNumber, int spinValue, int betVal)
    {
        int payoutMultiplier = 0;
        switch (field)
        {
        case (0):
            if (spinValue == betOnNumber)
                payoutMultiplier = 37;
            break;
        case (1):
            if (spinValue > 0 && spinValue < 13)
                payoutMultiplier = 3;
            break;
        case (2):
            if (spinValue > 12 && spinValue < 25)
                payoutMultiplier = 3;
            break;
        case (3):
            if (spinValue > 24 && spinValue < 37)
                payoutMultiplier = 3;
            break;
        case (4):
        	for (int i = 0; i < row1.length; i++)
        	{
        		if (row1[i] == spinValue)
        		{
        			payoutMultiplier = 3;
        		}
        	}
            break;
        case (5):
        	for (int i = 0; i < row2.length; i++)
        	{
        		if (row2[i] == spinValue)
        		{
        			payoutMultiplier = 3;
        		}
        	}
            break;
        case (6):
        	for (int i = 0; i < row3.length; i++)
        	{
        		if (row3[i] == spinValue)
        		{
        			payoutMultiplier = 3;
        		}
        	}
        	break;
        case (7):
            if (spinValue > 0 && spinValue < 19)
                payoutMultiplier = 2;
        	break;
        case (8):
            if (spinValue < 37 && spinValue > 18)
            		payoutMultiplier = 2;
        	break;
        case (9):
            if (spinValue % 2 == 0 && spinValue > 0)
                payoutMultiplier = 2;
            break;
        case (10):
            if (spinValue % 2 != 0 && spinValue > 0)
                payoutMultiplier = 2;
        	break;
        case (11):
        	for (int i = 0; i < black.length; i++)
        	{
        		if (black[i] == spinValue)
        		{
        			payoutMultiplier = 2;
        		}
        	}
            break;
        case (12):
        	for (int i = 0; i < red.length; i++)
        	{
        		if (red[i] == spinValue)
        		{
        			payoutMultiplier = 2;
        		}
        	}
            break;
        default:
            break;
        }
        return payoutMultiplier*betVal;
    }
}