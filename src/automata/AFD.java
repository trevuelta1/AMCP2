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
 * Clase AFD, que almacena los datos necesarios de los autómatas finitos deterministas.
 * @author Toni Revuelta
 **/
public class AFD implements Proceso{

    private final ArrayList<String> estados;
    private final ArrayList<String> estadosFinales;
    private final String estadoInicial;
    private ArrayList<TransicionAFD> transiciones;
    /**
    * Constructor de la clase AFD.
    * @param estados Lista de estados del autómata
    * @param estadoInicial Estado inicial
    * @param estadosFinales Lista de estados finales
    * @param transiciones Lista de transiciones
    **/
    public AFD(ArrayList<String> estados, String estadoInicial, ArrayList<String> estadosFinales, ArrayList<TransicionAFD> transiciones) {
        this.estados = estados;
        this.estadoInicial = estadoInicial;
        this.estadosFinales = estadosFinales;
        this.transiciones = transiciones;
    }
    /**
    * Devuelve el estado final de la transición que utiliza el estado inicial pasado por parámetro, consumiendo el símbolo también pasado por parámetro.
    * @param eini Estado inicial de la transición
    * @param sim Símbolo a consumir
    * @return Estado final de la transición
    **/
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
    /**
     * Comprueba si el estado pasado por parámetro es un estado final.
     * @param estado Estado a comprobar
     * @return true si es final, false si no lo es
     **/
    @Override
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
    /**
     * Agrega una nueva transición al autómata.
     * @param eini Estado inicial de la transición
     * @param sim Símbolo a consumir
     * @param efin Estado final de la transición
     **/
    public void agregarTransicion(String eini, char sim, String efin) {
        TransicionAFD transicion = new TransicionAFD(eini, sim, efin);
        this.transiciones.add(transicion);
    }
    /**
     * Comprueba la cadena de caracteres pasada por parámetro, que podrá ser aceptada si, tras consumir todos los símbolos, se llega a un estado final; rechazada si, tras consumir todos los símbolos, se llega a un estado que no es final; o no válida si no se pueden llegar a consumir todos los símbolos debido a cualquier motivo.
     * @param cadena Cadena a comprobar
     * @return true si la cadena es aceptada, false si es rechazada o no válida
     **/
    @Override
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
            i++;
        }
        if (cadenaErronea == true) {
            System.out.println("CADENA NO VÁLIDA.");
            return false;
        } else {
            boolean fin = esFinal(estadoActual);
            if (fin == true) {
                System.out.println("CADENA ACEPTADA.");
            } else {
                System.out.println("CADENA RECHAZADA.");
            }
            return fin;
        }
    }
    /**
     * Comprueba paso a paso la cadena de caracteres pasada por parámetro, que podrá ser aceptada si, tras consumir todos los símbolos, se llega a un estado final; rechazada si, tras consumir todos los símbolos, se llega a un estado que no es final; o no válida si no se pueden llegar a consumir todos los símbolos debido a cualquier motivo.
     * @param cadena Cadena a comprobar
     * @return true si la cadena es aceptada, false si es rechazada o no válida
     **/
    public boolean reconocerPasoAPaso(String cadena) {
        String estadoActual = this.estadoInicial;
        Scanner scan = new Scanner(System.in);
        char[] simbolos = cadena.toCharArray();
        boolean cadenaErronea = false;
        int i = 0;
        while (i < simbolos.length && estadoActual.equals("No encontrada") == false) {
            System.out.println("");
            System.out.println("Transición " + (i + 1) + ":");
            System.out.println("----------------------------");
            System.out.println("Estado inicial: " + estadoActual);
            estadoActual = transicion(estadoActual, simbolos[i]);
            if (estadoActual.equals("No encontrada") == true) {
                cadenaErronea = true;
            }
            if (cadenaErronea == false) {
                System.out.println("Transición a estado " + estadoActual + " consumiendo símbolo '" + simbolos[i] + "'.");
                scan.nextLine();
            }
            i++;
        }
        if (cadenaErronea == true) {
            System.out.println("CADENA NO VÁLIDA.");
            return false;
        } else {
            boolean fin = esFinal(estadoActual);
            if (fin == true) {
                System.out.println("CADENA ACEPTADA.");
            } else {
                System.out.println("CADENA RECHAZADA.");
            }
            return fin;
        }
    }
    /**
     * Lee datos desde fichero y devuelve un AFD con estos datos.
     * @return AFD
     * @throws FileNotFoundException
     * @throws IOException 
     **/
    public static AFD leeFichero() throws FileNotFoundException, IOException {
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
        if (tipo[0].trim().equals("TIPO") == false || tipo[1].trim().equals("AFD") == false) {
            solucion = null;
        } else {
            line = br.readLine();
            ArrayList<String> estados = new ArrayList();
            String[] est = line.split(":");
            est = est[1].trim().split(" ");
            for (int i = 0; i < est.length; i++) {
                estados.add(est[i].trim());
            }
            line = br.readLine();
            String eini = line.split(":")[1].trim();
            line = br.readLine();
            ArrayList<String> efinales = new ArrayList();
            String[] estfin = line.split(":");
            estfin = estfin[1].trim().split(" ");
            for (int i = 0; i < estfin.length; i++) {
                efinales.add(estfin[i].trim());
            }
            br.readLine();
            solucion = new AFD(estados, eini, efinales, new ArrayList<TransicionAFD>());
            boolean b = false;
            while (b == false) {
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
    /**
     * Pide al usuario datos por teclado y genera un AFD con esos datos.
     * @return AFD
     **/
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
                    System.out.println("Indica el nombre de cada uno de los estados del autómata separados por una coma (Ej: q1,q2,q3): ");
                    String nombres = scan.next();
                    ArrayList<String> estados = new ArrayList();
                    ArrayList<String> añadidos = new ArrayList();
                    String[] arrayestados = nombres.split(",");
                    for (String s : arrayestados) {
                        if (añadidos.isEmpty() == true) {
                            estados.add(s.trim());
                            añadidos.add(s.trim());
                        } else {
                            boolean b = false;
                            int i = 0;
                            while (i < añadidos.size() && b == false) {
                                if (añadidos.get(i).equals(s.trim()) == true) {
                                    b = true;
                                } else {
                                    i++;
                                }
                            }
                            if (b == false) {
                                estados.add(s.trim());
                                añadidos.add(s.trim());
                            }
                        }
                    }
                    boolean existe = false;
                    String eini = "";
                    while (existe == false) {
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
                    System.out.println("Indica el nombre de cada uno de los estados finales separados por una coma (Ej: q1,q2,q3): ");
                    String nfinales = scan.next();
                    String[] nomfinales = nfinales.split(",");
                    for (String s : nomfinales) {
                        int j = 0;
                        boolean c = false;
                        while (c == false && j < estados.size()) {
                            if (estados.get(j).equals(s.trim()) == true) {
                                c = true;
                            } else {
                                j++;
                            }
                        }
                        if (añadidosf.isEmpty() == true) {
                            if (c == true) {
                                añadidosf.add(s.trim());
                                estadosf.add(s.trim());
                            }
                        } else {
                            boolean b = false;
                            int i = 0;
                            while (i < añadidosf.size() && b == false) {
                                if (añadidosf.get(i).equals(s.trim()) == true) {
                                    b = true;
                                } else {
                                    i++;
                                }
                            }
                            if (b == false && c == true) {
                                estadosf.add(s.trim());
                                añadidosf.add(s.trim());
                            }
                        }
                    }
                    afd = new AFD(estados, eini, estadosf, new ArrayList<TransicionAFD>());
                    System.out.println("");
                    System.out.println("Añade las transiciones que desees con el siguiente formato: estado1,símbolo,estado2.");
                    System.out.println("Ten en consideración que el símbolo debe ser un único carácter (Ej: q1,p,q2).");
                    System.out.println("Cuando no desees introducir más transiciones, escribe la palabra SALIR y pulsa Enter.");
                    String cadenatransicion = "";
                    while (cadenatransicion.equals("SALIR") == false && cadenatransicion.equals("Salir") == false && cadenatransicion.equals("salir") == false) {
                        System.out.println("Escribe una transición: ");
                        cadenatransicion = scan.next();
                        if (cadenatransicion.equals("SALIR") == false && cadenatransicion.equals("Salir") == false && cadenatransicion.equals("salir") == false) {
                            String[] elementos = cadenatransicion.split(",");
                            String[] est = new String[2];
                            est[0] = elementos[0].trim();
                            est[1] = elementos[2].trim();
                            char sim = elementos[1].trim().charAt(0);
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
                                if (c == false) {
                                    correcto = false;
                                }
                            }
                            if (correcto == true) {
                                afd.agregarTransicion(est[0], sim, est[1]);
                                System.out.println("Transicion agregada correctamente.");
                            } else {
                                System.out.println("ERROR: La transicion no se ha agregado, revisa los estados indicados.");
                            }
                        }
                    }
                    System.out.println("");
                    System.out.println("");
                    System.out.println("Fichero leído correctamente, pulsa 3 para salir y poder trabajar con el autómata.");
                    System.out.println("ATENCIÓN: ES NECESARIO SALIR DE ESTE MENÚ PARA PODER TRABAJAR CON EL AUTÓMATA, PULSA 3 PARA CONTINUAR.");
                    System.out.println("");
                    System.out.println("");
                    break;
                }
                case 2: {
                    try {
                        afd = leeFichero();
                        if (afd != null) {
                            System.out.println("");
                            System.out.println("");
                            System.out.println("Fichero leído correctamente, pulsa 3 para salir y poder trabajar con el autómata.");
                            System.out.println("ATENCIÓN: ES NECESARIO SALIR DE ESTE MENÚ PARA PODER TRABAJAR CON EL AUTÓMATA, PULSA 3 PARA CONTINUAR.");
                            System.out.println("");
                            System.out.println("");
                        }
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
    /**
     * Muestra los datos del AFD actual.
     **/
    public void ver() {
        System.out.println(toString());
    }
    /**
     * Devuelve un String con los datos del autómata.
     * @return String con los datos del autómata.
     **/
    @Override
    public String toString(){
        String solucion =  "\nEstados: " + this.estados + "\nEstado inicial: " + this.estadoInicial + "\nEstados finales: " + this.estadosFinales + "\n\nTransiciones:\n";
        for (int i = 0; i < this.transiciones.size(); i++) {
            solucion = solucion + this.transiciones.get(i).toString() + "\n";
        }
        solucion = solucion + "\n";
        return solucion;
    }
}
