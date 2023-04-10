package minesdts.vista;

import minesdts.model.Model;

public class Vista {

    /**
     * Aquesta funcio mostra el camp de mines visible a l'usuari.
     * @param : array bidimensional de caracters anomenat CampMinas.
     */
    public static void MostrarCampMines(char[][] CampMinas){

        System.out.print(" ");
        for (int i = 0; i < 9; i++) {
            if (i > 0){
            System.out.print(" " + i + " ");}
        }
        System.out.println();

        char lletra = 'A';
        for (int i = 0; i < 8; i++) {
            System.out.print(lletra);
            for (int j = 0; j < CampMinas.length; j++) {
                System.out.print(" " + CampMinas[i][j] + " ");
            }
            lletra++;
            System.out.println();
        }
    }

    /**
     * Aquesta funcio serveix per mostrar missatges
     * @param Missatge
     */
    static void MostrarMissatge(String Missatge){
    }
}

