/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.util.Arrays;

/**
 *
 * @author Carlos
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         int[][] array1 = {{1, 2, 3, 4}, {5, 6, 7, 8}};
      for(int[] val: array1)
      {
        System.out.println (val[3]);
      }
    }
    
}
