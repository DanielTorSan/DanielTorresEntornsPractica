package minesdts.model;
import minesdts.controlador.Controlador;
import minesdts.vista.Vista;

import java.util.Scanner;

public class Model {

    static Scanner scan = new Scanner(System.in);


    static int files, columnes, bombes;

    static boolean JocFinalitzat;

    static char[][] campMines;

    static char[][] campVisible;

    /**
     * Aquesta funció serveix per iniciar el joc
     * @param numfiles
     * @param numcolumnes
     * @param numbombes
     */
    public static void InicialitzarJoc(int numfiles, int numcolumnes, int numbombes) {
        files = numfiles;
        columnes = numcolumnes;
        bombes = numbombes;
        char[][] campMinesJ = new char[files][columnes];
        char[][] campVisibleJ = new char[files][columnes];

        InicialitzarCampMines(campMinesJ, ' ');

        InicialitzarCampMines(campVisibleJ, '·');

        PosarBombes(campMinesJ);

        ComptarBombes(campMinesJ);

        Vista.MostrarCampMines(campVisibleJ);

        campMines = campMinesJ;
        campVisible = campVisibleJ;
    }

    /**
     * Aquesta funcio serveix per donar els valors a les files i les columnes de l'array.
     *
     * @param A
     * @param lletra
     */
    static void InicialitzarCampMines(char[][] A, char lletra) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                A[i][j] = lletra;
            }
        }
    }

    /**
     * Afageix bombes al camp de mines
     */
    static void PosarBombes(char[][] A) {

        //Bombes de cada fila
        int fila;
        //Bombes de cada columna
        int columna;

        for (int i = 0; i < bombes; i++) {

            fila = (int) (Math.random() * (files - 2) + 1);

            columna = (int) (Math.random() * (columnes - 2) + 1);

            A[fila][columna] = 'B';
        }
    }

    /**
     * Aquesta funció serveix per comptar les bombes en el cam de mines i indicar el numero de cada espai
     * per tal de que l'usuari pugui veure les posibles mines del boltant.
     * @param A
     */
    static void ComptarBombes(char[][] A) {

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++ ){
                int NumBombes = 0;
                if (A[i][j] != 'B'){
                    if (VerificarFilaColumna(i-1 ,j-1) && (A[i-1][j-1] == 'B')) NumBombes++;
                    if (VerificarFilaColumna(i-1 ,j) && (A[i-1][j] == 'B')) NumBombes++;
                    if (VerificarFilaColumna(i-1 ,j+1) && (A[i-1][j+1] == 'B')) NumBombes++;
                    if (VerificarFilaColumna(i ,j-1) && (A[i][j-1] == 'B')) NumBombes++;
                    if (VerificarFilaColumna(i ,j+1) && (A[i][j+1] == 'B')) NumBombes++;
                    if (VerificarFilaColumna(i+1 ,j-1) && (A[i+1][j-1] == 'B')) NumBombes++;
                    if (VerificarFilaColumna(i+1 ,j) && (A[i+1][j] == 'B')) NumBombes++;
                    if (VerificarFilaColumna(i+1 ,j+1) && (A[i+1][j+1] == 'B')) NumBombes++;
                    if (NumBombes != 0)A[i][j]=(char)(NumBombes+'0');
                }
            }
        }
    }

    /**
     * Aquesta funció serveix per indicar si la fila o la columna es troba dins del camp de mines per tal de
     * contabilitzar les bombes del voltant.
     * @param fila
     * @param columna
     * @return bollean (true)(false)
     */
    static boolean VerificarFilaColumna(int fila, int columna){

        boolean Verificacio = true;
        if ((fila < 0 || fila > files-1 ) || (columna < 0 || columna > columnes-1 ) ) {
            Verificacio = false;
        } return Verificacio;
    }

    /**
     * Aquesta funcio serveix per trepitjar el espai seleccionat per l'usuari.
     * @param fila
     * @param columna
     */
   public static void Trepitjar(int fila , int columna) {

       if (VerificarFilaColumna(fila,columna) == false) {
           Vista.MostrarCampMines(campVisible);
           System.out.println("Introdueix un camp valid A-H 1-8");
       }
       else if (campVisible[fila][columna] != '·') {
           System.out.println("Aquesta casella no es pot trepitjar");
           Vista.MostrarCampMines(campVisible);
       }
       else if (campMines[fila][columna] == 'B') {
           MostrarSolucio();
           System.out.println("Has Perdut");

       }
       else if (campMines[fila][columna] != 'B') {
           TrepitjarRecursivament(fila,columna);
           Vista.MostrarCampMines(campVisible);

           if (ComprobarGuanyat() == true) {
               Vista.MostrarCampMines(campVisible);
               System.out.println("Has Guanyat");
           }
       }
   }

    /**
     * Aquesta funcio serveix per posar o treure banderas en una pocicio del cam de mines.
     * @param fila
     * @param columna
     */
    public static void PosarBandera(int fila, int columna) {

        if (VerificarFilaColumna(fila,columna) == false) {
            System.out.println("Introdueix un camp valid A-H 1-8");
        }
        else if (campVisible[fila][columna] != 'B') {
            campVisible[fila][columna] = 'B';
            Vista.MostrarCampMines(campVisible);
        }
        else if (campVisible[fila][columna] == 'B') {
            campVisible[fila][columna] = '·';
            Vista.MostrarCampMines(campVisible);
        }
        if (ComprobarGuanyat() == true) {
            Vista.MostrarCampMines(campVisible);
            System.out.println("Has Guanyat");
        }
    }

    /**
     * Aquesta funcio serveix per comprobar si el jugador ha guanyat el joc.
     * @return true o false depenent de si els arrays son iguals
     */
    static boolean ComprobarGuanyat(){

        boolean Victoria = false;

        if(campMines == campVisible){
        Victoria = true;
        }
        return Victoria;

    }

    /**
     * Aquesta funcio mastra per pantalla el camp de mines amb la solució i el que tenia l'usuari.
     */
    static void MostrarSolucio(){
        Vista.MostrarCampMines(campVisible);
        System.out.println();
        Vista.MostrarCampMines(campMines);
    }

    /**
     * Aquesta funcio destapa totes les caselles buides i els numeros del voltant.
     * @param fila
     * @param columna
     */
    static void TrepitjarRecursivament(int fila , int columna){
        if (VerificarFilaColumna(fila,columna)){
            if (campMines[fila][columna] == ' ') {

                if (campVisible[fila][columna] != campMines[fila][columna]) {
                    campVisible[fila][columna] = campMines[fila][columna];

                    TrepitjarRecursivament(fila - 1, columna - 1);
                    TrepitjarRecursivament(fila - 1, columna);
                    TrepitjarRecursivament(fila - 1, columna + 1);
                    TrepitjarRecursivament(fila, columna - 1);
                    TrepitjarRecursivament(fila, columna + 1);
                    TrepitjarRecursivament(fila + 1, columna - 1);
                    TrepitjarRecursivament(fila + 1, columna);
                    TrepitjarRecursivament(fila + 1, columna + 1);

                }else if (campVisible[fila][columna] == campMines[fila][columna]) ;
            }
               else if (campMines[fila][columna] > 48 || campMines[fila][columna] < 57)
               {campVisible[fila][columna] = campMines[fila][columna];}
            }
    }
}