/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karnopMap;

/**
 *
 * @author hp
 */
public class Main {
    public static void main(String[]a)
    {
        kMap k =new kMap();
        int as[]={1,5};
        String ar[][]=k.kMapInitialize(4,as);
        
        for(String t[]: ar)
        {
            for(String f:t)
            System.out.print(" "+f);
            System.out.println();
        }
        //System.out.println(k.binaryToBoolean(k.binaryConverter(16)));
    }
    
    
}
