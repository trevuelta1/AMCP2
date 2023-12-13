/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automata;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class AFD {

    private final ArrayList<String> estados;
    private final ArrayList<String> estadosFinales;
    private final String estadoInicial;
    private ArrayList<TransicionAFD> transiciones;

    public AFD(ArrayList<String> estados, String estadoInicial, ArrayList<String> estadosFinales, ArrayList<TransicionAFD> transiciones) {
        this.estados = estados;
        this.estadoInicial = estadoInicial;
        this.estadosFinales = estadosFinales;
        this.transiciones = transiciones;
    }

    public String transicion(String eini, char sim) {
        String solucion = "No encontrada";
        boolean b = false;
        int i = 0;
        while (b == false && i < this.transiciones.size()) {
            if (this.transiciones.get(i).getEstadoInicial().equals(eini) == true && this.transiciones.get(i).getSimbolo() == sim) {
                b = true;
                solucion = this.transiciones.get(i).getEstadoFinal();
            } else {
                i++;
            }
        }
        return solucion;
    }

    public boolean esFinal(String estado) {
        boolean b = false;
        int i = 0;
        while (b == false && i < this.estadosFinales.size()) {
            if (this.estadosFinales.get(i).equals(estado) == true) {
                b = true;
            } else {
                i++;
            }
        }
        return b;
    }

    public void agregarTransicion(String eini, char sim, String efin) {
        TransicionAFD transicion = new TransicionAFD(eini, sim, efin);
        this.transiciones.add(transicion);
    }

    public boolean reconocer(String cadena) {
        String estadoActual = this.estadoInicial;
        char[] simbolos = cadena.toCharArray();
        boolean cadenaErronea = false;
        int i = 0;
        while (i < simbolos.length && estadoActual.equals("No encontrada") == false) {
            estadoActual = transicion(estadoActual, simbolos[i]);
            if (estadoActual.equals("No encontrada") == true) {
                cadenaErronea = true;
            }
        }
        if (cadenaErronea == true) {
            return false;
        } else {
            return esFinal(estadoActual);
        }
    }
    
    public static AFD leeFichero() throws FileNotFoundException, IOException{
        AFD solucion;
        FileReader fr;
        String url;
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe la ruta del fichero que deseas utilizar: ");
        url = sc.next();
        fr = new FileReader(url);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        line = br.readLine();
        String[] tipo = new String[2];
        tipo = line.split(":");
        if(tipo[0].equals("TIPO") == false || tipo[1].equals("AFD") == false){
            solucion = null;
        } else{
            line = br.readLine();
            ArrayList<String> estados = new ArrayList();
            String[] est = line.split(":");
            est = est[1].split(" ");
            for(int i = 0; i < est.length; i++){
                estados.add(est[i].trim());
            }
            line = br.readLine();
            String eini = line.split(":")[1].trim();
            line = br.readLine();
            ArrayList<String> efinales = new ArrayList();
            String[] estfin = line.split(":");
            estfin = estfin[1].split(" ");
            for(int i = 0; i < estfin.length; i++){
                efinales.add(estfin[i].trim());
            }
            br.readLine();
            solucion = new AFD(estados, eini, efinales, new ArrayList<TransicionAFD>());
            boolean b = false;
            while(b == false){
                line = br.readLine();
                if ("FIN".equals(line)) {
                    b = true;
                } else {
                    String[] datos = line.split(" ");
                    String e1 = datos[0].trim();
                    String simbol = datos[1].trim();
                    char sim = simbol.toCharArray()[1];
                    String e2 = datos[2].trim();
                    solucion.agregarTransicion(e1, sim, e2);
                }
            }
        }
        return solucion;
    }

    public static AFD pedir() {
        AFD afd = null;
        int opt = 0;
        Scanner scan = new Scanner(System.in);
        while (opt != 3) {
            System.out.println("Indica si deseas crear un nuevo AFD o leer uno existente desde fichero.");
            System.out.println("");
            System.out.println("1. Crear AFD.");
            System.out.println("2. Leer AFD desde fichero.");
            System.out.println("3. Salir.");
            System.out.println("");
            System.out.println("Escoge una opción: ");
            opt = scan.nextInt();
            switch (opt) {
                case 1: {
                    System.out.println("Indica el nombre de cada uno de los estados del autómata separados por una coma (Ej: q1, q2, q3): ");
                    String nombres = scan.nextLine();
                    ArrayList<String> estados = new ArrayList();
                    ArrayList<String> añadidos = new ArrayList();
                    String[] arrayestados = nombres.split(",");
                    for (String s : arrayestados) {
                        if (añadidos.isEmpty() == true) {
                            estados.add(s);
                            añadidos.add(s);
                        } else {
                            boolean b = false;
                            int i = 0;
                            while (i < añadidos.size() && b == false) {
                                if (añadidos.get(i).equals(s) == true) {
                                    b = true;
                                } else {
                                    i++;
                                }
                            }
                            if (b == false) {
                                estados.add(s);
                                añadidos.add(s);
                            }
                        }
                    }
                    boolean existe = true;
                    String eini = "";
                    while (existe == true) {
                        existe = false;
                        System.out.println("Indica el nombre del estado inicial: ");
                        eini = scan.next();
                        int j = 0;
                        boolean c = false;
                        while (c == false && j < estados.size()) {
                            if (estados.get(j).equals(eini) == true) {
                                c = true;
                                existe = true;
                            } else {
                                j++;
                            }
                        }
                    }
                    ArrayList<String> estadosf = new ArrayList();
                    ArrayList<String> añadidosf = new ArrayList();
                    System.out.println("Indica el nombre de cada uno de los estados finales separados por una coma (Ej: q1, q2, q3): ");
                    String nfinales = scan.nextLine();
                    String[] nomfinales = nfinales.split(",");
                    for (String s : nomfinales) {
                        int j = 0;
                        boolean c = false;
                        while (c == false && j < estados.size()) {
                            if (estados.get(j).equals(s) == true) {
                                c = true;
                            } else {
                                j++;
                            }
                        }
                        if (añadidosf.isEmpty() == true) {
                            if (c == true) {
                                añadidosf.add(s);
                                estadosf.add(s);
                            }
                        } else {
                            boolean b = false;
                            int i = 0;
                            while (i < añadidos.size() && b == false) {
                                if (añadidos.get(i).equals(s) == true) {
                                    b = true;
                                } else {
                                    i++;
                                }
                            }
                            if (b == false && c == true) {
                                estados.add(s);
                                añadidos.add(s);
                            }
                        }
                    }
                    afd = new AFD(estados, eini, estadosf, new ArrayList<TransicionAFD>());
                    System.out.println("Añade las transiciones que desees con el siguiente formato: estado1, símbolo, estado2.");
                    System.out.println("Ten en consideración que el símbolo debe ser un único carácter (Ej: q1, p, q2).");
                    System.out.println("Cuando no desees introducir más transiciones, escribe la palabra SALIR y pulsa Enter.");
                    String cadenatransicion = "";
                    while (cadenatransicion.equals("SALIR") == false && cadenatransicion.equals("Salir") == false && cadenatransicion.equals("salir") == false) {
                        System.out.println("Escribe una transición: ");
                        cadenatransicion = scan.nextLine();
                        String[] elementos = cadenatransicion.split(",");
                        String[] est = new String[2];
                        est[0] = elementos[0];
                        est[1] = elementos[2];
                        char sim = elementos[1].charAt(0);
                        boolean correcto = true;
                        for (String s : est) {
                            int j = 0;
                            boolean c = false;
                            while (c == false && j < estados.size()) {
                                if (estados.get(j).equals(s) == true) {
                                    c = true;
                                } else {
                                    j++;
                                }
                            }
                            if(c == false){
                                correcto = false;
                            }
                        }
                        if(correcto == true){
                            afd.agregarTransicion(est[0], sim, est[1]);
                            System.out.println("Transicion agregada correctamente.");
                        } else{
                            System.out.println("ERROR: La transicion no se ha agregado, revisa los estados indicados.");
                        }
                    }
                    break;
                }
                case 2: {
                    try {
                        afd = leeFichero();
                        break;
                    } catch (IOException ex) {
                        Logger.getLogger(AFD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                default: {
                    break;
                }
            }
        }
        return afd;
    }
}
