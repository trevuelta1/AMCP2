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
public class TransicionLambda {
    private String estadoInicial;
    private ArrayList<String> macroestadoFinal;
    public TransicionLambda(String eini, ArrayList<String> efin){
        this.estadoInicial = eini;
        this.macroestadoFinal = efin;
    }
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
    public void addEstadoFinal(String estadoFinal){
        this.macroestadoFinal.add(estadoFinal);
    }
}
