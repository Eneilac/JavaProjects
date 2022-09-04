/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Generico.Celda;
import Generico.Constantes;
import Generico.Mapa;
import Generico.Utilidad;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Eduardo
 */
public abstract class Militar {

    /**
     * Atributos
     */
    private String nombre;
    private int turno;
    private int celdaActual;
    private ArrayList<Character> vRuta;
    private char marca;

    /**
     * Constructor por defecto
     */
    public Militar() {
        this.nombre = "";
        this.turno = 0;
        this.celdaActual = 0;
        this.marca = ' ';
        this.vRuta = new ArrayList<>();
    }

    /**
     * Constructor parametrizado
     *
     * @param nombre String nombre del militar
     * @param turno número tipo int del turno actual del militar
     * @param celdaActual numero tipo int de la celda actual del militar
     * @param marca char identificador del militar a la hora de pintarlo
     */
    public Militar(String nombre, int turno, int celdaActual, char marca) {
        this.nombre = nombre;
        this.turno = turno;
        this.celdaActual = celdaActual;
        this.marca = marca;
        this.vRuta = new ArrayList<>();

    }

    //Metodos Setters y Getters
    /**
     *
     * @return tipo char
     */
    public char getMarca() {
        return marca;
    }

    /**
     *
     * @param marca establece el identificador por paramentro
     */
    public void setMarca(char marca) {
        this.marca = marca;
    }

    /**
     *
     * @return el nombre del militar
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre Establece el nombre del militar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return el turno del militar
     */
    public int getTurno() {
        return turno;
    }

    /**
     *
     * @param turno establece el turno del militar
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }

    /**
     *
     * @return el id de la celda actual
     */
    public int getCeldaActual() {
        return celdaActual;
    }

    /**
     *
     * @param celdaActual establece la celda actual
     */
    public void setCeldaActual(int celdaActual) {
        this.celdaActual = celdaActual;
    }

    /**
     *
     * @return El primer movimiento de la ruta
     */
    public char getPrimerMovimiento() {
        return vRuta.get(0);
    }

    /**
     * Método que borra el primer movimiento de la ruta.
     */
    public void borrarPrimerMovimiento() {

        vRuta.remove(0);
    }

    /**
     * Metodo que inserta un nuevo movimiento al final de la lista vRuta
     *
     * @param nMovimiento Movimiento a insertar por parámetro.
     */
    public void insertarNuevoMovimiento(char nMovimiento) {

        vRuta.add(nMovimiento);

    }

    /**
     *
     * @return la siguiente celda a la que se quiere acceder.
     */
    public int calcularSiguienteCelda() {
        char direccion;
        int siguienteCelda;

        siguienteCelda = celdaActual;

        direccion = getPrimerMovimiento();

        switch (direccion) {

            case Constantes.NORTE:
                siguienteCelda = siguienteCelda - Mapa.getInstancia().getColumnas();  // usamos las columnas por que si no fuese una matriz regular nos saldriamos del mapa o nos iriamos a la casilla incorrecta.
                break;
            case Constantes.SUR:
                siguienteCelda = siguienteCelda + Mapa.getInstancia().getColumnas();
                break;
            case Constantes.OESTE:
                siguienteCelda = siguienteCelda - 1;
                break;
            case Constantes.ESTE:
                siguienteCelda = siguienteCelda + 1;
                break;
            default:
                System.out.println(Constantes.DIRECCION);/*Funcionando todo ok no deberia mostrar nada por pantalla y en caso contrario sirve de aviso*/
        }
        return siguienteCelda;
    }

    /**
     *
     * @return true si esta llena y false si esta vacía.
     */
    public boolean isLLeno() {

        if (vRuta.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Método que muestra por pantalla la ruta del militar ( método de la
     * anterior entrega con modificaciones por el cambio de estructura).
     */
    public void mostrarRuta() {
        String mruta = "";

        for (int i = 0; i < vRuta.size(); i++) {

            mruta = mruta + "[" + vRuta.get(i) + "] ";
        }
        System.out.print(mruta);
    }

    /**
     * Parte común del metodo mover para todas las clases
     *
     * @param pw
     *
     */
    public void mover(PrintWriter pw) {
        Celda celda;
        int fila, columna, filaS, columnaS;
        String nombreM;
        Mapa miMapa = Mapa.getInstancia();

        nombreM = getNombre();

        fila = Utilidad.calcularFila(getCeldaActual());     // Con estos dos sacamos las cordenadas de la celda actual
        columna = Utilidad.calcularColumna(getCeldaActual());

        Celda estaCelda = miMapa.getCelda(fila, columna);   //Asignamos dentro de nuestro objeto tipo celda las cordenadas de la celda actual

        if (miMapa.hayCamino(getCeldaActual(), calcularSiguienteCelda())) {

            filaS = Utilidad.calcularFila(calcularSiguienteCelda());
            columnaS = Utilidad.calcularColumna(calcularSiguienteCelda());     //Asignamos dentro de nuestro objeto tipo celda las cordenadas de la siguiente celda
            celda = miMapa.getCelda(filaS, columnaS);

            // Con estas 3 lineas calculamos la siguiente celda
            celda.insertarMilitar(this);
            estaCelda.borrarMilitar(nombreM);
            setCeldaActual(calcularSiguienteCelda());

        } else {

            pw.println(getNombre() + Constantes.SEPARADOR + Constantes.NO_PUEDO + "(" + getPrimerMovimiento() + ")");
        }

    }

    /**
     * Método que coge la ruta establecida en el Main para los aliados o los
     * nazis y lo carga dentro del atributo vRuta de la clase que corresponda.
     *
     * @param vRuta vector de char con la ruta del militar recibito por
     * parámetro.
     */
    public void cargarMovimientos(char[] vRuta) {

        for (int i = 0; i < vRuta.length; i++) {

            this.vRuta.add(vRuta[i]);
        }
    }

    /**
     *
     * @param pw flujo de escritura
     */
    public abstract void realizarAccion(PrintWriter pw);

}
