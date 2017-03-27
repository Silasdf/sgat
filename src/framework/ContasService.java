package framework;

import java.util.List;

import sgat.entidades.Cliente;

public interface ContasService {
	
	// INSERT
	public void salvar(Cliente cliente);
	
	// RETRIEVE
	public List<Cliente> buscarTodos();
	
	public Cliente buscaPorCodigo(int condigo);
	
	// UPDATE
	public void atualizar(Cliente cliente);
	
	// DELETE
	public void apagar(int codigo);

}