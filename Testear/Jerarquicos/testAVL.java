package Estructura.Testear.Jerarquicos;
import Estructura.jerarquicas.ArbolConjuntista.*;
public class testAVL {
    
    public static void main(String[] args) {
        
        AVL arbol1 = new AVL();
        arbol1.insertarRecursivo(75);
        arbol1.insertarRecursivo(18);
        arbol1.insertarRecursivo(80);
        arbol1.insertarRecursivo(14);
        arbol1.insertarRecursivo(20);
        arbol1.insertarRecursivo(77);
        arbol1.insertarRecursivo(93);
        arbol1.insertarRecursivo(13);
        arbol1.insertarRecursivo(15);
        arbol1.insertarRecursivo(25);
        arbol1.insertarRecursivo(78);
        arbol1.insertarRecursivo(16);
        System.out.println(arbol1.toString());
        System.out.println();
        arbol1.eliminar(93);
        System.out.println(arbol1.toString());
    }
}
