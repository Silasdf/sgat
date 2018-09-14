package application.clientes;

import java.util.List;

import sgat.entidades.Cliente;

public interface ClientesService {
	
	public Cliente getCliente();
	public void setCliente (Cliente cliente);
	
	public Cliente getClienteSelecionado();
	public void setClienteSelecionado (Cliente clienteSelecionado);
	
	// INSERT
	public void salvar(Cliente cliente);
	
	// RETRIEVE
	public List<Cliente> buscarClientes(Cliente cliente);
	
	public Cliente buscaPorCodigo(int condigo);
	
	// UPDATE
	public void atualizar(Cliente cliente);
	
	// DELETE
	public void apagar(Cliente cliente);
	
	public List<Cliente> buscarAniversariantes();

//	public static ClientesService getNewInstance() {
//
//		return new ClientesDBService();
//	}

}