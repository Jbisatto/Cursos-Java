package bancoDeDados;

public interface IPadraoDAO {
	public abstract boolean criaTabela() throws BancoDadosException;

	public abstract boolean destroiTabela() throws BancoDadosException;

}
