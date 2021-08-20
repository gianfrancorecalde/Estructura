package Estructura.Testear.Jerarquicos;
import Estructura.jerarquicas.ArbolGenerico.*;
import Estructura.Lineales.Dinamicas.Lista;

public class otroTest {
    
    public static void main(String[] args) {
        
        ArbolGen arbol1 = new ArbolGen();
        
        arbol1.insertar('A', 'A');
        arbol1.insertar('E', 'A');
        arbol1.insertar('D', 'A');
        arbol1.insertar('C', 'A');
        arbol1.insertar('B', 'A');
        arbol1.insertar('G', 'B');
        arbol1.insertar('F', 'B');
        arbol1.insertar('J', 'D');
        arbol1.insertar('I', 'D');
        arbol1.insertar('H', 'D');
        arbol1.insertar('K', 'H');
        arbol1.insertar('T', 'E');
        arbol1.insertar('O', 'E');
        arbol1.insertar('R', 'E');
        
        // cheaquear toString
            System.out.println(arbol1.toString());
            System.out.println(arbol1.listarPorNivelPar().toString());
        // verificar lista
            //lis.insertar(27, 1);
            //lis.insertar(27, 1);
            //lis.insertar(54, 1);
            //lis.insertar(20, 1);
            //System.out.println(arbol1.listarEntreNiveles(1, 2).toString());
            //System.out.println(arbol1.toString());
            //System.out.println(arbol1.verificarPatronRecursivo(lis));
        // chequear padre 
            //System.out.println(arbol1.padre('M'));
        // ancestro
            //Lista lista1 = arbol1.ancestros('T');
            //System.out.println(lista1.toString());
        // listar posOrden
            //Lista lista1 = arbol1.listarPosOrden();
            //System.out.println(lista1.toString());
        // chequear los diferentes listar
            //Lista lista1 = arbol1.listarPreOrden();
            //System.out.println(lista1.toString());

    }
}
