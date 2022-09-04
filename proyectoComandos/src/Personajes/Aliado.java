/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Generico.Celda;
import Generico.Mapa;
import Generico.Obra;
import Generico.Utilidad;
import java.io.PrintWriter;

/**
 *
 * @author Eduardo
 */
public abstract class Aliado extends Militar {

    //Atributos
    private boolean capturado;
    private boolean enPuntoEncuentro;
    private Obra obra;

    /**
     * Constructor por defecto
     */
    public Aliado() {
        super();
        this.capturado = false;
        this.enPuntoEncuentro = false;
        this.obra = null;

    }

    /**
     * Constructor parametrizado
     *
     * @param nombre String nombre del aliado
     * @param turno numero tipo int del turno del Aliado
     * @param celdaActual número tipo int del idCelda actual.
     * @param marca Char que simboliza el personaje.
     */
    public Aliado(String nombre, int turno, int celdaActual, char marca) {
        super(nombre, turno, celdaActual, marca);
        this.capturado = false;
        this.enPuntoEncuentro = false;
        this.obra = null;
    }

    //Métodos Setter y Getter
    /**
     *
     * @return true o false dependiendo si esta o no capturado
     */
    public boolean isCapturado() {
        return capturado;
    }

    /**
     *
     * @param enPuntoEncuentro booleano
     */
    public void setEnPuntoEncuentro(boolean enPuntoEncuentro) {
        this.enPuntoEncuentro = enPuntoEncuentro;
    }

    /**
     *
     * @param capturado Establece si esta capturado o no por parámetro
     */
    public void setCapturado(boolean capturado) {
        this.capturado = capturado;
    }

    /**
     *
     * @return true en caso de estar en el punto de encuentro, false en caso
     * contrario
     */
    public boolean estaEnPuntoEncuentro() {
        Mapa miMapa = Mapa.getInstancia();
        Celda celda = miMapa.getCelda(getCeldaActual());

        if (celda.getPuntoDeEncuentro()) {
            if (!enPuntoEncuentro) {
                miMapa.aliadoEnPunto();
                enPuntoEncuentro = true;
            }
        }
        return enPuntoEncuentro;
    }

    /**
     *
     * @return retorna si esta en el punto de encuentro
     */
    public boolean isEnPuntoEncuentro() {
        return enPuntoEncuentro;
    }

    /**
     *
     * @return retorna objeto tipo obra
     */
    public Obra getObra() {
        return obra;
    }

    /**
     *
     * @param obra estable objeto tipo obra
     */
    public void setObra(Obra obra) {
        this.obra = obra;
    }

    /**
     *
     * @param pw flujo escritura
     */
    @Override
    public void mover(PrintWriter pw) {
        super.mover(pw);
        borrarPrimerMovimiento();
    }

    /**
     *
     * @return retorna true en caso de encontrar un nazi y un false en caso
     * contrario
     */
    public boolean asegurarPerimetro() {

        Mapa m = Mapa.getInstancia();
        int siguienteCelda = calcularSiguienteCelda();

        Celda sCelda;
        boolean asegurado = false;

        if (m.hayCamino(this.getCeldaActual(), siguienteCelda)) {
            sCelda = m.getCelda(Utilidad.calcularFila(siguienteCelda), Utilidad.calcularColumna(siguienteCelda));
            asegurado = sCelda.isNazi();
        }
        return asegurado;
    }

    // Métodos Abstractos
    /**
     *
     * @param pw flujo escritura
     */
    public abstract void recogerObra(PrintWriter pw);

    /**
     *
     * @param pw flujo escritura
     */
    @Override
    public abstract void realizarAccion(PrintWriter pw);

}
