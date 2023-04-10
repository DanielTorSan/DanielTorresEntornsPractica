package minesdts.controlador;
import minesdts.model.Model;
import java.util.Scanner;

public class Controlador {

    static Scanner scan = new Scanner(System.in);

    /**
     * Aquesta funció serveix per jugar al joc
     */
    public static void jugar() {
        Model.InicialitzarJoc(8, 8, 10);
        boolean Acabar = false;
        int fila = 0;
        int columna;
        int opcio;
        char filalletra;
        do {
            System.out.println("Tria una opció:");
            System.out.println("1. Trepitjar");
            System.out.println("2. Posar Bandera");
            System.out.println("3. Acabar el joc");
            opcio = scan.nextInt();
            scan.nextLine();

            switch (opcio) {

                case 1:
                    System.out.println("Indica quina pocició vols trepitjar:");
                  filalletra = scan.next().trim().charAt(0);
                  columna = scan.nextInt();
                  fila = filalletra - 'A';
                  Model.Trepitjar(fila,columna-1);
                  break;

                case 2:
                    System.out.println("Indica en quina pocició vols posar una bandera:");
                    filalletra = scan.next().trim().charAt(0);
                    columna = scan.nextInt();
                    fila = filalletra - 'A';
                    Model.PosarBandera(fila,columna-1);
                    break;

                case 3:
                    opcio = 0;
                    System.out.println("Has sortit del joc");
                    break;

                default:
                    System.out.println("Ha de ser un valor entre 1 i 3");
            }
        }while (opcio != 0 || Acabar == true );
    }
}