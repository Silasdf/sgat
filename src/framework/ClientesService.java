package framework;

import java.util.List;

import sgat.entidades.Cliente;

public interface ClientesService {
	
	public Cliente getCliente();
	public void setCliente (Cliente cliente);
	
//	public abstract void getInstance();
	
//	private static final ClientesService instance;
//	protected ClientesService(){
//
//	}
	
	// INSERT
	public void salvar(Cliente cliente);
	
	// RETRIEVE
	public List<Cliente> buscarTodos();
	
	public List<Cliente> buscarClientes(Cliente cliente);
	
	public Cliente buscaPorCodigo(int condigo);
	
	// UPDATE
	public void atualizar(Cliente cliente);
	
	// DELETE
	public void apagar(int codigo);

//	public static ClientesService getNewInstance() {
//
//		return new ClientesDBService();
//	}

}