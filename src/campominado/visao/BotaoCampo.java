package campominado.visao;

import campominado.modelo.Campo;
import campominado.modelo.CampoEvento;
import campominado.modelo.CampoObservador;
import java.awt.Color;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import org.w3c.dom.events.MouseEvent;

/**
 *
 * @author Filipe
 */
public class BotaoCampo extends JButton implements CampoObservador, MouseListener{
    
    private final Color BG_PADRAO = new Color(184, 184, 184);
    private final Color BG_MARCADO = new Color(8, 179, 247);
    private final Color BG_EXPLOSAO = new Color(189, 66, 68);
    private final Color TEXTO_VERDE = new Color(0, 100, 0);
    
    private Campo campo;
    
    public BotaoCampo(Campo campo){
        this.campo = campo;
        setOpaque(true);
        setBorder(BorderFactory.createBevelBorder(0));
        setBackground(BG_PADRAO);
        
        addMouseListener(this);
        campo.registrarObservadores(this);   
    }

    @Override
    public void eventoOcorreu(Campo campo, CampoEvento evento) {
        switch(evento){
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
        
        SwingUtilities.invokeLater(() -> {
            repaint();
            validate();
        });
    }

    private void aplicarEstiloAbrir() {
        setBackground(BG_PADRAO);
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        if(campo.isMinado()){
            setBackground(BG_EXPLOSAO);
            return;
        }
        
        switch(campo.minasNaVizinhanca()){
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
        
        String valor = !campo.vizinhancaSegura() ? "" + campo.minasNaVizinhanca() : "";
        setText(valor);
    }

    private void aplicarEstiloExplodir() {
        setForeground(Color.WHITE);
        setBackground(BG_EXPLOSAO);
        setText("X");
    }

    private void aplicarEstiloMarcar() {
        setForeground(Color.WHITE);
        setBackground(BG_MARCADO);
        setText("M");
    }
    
    private void aplicarEstiloPadrao() {
        setBorder(BorderFactory.createBevelBorder(0));
        setBackground(BG_PADRAO);
        setText("");
    }
    
    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        if(e.getButton() == 1){ //botao esquerdo
            campo.abrir();
        } else if (e.getButton() == 3){ //botao direito
            campo.alternarMarcacao();
        }
    }
    
    // métodos do mouse não utilizados
    public void mouseClicked(java.awt.event.MouseEvent e) {}
    public void mouseReleased(java.awt.event.MouseEvent e) {} 
    public void mouseEntered(java.awt.event.MouseEvent e) {}
    public void mouseExited(java.awt.event.MouseEvent e) {}
    
}
