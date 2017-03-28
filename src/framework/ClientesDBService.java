package framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sgat.entidades.Cliente;

public class ClientesDBService implements ClientesService{
	
	final String INSERIR = "INSERT INTO cliente(nome, cpf, dataNascimento, rg, endereco, cidade, viagensPelaEmpresa) VALUES(?, ?, ?, ?, ?, ?, ?)";
	final String ATUALIZAR = "UPDATE cliente SET nome=?, cpf=?, dataNascimento=?, rg=?, endereco=?, cidade=?, viagensPelaEmpresa=?, ativo=? WHERE codigo = ?";
	final String BUSCAR = "SELECT codigo, nome, cpf, dataNascimento, rg, endereco, cidade, viagensPelaEmpresa, ativo FROM cliente WHERE CODIGO = ?";
	final String BUSCAR_TODOS = "SELECT codigo, nome, cpf, dataNascimento, rg, endereco, cidade, viagensPelaEmpresa, ativo FROM cliente";
	final String APAGAR = "DELETE FROM cliente WHERE codigo = ?";
	
	@Override
	public void salvar(Cliente cliente) {
		try {
			Connection con = conexao();
			PreparedStatement salvar = con.prepareStatement(INSERIR);
			salvar.setString(1, cliente.getNome());
			salvar.setString(2, cliente.getCpf());
			salvar.setDate(3, java.sql.Date.valueOf(cliente.getDataNascimento()));
//			salvar.setDate(3, new java.sql.Date(0L));
			salvar.setString(4, cliente.getRg());
			salvar.setString(5, cliente.getEndereco());
			salvar.setString(6, cliente.getCidade());
			salvar.setString(7, cliente.getViagemEmpresa());
			salvar.executeUpdate();
			salvar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO SALVAR CLIENTE");
			System.exit(0);
		} 
	}
	
	@Override
	public List<Cliente> buscarTodos() {
		List<Cliente> clientes = new ArrayList<>();
		try {
			Connection con = conexao();
			PreparedStatement buscarTodos = con.prepareStatement(BUSCAR_TODOS);
			ResultSet resultadoBusca = buscarTodos.executeQuery();
			while (resultadoBusca.next()) {
				Cliente cliente = extraiCliente(resultadoBusca);
				clientes.add(cliente);
			}
			buscarTodos.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR BUSCANDO TODAS AS CONTAS.");
			System.exit(0);
		} 
		return clientes;
	}
	
	@Override
	public Cliente buscaPorCodigo(int codigo) {
		Cliente cliente = null;
		try {
			Connection con = conexao();
			PreparedStatement buscar = con.prepareStatement(BUSCAR);
			buscar.setInt(1, codigo);
			ResultSet resultadoBusca = buscar.executeQuery();
			resultadoBusca.next();
			cliente = extraiCliente(resultadoBusca);
			buscar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO BUSCAR CLIENTE COM CODIGO " + codigo);
			System.exit(0);
		} 
		return cliente;
	}
	
	@Override
	public void apagar(int codigo) {
		try {
			Connection con = conexao();
			PreparedStatement apagar = con.prepareStatement(APAGAR);
			apagar.setInt(1, codigo);
			apagar.executeUpdate();
			apagar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO APAGAR CLIENTE COM CODIGO " + codigo);
			System.exit(0);
		} 
	}
	
	@Override
	public void atualizar(Cliente cliente) {
		try {
			Connection con = conexao();
			PreparedStatement atualizar = con.prepareStatement(ATUALIZAR);
			atualizar.setString(1, cliente.getNome());
			atualizar.setString(2, cliente.getCpf());
			atualizar.setDate(3, java.sql.Date.valueOf(cliente.getDataNascimento()));
			atualizar.setString(4, cliente.getRg());
			atualizar.setString(5, cliente.getEndereco());
			atualizar.setString(6, cliente.getCidade());
			atualizar.setString(7, cliente.getViagemEmpresa());
			atualizar.setInt(8, cliente.getCodigo());
			atualizar.executeUpdate();
			atualizar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO ATUALIZAR CLIENTE COM CODIGO " + cliente.getCodigo());
			System.exit(0);
		} 
	}

	// abre uma nova conexão com o banco de dados. Se algum erro for lançado
	// aqui, verifique o erro com atenção e se o banco está rodando
	private Connection conexao() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sgatdb?user=vfturismo&password=vfturismo");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			if(e instanceof ClassNotFoundException) {
				System.err.println("VERIFIQUE SE O DRIVER DO BANCO DE DADOS ESTÁ NO CLASSPATH");
			} else {
				System.err.println("VERIFIQUE SE O BANCO ESTÁ RODANDO E SE OS DADOS DE CONEXÃO ESTÃO CORRETOS");
			}
			System.exit(0);
			// o sistema deverá sair antes de chegar aqui...
			return null;
		}
	}

	// extrai o objeto Cliente do result set
	private Cliente extraiCliente(ResultSet resultadoBusca) throws SQLException, ParseException {
		Cliente cliente = new Cliente();
		cliente.setCodigo(resultadoBusca.getInt(1));
		cliente.setNome(resultadoBusca.getString(2));
		cliente.setCpf(resultadoBusca.getString(3));
		Date dataNascimento = resultadoBusca.getDate(4);
		LocalDate localDateNascimento = dataNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		cliente.setDataNascimento(localDateNascimento);
		cliente.setRg(resultadoBusca.getString(5));
		cliente.setEndereco(resultadoBusca.getString(6));
		cliente.setCidade(resultadoBusca.getString(7));
		cliente.setViagemEmpresa(resultadoBusca.getString(8));
		return cliente;
	}

}
