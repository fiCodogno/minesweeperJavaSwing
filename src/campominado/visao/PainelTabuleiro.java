package campominado.visao;

import campominado.modelo.Tabuleiro;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Filipe
 */
public class PainelTabuleiro extends JPanel {

    public PainelTabuleiro(Tabuleiro tabuleiro) {

        setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));

        tabuleiro.paraCada(c -> add(new BotaoCampo(c)));
        tabuleiro.registrarObservador(e -> {

            SwingUtilities.invokeLater(() -> {
                if (e.isGanhou()) {
                    JOptionPane.showMessageDialog(this, "Ganhou!");
                } else {
                    JOptionPane.showMessageDialog(this, "BOOOOOOOOM! Você perdeu!");
                }
                
                tabuleiro.reiniciar();
            });
        });

    }

}
