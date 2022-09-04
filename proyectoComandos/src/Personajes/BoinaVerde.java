/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Generico.Mapa;
import java.util.HashSet;
import Generico.Obra;
import java.util.Iterator;
import Generico.Celda;
import Generico.Constantes;
import java.io.PrintWriter;

/**
 *
 * @author Eduardo
 */
public class BoinaVerde extends Aliado {

    //Atributos
    private HashSet<Obra> obra;

    /**
     * Constructor por defecto
     */
    public BoinaVerde() {
        super();
        this.obra = new HashSet<>();
    }

    /**
     * Constructor Parametrizado
     *
     * @param nombre Nombre del boina verde
     * @param turno turno actual del boina verde
     * @param celdaActual celda en la que se encuentra el boina verde
     * @param marca Identificador del boina verde a la hora de pintarlo
     */
    public BoinaVerde(String nombre, int turno, int celdaActual, char marca) {

        super(nombre, turno, celdaActual, marca);
        this.obra = new HashSet<>();

    }

    /**
     *
     * @return retorna conjunto de obras para los registros
     */
    public HashSet<Obra> getObrasConjunto() {
        return obra;
    }

    /**
     *
     * @param obra establece objeto tipo obra
     */
    @Override
    public void setObra(Obra obra) {
        this.obra.add(obra);
    }

    /**
     * Método de la anterior entrega que muestra un string cuando se llama a
     * este método.
     *
     * @param pw
     */
    @Override
    public void recogerObra(PrintWriter pw) {

        Mapa miMapa = Mapa.getInstancia();
        Celda celda = miMapa.getCelda(getCeldaActual());
        Iterator<Obra> it = celda.getObras().iterator();

        while (it.hasNext()) {
            Obra obraAux = it.next();

            obra.add(obraAux);
            celda.borrarObra(obraAux);
            pw.println(getNombre() + Constantes.DOS_PUNTOS + obraAux.getNombre() + Constantes.RECUPERADO);
        }
    }

    /**
     * Método realizar accion del boina verde
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
}
