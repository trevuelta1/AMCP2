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
        this.macroestadoInicial = transicionlambda(this.estadoInicial);
    }

    public ArrayList<String> transicion(String eini, char sim) {
        ArrayList<String> macroestado = new ArrayList();
        macroestado.add(eini);
        return transicion(macroestado, sim);
    }

    public ArrayList<String> transicion(ArrayList<String> eini, char sim) {
        ArrayList<String> solucion = new ArrayList();
        ArrayList<String> macroestado = transicionlambda(eini);
        System.out.println(macroestado);
        ArrayList<String> añadidos = new ArrayList(); //evita duplicados en solucion
        boolean existe = false;
        int i = 0;
        boolean b = false;
        boolean existesimbolo = false;
        while (i < this.transiciones.size() && b == false) {
            TransicionAFND t = this.transiciones.get(i);
            if (sim == t.getSimbolo()) {
                existesimbolo = true;
            }
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
        if (existe == false) {
            solucion = null;
            System.out.println("No existe transición");
        } else if (existesimbolo == false) {
            solucion = null;
            System.out.println("No existe símbolo");
        } else {
            solucion = transicionlambda(solucion);
        }
        return solucion;
    }

    public ArrayList<String> transicionlambda(String eini) {
        ArrayList<String> est = new ArrayList();
        est.add(eini);
        return transicionlambda(est);
    }

    public ArrayList<String> transicionlambda(ArrayList<String> eini) {
        ArrayList<String> solucion = new ArrayList();
        solucion.addAll(eini);
        boolean repetir = true;
        int tinicial = solucion.size();
        int tfinal = tinicial * 2;
        while (repetir == true) {
            int limite = tfinal - tinicial;
            tinicial = solucion.size();
            int inicio = tinicial - limite;
            limite = inicio + limite;
            for (int k = inicio; k < limite; k++) {
                int i = 0;
                boolean b = false;
                while (i < this.transicioneslambda.size() && b == false) {
                    TransicionLambda t = this.transicioneslambda.get(i);
                    if (t.getEstadoInicial().equals(solucion.get(k)) == true) {
                        for (String s : t.getMacroestadoFinal()) {
                            solucion.add(s);
                        }
                        b = true;
                    } else {
                        i++;
                    }
                }
                ArrayList<String> añadidos = new ArrayList();
                ArrayList<String> eliminar = new ArrayList();
                añadidos.add(solucion.get(0));
                tfinal = solucion.size();
                for (int j = 1; j < tfinal; j++) {
                    int f = 0;
                    boolean esta = false;
                    String elemento = solucion.get(j);
                    while (f < añadidos.size() && esta == false) {
                        if (añadidos.get(f).equals(elemento) == true) {
                            esta = true;
                        } else {
                            f++;
                        }
                    }
                    if (esta == false) {
                        añadidos.add(elemento);
                    } else{
                        eliminar.add(elemento);
                    }
                }
                for(String e : eliminar){
                    solucion.remove(e);
                }
            }
            tfinal = solucion.size();
            if (tinicial == tfinal) {
                repetir = false;
            }
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
        while (b == false && i < macroestado.size()) {
            if (esFinal(macroestado.get(i)) == true) {
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
        int i = 0;
        boolean error = false;
        while (i < simbolo.length && error == false) {
            macroestado = transicion(macroestado, simbolo[i]);
            if (macroestado == null) {
                error = true;
            }
            i++;
        }
        if (error == true) {
            System.out.println("CADENA CON CARACTERES NO VÁLIDOS.");
            return false;
        } else {
            boolean fin = esFinal(macroestado);
            if (fin == true) {
                System.out.println("CADENA ACEPTADA.");
            } else {
                System.out.println("CADENA RECHAZADA.");
            }
            return fin;
        }
    }

    public boolean reconocerPasoAPaso(String cadena) {
        char[] simbolo = cadena.toCharArray();
        ArrayList<String> macroestado = this.macroestadoInicial;
        int i = 0;
        boolean error = false;
        while (i < simbolo.length && error == false) {
            macroestado = transicion(macroestado, simbolo[i]);
            if (macroestado == null) {
                error = true;
            }
            if (error == false) {
                System.out.println("Símbolo: '" + simbolo[i] + "'.");
                System.out.println("Estados accesibles:");
                System.out.println(macroestado);
            } else {
                System.out.println("CADENA CON CARACTERES NO VÁLIDOS.");
            }
            i++;
        }
        if (error == true) {
            return false;
        } else {
            boolean fin = esFinal(macroestado);
            if (fin == true) {
                System.out.println("CADENA ACEPTADA.");
            } else {
                System.out.println("CADENA RECHAZADA.");
            }
            return fin;
        }
    }

    public static AFND leeFichero() throws FileNotFoundException, IOException {
        AFND solucion;
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
        if (tipo[0].trim().equals("TIPO") == false || tipo[1].trim().equals("AFND") == false) {
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
            solucion = new AFND(estados, eini, efinales, new ArrayList<TransicionAFND>(), new ArrayList<TransicionLambda>());
            boolean b = false;
            while (b == false) {
                line = br.readLine();
                if ("TRANSICIONES LAMBDA:".equals(line)) {
                    b = true;
                } else {
                    String[] datos = line.split(" ");
                    String e1 = datos[0].trim();
                    String simbol = datos[1].trim();
                    char sim = simbol.toCharArray()[1];
                    ArrayList<String> mefin = new ArrayList();
                    for (int i = 2; i < datos.length; i++) {
                        mefin.add(datos[i].trim());
                    }
                    solucion.agregarTransicion(e1, sim, mefin);
                }
            }
            b = false;
            while (b == false) {
                line = br.readLine();
                if ("FIN".equals(line)) {
                    b = true;
                } else {
                    String[] datos = line.split(" ");
                    String e1 = datos[0].trim();
                    ArrayList<String> mefin = new ArrayList();
                    for (int i = 1; i < datos.length; i++) {
                        mefin.add(datos[i].trim());
                    }
                    solucion.agregarTransicionLambda(e1, mefin);
                }
            }
        }
        return solucion;
    }

    public static AFND pedir() {
        AFND afnd = null;
        int opt = 0;
        Scanner scan = new Scanner(System.in);
        while (opt != 3) {
            System.out.println("Indica si deseas crear un nuevo AFND o leer uno existente desde fichero.");
            System.out.println("");
            System.out.println("1. Crear AFND.");
            System.out.println("2. Leer AFND desde fichero.");
            System.out.println("3. Salir.");
            System.out.println("");
            System.out.println("Escoge una opción: ");
            opt = scan.nextInt();
            switch (opt) {
                case 1: {
                    System.out.println("Indica el nombre de cada uno de los estados del autómata separados por una coma (Ej: q1, q2, q3): ");
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
                    System.out.println("Indica el nombre de cada uno de los estados finales separados por una coma (Ej: q1, q2, q3): ");
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
                    afnd = new AFND(estados, eini, estadosf, new ArrayList<TransicionAFND>(), new ArrayList<TransicionLambda>());
                    System.out.println("");
                    System.out.println("Añade las transiciones que desees con el siguiente formato: estado1, símbolo, estado2.");
                    System.out.println("Ten en consideración que el símbolo debe ser un único carácter (Ej: q1, p, q2).");
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
                                afnd.agregarTransicion(est[0], sim, est[1]);
                                System.out.println("Transicion agregada correctamente.");
                            } else {
                                System.out.println("ERROR: La transicion no se ha agregado, revisa los estados indicados.");
                            }
                        }
                    }
                    System.out.println("");
                    System.out.println("Añade ahora las transiciones lambda que desees con el siguiente formato: estado1, estado2 (Ej: q1, q2).");
                    System.out.println("Cuando no desees introducir más transiciones, escribe la palabra SALIR y pulsa Enter.");
                    cadenatransicion = "";
                    while (cadenatransicion.equals("SALIR") == false && cadenatransicion.equals("Salir") == false && cadenatransicion.equals("salir") == false) {
                        System.out.println("Escribe una transición: ");
                        cadenatransicion = scan.next();
                        if (cadenatransicion.equals("SALIR") == false && cadenatransicion.equals("Salir") == false && cadenatransicion.equals("salir") == false) {
                            String[] elementos = cadenatransicion.split(",");
                            boolean correcto = true;
                            for (int k = 0; k < 2; k++) {
                                int j = 0;
                                boolean c = false;
                                while (c == false && j < estados.size()) {
                                    if (estados.get(j).equals(elementos[k].trim()) == true) {
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
                                afnd.agregarTransicionLambda(elementos[0].trim(), elementos[1].trim());
                                System.out.println("Transicion Lambda agregada correctamente.");
                            } else {
                                System.out.println("ERROR: La transicion Lambda no se ha agregado, revisa los estados indicados.");
                            }
                        }
                    }
                    break;
                }
                case 2: {
                    try {
                        afnd = leeFichero();
                        if (afnd != null) {
                            System.out.println("");
                            System.out.println("");
                            System.out.println("Fichero leído correctamente, pulsa 3 para salir y poder trabajar con el autómata.");
                            System.out.println("");
                            System.out.println("");
                        }
                        break;
                    } catch (IOException ex) {
                        Logger.getLogger(AFND.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                default: {
                    break;
                }
            }
        }
        return afnd;
    }

    public void ver() {
        System.out.println("");
        System.out.println("Estados: " + this.estados);
        System.out.println("Estado inicial: " + this.estadoInicial);
        System.out.println("Estados finales: " + this.macroestadoFinal);
        System.out.println("");
        System.out.println("Transiciones:");
        for (int i = 0; i < this.transiciones.size(); i++) {
            System.out.println(this.transiciones.get(i).toString());
        }
        System.out.println("");
        System.out.println("Transiciones lambda:");
        for (int i = 0; i < this.transicioneslambda.size(); i++) {
            System.out.println(this.transicioneslambda.get(i).toString());
        }
        System.out.println("");
    }
}
