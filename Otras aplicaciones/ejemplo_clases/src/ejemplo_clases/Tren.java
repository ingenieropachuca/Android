/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo_clases;

/**
 *
 * @author Carlos
 */
public class Tren {
    int vagones[][] =new int[5][2];
    String maquinista;
    int antiguedad;
    
    public Tren()
    {
        for (int i = 0; i <= 4; i++) {
            vagones[i][0]=(i+1)*10;
            vagones[i][1]=0;
        }
        maquinista="anonimo";
        antiguedad=0;
    }
    
        public Tren(String nombre, int anios)
    {
        for (int i = 0; i <= 4; i++) {
            vagones[i][0]=(i+1)*10;
            vagones[i][1]=0;
        }
        maquinista=nombre;
        antiguedad=anios;
    }
        
        public void completarvagon(int vagon){
            vagones[vagon][1]=vagones[vagon][0];
            System.out.println("el vagon "+(vagon+1)+" ah sido completado");
        }
}
