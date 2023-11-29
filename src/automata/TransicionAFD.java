/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automata;

/**
 *
 * @author usuario
 */
public class TransicionAFD {
    private String estadoInicial;
    private char simbolo;
    private String estadoFinal;
    public TransicionAFD(String eini, char sim, String efin){
        this.estadoInicial = eini;
        this.simbolo = sim;
        this.estadoFinal = efin;
    }
    @Override
    public String toString(){
        return this.getEstadoInicial() + " '" + this.getSimbolo() + "' " + this.getEstadoFinal();
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

    /**
     * @return the estadoFinal
     */
    public String getEstadoFinal() {
        return estadoFinal;
    }

    /**
     * @param estadoFinal the estadoFinal to set
     */
    public void setEstadoFinal(String estadoFinal) {
        this.estadoFinal = estadoFinal;
    }
}
