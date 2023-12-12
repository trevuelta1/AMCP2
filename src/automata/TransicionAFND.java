/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automata;

import java.util.ArrayList;

/**
 *
 * @author USUARIO1
 */
public class TransicionAFND {
    private String estadoInicial;
    private ArrayList<String> macroestadoFinal;
    private char simbolo;
    public TransicionAFND(String eini, char sim, ArrayList<String> efin){
        this.estadoInicial = eini;
        this.macroestadoFinal = efin;
        this.simbolo = sim;
    }
    
    @Override
    public String toString(){
        return this.getEstadoInicial() + " '" + this.getSimbolo() + "' " + this.getMacroestadoFinal();
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
    
    public void addEstadoFinal(String estadoFinal){
        this.macroestadoFinal.add(estadoFinal);
    }

    /**
     * @return the simbolo
     */
    public char getSimbolo() {
        return simbolo;
    }

    /**
     * @param simbolo the simbolo to set
     */
    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }

}
