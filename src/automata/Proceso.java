/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package automata;

/**
 * Interfaz Proceso implementada por los AFD y AFND.
 * @author Toni Revuelta
 */
public interface Proceso {
    public abstract boolean esFinal(String estado);
    public abstract boolean reconocer(String cadena);
    @Override
    public abstract String toString();
}
