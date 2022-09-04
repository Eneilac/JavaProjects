/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;



/**
 *
 * @author Eduardo
 */
public class Soldado extends Nazi {

    /**
     * Constructor por defecto. 
     */
    public Soldado() {
        super();
    }

    /**
     * Constructor parametrizado.
     * 
     * @param nombre nombre del soldado
     * @param turno turno actual del soldado
     * @param celdaActual celda en la que se encuentra el soldado
     * @param marca  identificador de la celda del soldado.
     */
    public Soldado(String nombre, int turno,int celdaActual ,char marca) {
        super(nombre, turno, celdaActual, marca);
        
    }
}
