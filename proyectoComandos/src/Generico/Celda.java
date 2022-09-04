/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generico;

import Personajes.Aliado;
import Personajes.Militar;
import Personajes.Nazi;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author Eduardo Neila Calzado
 */
public class Celda {

    /**
     * Atributos
     */
    private int idCelda;
    private ArrayList<Militar> lMilitares;
    private boolean disfraz;
    private boolean puntoDeEncuentro;
    private HashSet<Obra> obra;

    /**
     *
     * constructtor por defecto
     */
    public Celda() {
        this.idCelda = 0;
        this.lMilitares = new ArrayList<>();
        this.disfraz = false;
        this.puntoDeEncuentro = false;
        this.obra = new HashSet<>();
    }

    /**
     *
     * Constructor parametrizado
     *
     * @param celda Entero de la celda
     */
    public Celda(int celda) {
        this.idCelda = celda;
        this.disfraz = false;
        this.puntoDeEncuentro = false;
        this.lMilitares = new ArrayList<>();
        this.obra = new HashSet<>();
    }

    /*Métodos Setters y Getters*/
    /**
     *
     * @param idCelda recibe un idcelda tipo int y lo establece.
     */
    public void setIdCelda(int idCelda) {
        this.idCelda = idCelda;
    }

    /**
     *
     * @param disfraz recibe un booleano por parametro y lo establece.
     */
    public void setDisfraz(boolean disfraz) {
        this.disfraz = disfraz;
    }

    /**
     *
     * @return Retorna un booleano del atributo disfraz
     */
    public boolean isDisfraz() {
        return this.disfraz;
    }

    /**
     *
     * @return Retorna el id de la celda actual
     */
    public int getIdCelda() {
        return idCelda;
    }

    /**
     *
     * @return Retorna si la celda actual es el punto de encuentro
     */
    public boolean getPuntoDeEncuentro() {
        return this.puntoDeEncuentro;
    }

    /**
     *
     * @param puntoDeEncuentro Establece por parametro el punto de encuentro con
     * un booleano
     */
    public void setPuntoDeEncuentro(boolean puntoDeEncuentro) {
        this.puntoDeEncuentro = puntoDeEncuentro;
    }

    /**
     * @param nMilitar String que contiene el nombre del militar.
     * @return Retorna el objeto tipo Militar. Método que buscar por un nombre
     * un militar y lo retorna.
     */
    public Militar buscarMilitar(String nMilitar) {
        int i = 0;
        Boolean enc = false;

        Militar nuevoMilitar = null; // no me deja hacer el constructor por defecto por ser abstracto

        while ((!enc) && (i < lMilitares.size())) {

            if (lMilitares.get(i).getNombre().equalsIgnoreCase(nMilitar)) {

                nuevoMilitar = lMilitares.get(i);
                enc = true;

            } else {
                i++;
            }
        }
        return nuevoMilitar;
    }

    /**
     *
     * @param posicion Entero con la posicion del militar
     * @return Retorna el objeto tipo Militar.
     *
     */
    public Militar buscarMilitar(int posicion) {

        return lMilitares.get(posicion);

    }

    /**
     *
     * @param nMilitar Objeto tipo militar recibito por parametro Método que
     * inserta un
     */
    public void insertarMilitar(Militar nMilitar) {

        this.lMilitares.add(nMilitar);
    }

    /**
     *
     * @param nMilitar String del militar que se quiere borrar Método que borrar
     * un militar apartir de su nombre.
     */
    public void borrarMilitar(String nMilitar) {
        int i = 0;
        Boolean enc = false;

        while ((!enc) && (i < lMilitares.size())) {

            if (lMilitares.get(i).getNombre().equalsIgnoreCase(nMilitar)) {
                this.lMilitares.remove(i);
                enc = true;

            } else {
                i++;
            }
        }
    }

    /**
     * Método que comprueba si hay un aliado para el metodo capturar aliado
     *
     * @return un booleano
     * @param nazi nazi
     */
    public boolean isAliado(Nazi nazi) {
        boolean esAliado = false;
        int celdaActual = nazi.getCeldaActual();
        Celda celda = Mapa.getInstancia().getCelda(celdaActual);
        ArrayList<Militar> militaresCelda = celda.getlMilitares();
        Iterator<Militar> it = militaresCelda.iterator();

        Militar militar;

        while (it.hasNext() && !esAliado) {
            militar = it.next();
            esAliado = militar instanceof Aliado;
        }
        return esAliado;
    }
    /**
     *
     * @return retorna true o false Método que comprueba si el objeto es de tipo
     * Nazi
     */
    public boolean isNazi() {
        int i = 0;
        boolean enc = false;

        if (!this.lMilitares.isEmpty()) {
            while ((i < lMilitares.size()) && (!enc)) {

                if (lMilitares.get(i) instanceof Nazi) {
                    enc = true;
                }
                i++;
            }
        }
        return enc;
    }

    /**
     *
     * @return el tamaño de la lista
     */
    public int getTamano() {

        return lMilitares.size();

    }

    /**
     *
     * @return una obra del conjunto
     */
    public Obra getObra() {

        Iterator<Obra> it = obra.iterator();

        if (it.next() == null) {
            return null;
        } else {

            return it.next();
        }

    }

    /**
     *
     * @param obra Objeto tipo obra
     */
    public void setObra(Obra obra) {

        this.obra.add(obra);
    }

    /**
     *
     * @param obra Objeto tipo obra
     */
    public void borrarObra(Obra obra) {

        this.obra.remove(obra);
    }

    /**
     *
     * @return Un conjunto de obras
     */
    public HashSet getObras() {

        return obra;

    }

    /**
     *
     * @return false si contiene alguna obra true n caso contrario
     */
    public boolean hayObras() {

        return this.obra.isEmpty();
    }

    public ArrayList<Militar> getlMilitares() {
        return lMilitares;
    }

    /**
     * MÉTODOS PARA PINTAR MAPA
     *
     * @return el simbolo a pintar
     */
    @Override
    public String toString() {
        if (this.lMilitares.size() > 0) {
            if (this.lMilitares.size() == 1) {
                //valueOf convierte a String el char recibido por parámetro
                return String.valueOf(this.lMilitares.get(0).getMarca());
            } else {
                //valueOf convierte a String el int recibido por parámetro
                return String.valueOf(this.lMilitares.size());
            }
        } else {

            if (hayObras()) {
                return Constantes.CERO;
            } else {
                return Constantes.INTERROGACION;
            }
        }

    }


}
