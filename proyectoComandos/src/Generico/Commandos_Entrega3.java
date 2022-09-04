/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generico;

import Ficheros.Escritura;
import Ficheros.Lectura;
import Personajes.Militar;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Eduardo Neila Calzado
 */
public class Commandos_Entrega3 {

    //SIMULACIÓN COMMANDOS: BEHIND MONUMENTS MEN
    /**
     * Método que da comienzo y fin a la simulación del mapa seleccionado
     */
    public static void simulacion() {
        FileWriter fw;
        PrintWriter pw;
        int turno = 0;

        fw = Escritura.abrirFlujoEscrituraFl();     // Abrimos flujos de escritura 
        pw = Escritura.abrirFlujoEscrituraPw(fw);

        //SIMULACIÓN
        pw.println(Constantes.TURNO + turno);
        Mapa.getInstancia().pintarMatriz(pw);

        while ((Mapa.getInstancia().getNumAliadosPuntoEncuentro()) != (Mapa.getInstancia().getAliadosMapa())) {

            turno++;
            pw.println(Constantes.TURNO + turno);
            Mapa.getInstancia().setTurno(turno);

            for (int i = 0; i < Mapa.getInstancia().tamanoLista(); i++) {
                Militar aux = Mapa.getInstancia().getMilitar(i);
                aux.realizarAccion(pw);

            }
            Mapa.getInstancia().pintarMatriz(pw);

        }
        Escritura.flujosEscrituraLogs();    //Flujo de escritura solo para los registros
        Escritura.cerrarFlujosEscritura(fw, pw);        // Cerramos los flujos de escritura
    }

    /**
     *Método para mostrar un menú por consola
     */
    public static void menu() {
        System.out.println(Constantes.SELECCIONA);
        System.out.println(Constantes.EJECUTAR1);
        System.out.println(Constantes.EJECUTAR2);
        System.out.println(Constantes.EJECUTAR3);
        System.out.println(Constantes.SALIR);

    }

    /**
     *Método para solicitar el entero correspondiente a una de las opciones del menú
     * @return un número entero
     */
    public static int pedirOpcion() {

        Scanner teclado = new Scanner(System.in);
        int opcion = 0;
        System.out.print(Constantes.OPCION);

        try {

            opcion = teclado.nextInt();

        } catch (InputMismatchException e) {
            System.out.println(Constantes.ERROR_LETRA);
            System.out.println(e.getMessage());

        }
        return opcion;

    }

    /**
     * Método main del programa
     *
     * @param args definición de argumentos que recibe el mátodo main
     */
    public static void main(String[] args) {
        int opc;
        menu();

        do {
            opc = pedirOpcion();
        } while (opc == 0);

    switch (opc) {
            case 1:
                Lectura.abrirFlujosLectura(Constantes.INICIO3X3);
                simulacion();
                break;
            case 2:
                Lectura.abrirFlujosLectura(Constantes.INICIO5X5);
                simulacion();
                break;
            case 3:
                Lectura.abrirFlujosLectura(Constantes.INICIO8X8);
                simulacion();
                break;
            case 4:
                System.out.println(Constantes.ADIOS);
                break;
            default:
                System.out.println(Constantes.MAPA_NO_VALIDO);

        }
    }
}
