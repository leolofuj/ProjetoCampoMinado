/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.leonardo.cmapp.visao;

import br.com.leonardo.cmapp.modelo.Campo;
import br.com.leonardo.cmapp.modelo.CampoEvento;
import br.com.leonardo.cmapp.modelo.CampoObservador;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

/**
 *
 * @author leonardo
 */
public class BotaoCampo extends JButton 
        implements CampoObservador, MouseListener{
    
    private final Color BG_PADRAO = new Color(184, 184, 184);
    private final Color BG_MARCAR = new Color(8, 179, 247);
    private final Color BG_EXPLODIR = new Color(189, 66, 68);
    private final Color TEXTO_VERDE = new Color(0, 100, 0);
    
    private Campo campo;
    
    // Construtor da classe BotaoCampo
    public BotaoCampo(Campo campo) {
        this.campo = campo;
        setBackground(BG_PADRAO);
        setBorder(BorderFactory.createBevelBorder(0));
        
        addMouseListener(this);
        campo.registrarObservador(this);
        
    }
    
    // Método implementado pela interface CampoObservador
    @Override
    public void eventoOcorreu(Campo campo, CampoEvento evento) {
        
        // switch contendo as opções e estilos de cada campo aberto
        switch (evento) {
            
            case ABRIR:
                aplicarEstiloAbrir();
                break;
            case MARCAR:
                aplicarEstiloMarcar();
                break;  
            case EXPLODIR:
                aplicarEstiloExplodir();
                break;      
            default:
                aplicarEstiloPadrao();
        }
        // Utilizado para garantir que os bões sejam corretamente repintados ao reiniciar o jogo
        SwingUtilities.invokeLater(() -> { 
            repaint();
            validate();
        });
    }
    
    private void aplicarEstiloPadrao() {
        setBackground(BG_PADRAO);
        setBorder(BorderFactory.createBevelBorder(0));
        setText("");
        
        
    }
    private void aplicarEstiloAbrir() {
        
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        if(campo.isMinado()){
            setBackground(BG_EXPLODIR);
            setForeground(Color.WHITE);
            setText("B");
            //setIcon(new ImageIcon("bomb.png"));
            //validate();
            return;// sai do método
        }
        setBackground(BG_PADRAO);
        //setOpaque(true);
        
        
        switch (campo.minasNaVizinhanca()) {
            
            case 1:
                setForeground(TEXTO_VERDE);
                break;
            case 2:
                setForeground(Color.BLUE);
                break;  
            case 3:
                setForeground(Color.YELLOW);
                break; 
            case 4:
            case 5:
            case 6:
                setForeground(Color.RED);
                break;    
            default:
                
                setForeground(Color.PINK);
               
                
        }
        
        String valor = !campo.vizinhancaSegura() ? 
                campo.minasNaVizinhanca() + "" : "";
        setText(valor);
        
        
    }

    private void aplicarEstiloMarcar() {
        setBackground(BG_MARCAR);
        setForeground(Color.WHITE);
        setText("M");
        
    }

    private void aplicarEstiloExplodir() {
        
        setBackground(BG_EXPLODIR);
        setForeground(Color.WHITE);
        setText("B");
        //setIcon(new ImageIcon("bomb.png"));
        
    
    }
    
    // Interface dos eventos do mouse
    
     @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == 1){
            campo.abrir();
        } else {
            campo.alternarMarcacao();
            
        }
        
    }
    public void mouseClicked(MouseEvent e){}

    public void mouseReleased(MouseEvent e){}

    public void mouseEntered(MouseEvent e){}

    public void mouseExited(MouseEvent e) {}  
    
}
