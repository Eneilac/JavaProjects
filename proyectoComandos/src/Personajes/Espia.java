/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Generico.Obra;
import Generico.Celda;
import Generico.Constantes;
import Generico.Mapa;
import Generico.Utilidad;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author Eduardo
 */
public class Espia extends Aliado {

    /**
     * Atributos
     */
    private boolean disfrazado;
    private Obra obra;

    /**
     * Constructor por defecto
     */
    public Espia() {
        super();
        this.disfrazado = false;
        this.obra = null;
    }

    /**
     * Constructor parametrizado
     *
     * @param nombre Nombre del espia
     * @param turno turno actual del espia
     * @param celdaActual celda en la que se encuentra el espia
     * @param marca identificador del espia a la hora de pintar la matriz
     */
    public Espia(String nombre, int turno, int celdaActual, char marca) {
        super(nombre, turno, celdaActual, marca);
        this.disfrazado = false;
        this.obra = null;

    }

    //Metodos Setter y Getter
    /**
     *
     * @return True en caso de estar disfrazado. false en caso contrario
     */
    public boolean isDisfrazado() {
        return disfrazado;
    }

    /**
     *
     * @param disfrazado establece si esta disfrazado o no.
     */
    public void setDisfrazado(boolean disfrazado) {
        this.disfrazado = disfrazado;
    }

    /**
     *
     * @return retorna objeto tipo obra
     */
    @Override
    public Obra getObra() {
        return obra;
    }

    /**
     *
     * @param obra establece objeto tipo obra
     */
    @Override
    public void setObra(Obra obra) {
        this.obra = obra;
    }

    /**
     * Método que recoge el disfraz se lo pone y lo quita de la celda .
     *
     * @param pw
     * @param celda objeto tipo celda
     */
    public void recorgerDisfraz(Celda celda, PrintWriter pw) {
        // no compruebo si la celda tiene disfraz ya que lo compruebo fuera.
        pw.println(getNombre() + Constantes.DOS_PUNTOS + Constantes.RECOGER_DISFRAZ);
        setDisfrazado(true);
        celda.setDisfraz(false);

    }

    /**
     *
     * @param pw flujo escritura
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

    /**
     * Método realizar acción
     */
    @Override
    public void realizarAccion(PrintWriter pw) {
        int filaS, columnaS, siguienteCelda;
        boolean sePuedeMover = false;
        Mapa miMapa = Mapa.getInstancia();
        Celda celda;

        if (miMapa.getTurno() == this.getTurno()) {
            if (!isCapturado()) {

                if (!estaEnPuntoEncuentro()) {

                    siguienteCelda = calcularSiguienteCelda();
                    filaS = Utilidad.calcularFila(siguienteCelda);
                    columnaS = Utilidad.calcularColumna(siguienteCelda);

                    if (isDisfrazado()) {
                        sePuedeMover = true;
                    } else {

                        if (!asegurarPerimetro()) {

                            if (miMapa.hayCamino(getCeldaActual(), siguienteCelda)) {

                                celda = miMapa.getCelda(filaS, columnaS);
                                if (celda.isDisfraz()) {
                                    recorgerDisfraz(celda, pw);
                                }
                            }
                            sePuedeMover = true;
                        } else {
                            pw.println(getNombre() + Constantes.DOS_PUNTOS + Constantes.PERIMETRO);
                        }
                    }
                } else {

                    pw.println(getNombre() + Constantes.ESTA_PUNTO_ENCUENTRO);
                }
            } else {
                pw.println(getNombre() + Constantes.DOS_PUNTOS + Constantes.CAPTURADO);
                setCapturado(false);
            }
            setTurno(getTurno() + 1);

        }
        if (sePuedeMover) { /*variable necesaria para no duplicar la llamada a los metodos mover o recoger obra */
            mover(pw);
            recogerObra(pw);
        }
    }
}
