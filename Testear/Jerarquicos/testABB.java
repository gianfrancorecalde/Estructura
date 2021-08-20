package Estructura.Testear.Jerarquicos;
import Estructura.jerarquicas.ArbolConjuntista.*;
import Estructura.Lineales.Dinamicas.*;
public class testABB {
    
    public static void main(String[] args) {
        
       /* ABB arbol1 = new ABB();
        arbol1.insertarRecursivo(32);
        arbol1.insertarRecursivo(9);
        arbol1.insertarRecursivo(5);
        arbol1.insertarRecursivo(19);
        arbol1.insertarRecursivo(1);
        arbol1.insertarRecursivo(8);
        arbol1.insertarRecursivo(17);
        arbol1.insertarRecursivo(23);
        arbol1.insertarRecursivo(56);
        arbol1.insertarRecursivo(43);
        arbol1.insertarRecursivo(72);
        arbol1.insertarRecursivo(41);
        arbol1.insertarRecursivo(53);
        arbol1.insertarRecursivo(64);
        arbol1.insertarRecursivo(80);
        ABB arbol2 = arbol1.clone();
        Lista lis1 = arbol1.listarInOrden();
        Lista lis2 = arbol2.listarInOrden();
        System.out.println(lis1.toString());
        System.out.println(lis2.toString());
        //System.out.println(arbol1.toString());
        //System.out.println(arbol1.perteneceIterativo(1));
        //System.out.println(arbol2.toString());
        */

        ABB arbol1 = new ABB();
        // caso 1 
        arbol1.insertarIterativo(50);
        arbol1.insertarIterativo(32);
        arbol1.insertarIterativo(91);
        arbol1.insertarIterativo(21);
        arbol1.insertarIterativo(44);
        arbol1.insertarIterativo(67);
        arbol1.insertarIterativo(131);
        arbol1.insertarIterativo(58);
        arbol1.insertarIterativo(73);
        arbol1.insertarIterativo(63);
        Pila pila = new Pila();
        pila.apilar(44);
        pila.apilar(32);
        pila.apilar(50);
        boolean a = arbol1.verificarCamino(pila); 
        System.out.println(a);
        /* ABB arbol2 = new ABB();
        arbol2.insertarIterativo(20);
        arbol2.insertarIterativo(14);
        arbol2.insertarIterativo(44);
        arbol2.insertarIterativo(8);
        arbol2.insertarIterativo(18);
        arbol2.insertarIterativo(22);
        arbol2.insertarIterativo(50);
        arbol2.insertarIterativo(1);
        arbol2.insertarIterativo(16);
        arbol2.insertarIterativo(19);
        int b = arbol2.sumarInordenDesde(14, 50); 
        System.out.println(b); */
        //arbol1.insertarIterativo(21);
        //System.out.println(arbol1.toString());
        //System.out.println();
        //arbol1.eliminar(20);
        //System.out.println(arbol1.toString());
    }
}
