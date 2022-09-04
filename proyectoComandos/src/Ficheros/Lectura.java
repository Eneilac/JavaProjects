/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ficheros;

import Generico.Constantes;
import Generico.Mapa;
import Generico.Obra;
import Personajes.Aliado;
import Personajes.BoinaVerde;
import Personajes.Espia;
import Personajes.General;
import Personajes.Militar;
import Personajes.Soldado;
import Personajes.Zapador;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Eduardo
 */
public class Lectura {

    /**
     *
     * @param inicioMapa Tipo String que indica el tipo de mapa a ejecutar.
     * Método que se encarga de iniciar la lectura del fichero seleccionado
     */
    public static void abrirFlujosLectura(String inicioMapa) {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(Constantes.FICHEROS_DE_CARGA + inicioMapa);
            br = new BufferedReader(fr);
            cargarFichero(br);
        } catch (FileNotFoundException e) {
            System.out.println(Constantes.ARCHIVO_NO);
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     *
     * @param br objeto tipo BufferedReader para leer nuestro archivo linea
     * alinea
     * @throws IOException Control de excepciones de tipo entrada/salida
     * genérica
     */
    private static void cargarFichero(BufferedReader br) throws IOException {
        int opc, tipoMilitar;
        Militar militar;
        String[] token;
        boolean creado;
        String linea;
        creado = false;

        linea = br.readLine();
        while (linea != null) {
            token = linea.split(Constantes.PATRON_SPLIT);
            opc = queElemento(token[0]);  //Aquí evaluamos el primer String del primer segmento para saber que tipo de objeto debemos crear
            switch (opc) {
                case 0:
                    crearMapa(token);
                    creado = true;
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    if (creado) {
                        tipoMilitar = opc;
                        militar = crearMilitar(token, tipoMilitar);
                        insertarMilitares(militar);
                    }
                    break;
                case 6:
                    if (creado) {
                        crearObraArte(token);
                    }
                    break;
                default:

            }
            linea = br.readLine();
        }
    }

    /**
     * Metodo que decide el tipo de objeto a crear
     *
     * @param tipo 
     * @return retorna un entero de la posición del índice
     */
    private static int queElemento(String tipo) {
        int i = 0;
        boolean enc = false;
        String[] palabras = new String[]{Constantes.MAPA, Constantes.ESPIA, Constantes.BOINAVERDE, Constantes.ZAPADOR, Constantes.GENERAL, Constantes.SOLDADO, Constantes.OBRAARTE};
        while ((i < palabras.length) && (!enc)) {
            if (palabras[i].equalsIgnoreCase(tipo)) {
                enc = true;
            } else {
                i++;
            }
        }
        if (!enc) {
            return -1;
        } else {
            return i;
        }
    }

    /**
     *
     * @param token Parte del split que determina el tipo de mapa a crear.
     * Método que crea el mapa apartir de las constantes
     */
    private static void crearMapa(String[] token) {
        Mapa miMapa = Mapa.getInstancia();
        int celdaDisfraz, tipoMapa;
        String aux;
        char letra;

        letra = token[1].charAt(0);
        aux = Character.toString(letra);
        tipoMapa = Integer.parseInt(aux);
        celdaDisfraz = Integer.parseInt(token[2]);

        switch (tipoMapa) {
            case 3:
                miMapa.crearMapa(Constantes.TRESPORTRES);
                break;
            case 5:
                miMapa.crearMapa(Constantes.CINCOPORCINCO);
                break;
            case 8:
                miMapa.crearMapa(Constantes.OCHOPOROCHO);
                break;
            default:
        }
        miMapa.cargarDisfrazEnMapa(celdaDisfraz);
    }

    /**
     *
     * @param token datos necesarios para la creacion de los militares mediante
     * parametrizado.
     * @param tipoMilitar indice del switch del militar a crear.
     * @return Un objeto militar para su posterior tratamiento.
     */
    private static Militar crearMilitar(String[] token, int tipoMilitar) {
        int celdaActual = Integer.parseInt(token[3]);
        int turno = Integer.parseInt(token[2]);
        char marca = token[4].charAt(0);
        String nombre = token[1];
        Militar militar = null;
        char[] vRuta = token[5].toCharArray();

        switch (tipoMilitar) {
            case 1:
                militar = new Espia(nombre, turno, celdaActual, marca);
                break;
            case 2:
                militar = new BoinaVerde(nombre, turno, celdaActual, marca);
                break;
            case 3:
                militar = new Zapador(nombre, turno, celdaActual, marca);
                break;
            case 4:
                militar = new General(nombre, turno, celdaActual, marca);
                break;
            case 5:
                militar = new Soldado(nombre, turno, celdaActual, marca);
                break;
            default:

        }
        militar.cargarMovimientos(vRuta); /*Da una advertencia*/

        return militar;
    }

    /**
     * Método que crea la obra de arte con los datos recogidos en la linea del
     * fichero.
     *
     * @param token datos para crear la obra de arte.
     */
    private static void crearObraArte(String[] token) {
        Mapa miMapa = Mapa.getInstancia();
        String nombre, autor;
        int codigo, celda;

        codigo = Integer.parseInt(token[1]);
        celda = Integer.parseInt(token[2]);
        nombre = token[3];
        autor = token[4];
        miMapa.cargarObraEnCelda(new Obra(codigo, celda, nombre, autor), celda);
    }

    /**
     * Método para dar tratamiento a cualquier tipo de militar
     *
     * @param nMilitar Objeto tipo Militar
     * @param token necesario para cargar la ruta del militar
     */
    private static void insertarMilitares(Militar nMilitar) {
        Mapa miMapa = Mapa.getInstancia();

        miMapa.insertarMilitar(nMilitar);

        miMapa.insertarMilitarEnLista(nMilitar);
        if (nMilitar instanceof Aliado) {
            miMapa.incrementarAliadosEnMapa();
        }
    }
}
