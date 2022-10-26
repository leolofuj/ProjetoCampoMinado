/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.leonardo.cmapp.modelo;

/**
 *
 * @author leonardo
 * 
 * 2ª parte
 * 
 * Criação de uma interface contendo um método que recebe o objeto a ser modificado 
 * (Campo) e o evento que modificará o estado do obj
 * 
 */
public interface CampoObservador { // Esta interface representa o observador do campo
    
    public void eventoOcorreu(Campo campo, CampoEvento evento);
    
}
