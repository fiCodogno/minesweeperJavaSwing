package campominado.modelo;

/**
 *
 * @author Filipe
 */
@FunctionalInterface
public interface CampoObservador {
    public void eventoOcorreu(Campo campo, CampoEvento evento);
}
