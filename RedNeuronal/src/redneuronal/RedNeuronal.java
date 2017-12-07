/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redneuronal;

import java.util.Random;

/**
 *
 * @author Carlos
 */
public class RedNeuronal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RedNeuronal poblacion = new RedNeuronal();
        
        poblacion.crearPoblacion();
    }
    
    
    public int[] crearPoblacion(){
    int [] elementos = new int[21];    
    int n=0; 
    
    Random r = new Random();
    for(int i =0;i<=20;i++){
       int cromosoma = r.nextInt(1024);
       elementos[i] = cromosoma; //guardamos el entero obtenido en el array
       elementos[i]++;  //le sumamos uno a la posición del array para poder guardar el siguiente número
       System.out.println(elementos[i]);
    }
    return elementos;
   }
    
}
