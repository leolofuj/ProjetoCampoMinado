/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.leonardo.cmapp.modelo;

/**
 *
 * @author leonardo
 */
public class OpcoesDeDificuldade {
    
    private int minas ;
    private final int linhas = 16 ;
    private final int colunas = 30 ;
    Tabuleiro tabuleiro;

    public Tabuleiro OpcoesDeDificuldade(int minas) {
      this.minas = minas; 
        System.out.println(minas);  
        switch (minas) {
            case 0:
                minas = 5;
                break;
            case 1:
                minas = 40;
                break;
            case 2:
                minas = 70;
                break;
            default:
                break;
        }
        tabuleiro = new Tabuleiro(linhas, colunas, minas);
      
        return tabuleiro; 
      
    }
   

    public int getMinas() {
        return minas;
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public void setMinas(int minas) {
        this.minas = minas;
    }

   
   

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
    
    
    
}
