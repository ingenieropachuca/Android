
package javaapplication1;

public class JavaApplication1 {

    public static void main(String[] args) {
        
//        double numero;
//        numero=2147.3;
//        char letra='A';
//        boolean luz = true;
//        int paginas[] = new int[6];
//        paginas[0]=20;
//        String cadena="HOaaaaaa";
//        String tablero[][]= new String[8][8];
//        System.out.println(numero);
//        System.out.println(letra);
//        System.out.println(luz);
//        tablero[5][4]="rey negro";
//        System.out.println(tablero[5][4]);
//        SEGUNDO BLOQUE DEL PROGRAMA 

//int edad = 15; 
//boolean comprobacion;
//comprobacion=edad >= 18;
//        if (comprobacion) {
//            System.out.println("Eres mayor de edad");
//        }
//        else{
//            System.out.println("no eres mayor");
//        }
// TERCERO BLOQUE DE CODIGO

int tablero[][] =new int [5][5];
int fila;
int columna;

for(fila=0;fila<=4;fila++){
    for (columna = 0; columna <= 4; columna++) {
        tablero[fila][columna]=0;
    }
}
tablero[3][0]=1;
tablero[2][2]=2;
tablero[2][3]=2;

for(fila=0;fila<=4;fila++){
    for (columna = 0; columna <= 4; columna++) {
        if (tablero[fila][columna]==2) {
            System.out.println("jugador 2 hayado en la casiila"+fila+ "/"+columna);           
        }
    }
}

 }
    
}
