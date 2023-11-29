/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automata;

import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class AFD {
    private final ArrayList<String> estados;
    private final ArrayList<String> estadosFinales;
    private final String estadoInicial;
    private ArrayList<TransicionAFD> transiciones;
    public AFD(ArrayList<String> estados, String estadoInicial, ArrayList<String> estadosFinales, ArrayList<TransicionAFD> transiciones){
        this.estados = estados;
        this.estadoInicial = estadoInicial;
        this.estadosFinales = estadosFinales;
        this.transiciones = transiciones;
    }
    public String transicion(String eini, char sim){
        String solucion = "No encontrada";
        boolean b = false;
        int i = 0;
        while(b == false && i < this.transiciones.size()){
            if(this.transiciones.get(i).getEstadoInicial().equals(eini) == true && this.transiciones.get(i).getSimbolo() == sim){
                b = true;
                solucion = this.transiciones.get(i).getEstadoFinal();
            } else{i++;}
        }
        return solucion;
    }
    public boolean esFinal(String estado){
        boolean b = false;
        int i = 0;
        while(b == false && i < this.estadosFinales.size()){
            if(this.estadosFinales.get(i).equals(estado) == true){
                b = true;
            } else{i++;}
        }
        return b;
    }
    public void agregarTransicion(String eini, char sim, String efin){
        TransicionAFD transicion = new TransicionAFD(eini, sim, efin);
        this.transiciones.add(transicion);
    }
    public boolean reconocer(String cadena){
        String estadoActual = this.estadoInicial;
        char[] simbolos = cadena.toCharArray();
        boolean cadenaErronea = false;
        int i = 0;
        while(i < simbolos.length && estadoActual.equals("No encontrada") == false){
            estadoActual = transicion(estadoActual, simbolos[i]);
            if(estadoActual.equals("No encontrada") == true){
                cadenaErronea = true;
            }
        }
        if(cadenaErronea == true){
            return false;
        } else return esFinal(estadoActual);
    }
}
