/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.leonardo.cmapp.modelo;

/**
 *
 * @author leonardo
 */
public class ResultadoEvento {
    
    private final boolean ganhou;
    
      public ResultadoEvento(boolean ganhou) {
        this.ganhou = ganhou;
    }

    public boolean isGanhou() {
        return ganhou;
    }   
    
}
