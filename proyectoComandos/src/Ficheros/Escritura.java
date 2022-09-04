/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ficheros;

import Generico.Constantes;
import Generico.Mapa;
import Generico.Obra;
import Generico.Utilidad;
import Personajes.Aliado;
import Personajes.BoinaVerde;
import Personajes.Espia;
import Personajes.Militar;
import Personajes.Zapador;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 *
 * @author Eduardo
 */
public class Escritura {

    /**
     * Método que establece la ruta y el nombre del archivo de la simulación
     *
     * @return retorna el filewriter iniciado
     */
    public static FileWriter abrirFlujoEscrituraFl() {
        String nomFichero;
        File carpeta = new File(Constantes.CARPETA_SIMULACIONES);
        FileWriter fw = null;

        nomFichero = Utilidad.calculaNombreFichero(Constantes.SIMULACION, Constantes.EXTENSION);

        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
        try {
            fw = new FileWriter(Constantes.FICHEROS_DE_SIMULACION + nomFichero);

        } catch (FileNotFoundException e) {
            System.out.println(Constantes.ERROR_FILENOT);
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(Constantes.ERROR_IO);
            System.out.println(e.getMessage());
        }

        return fw;
    }

    /**
     *
     * @param fw flujo abierto
     * @return retorna el flujo PrintWriter iniciado
     */
    public static PrintWriter abrirFlujoEscrituraPw(FileWriter fw) {
        PrintWriter pw;

        pw = new PrintWriter(fw);

        return pw;
    }

    /**
     *
     * @param fw flujo abierto
     * @param pw flujo abierto
     */
    public static void cerrarFlujosEscritura(FileWriter fw, PrintWriter pw) {
        if (pw != null) {
            pw.close();
        }
        if (fw != null) {
            try {
                fw.close();
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    /**
     * Método que abre un flujo y lo cierra para escribir el log de la
     * simulacion
     *
     */
    public static void flujosEscrituraLogs() {
        String nomFichero;
        File carpeta = new File(Constantes.CARPETA_REGISTROS);
        FileWriter fw = null;
        PrintWriter pw = null;

        nomFichero = Utilidad.calculaNombreFichero(Constantes.REGISTRO, Constantes.EXTENSION);

        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
        try {
            fw = new FileWriter(Constantes.FICHEROS_DE_REGISTROS + nomFichero);
            pw = new PrintWriter(fw);
            escribirRegistroLog(pw);
        } catch (FileNotFoundException e) {
            System.out.println(Constantes.ERROR_FILENOT);  // Dejo estos sout ya que si no funciona el pw tiraria un null pointer
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(Constantes.ERROR_IO);
            System.out.println(e.getMessage());
        }
        if (pw != null) {
            pw.close();
        }
        if (fw != null) {

            try {
                fw.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println(Constantes.ERROR_IO);
            }
        }

    }

    /**
     * Método que se encarga de el log de los aliados
     *
     * @param pw flujo escritura abierto
     */
    private static void escribirRegistroLog(PrintWriter pw) {
        Mapa miMapa = Mapa.getInstancia();
        Iterator<Militar> it = miMapa.getlMilitares().iterator();
        Espia esp;
        Zapador zapa;
        Obra obra;
        Militar aux;

        while (it.hasNext()) {
            aux = it.next();

            if (aux instanceof Aliado) {

                if (aux instanceof BoinaVerde) {
                    pw.println(Constantes.PARENTESIS_APER + Constantes.BOINAVERDE + Constantes.SEPARADOR + aux.getNombre() + Constantes.SEPARADOR + aux.getMarca() + Constantes.PARENTESIS_CIE);
                    logBoinaVerde(pw, aux);
                }
                if (aux instanceof Espia) {
                    pw.println(Constantes.PARENTESIS_APER + Constantes.ESPIA + Constantes.SEPARADOR + aux.getNombre() + Constantes.SEPARADOR + aux.getMarca() + Constantes.PARENTESIS_CIE);
                    esp = (Espia) aux;
                    obra = esp.getObra();
                    pw.println(Constantes.PARENTESIS_APER + obra.getCodigo() + Constantes.SEPARADOR + obra.getNombre() + Constantes.SEPARADOR + obra.getAutor() + Constantes.PARENTESIS_CIE);
                }
                if (aux instanceof Zapador) {
                    pw.println(Constantes.PARENTESIS_APER + Constantes.ZAPADOR + Constantes.SEPARADOR + aux.getNombre() + Constantes.SEPARADOR + aux.getMarca() + Constantes.PARENTESIS_CIE);
                    zapa = (Zapador) aux;
                    obra = zapa.getObra();
                    pw.println(Constantes.PARENTESIS_APER + obra.getCodigo() + Constantes.SEPARADOR + obra.getNombre() + Constantes.SEPARADOR + obra.getAutor() + Constantes.PARENTESIS_CIE);

                }
            }
        }
    }

    /**
     * Método que hace el registro del conjunto de obras del boinaverde
     *
     * @param pw flujo de escritura abierto
     * @param militar objeto del boinaverde
     */
    private static void logBoinaVerde(PrintWriter pw, Militar militar) {
        BoinaVerde bverde;
        bverde = (BoinaVerde) militar;
        Obra aux;

        Iterator<Obra> it = bverde.getObrasConjunto().iterator();

        while (it.hasNext()) {
            aux = it.next();

            pw.println(Constantes.PARENTESIS_APER + aux.getCodigo() + Constantes.SEPARADOR + aux.getNombre() + Constantes.SEPARADOR + aux.getAutor() + Constantes.PARENTESIS_CIE);
        }

    }

}
