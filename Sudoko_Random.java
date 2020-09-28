import java.io.*;
import java.util.ArrayList; // Pour utiliser les  ArrayList<Integer>.
import java.util.Random;// Pour utiliser la fonction nextInt.
public class SudokoDev {/* ici le mot clé public  sert à définir le droit d’accès des
    autres classes à la classe SudokoDev */

        public static void main(String[] args) { /* ici a)le mot clé public est obligatoire pour que le
                                            programme puisse s'exécuter
                                            b) le mot clé void est utilisé puisque le programme ne retourne rien
                                            c) le mot clé static pour indiquer que la méthode main de la classe SommeMillion
                                            n'est pas liée à une instance particulière de la classe */
            GenererSudoko();// appel de la méthode GenererSudoko

        }
    private static void GenererSudoko(){ /* a)private: accès réservé à la classe RandomAlphabetClean
                                                                            b)  peut être appelée sans la
création d’une instance de la classe dans laquelle elle est définie.
                                                                        c)void: cette fonction ne retourne rien  */

            Random r = new Random();// nouvelle instance de la classe Random.
            int[][] tableau = new int[9][9];// tableau qui recoit les chiffres générs pour le Sudoko
            int[][] tabtr = new int[9][9];// tableau qui recoit les chiffres générs pour le Sudoko
            all:// Création d'un bloc pour sortir de la boucle while
            while (true) {// boucle infinie
                try {// permet de gérer les exceptions qui seront générées.

                    for (int i = 0; i < 9; i++) {// boucle pour changer de ligne

                        for (int j = 0; j < 9; j++) { // boucle pour remplir les colonnes

                            ArrayList<Integer> myArrs = new ArrayList<Integer>();
                            ArrayList<Integer> myTemp = new ArrayList<Integer>();
                            ArrayList<Integer> myInts = new ArrayList<Integer>();
                            myInts.add(1);
                            myInts.add(2);
                            myInts.add(3);
                            myInts.add(4);
                            myInts.add(5);
                            myInts.add(6);
                            myInts.add(7);
                            myInts.add(8);
                            myInts.add(9);
                            myArrs.add(0);// première affectation: valeur qui sera enlevée par la suite
                            myTemp.add(0);// première affectation : valeur qui sera enlevée par la suite

                            int n = 0;
                            while (n < i) {// boucle pour collecter les chiffres déja générés sur une colonne
                                for (int v = 0; v < myArrs.size(); v++)
                                    if (myArrs.get(v) != tableau[n][j]) {
                                        myArrs.add(tableau[n][j]);

                                    }

                                n++;
                            }
                            int m = 0;
                            while (m < j) { // boucle pour collecter les chiffres déja générés sur une ligne
                                for (int w = 0; w < myTemp.size(); w++)
                                    if (myTemp.get(w) != tableau[i][m]) {
                                        myTemp.add(tableau[i][m]);

                                    }
                                m++;
                            }

                            myArrs.removeIf(b -> (b < 1));// On enlève la valeur de 0 à la première affectation
                            myTemp.removeIf(b -> (b < 1));// On enlève la valeur de 0 à la première affectation
                            myArrs.addAll(myTemp);// tous les chiffres distincts sur une ligne et une colonne
                            myInts.removeAll(myArrs);//On enlève ces chiffres avant la génération des nouveaux chiffres
                            tableau[i][j] = myInts.get(r.nextInt(myInts.size()));// remplissage de la case en question


                            if (i == 8 && j == 8) {// Condition de sortie de la boucle infinie

                                for (int l = 0; l < 9; l++) {
                                    for (int c = 0; c < 9; c++) {
                                        System.out.print(tableau[l][c]);/* On affiche ligne par ligne  le tableau Sudoko à l'écran*/
                                        System.out.print(' ');
                                        System.out.print(' ');

                                    }
                                    System.out.println();
                                }
                                for (int l = 0; l< 9; l++) {
                                    for (int c = 0; c < 9; c++) {
                                        tabtr[l][c] = tableau[c][l];

                                    }
                                }
                                System.out.println();
                                PrintWriter sortie = null;
                                File fichier = new File("partie3.txt");
                                sortie = new PrintWriter(new BufferedWriter(new FileWriter(fichier)));
                                for (int l = 0; l < 9; l++) {
                                    for (int c = 0; c < 9; c++) {
                                        System.out.print(tabtr[l][c]);/* On affiche ligne par ligne  le tableau Sudoko à l'écran*/
                                        System.out.print(' ');
                                        System.out.print(' ');
                                        //sortie.println((char)l(char)c+(char)tabtr[l][c]);
                                        sortie.println(""+l+""+c+""+tabtr[l][c]);
                                    }

                                    System.out.println();

                                }
                                sortie.close();
                                break all;/* On arrête le programme*/

                            }
                        }

                    }


                } catch (Exception e) {/* en lien avec try, permettent de gerer les exceptions*/
                    //*System.out.print("E");

                }


            }
        }
    }













