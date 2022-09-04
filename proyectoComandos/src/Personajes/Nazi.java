/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Generico.Celda;
import Generico.Constantes;
import Generico.Mapa;
import java.io.PrintWriter;
import java.util.Iterator;

/**
 *
 * @author Eduardo
 */
public abstract class Nazi extends Militar {

    /**
     * Constructor por defecto
     */
    public Nazi() {

        super();
    }

    /**
     * Constructor parametrizado
     *
     * @param nombre String nombre del nazi
     * @param turno numero tipo int del turno del personaje
     * @param celdaActual numero tipo int de la celda en la que se encuentra el
     * personaje
     * @param marca char que identifica el personaje al pintarlo
     */
    public Nazi(String nombre, int turno, int celdaActual, char marca) {
        super(nombre, turno, celdaActual, marca);

    }

    /**
     * Metodo sobre escrito del padre con una parte común y una diferenciada
     */
    @Override

    public void mover(PrintWriter pw) {

        super.mover(pw);  //parte del padre
        insertarNuevoMovimiento(getPrimerMovimiento()); //parte diferenciada del método
        borrarPrimerMovimiento();
    }

    /**
     * Método que captura un aliado
     *
     * @param pw flujo de escritura
     */
    public void capturarAliado(PrintWriter pw) {

        Celda celda = Mapa.getInstancia().getCelda(getCeldaActual());
        Iterator<Militar> it = celda.getlMilitares().iterator();
        Militar miliAux;

        while (it.hasNext()) {

            miliAux = it.next();

            if (miliAux instanceof Espia) {

                if (!((Espia) miliAux).isDisfrazado()) {
                    ((Espia) miliAux).setCapturado(true);

                } else {

                    pw.println(miliAux.getNombre() + Constantes.DOS_PUNTOS + Constantes.ENGANO);
                }
            }

            if (miliAux instanceof BoinaVerde) {
                ((BoinaVerde) miliAux).setCapturado(true);
            }

            if (miliAux instanceof Zapador) {
                ((Zapador) miliAux).setCapturado(true);
            }
        }
    }


    
    /**
     * Como en esta entrega el realizar accion de ambos nazis es igual lo
     * heredan directamente desde el padre
     *
     * Método realizar accion
     */
    @Override
    public void realizarAccion(PrintWriter pw) {
        Celda celda;
        int idCelda;
        idCelda = getCeldaActual();
        celda = Mapa.getInstancia().getCelda(idCelda);

        Mapa miMapa = Mapa.getInstancia();

        if (miMapa.getTurno() == getTurno()) {
            mover(pw);
            if (celda.isAliado(this)) {
                capturarAliado(pw);
            }
            setTurno(getTurno() + 1);
        }

    }

}
