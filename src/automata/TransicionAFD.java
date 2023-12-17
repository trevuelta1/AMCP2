/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automata;

/**
 * Clase TransicionAFD que almacena los datos de las transiciones que utilizan los AFD.
 * @author Toni Revuelta
 **/
public class TransicionAFD {
    private String estadoInicial;
    private char simbolo;
    private String estadoFinal;
    /**
     * Constructor de la clase TransicionAFD.
     * @param eini Estado inicial de la transición
     * @param sim Símbolo a consumir
     * @param efin Estado final de la transición
     **/
    public TransicionAFD(String eini, char sim, String efin){
        this.estadoInicial = eini;
        this.simbolo = sim;
        this.estadoFinal = efin;
    }
    /**
     * Devuelve un String con los datos de la transición.
     * @return String con los datos de la transición.
     **/
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
