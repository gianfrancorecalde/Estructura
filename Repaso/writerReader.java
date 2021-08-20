package Estructura.Repaso;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;



public class writerReader {
    
    public static void main(String[] args) {
        /*System.out.println("Padre " + " HI "+ " HD \n");
        System.out.println("Padre " + " HI "+ " HD \n"); */
        String cadena = "",temp;
        StringTokenizer tokens;
        try(
            //FileWriter fw = new FileWriter("C:\\Users\\Gian Franco Recalde\\Documents\\prueba.txt");
            FileReader fr = new FileReader("C:\\Users\\Gian Franco Recalde\\Documents\\Equipos.txt");
            ){
                int valor = fr.read();
                while(valor!=10){
                    cadena += (char)valor;
                    valor = fr.read();
                }
                tokens = new StringTokenizer(cadena, ";");
                System.out.println(tokens);
                while(tokens.hasMoreTokens()){
                    temp = tokens.nextToken();
                    System.out.println(temp + " " + tokens.countTokens());
                }
        }catch(IOException e){
            System.out.println("Error E/S: "+e);
        }
    }
}
