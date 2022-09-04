/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generico;

import java.time.LocalDate;

/**
 *
 * @author Eduardo
 */
public class Utilidad {

    /**
     *
     * @param idCelda entero con el idCelda.
     * @return retorna la fila
     */
    public static int calcularFila(int idCelda) {
        return idCelda / Mapa.getInstancia().getFilas();
    }

    /**
     * @param idCelda entero con el idCelda.
     * @return retorna la columna
     */
    public static int calcularColumna(int idCelda) {
        return idCelda % Mapa.getInstancia().getColumnas();
    }

    /**
     *
     * @param baseNombre String con la base del fichero
     * @param extension String con la extension del fichero
     * @return String con el nombre final del fichero
     */
    public static String calculaNombreFichero(String baseNombre, String extension) {
        String tamMapa, fecha, nombreFichero;

        tamMapa = Mapa.getInstancia().getFilas() + Constantes.X + Mapa.getInstancia().getColumnas();
        fecha = Constantes.BARRA_BAJA + LocalDate.now().getDayOfMonth() + LocalDate.now().getMonthValue();

        nombreFichero = baseNombre + tamMapa + fecha + extension;

        return nombreFichero;

    }

}
