/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
kmap value sequance ex:
size 16
0000
0001
0011
0010
0110
0111
0101
0100
1100
1101
1111
1110
1010
1011
1001
1000

 */
package karnopMap;

import java.util.Arrays;

/**
 *
 * @author hp
 */
public class kMap {

    //return kmaps row/column value. like 00 01 11 10
    String[] arrayValue(int size) {
        String[] array = new String[size];
        int valueLength = (int) (Math.log(size) / Math.log(2.0));// length of bit of the value of array
        String[] bit = new String[valueLength];// contain 0/1. continusly chainge the value 0-to-1 or 1-to-0
        Arrays.fill(bit, "1");
        for (int i = 0; i < size; i++)// loop the array to fill it with value
        {
            array[i] = "";
            for (int j = 0; j < valueLength; j++)//loop value to fill it's every position with 0/1
            {
                System.out.println("value length  " + j);
                if (i + 1 <= (int) Math.pow(2, j)) {
                    array[i] = "0" + array[i];
                    System.out.println("array if  " + array[i]);
                } else {
                    if ((((i + 1) - ((int) Math.pow(2, j))) % (int) Math.pow(2, j + 1)) == 0) {
                        array[i] = bit[j] + array[i];
                        System.out.println("chainge  ");
                        if (bit[j].equals("1")) {
                            System.out.println("chainge  1");
                            bit[j] = "0";
                        } else {
                            System.out.println("chainge  1");
                            bit[j] = "1";
                        }

                    } else {
                        array[i] = bit[j] + array[i];
                    }
                }

            }
            System.out.println(array[i] + "  " + i);
        }
        return array;
    }

    /*
    the 4:4 map will be like 
    0    1    3    2    0    1    3    2
    
    4    5    7    6    4    5    7    6
    
    12   13   15   14   12   13   15   14
    
    8    9    11   10   8    9    11   10
    
    0    1    3    2    0    1    3    2
    
    4    5    7    6    4    5    7    6
    
    12   13   15   14   12   13   15   14
    
    8    9    11   10   8    9    11   10
    each of the value will convert to binary then convert to variable
    like = 15 to 1000 to ab'c'd'
    
     */
    String[][] kMapArray(int variable) {
        String alphabet[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        double half = (double) variable / 2;
        System.out.println("half " + half);
        int rowVariable = (int) Math.floor(half);// hold number of variable that will be in row
        System.out.println("rowvariable " + rowVariable);
        int columnVariable = (int) Math.ceil(half);// hold number of variable that will be in column
        System.out.println("columnvariable " + columnVariable);
        int row = (int) Math.pow(2, rowVariable);// number of row of actual map
        int column = (int) Math.pow(2, columnVariable);// number of column of actual map

        String[][] map = new String[row * 2][column * 2];// hold the map
        //Arrays.fill(map, "");// fill the array with null value/ ""
        String[] rArray = arrayValue(row);//hold the row value 
        String[] cArray = arrayValue(column);// hold the column value
        for (int i = 0; i < row; i++) //fill the arrays first half row with row variable value 
        {
            String variableValue = "";//contain variable value like A'BC
            for (int index = 0; index < rowVariable; index++) {
                if ((rArray[i].charAt(index)) == '0') {
                    variableValue += alphabet[index] + "'";
                } else {
                    variableValue += alphabet[index];
                }
            }
            for (int j = 0; j < column; j++) {
                map[i][j] = variableValue;
            }
            for (int j = column; j < column * 2; j++) {
                map[i][j] = variableValue;
            }
        }
        for (int i = 0; i < row; i++)//fill the arrays last half row with row variable value 
        {
            String variableValue = "";
            for (int index = 0; index < rowVariable; index++) {
                if ((rArray[i].charAt(index)) == '0') {
                    variableValue += alphabet[index] + "'";
                } else {
                    variableValue += alphabet[index];
                }
            }
            for (int j = 0; j < column; j++) {
                map[i + row][j] = variableValue;
            }
            for (int j = column; j < column * 2; j++) {
                map[i + row][j] = variableValue;
            }
        }

        for (int i = 0; i < column; i++) //fill the arrays first half column with column variable value 
        {
            String variableValue = "";//contain variable value like A'BC
            for (int index = 0; index < columnVariable; index++) {
                if ((cArray[i].charAt(index)) == '0') {
                    variableValue += alphabet[rowVariable + index] + "'";
                } else {
                    variableValue += alphabet[rowVariable + index];
                }
            }
            for (int j = 0; j < row; j++) {
                map[j][i] += variableValue;
            }
            for (int j = row; j < row * 2; j++) {
                map[j][i] += variableValue;
            }
        }

        for (int i = 0; i < column; i++) //fill the arrays last half column with column variable value 
        {
            String variableValue = "";//contain variable value like A'BC
            for (int index = 0; index < columnVariable; index++) {
                if ((cArray[i].charAt(index)) == '0') {
                    variableValue += alphabet[rowVariable + index] + "'";
                } else {
                    variableValue += alphabet[rowVariable + index];
                }
            }
            for (int j = 0; j < row; j++) {
                map[j][i + column] += variableValue;
            }
            for (int j = row; j < row * 2; j++) {
                map[j][i + column] += variableValue;
            }
        }

        return map;
    }
    // convert binarry to variable. like 1000 to ab'c'd'
    String binaryToBoolean(String binaryValue) {
        String alphabet[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        String booleanValue = "";
        int length = binaryValue.length();
        for (int index = 0; index < length; index++) {
            if ((binaryValue.charAt(index)) == '0') {
                booleanValue += alphabet[index] + "'";
            } else {
                booleanValue += alphabet[index];
            }
        }
        return booleanValue;
    }
    //convert dicimal to binary
    String binaryConverter(int number,int bit) {
        String binaryValue = "";
        int remainder;
        while (number != 0) {
            remainder = number % 2;
            number = number / 2;
            binaryValue=String.valueOf(remainder)+binaryValue;

        }
        if(binaryValue.length()!=bit)
        {
            int length=binaryValue.length();
            
            for(int i=0;i<(bit-length);i++)
            {
                binaryValue="0"+binaryValue;
            }
        }
        return binaryValue;
    }
    String[][] kMapInitialize(int numberOfVariable,int[] meanturmValue)
    {
        String map[][]=kMapArray(numberOfVariable);
        int size=meanturmValue.length;
        String meanturmString[] = new String [size];
        for(int i=0;i<size;i++)
        {
            meanturmString[i]=binaryToBoolean(binaryConverter(meanturmValue[i],numberOfVariable));
        }
        /*
        fill the array like kmaps 0 1 0 1
                                  0 1 0 0
        here it 0 a'b'c1 0 a'bc'1
                0 ab.c1  0 0 
        */
        for(int i=0;i<map.length;i++)
        {
            for(int j=0;j<map[i].length;j++)
            {
                //int check=0;
                for(int index=0;index<meanturmString.length;)
                {
                    
                    System.out.println(map[i][j]+" "+meanturmString[index]);
                    if(map[i][j].equals(meanturmString[index])==true)
                    {
                        map[i][j]+="1";
                        //check=1;
                        break;
                    }
                    index++;
                    if(index==meanturmString.length)map[i][j]="0";
                }
                
            }
        }
            
            
        return map;
    }
    

}
