package Estructura.jerarquicas.ArbolConjuntista.TablaHash;

public class CeldaHash {

    /*  Atributo    */

    private Object elemento;
    private int estado;

    /*  Constructor */

    public CeldaHash() {
        // inicializar las celdas con elemento null y en 0 para podes ingresar los nuevos elemento en la tabla hash cerrado
        this.elemento = null;
        this.estado = 0;
    }

    /*  Modificadores/Visualizadores    */
    
    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
}
