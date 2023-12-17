/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automata;

import java.util.ArrayList;

/**
 * Clase TransicionLambda que almacena los datos de las transiciones Lambda que utilizan los AFND.
 * @author Toni Revuelta
 **/
public class TransicionLambda {
    private String estadoInicial;
    private ArrayList<String> macroestadoFinal;
    /**
     * Constructor de la clase TransicionLambda.
     * @param eini Estado inicial de la transición
     * @param efin Estados finales de la transición
     **/
    public TransicionLambda(String eini, ArrayList<String> efin){
        this.estadoInicial = eini;
        this.macroestadoFinal = efin;
    }
    /**
     * Devuelve un String con los datos de la transición.
     * @return String con los datos de la transición.
     **/
    @Override
    public String toString(){
        return this.getEstadoInicial() + " " + this.getMacroestadoFinal();
    }

    /**
     * @return the estadoInicial
     */
    public String getEstadoInicial() {
        return estadoInicial;
    }

    /**
     * @param estadoInicial the estadoInicial to set
     */
    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    /**
     * @return the macroestadoFinal
     */
    public ArrayList<String> getMacroestadoFinal() {
        return macroestadoFinal;
    }

    /**
     * @param macroestadoFinal the macroestadoFinal to set
     */
    public void setMacroestadoFinal(ArrayList<String> macroestadoFinal) {
        this.macroestadoFinal = macroestadoFinal;
    }
    /**
     * Añade un nuevo estado final a la transición.
     * @param estadoFinal Estado final a añadir
     **/
    public void addEstadoFinal(String estadoFinal){
        this.macroestadoFinal.add(estadoFinal);
    }
}
