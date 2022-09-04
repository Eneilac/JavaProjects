/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generico;

/**
 *
 * @author Eduardo
 */
public class Obra {

    /**
     * Atributos
     */
    private String nombre;
    private String autor;
    private int codigo;
    private int celdaActual;

    /**
     * Constructor por defecto con valores dados por el programador.
     */
    public Obra() {
        this.nombre = "";
        this.autor = "";
        this.codigo = 0;
        this.celdaActual = 0;
    }

    /**
     * Constructor parametrizado
     *
     * @param codigo codigo tipo entero
     * @param celdaActual celda en la que se encuentra la obra
     * @param nombre nombre de la obra
     * @param autor nombre del autor
     */
    public Obra(int codigo, int celdaActual, String nombre, String autor) {
        this.nombre = nombre;
        this.autor = autor;
        this.codigo = codigo;
        this.celdaActual = celdaActual;
    }

    //Metodos Getters y Setters
    
    /**
     *
     * @return nombre de la obra
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre nombre de la obra
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return nombre del autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     *
     * @param autor nombre del autor
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     *
     * @return codigo de la obra
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     *
     * @param codigo de la obra
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     *
     * @return id de la celda actual
     */
    public int getCeldaActual() {
        return celdaActual;
    }

    /**
     *
     * @param celdaActual id de la celda actual
     */
    public void setCeldaActual(int celdaActual) {
        this.celdaActual = celdaActual;
    }

}