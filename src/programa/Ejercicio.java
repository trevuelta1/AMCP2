/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programa;

import automata.*;
import java.util.Scanner;

/**
 * Clase Ejercicio con el método main.
 * @author Toni Revuelta
 **/
public class Ejercicio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opt = 0;
        Scanner scan = new Scanner(System.in);
        while (opt != 3) {
            System.out.println("Indique si desea cargar un AFD o un AFND.");
            System.out.println("");
            System.out.println("1. Cargar AFD.");
            System.out.println("2. Cargar AFND.");
            System.out.println("3. Salir.");
            System.out.println("");
            System.out.println("Elige una opción: ");
            opt = scan.nextInt();
            switch (opt) {
                case 1: {
                    AFD afd = AFD.pedir();
                    if (afd != null) {
                        int hacer = 0;
                        while (hacer != 4) {
                            System.out.println("Indica qué deseas hacer a continuación.");
                            System.out.println("");
                            System.out.println("1. Ver autómata.");
                            System.out.println("2. Reconocer cadena.");
                            System.out.println("3. Reconocer cadena con desarrollo paso a paso.");
                            System.out.println("4. Salir.");
                            System.out.println("");
                            System.out.println("Elige una opción: ");
                            hacer = scan.nextInt();
                            switch(hacer){
                                case 1:{
                                    afd.ver();
                                    break;
                                }
                                case 2:{
                                    System.out.println("Escribe la cadena que deseas que el autómata reconozca: ");
                                    String cadena = scan.next();
                                    afd.reconocer(cadena);
                                    break;
                                }
                                case 3:{
                                    System.out.println("Escribe la cadena que deseas que el autómata reconozca: ");
                                    String cadena = scan.next();
                                    afd.reconocerPasoAPaso(cadena);
                                    break;
                                }
                                default:{
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }
                case 2: {
                    AFND afnd = AFND.pedir();
                    if (afnd != null) {
                        int hacer = 0;
                        while (hacer != 4) {
                            System.out.println("Indica qué deseas hacer a continuación.");
                            System.out.println("");
                            System.out.println("1. Ver autómata.");
                            System.out.println("2. Reconocer cadena.");
                            System.out.println("3. Reconocer cadena con desarrollo paso a paso.");
                            System.out.println("4. Salir.");
                            System.out.println("");
                            System.out.println("Elige una opción: ");
                            hacer = scan.nextInt();
                            switch(hacer){
                                case 1:{
                                    afnd.ver();
                                    break;
                                }
                                case 2:{
                                    System.out.println("Escribe la cadena que deseas que el autómata reconozca: ");
                                    String cadena = scan.next();
                                    afnd.reconocer(cadena);
                                    break;
                                }
                                case 3:{
                                    System.out.println("Escribe la cadena que deseas que el autómata reconozca: ");
                                    String cadena = scan.next();
                                    afnd.reconocerPasoAPaso(cadena);
                                    break;
                                }
                                default:{
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }

}
