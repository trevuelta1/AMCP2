/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automata;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class AFND {

    private final ArrayList<String> estados;
    private final ArrayList<String> macroestadoFinal;
    private final String estadoInicial;
    private final ArrayList<String> macroestadoInicial;
    private ArrayList<TransicionAFND> transiciones;
    private ArrayList<TransicionLambda> transicioneslambda;

    public AFND(ArrayList<String> estados, String estadoInicial, ArrayList<String> macroestadoFinal, ArrayList<TransicionAFND> transiciones, ArrayList<TransicionLambda> transicionesLambda) {
        this.estados = estados;
        this.estadoInicial = estadoInicial;
        this.macroestadoFinal = macroestadoFinal;
        this.transiciones = transiciones;
        this.transicioneslambda = transicionesLambda;
        this.macroestadoInicial = new ArrayList();
        this.macroestadoInicial.add(this.estadoInicial);
        int i = 0;
        boolean b = false;
        while (i < this.transicioneslambda.size() && b == false) {
            if (this.transicioneslambda.get(i).getEstadoInicial().equals(this.estadoInicial) == true) {
                for (String s : this.transicioneslambda.get(i).getMacroestadoFinal()) {
                    this.macroestadoInicial.add(s);
                }
                b = true;
            } else {
                i++;
            }
        }
    }

    public ArrayList<String> transicion(String eini, char sim) {
        ArrayList<String> macroestado = new ArrayList();
        macroestado.add(eini);
        int ind = 0;
        boolean encontrado = false;
        while (ind < this.transicioneslambda.size() && encontrado == false) {
            if (this.transicioneslambda.get(ind).getEstadoInicial().equals(eini) == true) {
                for (String s : this.transicioneslambda.get(ind).getMacroestadoFinal()) {
                    macroestado.add(s);
                }
                encontrado = true;
            } else {
                ind++;
            }
        }
        return transicion(macroestado, sim);
    }

    public ArrayList<String> transicion(ArrayList<String> eini, char sim) {
        ArrayList<String> solucion = new ArrayList();
        ArrayList<String> añadidos = new ArrayList(); //evita duplicados en solucion
        boolean existe = false;
        for (String e : eini) {
            ArrayList<String> macroestado = new ArrayList();
            macroestado.add(e);
            int ind = 0;
            boolean encontrado = false;
            while (ind < this.transicioneslambda.size() && encontrado == false) {
                if (this.transicioneslambda.get(ind).getEstadoInicial().equals(e) == true) {
                    for (String s : this.transicioneslambda.get(ind).getMacroestadoFinal()) {
                        macroestado.add(s);
                    }
                    encontrado = true;
                } else {
                    ind++;
                }
            }
            int i = 0;
            boolean b = false;
            while (i < this.transiciones.size() && b == false) {
                TransicionAFND t = this.transiciones.get(i);
                for (String m : macroestado) {
                    if (t.getEstadoInicial().equals(m) == true && t.getSimbolo() == sim) {
                        for (String s : t.getMacroestadoFinal()) {
                            boolean a = false;
                            if (añadidos.isEmpty() == false) {
                                int j = 0;
                                while (j < añadidos.size() && a == false) {
                                    if (añadidos.get(j).equals(s) == true) {
                                        a = true;
                                    } else {
                                        j++;
                                    }
                                }
                            }
                            if (a == false) {
                                solucion.add(s);
                                añadidos.add(s);
                            }
                        }
                        b = true;
                        existe = true;
                    } else {
                        i++;
                    }
                }
            }
        }
        if (existe == false) {
            solucion = null;
        }
        return solucion;
    }

    public ArrayList<String> transicionlambda(String eini) {
        int i = 0;
        boolean b = false;
        ArrayList<String> solucion = null;
        while (i < this.transicioneslambda.size() && b == false) {
            TransicionLambda t = this.transicioneslambda.get(i);
            if (t.getEstadoInicial().equals(eini) == true) {
                solucion = t.getMacroestadoFinal();
                b = true;
            } else {
                i++;
            }
        }
        return solucion;
    }

    public ArrayList<String> transicionlambda(ArrayList<String> eini) {
        ArrayList<String> solucion = new ArrayList();
        boolean existe = false;
        for (String e : eini) {
            int i = 0;
            boolean b = false;
            while (i < this.transicioneslambda.size() && b == false) {
                TransicionLambda t = this.transicioneslambda.get(i);
                if (t.getEstadoInicial().equals(e) == true) {
                    for (String s : t.getMacroestadoFinal()) {
                        solucion.add(s);
                    }
                    b = true;
                    existe = true;
                } else {
                    i++;
                }
            }
        }
        if (existe == false) {
            solucion = null;
        }
        return solucion;
    }

    public void agregarTransicion(String eini, char sim, ArrayList<String> efin) {
        TransicionAFND t = new TransicionAFND(eini, sim, efin);
        this.transiciones.add(t);
    }

    public void agregarTransicion(String eini, char sim, String efin) {
        int i = 0;
        boolean b = false;
        while (i < this.transiciones.size() && b == false) {
            TransicionAFND t = this.transiciones.get(i);
            if (t.getEstadoInicial().equals(eini) == true && t.getSimbolo() == sim) {
                int j = 0;
                boolean c = false;
                while (j < t.getMacroestadoFinal().size() && c == false) {
                    if (t.getMacroestadoFinal().get(j).equals(efin) == true) {
                        c = true;
                    } else {
                        j++;
                    }
                }
                if (c == false) {
                    this.transiciones.get(i).addEstadoFinal(efin);
                }
                b = true;
            } else {
                i++;
            }
        }
        if (b == false) {
            ArrayList<String> efinales = new ArrayList();
            efinales.add(efin);
            TransicionAFND tran = new TransicionAFND(eini, sim, efinales);
            this.transiciones.add(tran);
        }
    }

    public void agregarTransicionLambda(String eini, ArrayList<String> efin) {
        TransicionLambda t = new TransicionLambda(eini, efin);
        this.transicioneslambda.add(t);
    }

    public void agregarTransicionLambda(String eini, String efin) {
        int i = 0;
        boolean b = false;
        while (i < this.transicioneslambda.size() && b == false) {
            TransicionLambda t = this.transicioneslambda.get(i);
            if (t.getEstadoInicial().equals(eini) == true) {
                int j = 0;
                boolean c = false;
                while (j < t.getMacroestadoFinal().size() && c == false) {
                    if (t.getMacroestadoFinal().get(j).equals(efin) == true) {
                        c = true;
                    } else {
                        j++;
                    }
                }
                if (c == false) {
                    this.transicioneslambda.get(i).addEstadoFinal(efin);
                }
                b = true;
            } else {
                i++;
            }
        }
        if (b == false) {
            ArrayList<String> efinales = new ArrayList();
            efinales.add(efin);
            TransicionLambda tran = new TransicionLambda(eini, efinales);
            this.transicioneslambda.add(tran);
        }
    }

    public boolean esFinal(String estado) {
        boolean b = false;
        int i = 0;
        while (b == false && i < this.macroestadoFinal.size()) {
            if (this.macroestadoFinal.get(i).equals(estado) == true) {
                b = true;
            } else {
                i++;
            }
        }
        return b;
    }

    public boolean esFinal(ArrayList<String> macroestado) {
        boolean b = false;
        int i = 0;
        while (b == false && i < this.macroestadoFinal.size()) {
            if (esFinal(this.macroestadoFinal.get(i)) == true) {
                b = true;
            } else {
                i++;
            }
        }
        return b;
    }

    public boolean reconocer(String cadena) {
        char[] simbolo = cadena.toCharArray();
        ArrayList<String> macroestado = this.macroestadoInicial;
        for (int i = 0; i < simbolo.length; i++) {
            macroestado = transicion(macroestado, simbolo[i]);
        }
        return esFinal(macroestado);
    }
    
    public static AFND leeFichero(){
        return null;
    }

    public static AFND pedir() {
        AFND afnd = null;
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
                    afnd = new AFND(estados, eini, estadosf, new ArrayList<TransicionAFND>(), new ArrayList<TransicionLambda>());
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
                            afnd.agregarTransicion(est[0], sim, est[1]);
                            System.out.println("Transicion agregada correctamente.");
                        } else{
                            System.out.println("ERROR: La transicion no se ha agregado, revisa los estados indicados.");
                        }
                    }
                    System.out.println("Añade ahora las transiciones lambda que desees con el siguiente formato: estado1, estado2 (Ej: q1, q2).");
                    System.out.println("Cuando no desees introducir más transiciones, escribe la palabra SALIR y pulsa Enter.");
                    cadenatransicion = "";
                    while (cadenatransicion.equals("SALIR") == false && cadenatransicion.equals("Salir") == false && cadenatransicion.equals("salir") == false) {
                        System.out.println("Escribe una transición: ");
                        cadenatransicion = scan.nextLine();
                        String[] elementos = cadenatransicion.split(",");
                        boolean correcto = true;
                        for (int k = 0; k < 2; k++) {
                            int j = 0;
                            boolean c = false;
                            while (c == false && j < estados.size()) {
                                if (estados.get(j).equals(elementos[k]) == true) {
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
                            afnd.agregarTransicionLambda(elementos[0], elementos[1]);
                            System.out.println("Transicion Lambda agregada correctamente.");
                        } else{
                            System.out.println("ERROR: La transicion Lambda no se ha agregado, revisa los estados indicados.");
                        }
                    }
                    break;
                }
                case 2: {
                    afnd = leeFichero();
                    break;
                }
                default: {
                    break;
                }
            }
        }
        return afnd;
    }
}
