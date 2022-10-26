/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.leonardo.cmapp.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leonardo
 * 
 * 3ª parte criação de uma lista de observadores que modificarão os objetos conforme
 * a necessidade no curso do jogo
 */
public class Campo {
    
    private final int linha;
    
    private final int coluna;
    
    private boolean minado = false;
    
    private boolean aberto = false;
    
    private boolean marcado = false;
    
    private List<Campo> vizinhos = new ArrayList<>();
    private List<CampoObservador> observadores = new ArrayList<>();
    //private List<BiConsumer<Campo, CampoEvento>> obs = new ArrayList<>(); // pode ser utilizado a @FunctionalInterface BiConsumer ao inves de criar uma interface como a CampoObservadores 
    
    public Campo(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
    }
    
    public void registrarObservador(CampoObservador observador){ // registra observador
        observadores.add(observador);// Fluxo de eventos 1º Algum observador se registra
    }
    
    private void notificarObservadores(CampoEvento evento){ // notifica que um evento aconteceu
        observadores.stream() // 2º Quando vc quiser notificar um evento que ocorreu vc percorre toda a lista de observadore registrados
                .forEach(o -> o.eventoOcorreu(this, evento));// para cada um dos obs registrados(o) chama o metodo eventoOcorreu (interface)passando this (campo) e o evento
        
        
    }
    
    public boolean adicionarVizinho(Campo vizinho) {
        
        boolean linhaDiferente = linha != vizinho.linha;
        
        boolean colunaDiferente = coluna != vizinho.coluna;
         
        boolean diagonal = linhaDiferente && colunaDiferente; 
        
        int deltaLinha = Math.abs(linha - vizinho.linha);
        
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        
        int deltaGeral = deltaColuna + deltaLinha;
        
        if(deltaGeral == 1 && !diagonal) {
            vizinhos.add(vizinho);
            
            return true;
        }else if(deltaGeral == 2 && diagonal){
            vizinhos.add(vizinho);
            
            return true;
            
        }else{
            
            return false;
            
        }
        
        
    }
    
    public void alternarMarcacao(){
        if(!aberto){
            marcado = !marcado;
            
            if(marcado){
                
                notificarObservadores(CampoEvento.MARCAR);
            }else{
                notificarObservadores(CampoEvento.DESMARCAR);
            }
        }
    }
    
    public boolean abrir(){
        
        if(!aberto && !marcado){
             
            if(minado){
                
                notificarObservadores(CampoEvento.EXPLODIR);
                return true;
                
            }
            
            setAberto(true);
            
            
            
            if(vizinhancaSegura()){
                vizinhos.forEach(v -> v.abrir());
                
            }
            return true;
        }else{
            return false;
        }
            
            
    }
    
    public boolean vizinhancaSegura(){
        return vizinhos.stream().noneMatch(v -> v.minado);
    }
    
    public void minar(){
        
        minado = true;
    }
    
    public boolean isMinado(){
        return minado;
    }
    
    
    public boolean isMarcado(){
        
        return marcado;
        
    }
  
    
    void setAberto(boolean aberto){
        this.aberto = aberto;
        
        if(aberto){
            notificarObservadores(CampoEvento.ABRIR);
        }
    }
    
    public boolean isAberto(){
        return aberto;
    }
    
    public boolean isFechado(){
        return !isAberto();
    }
    
    public int getLinha(){
        return linha;
    }
    
     public int getColuna(){
        return coluna;
    }
    
     public boolean objetivoAlcancado(){
         boolean desvendado = !minado && aberto;
         boolean protegido = minado && marcado;
         
         return desvendado || protegido;
     }
     
     public int minasNaVizinhanca(){
         
         return (int) vizinhos.stream().filter(v -> v.minado).count();
         
     }
     
     public void reiniciar(){
         aberto = false;
         minado = false;
         marcado = false;
        notificarObservadores(CampoEvento.REINICIAR);
         
     }
    
        
}
