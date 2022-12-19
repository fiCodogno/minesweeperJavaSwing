package campominado.visao;

import campominado.modelo.Tabuleiro;
import javax.swing.JFrame;

/**
 *
 * @author Filipe
 */
public class TelaPrincipal extends JFrame{
    
    public TelaPrincipal(){
        Tabuleiro tabuleiro = new Tabuleiro(16, 30, 50);
        
        add(new PainelTabuleiro(tabuleiro));
        
        setSize(690, 438);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Campo Minado");
        setVisible(true);
    }
    
    public static void main(String[] args) {
        
        new TelaPrincipal();
        
    }
    
}
