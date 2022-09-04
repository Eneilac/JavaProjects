/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Generico.Celda;
import Generico.Constantes;
import Generico.Mapa;
import Generico.Obra;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author Eduardo
 */
public class Zapador extends Aliado {

    /**
     * Atributos
     */
    private Obra obra;

    /**
     * Constructor por defecto
     */
    public Zapador() {
        super();
        this.obra = null;

    }

    /**
     * Constructor parametrizado
     *
     * @param nombre nombre del zapador.
     * @param turno turno actual del zapador.
     * @param celdaActual celda actual del zapador.
     * @param marca identificador del zapador a la hora de pintar el mapa.
     *
     */
    public Zapador(String nombre, int turno, int celdaActual, char marca) {
        super(nombre, turno, celdaActual, marca);
        this.obra = null;

    }

    /**
     * Método realizar accion del zapador
     */
    @Override
    public void realizarAccion(PrintWriter pw) {

        Mapa miMapa = Mapa.getInstancia();

        if (miMapa.getTurno() == this.getTurno()) {

            if (!this.isCapturado()) {

                if (!estaEnPuntoEncuentro()) {

                    if (!asegurarPerimetro()) {

                        mover(pw);
                        recogerObra(pw);

                    } else {

                        pw.println(getNombre() + Constantes.DOS_PUNTOS + Constantes.PERIMETRO);
                    }

                } else {
                    pw.println(getNombre() + Constantes.ESTA_PUNTO_ENCUENTRO);
                }
            } else {
                pw.println(getNombre() + Constantes.DOS_PUNTOS + Constantes.CAPTURADO);
                setCapturado(false);
            }
            this.setTurno(this.getTurno() + 1);
        }

    }

    /**
     *
     * @return Objeto tipo obra
     */
    @Override
    public Obra getObra() {
        return obra;
    }

    /**
     *
     * @param obra Objeto tipo Obra
     */
    @Override
    public void setObra(Obra obra) {
        this.obra = obra;
    }

    /**
     *
     * @param pw flujo de escritura
     */
    @Override
    public void recogerObra(PrintWriter pw) {
        Celda celda = Mapa.getInstancia().getCelda(getCeldaActual());
        Obra obraCelda;
        HashSet<Obra> obraConjunto;
        obraConjunto = celda.getObras();
        Iterator<Obra> it = obraConjunto.iterator();

        try {
            obraCelda = it.next();

            if (this.obra != null) {
                pw.println(getNombre() + Constantes.DOS_PUNTOS + Constantes.NO_RECUPERADO + obraCelda.getNombre());
            }

            if ((obraCelda != null) && (this.obra == null)) {

                pw.println(getNombre() + Constantes.DOS_PUNTOS + obraCelda.getNombre() + Constantes.RECUPERADO);
                setObra(obraCelda);
                celda.borrarObra(obraCelda);

            }
        } catch (java.util.NoSuchElementException e) {
            /*Excepcion necesaria ya que en algunos turnos salta por el borrar obra*/

        }

    }

}
