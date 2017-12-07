
package ejemplo_clases;
import ejemplo_clases.Tren;


public class Ejemplo_clases {
    public static void main(String[] args) {
        Tren primero= new Tren();
        Tren segundo= new Tren("juan",3);
        
        System.out.println(segundo.maquinista);
        primero.maquinista="roberto";
     
    }
    
}
