package Estructura.Testear.Jerarquicos;
import Estructura.Lineales.Dinamicas.Lista;
import Estructura.jerarquicas.arbolBinario.*;

public class Testing {
    
    public static void main(String[] args) {
        ArbolBinario arbol1 = new ArbolBinario();

        arbol1.insertar('F', 0, 'I');
        arbol1.insertar('A', 'F', 'I');
        arbol1.insertar('T', 'F', 'D');
        arbol1.insertar('H', 'A', 'I');
        arbol1.insertar('F', 'A', 'D');
        arbol1.insertar('P', 'T', 'D');
        arbol1.insertar('X', 'P', 'I');
        arbol1.insertar('X', 'P', 'I');
        arbol1.insertar('F', 'P', 'I');
        System.out.println(arbol1.toString());
        arbol1.cambiarHijos('Z', 'T', 'Q');
        System.out.println();
        System.out.println(arbol1.toString());
      
    }
}
