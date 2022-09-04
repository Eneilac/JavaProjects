/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generico;

import Personajes.Militar;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Eduardo Neila
 */
public class Mapa {

    // Atributos
    private static Mapa miMapa;  //Atributo del patrón Singleton
    private int[][] adyacencia;  //Matriz de adyacencia
    private int turno;
    private Celda[][] matriz;
    private ArrayList<Militar> lMilitares;
    private Celda celda;
    private int columnas;
    private int filas;
    private int numAliadosPuntoEncuentro;
    private int aliadosMapa;

    /**
     * Constructor clase Mapa Será privado, ya que estamos utilizando el patrón
     * Singleton (así obligamos a utilizar el método getInstancia, asegurando de
     * esta manera que exista una única instancia
     */
    private Mapa() {
        this.turno = 0;
        this.matriz = new Celda[filas][columnas];
        this.lMilitares = new ArrayList<>();
        this.numAliadosPuntoEncuentro = 0;
        this.aliadosMapa = 0;
        int cont = 0;

        //Inicializamos celdas
        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz[i].length; j++) {
                this.matriz[i][j] = new Celda(cont);
                cont++;
            }
        }
    }

    /**
     * Método para crear la instancia del patrón singleton
     *
     * @return el mapa creado
     */
    public static Mapa getInstancia() {
        if (miMapa == null) {
            miMapa = new Mapa();
        }
        return miMapa;
    }

    /**
     * Crea el mapa del tamaño adecuado, según el parámetro recibido
     *
     * @param tamMapa tamaño del mapa
     */
    public void crearMapa(String tamMapa) {
        if (tamMapa.equalsIgnoreCase(Constantes.TRESPORTRES)) {
            this.filas = Constantes.TRES;
            this.columnas = Constantes.TRES;
        } else {
            if (tamMapa.equalsIgnoreCase(Constantes.CINCOPORCINCO)) {
                this.filas = Constantes.CINCO;
                this.columnas = Constantes.CINCO;
            } else {
                this.filas = Constantes.OCHO;
                this.columnas = Constantes.OCHO;
            }
        }

        this.matriz = new Celda[this.filas][this.columnas];

        int cont = 0;
        //Inicializamos celdas
        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz[i].length; j++) {
                this.matriz[i][j] = new Celda(cont);
                cont++;
            }
        }

        //Cargamos punto de encuentro
        this.matriz[this.filas - 1][this.columnas - 1].setPuntoDeEncuentro(true);

        this.adyacencia = new int[this.filas * this.filas][this.columnas * this.columnas];
        //Rellenamos matriz de adyacencia con 0s
        for (int i = 0; i < this.adyacencia.length; i++) {
            for (int j = 0; j < this.adyacencia[i].length; j++) {
                this.adyacencia[i][j] = 0;
            }
        }

        //Actualizamos las celdas que tienen 1s dependiendo del mapa
        if (tamMapa.equalsIgnoreCase(Constantes.TRESPORTRES)) {
            crearMapa3x3();

        } else {
            if (tamMapa.equalsIgnoreCase(Constantes.CINCOPORCINCO)) {
                crearMapa5x5();
            } else {
                crearMapa8x8();
            }
        }
    }

    /**
     * "Abre" los caminos en la madtriz de adyacencia del Mapa 3x3
     */
    private void crearMapa3x3() {

        //Actualizamos las celdas que tienen 1s
        this.adyacencia[0][1] = 1;
        this.adyacencia[0][3] = 1;

        this.adyacencia[1][0] = 1;
        this.adyacencia[1][2] = 1;
        this.adyacencia[1][4] = 1;

        this.adyacencia[2][1] = 1;

        this.adyacencia[3][0] = 1;
        this.adyacencia[3][4] = 1;
        this.adyacencia[3][6] = 1;

        this.adyacencia[4][1] = 1;
        this.adyacencia[4][3] = 1;
        this.adyacencia[4][7] = 1;

        this.adyacencia[6][3] = 1;
        this.adyacencia[6][7] = 1;

        this.adyacencia[7][4] = 1;
        this.adyacencia[7][6] = 1;
        this.adyacencia[7][8] = 1;

        this.adyacencia[8][7] = 1;
    }

    /**
     * "Abre" los caminos en la madtriz de adyacencia del Mapa 5x5
     */
    private void crearMapa5x5() {

        //Actualizamos las celdas que tienen 1s
        this.adyacencia[0][1] = 1;

        this.adyacencia[1][0] = 1;
        this.adyacencia[1][2] = 1;

        this.adyacencia[2][1] = 1;
        this.adyacencia[2][3] = 1;
        this.adyacencia[2][7] = 1;

        this.adyacencia[3][2] = 1;
        this.adyacencia[3][4] = 1;
        this.adyacencia[3][8] = 1;

        this.adyacencia[4][3] = 1;
        this.adyacencia[4][9] = 1;

        this.adyacencia[7][2] = 1;
        this.adyacencia[7][8] = 1;
        this.adyacencia[7][12] = 1;

        this.adyacencia[8][3] = 1;
        this.adyacencia[8][7] = 1;
        this.adyacencia[8][9] = 1;

        this.adyacencia[9][4] = 1;
        this.adyacencia[9][8] = 1;

        this.adyacencia[10][11] = 1;
        this.adyacencia[10][15] = 1;

        this.adyacencia[11][10] = 1;
        this.adyacencia[11][12] = 1;

        this.adyacencia[12][7] = 1;
        this.adyacencia[12][11] = 1;
        this.adyacencia[12][17] = 1;

        this.adyacencia[15][10] = 1;
        this.adyacencia[15][20] = 1;

        this.adyacencia[17][12] = 1;
        this.adyacencia[17][22] = 1;

        this.adyacencia[20][15] = 1;

        this.adyacencia[22][17] = 1;
        this.adyacencia[22][23] = 1;

        this.adyacencia[23][22] = 1;
        this.adyacencia[23][24] = 1;

        this.adyacencia[24][23] = 1;
    }

    /**
     * "Abre" los caminos en la madtriz de adyacencia del Mapa 8x8
     */
    private void crearMapa8x8() {
        //Actualizamos las celdas que tienen 1s
        this.adyacencia[0][1] = 1;
        this.adyacencia[0][8] = 1;

        this.adyacencia[1][0] = 1;
        this.adyacencia[1][2] = 1;

        this.adyacencia[2][1] = 1;
        this.adyacencia[2][3] = 1;
        this.adyacencia[2][10] = 1;

        this.adyacencia[3][2] = 1;
        this.adyacencia[3][4] = 1;
        this.adyacencia[3][11] = 1;

        this.adyacencia[4][3] = 1;
        this.adyacencia[4][5] = 1;
        this.adyacencia[4][12] = 1;

        this.adyacencia[5][4] = 1;
        this.adyacencia[5][6] = 1;

        this.adyacencia[6][5] = 1;
        this.adyacencia[6][7] = 1;

        this.adyacencia[7][6] = 1;

        this.adyacencia[8][0] = 1;
        this.adyacencia[8][16] = 1;

        this.adyacencia[10][2] = 1;
        this.adyacencia[10][11] = 1;
        this.adyacencia[10][18] = 1;

        this.adyacencia[11][3] = 1;
        this.adyacencia[11][10] = 1;
        this.adyacencia[11][12] = 1;
        this.adyacencia[11][19] = 1;

        this.adyacencia[12][4] = 1;
        this.adyacencia[12][11] = 1;
        this.adyacencia[12][20] = 1;

        this.adyacencia[16][8] = 1;
        this.adyacencia[16][24] = 1;

        this.adyacencia[18][10] = 1;
        this.adyacencia[18][19] = 1;
        this.adyacencia[18][26] = 1;

        this.adyacencia[19][11] = 1;
        this.adyacencia[19][18] = 1;
        this.adyacencia[19][20] = 1;
        this.adyacencia[19][27] = 1;

        this.adyacencia[20][12] = 1;
        this.adyacencia[20][19] = 1;
        this.adyacencia[20][21] = 1;
        this.adyacencia[20][28] = 1;

        this.adyacencia[21][20] = 1;
        this.adyacencia[21][22] = 1;
        this.adyacencia[21][29] = 1;

        this.adyacencia[22][21] = 1;
        this.adyacencia[22][23] = 1;
        this.adyacencia[22][30] = 1;

        this.adyacencia[23][22] = 1;
        this.adyacencia[23][31] = 1;

        this.adyacencia[24][16] = 1;
        this.adyacencia[24][25] = 1;
        this.adyacencia[24][32] = 1;

        this.adyacencia[25][24] = 1;
        this.adyacencia[25][26] = 1;
        this.adyacencia[25][33] = 1;

        this.adyacencia[26][18] = 1;
        this.adyacencia[26][25] = 1;
        this.adyacencia[26][27] = 1;

        this.adyacencia[27][19] = 1;
        this.adyacencia[27][26] = 1;
        this.adyacencia[27][28] = 1;
        this.adyacencia[27][35] = 1;

        this.adyacencia[28][20] = 1;
        this.adyacencia[28][27] = 1;
        this.adyacencia[28][29] = 1;

        this.adyacencia[29][21] = 1;
        this.adyacencia[29][28] = 1;
        this.adyacencia[29][30] = 1;

        this.adyacencia[30][22] = 1;
        this.adyacencia[30][29] = 1;
        this.adyacencia[30][31] = 1;

        this.adyacencia[31][23] = 1;
        this.adyacencia[31][30] = 1;
        this.adyacencia[31][39] = 1;

        this.adyacencia[32][24] = 1;
        this.adyacencia[32][33] = 1;
        this.adyacencia[32][40] = 1;

        this.adyacencia[33][25] = 1;
        this.adyacencia[33][32] = 1;

        this.adyacencia[35][27] = 1;
        this.adyacencia[35][43] = 1;

        this.adyacencia[39][31] = 1;
        this.adyacencia[39][47] = 1;

        this.adyacencia[40][32] = 1;
        this.adyacencia[40][48] = 1;

        this.adyacencia[43][35] = 1;
        this.adyacencia[43][51] = 1;

        this.adyacencia[46][47] = 1;
        this.adyacencia[46][54] = 1;

        this.adyacencia[47][39] = 1;
        this.adyacencia[47][46] = 1;
        this.adyacencia[47][55] = 1;

        this.adyacencia[48][40] = 1;
        this.adyacencia[48][49] = 1;
        this.adyacencia[48][56] = 1;

        this.adyacencia[49][48] = 1;
        this.adyacencia[49][50] = 1;
        this.adyacencia[49][57] = 1;

        this.adyacencia[50][49] = 1;
        this.adyacencia[50][51] = 1;

        this.adyacencia[51][43] = 1;
        this.adyacencia[51][50] = 1;
        this.adyacencia[51][52] = 1;
        this.adyacencia[51][59] = 1;

        this.adyacencia[52][51] = 1;
        this.adyacencia[52][53] = 1;
        this.adyacencia[52][60] = 1;

        this.adyacencia[53][52] = 1;
        this.adyacencia[53][54] = 1;
        this.adyacencia[53][61] = 1;

        this.adyacencia[54][46] = 1;
        this.adyacencia[54][53] = 1;
        this.adyacencia[54][55] = 1;
        this.adyacencia[54][62] = 1;

        this.adyacencia[55][47] = 1;
        this.adyacencia[55][54] = 1;
        this.adyacencia[55][63] = 1;

        this.adyacencia[56][48] = 1;
        this.adyacencia[56][57] = 1;

        this.adyacencia[57][49] = 1;
        this.adyacencia[57][56] = 1;

        this.adyacencia[59][51] = 1;
        this.adyacencia[59][60] = 1;

        this.adyacencia[60][52] = 1;
        this.adyacencia[60][59] = 1;
        this.adyacencia[60][61] = 1;

        this.adyacencia[61][53] = 1;
        this.adyacencia[61][60] = 1;
        this.adyacencia[61][62] = 1;

        this.adyacencia[62][54] = 1;
        this.adyacencia[62][61] = 1;
        this.adyacencia[62][63] = 1;

        this.adyacencia[63][55] = 1;
        this.adyacencia[63][62] = 1;
    }

    /**
     * Carga el difraz del Espía en el Mapa. Se cargará en la celda indicada por
     * parámetros
     *
     * @param idCelda id de la celda donde cargar el disfraz
     */
    public void cargarDisfrazEnMapa(int idCelda) {
        int fila = Utilidad.calcularFila(idCelda);
        int columna = Utilidad.calcularColumna(idCelda);
        //Cargamos el disfraz en la celda [fila, columna]
        this.matriz[fila][columna].setDisfraz(true);
    }

    /**
     * @param turno recibe por parametro un entero referente al turno del mapa.
     * Método que establece el turno al mapa.
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }
    // no tengo los Setters ni Getters de la matriz para que no se use en casos que no me interesa.

    /**
     * @return retorna el turno del mapa.
     */
    public int getTurno() {
        return turno;
    }

    /**
     *
     * @return retorna objeto tipo Celda.
     *
     */
    public Celda getCelda() {
        return celda;
    }

    /**
     * @param celda establece una celda
     *
     */
    public void setCelda(Celda celda) {
        this.celda = celda;
    }

    /**
     *
     * @return retorna un entero de los aliados que hay en el punto de encuentro
     */
    public int getNumAliadosPuntoEncuentro() {
        return numAliadosPuntoEncuentro;
    }

    /**
     *
     * @param numAliadosPuntoEncuentro establece el numero de aliados que hay en
     * el punto de encuentro
     *
     */
    public void setNumAliadosPuntoEncuentro(int numAliadosPuntoEncuentro) {
        this.numAliadosPuntoEncuentro = numAliadosPuntoEncuentro;
    }

    /**
     *
     * @return un entero de los aliados que hay en esa simulación
     */
    public int getAliadosMapa() {
        return aliadosMapa;
    }

    /**
     * incrementa los aliados que llegan al punto de encuentro
     */
    public void aliadoEnPunto() {
        numAliadosPuntoEncuentro++;
    }

    /**
     * incrementa los aliados que hay en el mapa
     */
    public void incrementarAliadosEnMapa() {
        aliadosMapa++;
    }

    /**
     *
     * @param militar Objeto tipo Militar, Añade un militar a la lista de
     * militares
     */
    public void insertarMilitarEnLista(Militar militar) {

        lMilitares.add(militar);

    }

    /**
     *
     * @return entero de el tamaño de la lista de militares
     */
    public int tamanoLista() {

        return lMilitares.size();
    }

    /**
     *
     * @param posicion entero sobre la posicion del militar
     * @return un objeto tipo Militar
     */
    public Militar getMilitar(int posicion) {

        return lMilitares.get(posicion);
    }

    /**
     *
     * @return entero referente al numero de columnas
     */
    public int getColumnas() {
        return columnas;
    }

    /**
     *
     * @return entero referente al numero de filas
     */
    public int getFilas() {
        return filas;
    }

    /**
     *
     * @param obra objeto tipo Obra
     * @param idCelda id de la celda donde se quiere insertar la obra
     */
    public void cargarObraEnCelda(Obra obra, int idCelda) {
        int fila = Utilidad.calcularFila(idCelda);
        int columna = Utilidad.calcularColumna(idCelda);
        //Cargamos la obra en el conjunto de celda [fila, columna]
        this.matriz[fila][columna].setObra(obra);
    }

    /**
     *
     * @param obra Objeto tipo Obra
     * @param idCelda id de la celda de donde se quiere borrar la obra.
     */
    public void borrarObraEnCelda(Obra obra, int idCelda) {

        int fila = Utilidad.calcularFila(idCelda);
        int columna = Utilidad.calcularColumna(idCelda);

        this.matriz[fila][columna].borrarObra(obra);
    }

    /**
     *
     * @param fila número tipo entero referente a la fila
     * @param columna número tipo entero referente a la columna
     * @return retorna la Celda de esas cordenadas en la matriz
     */
    public Celda getCelda(int fila, int columna) { // Utilizo la sobre carga de metodos.
        return matriz[fila][columna];
    }

    /**
     * Método para borrar un militar de la celda indicada
     *
     * @param nMilitar String del nombre del militar
     * @param idCelda numero entero con el identificador de la celda.
     */
    public void borrarMilitar(String nMilitar, int idCelda) {
        int fil = Utilidad.calcularFila(idCelda);
        int col = Utilidad.calcularColumna(idCelda);

        matriz[fil][col].borrarMilitar(nMilitar);
    }

    /**
     *
     * @param nMilitar Objeto tipo Militar Método que inserta un objeto tipo
     * militar en las cordenadas de la matriz indicadas.
     */
    public void insertarMilitar(Militar nMilitar) {
        int idCelda, fil, col;

        idCelda = nMilitar.getCeldaActual();

        fil = Utilidad.calcularFila(idCelda);
        col = Utilidad.calcularColumna(idCelda);

        matriz[fil][col].insertarMilitar(nMilitar);
    }

    /**
     * Método que compara con la matriz de adyacencia para saber si el camino es
     * accesible o no
     *
     * @param celdaOrigen id de la celda en la que se encuentra tipo entero
     * @param celdaDestino celda a la que quiere acceder tipo entero
     * @return retorna true si se puede acceder y false en caso contrario.
     */
    public boolean hayCamino(int celdaOrigen, int celdaDestino) {
        boolean hayCamino = false;

        try {

            if (this.adyacencia[celdaOrigen][celdaDestino] == 1) {
                hayCamino = true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        return hayCamino;

    }

    /**
     *
     * @param idCelda
     * @return
     */
    public Celda getCelda(int idCelda) { //Sobre carga de metodos

        int fila = Utilidad.calcularFila(idCelda);
        int col = Utilidad.calcularColumna(idCelda);

        return matriz[fila][col];

    }

    /**
     * Metodo necesario para realizar el registro .log
     *
     * @return lista de militares
     */
    public ArrayList<Militar> getlMilitares() {
        return lMilitares;
    }

    /* MÉTODOS PARA PINTAR MAPA*/
    /**
     * Retorna true si la celda NO es accesible (hay un edificio, un árbol,
     * ...). El método es privado porque sólo se utiliza en la clase Mapa
     *
     * @param idCelda entero referente a la celda
     * @return un booleano
     */
    private boolean celdaNoAccesible(int idCelda) {
        boolean noAccesible = true;
        int j = 0;
        while ((j < (filas * filas)) && (noAccesible)) {
            if (this.adyacencia[idCelda][j] == 1) {
                noAccesible = false;
            }
            j++;
        }
        return noAccesible;
    }

    /**
     * Pinta la primera línea del mapa. El método es privado porque sólo se
     * utiliza en la clase Mapa
     *
     * @param i número entero
     * @param pw flujo de escritura
     */
    private void pintarLineaSuperior(int i, PrintWriter pw) {
        int celdaOrigen, celdaDestino, j;
        if (i == 0) {
            //Línea superior. Empezamos dejando un hueco para dejas un primer hueco para pintar la línea izquierda
            pw.print(" ");
            for (j = 0; j < this.matriz[0].length; j++) {
                pw.print("------- ");
            }
        } else {
            pw.print("|");
            for (j = 0; j < this.matriz[i].length - 1; j++) {
                //Calculamos celdaOrigen y celdaDestino
                celdaOrigen = (filas * i) + j;
                celdaDestino = celdaOrigen - filas;
                if (hayCamino(celdaOrigen, celdaDestino)) {
                    pw.print("        ");
                } else {
                    pw.print("------- ");
                }
            }
            //Calculamos celdaOrigen y celdaDestino
            celdaOrigen = (filas * i) + j;
            celdaDestino = celdaOrigen - filas;
            if (hayCamino(celdaOrigen, celdaDestino)) {
                pw.print("       |");
            } else {
                pw.print("-------|");
            }
        }
        //Ponemos el cursor en la siguiente línea
        pw.print("\n");
    }

    /**
     * Pinta línea inferior del mapa. El método es privado porque sólo se
     * utiliza en la clase Mapa
     *
     * @param pw flujo de escritura
     */
    private void pintarLineaInferior(PrintWriter pw) {
        //Línea inferior. Empezamos dejando un hueco para dejas un primer hueco para pintar la línea izquierda
        pw.print(" ");
        for (int j = 0; j < this.matriz[0].length; j++) {
            pw.print("------- ");
        }
        //Ponemos el cursor en la siguiente línea
        pw.print("\n");
    }

    /**
     * Pinta la columna 0 de la matriz. El método es privado porque sólo se
     * utiliza en la clase Mapa
     *
     * @param celdaOrigen entero de la celda origen
     * @param celdaDestino entero de la celda destino
     * @param i número entero
     * @param j número entero
     * @param x número entero
     * @param pw número entero
     */
    private void pintarColumnaCero(int celdaOrigen, int celdaDestino, int i, int j, int x, PrintWriter pw) {
        if (celdaNoAccesible(celdaOrigen)) {
            pw.print("|///////|");
        } else {
            if (hayCamino(celdaOrigen, celdaDestino)) {
                //Si estamos en la primera o última de las 3 líneas
                if (x % 2 == 0) {
                    pw.print("|        ");
                } else {  //Si estamos en la línea central
                    pw.print("|   " + this.matriz[i][j].toString() + "    ");
                }
            } else {
                //Si estamos en la primera o última de las 3 líneas
                if (x % 2 == 0) {
                    pw.print("|       |");
                } else {  //Si estamos en la línea central
                    pw.print("|   " + this.matriz[i][j].toString() + "   |");
                }
            }
        }

    }

    /**
     * * Pinta las columnas de la matriz (excepto la columna 0). El método es
     * privado porque sólo se utiliza en la clase Mapa
     *
     * @param celdaOrigen
     * @param celdaDestino
     * @param i numero entero
     * @param j numero entero
     * @param x numero entero
     * @param pw flujo de escritura
     */
    private void pintarRestoColumnas(int celdaOrigen, int celdaDestino, int i, int j, int x, PrintWriter pw) {
        if (celdaNoAccesible(celdaOrigen)) {
            pw.print("///////|");
        } else {
            if (hayCamino(celdaOrigen, celdaDestino)) {
                //Si estamos en la primera o última de las 3 líneas
                if (x % 2 == 0) {
                    pw.print("        ");
                } else {  //Si estamos en la línea central
                    pw.print("   " + this.matriz[i][j].toString() + "    ");
                }
            } else {
                //Si estamos en la primera o última de las 3 líneas
                if (x % 2 == 0) {
                    pw.print("       |");
                } else {  //Si estamos en la línea central
                    pw.print("   " + this.matriz[i][j].toString() + "   |");
                }
            }
        }
    }

    /**
     * Pinta la fila "i" de la matriz. El método es privado porque sólo se
     * utiliza en la clase Mapa
     *
     * @param pw flujo de escritura
     * @param i numero entero
     */
    private void pintarFila(int i, PrintWriter pw) {
        int j, x;
        int celdaOrigen, celdaDestino;
        for (x = 0; x < 3; x++) {  //Cada celda la dividimos en 3 líneas
            for (j = 0; j < this.matriz[i].length; j++) {  //Columnas
                //Calculamos celdaOrigen y celdaDestino
                celdaOrigen = (filas * i) + j;
                celdaDestino = celdaOrigen + 1;

                //Si estamos en la primera columna 
                if (j == 0) {
                    pintarColumnaCero(celdaOrigen, celdaDestino, i, j, x, pw);
                } else {  //Si estamos en cualquier columna del medio
                    pintarRestoColumnas(celdaOrigen, celdaDestino, i, j, x, pw);
                }
            }
            //Ponemos el cursor en la siguiente línea
            pw.print("\n");
        }
    }

    /**
     * Muestra el mapa al usuario
     *
     * @param pw flujo de escritura
     */
    public void pintarMatriz(PrintWriter pw) {
        int i;
        for (i = 0; i < this.matriz.length; i++) {  //Filas
            pintarLineaSuperior(i, pw);
            pintarFila(i, pw);

        }
        pintarLineaInferior(pw);
    }

}
