package Estructura.jerarquicas.ArbolConjuntista.TablaHash;

public class Funciones {
    
    /*      Funcion Doblamiento     */

    public int doblamiento(Object clave, int tamanio){

        int aux = (int)clave, longitudClave = 0, posicion = 0, cantGrupos, i;
        // Se calcula la cantidad de digitos de la clave
        while(aux > 0){
            aux = aux/10;
            longitudClave++;
        }
        // Se calcula la cantidad de grupos en la que se separara la clave
        cantGrupos = longitudClave/2;
        if(longitudClave%2 != 0){
            cantGrupos++;
        }
        aux = (int)clave;
        // Sumo los los grupos que en los que se dividio la clave
        for(i=0; i<cantGrupos; i++){
            posicion = posicion + aux%100;
            aux = aux/100;
        }
        // Verifico que la posicion obtenida no sea mayor que el tamanio de la tabla hash
        if(posicion >= tamanio){
            posicion = posicion%tamanio;
        }
        return posicion;
    }

    public static int string( String clave, int tamanio){

        int posicion = 0, i;
        for(i=0; i<clave.length();i++){
            posicion = posicion + (int)clave.charAt(i);
        }
        if(posicion >= tamanio){
            posicion = posicion%tamanio;
        }
        return posicion;
    }

    public static int rehashing(int elemento){
        return elemento/10;
    }
}
