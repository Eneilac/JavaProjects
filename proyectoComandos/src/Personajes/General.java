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
import Generico.Utilidad;
import java.io.PrintWriter;
import java.util.Iterator;

/**
 *
 * @author Eduardo
 */
public class General extends Nazi {

    /**
     * Constructor por defecto
     */
    public General() {
        super();
    }

    /**
     * Construcctor parametrizado
     * @param nombre tipo String
     * @param turno tipo entero
     * @param celdaActual tipo entero
     * @param marca tipo char
     */
    public General(String nombre, int turno, int celdaActual, char marca) {
        super(nombre, turno, celdaActual, marca);
    }

    /**
     * @param pw
     * @param bVerd
     * @param celda
     */
    public void requisaBoinaVerde(BoinaVerde bVerd, Celda celda, PrintWriter pw) {
        Iterator<Obra> it;
        Obra obra;
        it = bVerd.getObrasConjunto().iterator();
        while (it.hasNext()) {
       
            obra = (Obra) it.next();
            celda.setObra(obra);
            pw.println(bVerd.getNombre() + Constantes.DOS_PUNTOS + obra.getNombre() + Constantes.REQUISADA);
        }
        bVerd.getObrasConjunto().clear(); // Limpia el conjunto de obras del boina verde 
    }

    
    /**
     * @param pw
     * @param espia
     * @param celda
     */
    public void requisarEspia(Espia espia, Celda celda, PrintWriter pw) {
        Obra obra;

        if ((!espia.isDisfrazado()) && (espia.getObra() != null)) {

            obra = espia.getObra();
            celda.setObra(obra);
            espia.setObra(null);
            pw.println(espia.getNombre() + Constantes.DOS_PUNTOS + obra.getNombre() + Constantes.REQUISADA);
        }
    }

    /**
     * @param pw
     * @param zapa
     * @param celda
     */
    public void requisarZapador(Zapador zapa, Celda celda, PrintWriter pw) {
        Obra obra;
        if (zapa.getObra() != null) {

            obra = zapa.getObra();
            celda.setObra(obra);
            zapa.setObra(null);
            pw.println(zapa.getNombre() + Constantes.DOS_PUNTOS + obra.getNombre() + Constantes.REQUISADA);
        }
    }

    /**
     * Metodo que requisa las obras de todos los aliados encontrados en dicha
     * celda
     *
     * @param pw
     */
    public void requisarObras(PrintWriter pw) {
        Mapa miMapa = Mapa.getInstancia();
        Aliado ali;
        Espia espia;
        Zapador zapa;
        BoinaVerde bverd;
        Celda celda = miMapa.getCelda(Utilidad.calcularFila(getCeldaActual()), Utilidad.calcularColumna(getCeldaActual()));

        for (int i = 0; i < celda.getTamano(); i++) {
            if (celda.getlMilitares().get(i) instanceof Aliado) {
                ali = (Aliado) celda.getlMilitares().get(i);

                if (ali instanceof BoinaVerde) {
                    bverd = (BoinaVerde) ali;
                    if (bverd.getObrasConjunto().size() > 0) {
                        requisaBoinaVerde(bverd, celda, pw);
                    }
                } else {
                    if (ali instanceof Espia) {
                        espia = (Espia) ali;
                        requisarEspia(espia, celda, pw);

                    } else {
                        if (ali instanceof Zapador) {
                            zapa = (Zapador) ali;
                            requisarZapador(zapa, celda, pw);
                        }
                    }
                }
            }
        }
    }

    /**
     * Metodo realizar acción del general
     */
    @Override
    public void realizarAccion(PrintWriter pw) {

        super.realizarAccion(pw);
        requisarObras(pw);

    }
}
