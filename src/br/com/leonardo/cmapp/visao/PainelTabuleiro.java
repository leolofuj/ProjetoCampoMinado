/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.leonardo.cmapp.visao;

import br.com.leonardo.cmapp.modelo.Tabuleiro;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author leonardo
 */
public class PainelTabuleiro extends JPanel {
    
    TelaPrincipal principal;
    
    public PainelTabuleiro(Tabuleiro tabuleiro) {
        
        setLayout(new GridLayout(
                tabuleiro.getLinhas(), tabuleiro.getColunas()));
        
       tabuleiro.paraCadaCampo(c -> add(new BotaoCampo(c)));

       tabuleiro.registrarObservador(e -> {
           SwingUtilities.invokeLater(() ->{
               if(e.isGanhou()) {
               JOptionPane.showMessageDialog(this, "Ganhou :)");
           }else{
               JOptionPane.showMessageDialog(this, "Perdeu :(");
           } 
               tabuleiro.reiniciar();       
           });
       });  
       
       // Observable utilizado para continuar jogo
        tabuleiro.registrarObservador2(e -> {
            SwingUtilities.invokeLater(() -> {
                if(continuarJogo() != 1){
                          if(principal.telaPrincipal != null){
                            principal.telaPrincipal.dispose();
                            principal.telaPrincipal.setVisible(false);
                            principal.telaPrincipal = new TelaPrincipal();
                          }  
                }else{
                    JOptionPane.showMessageDialog(this, "Obrigado por jogar :)");
                    System.exit(0);
                    
                }

            });
        }); 
    }
        // Método para perguntar se o jogador quer continuar o jogo
        public static int continuarJogo(){
        
        String[] options = new String[] {"sim", "não"};
        int response = JOptionPane.showOptionDialog(null, "Continuar o jogo?", "Campo Minado",
        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
        null, options, options[0]);
            
        return response;
      
    } 
}
