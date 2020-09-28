import java.io.*; /* Pour utiliser les  méthodes de lecture et d'écriture de fichier.*/
import java.util.ArrayList; /* Pour utiliser les  ArrayList<String>.*/
public class SudokoTranspoz { /* ici le mot clé public  sert à définir le droit d’accès des
    autres classes à la classe SudokoTranspoz */

    public static void main(String[] args) { /* ici a)le mot clé public est obligatoire pour que le
                                            programme puisse s'exécuter
                                            b) le mot clé void est utilisé puisque le programme ne retourne aucune valeur
                                            c) le mot clé static pour indiquer que la méthode main de la classe SudokoTranspoz
                                            n'est pas liée à une instance particulière de la classe */
        String pathfile="partie1.txt"; // Chemin du fichier à lire
        ArrayList<String> myarray = chargerFichier(pathfile); /* Déclaration d'un ArrayList pour capter le return de
         la fonction chargerFichier*/
        int[][] tab= evaluerGrilleSudoko(myarray); /* Déclaration d'un tableau à deux dimensions  pour capter le return de
         la fonction evaluerGrilleSudoko*/
        afficherTransposeGrille(tab);

    }
    private static ArrayList<String> chargerFichier(String pathname ) { /* a)private: accès réservé à la classe SudokoTranspoz
                                                                            b) static: fonction peut être appelée sans la création d’une instance de
                                                                            la classe dans laquelle elle est définie.
                                                                            c)cette fonction retourne un type  ArrayList<String> */

        ArrayList<String> myArrs = new ArrayList<String>();/* Déclaration d'une Arraylist<String> pour sauvegarder
                                                              chaque chaine de caractère du fichier lu*/

        try { /*avec catch, pour gérer les exceptions qui seront générées. */
            File FichierALire = new File(pathname);/* on construit un objet de type File*/
            FileReader unFichier = new FileReader(FichierALire);/* on construit une instance de l'objet FileReader pour lire
                                                                     le contenu du fichier*/
            BufferedReader leBuffer = new BufferedReader(unFichier); /* on construit un objet de type BufferedReader pour lire
             à haut niveau un  fichier*/
            String uneligne = leBuffer.readLine();
            while (uneligne != null) {/* Boucle pour lire e fichier ligne par ligne*/
                myArrs.add(uneligne);/* Ajout de la ligne lue à l'Arraylist*/
                uneligne = leBuffer.readLine();/* lecture d'une nouvelle ligne*/

            }
            leBuffer.close();/* fermeture du flux*/
            unFichier.close();/* fermeture du fluex*/
        } catch (FileNotFoundException exception) {/* Exception lorsque le fichier n'existe pas*/
            System.out.println(" Fichier introuvable!");
        } catch (IOException exception) { /* Exception lorsqu'il y a un problème pendant la lacture du fichier*/
            System.out.println("Il y a une erreur lors de la lecture: " + exception.getMessage());
        }
        return(myArrs);/* La fonction retourne un ArrayList*/
    }
    private static int[][] evaluerGrilleSudoko(ArrayList<String> myArrs) { /* a)private: accès réservé à la classe SudokoTranspoz
                                                                            b) static: fonction peut être appelée sans la création d’une instance de
                                                                            la classe dans laquelle elle est définie.
                                                                            c)cette fonction retourne un tableau à 2 dimensions
                                                                            contenant des entiers */
        int[][] tableau = new int[9][9];/*tableau qui recoit les chiffres */
        if (myArrs.size() != 81) {
            throw new IllegalArgumentException("La grille fournie n'est pas de taille 9X9");/* Exception si la grille
                                                                                            n'a pas la taille 9X9*/
        }
        for (int b = 0; b < myArrs.size() ; b++)
            for (int t = b+1; t < myArrs.size() ; t++)
        {
           if(myArrs.get(b).equals(myArrs.get(t)))
           {
               throw new IllegalArgumentException("Grille invalide, au moins 2 triplets sont égaux");
                /*Exception si au moins 2 triplets sont identiques*/
           }
        }

        for (String myArr : myArrs) {

            if (myArr.length() != 3 || !Character.isDigit(myArr.charAt(0)) || !Character.isDigit(myArr.charAt(1)) || !Character.isDigit(myArr.charAt(2)) || Character.getNumericValue(myArr.charAt(2)) == 0) {
                throw new IndexOutOfBoundsException("Trop de chiffres pour une case ou un caractère différent d'un entier ou zéro pour une case");
           /*Exception si le nombre de caractère par chaine est différent de 3 ou si les caractères
           ne sont pas des entiers ou si le dernier caractère est zéro*/

            } else {
                tableau[Character.getNumericValue(myArr.charAt(0))][Character.getNumericValue(myArr.charAt(1))] = Character.getNumericValue(myArr.charAt(2));
            }/* ici : Character.getNumericValue pour extraire la valeur numérique de chaque caractère d'un triplet (string)
             le premier caractète du triplet est le numéro de la ligne, le deuxième le numéro de la la colonne et le dernier la valeur */
        }

        for (int i = 0; i < 9; i++) {// boucle pour changer de ligne
            for (int j = 0; j < 9; j++) { // boucle pour changer de colonnes
                for (int n = i + 1; n < 9; n++) {/* boucle pour comparer une valeur aux autres valeurs de la colonne*/

                    if (tableau[i][j] != tableau[n][j]) { /*comparaison d'une valeur aux autres valeurs de la colonne*/
                        continue;
                    } else {
                        throw new IllegalArgumentException("Grille Sudoko invalide,au moins deux chiffres sont égaux sur une même colonne");
                /* Exception: au cas où on a au moins deux chiffres identiques dans une même colonne*/
                    }
                }

                for (int m = j + 1; m < 9; m++) {
                    if (tableau[i][j] != tableau[i][m]) { /*comparaison d'une valeur aux autres valeurs de la ligne*/
                        continue;
                    } else {
                        throw new IllegalArgumentException("Grille Sudoko invalide, au moins deux chiffres sont égaux sur une même ligne");
                        /* Exception: au cas où on a au moins deux chiffres identiques dans une même ligne*/
                    }

                }
            }
        }
        return(tableau);/*la méthode retourne un tableau à deux dimensions*/
    }
    private static void afficherTransposeGrille(int tableau[][]) { /* a)private: accès réservé à la classe SudokoTranspoz
                                                                            b) static: fonction peut être appelée sans la création d’une instance de
                                                                            la classe dans laquelle elle est définie.
                                                                            c)void: cette fonction ne retourne rien*/
        int[][] transpose = new int[9][9];/* transposée de la grille Sudoko constituée d'entiers et de taille 9X9
                                         comme la grille de départ*/
        for (int l = 0; l < 9; l++) {
            for (int c = 0; c < 9; c++) {
                transpose[l][c] = tableau[c][l];/*Constitution de la transposée de la grille Sudoko */
                System.out.print(transpose[l][c]);//On affiche la transposée de  la grille Sudoko à l'écran
                System.out.print(' ');
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    }


