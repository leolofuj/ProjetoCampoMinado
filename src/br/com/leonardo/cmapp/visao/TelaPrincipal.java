/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.leonardo.cmapp.visao;

import br.com.leonardo.cmapp.modelo.OpcoesDeDificuldade;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author leonardo
 */
public class TelaPrincipal extends JFrame{
        
    int response;
    OpcoesDeDificuldade opcoesDeDificuldade = new OpcoesDeDificuldade();
    public static TelaPrincipal telaPrincipal;
    
    public Component mostrarPainel(){
        
        String[] options = new String[] {"easy", "normal", "hard"};
        response = JOptionPane.showOptionDialog(null, "Escolha a dificuldade do jogo", "Campo Minado",
        JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION,
        null, options, options[0]);
       
        return add(new PainelTabuleiro(opcoesDeDificuldade.OpcoesDeDificuldade(response)));  
        
    }
    public TelaPrincipal() {
         mostrarPainel();
         setTitle("Campo Minado");
         setSize(690, 438);
         setLocationRelativeTo(null);
         setDefaultCloseOperation(DISPOSE_ON_CLOSE);
         setVisible(true);
    }
  
    public static void main(String[] args) {

        telaPrincipal = new TelaPrincipal();
       
    }   
    
}
