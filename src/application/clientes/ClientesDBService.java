package application.clientes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import framework.NamedParameterStatement;
import framework.SgatUtills;
import sgat.entidades.Cliente;

public class ClientesDBService implements ClientesService{
	
	final String INSERIR = "INSERT INTO cliente(nome, cpf, datanascimento, rg, endereco, cidade, viagenspelaempresa, telefone) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	final String ATUALIZAR = "UPDATE cliente SET nome=?, cpf=?, datanascimento=?, rg=?, endereco=?, cidade=?, viagenspelaempresa=?, telefone=? WHERE codigo = ?";
	final String BUSCAR = "SELECT codigo, nome, cpf, dataNascimento, rg, endereco, cidade, viagensPelaEmpresa, telefone, ativo FROM cliente WHERE codigo = ?";
//	final String BUSCAR_TODOS = "SELECT codigo, nome, cpf, dataNascimento, rg, endereco, cidade, viagensPelaEmpresa, ativo FROM cliente";
	final String APAGAR = "UPDATE cliente SET ativo = 'N' WHERE codigo = ?";
	
	final String BUSCAR_CLIENTES = "SELECT * FROM cliente WHERE ativo = 'S' ";
	
	final String BUSCAR_ANIVERSARIANTES_MÊS = "SELECT codigo, nome, cpf, dataNascimento, rg, endereco, cidade, viagensPelaEmpresa, telefone, ativo FROM cliente WHERE MONTH(datanascimento) = MOD(MONTH(CURDATE()), 12)";
	
	private Cliente cliente;
	private Cliente clienteSelecionado;
	private static ClientesService instance;
	
//	@Override
	public static ClientesService getInstance(){
		if (instance == null) {
		instance = new ClientesDBService();
		}
		return instance;
	}
	
	@Override
	public Cliente getCliente() {

		return this.cliente;
	}

	@Override
	public void setCliente(Cliente cliente) {

		this.cliente = cliente;
	}
	
	@Override
	public Cliente getClienteSelecionado() {

		return this.clienteSelecionado;
	}

	@Override
	public void setClienteSelecionado(Cliente clienteSelecionado) {

		this.clienteSelecionado = clienteSelecionado;
	}
	
	@Override
	public void salvar(Cliente cliente) {
		try {
			Connection con = conexao();
			PreparedStatement salvar = con.prepareStatement(INSERIR);
			salvar.setString(1, cliente.getNome());
			salvar.setString(2, cliente.getCpf());
			salvar.setDate(3, java.sql.Date.valueOf(cliente.getDataNascimento()));
			salvar.setString(4, cliente.getRg());
			salvar.setString(5, cliente.getEndereco());
			salvar.setString(6, cliente.getCidade());
			salvar.setInt(7, cliente.getViagemEmpresa());
			salvar.setString(8, cliente.getTelefone());
			salvar.executeUpdate();
			salvar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO CADASTRAR CLIENTE");
			System.exit(0);
		} 
	}

	@Override
	public List<Cliente> buscarClientes(Cliente cliente) {
		List<Cliente> clientes = new ArrayList<>();
		try {
			Connection con = conexao();
			String sql = BUSCAR_CLIENTES;
			if (!SgatUtills.isNullOrEmpty((cliente.getNome()))){
				sql += " and nome LIKE :nome";
			}
			if (!SgatUtills.isNullOrEmpty((cliente.getCpf()))){
				sql += " and cpf = :cpf";
			}
			if (cliente.getDataNascimento()!= null){
				sql += " and dataNascimento = :dataNascimento";
			}
			if (!SgatUtills.isNullOrEmpty((cliente.getRg()))){
				sql += " and rg = :rg";
			}
			if (!SgatUtills.isNullOrEmpty((cliente.getEndereco()))){
				sql += " and endereco LIKE :endereco";
			}
			if (!SgatUtills.isNullOrEmpty((cliente.getCidade()))){
				sql += " and cidade LIKE :cidade";
			}
			if (cliente.getViagemEmpresa()!= null){
				sql += " and viagensPelaEmpresa = :viagensPelaEmpresa";
			}
			if (!SgatUtills.isNullOrEmpty((cliente.getTelefone()))){
				sql += " and telefone = :telefone";
			}
			System.out.println("SQL = " + sql);
			NamedParameterStatement buscarClientes = new NamedParameterStatement(con, sql);
			if (!SgatUtills.isNullOrEmpty((cliente.getNome()))){
				buscarClientes.setString("nome", "%" + cliente.getNome() + "%");
			}
			if (!SgatUtills.isNullOrEmpty((cliente.getCpf()))){
				buscarClientes.setString("cpf", cliente.getCpf());
			}
			if (cliente.getDataNascimento()!= null){
				Timestamp timestamp = Timestamp.valueOf(cliente.getDataNascimento().atStartOfDay());
				buscarClientes.setTimestamp("dataNascimento", timestamp);
			}
			if (!SgatUtills.isNullOrEmpty((cliente.getRg()))){
				buscarClientes.setString("rg", cliente.getRg());
			}
			if (!SgatUtills.isNullOrEmpty((cliente.getEndereco()))){
				buscarClientes.setString("endereco", "%" + cliente.getEndereco() + "%");
			}
			if (!SgatUtills.isNullOrEmpty((cliente.getCidade()))){
				buscarClientes.setString("cidade", "%" + cliente.getCidade() + "%");
			}
			if (cliente.getViagemEmpresa()!= null){
				buscarClientes.setInt("viagensPelaEmpresa", cliente.getViagemEmpresa());
			}
			if (!SgatUtills.isNullOrEmpty((cliente.getTelefone()))){
				buscarClientes.setString("telefone", cliente.getTelefone());
			}
			System.out.println("cliente = " + cliente);
			ResultSet resultadoBusca = buscarClientes.executeQuery();
			while (resultadoBusca.next()) {
				Cliente clienteResultado = extraiCliente(resultadoBusca);
				clientes.add(clienteResultado);
			}
			buscarClientes.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO BUSCAR CLIENTES ESPECIFICOS.");
			System.exit(0);
		} 
		return clientes;
	}
	
//	@Override
//	public List<Cliente> buscarTodos() {
//		List<Cliente> clientes = new ArrayList<>();
//		try {
//			Connection con = conexao();
//			PreparedStatement buscarTodos = con.prepareStatement(BUSCAR_TODOS);
//			ResultSet resultadoBusca = buscarTodos.executeQuery();
//			while (resultadoBusca.next()) {
//				Cliente cliente = extraiCliente(resultadoBusca);
//				clientes.add(cliente);
//			}
//			buscarTodos.close();
//			con.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.err.println("ERROR AO BUSCAR TODOS OS CLIENTES.");
//			System.exit(0);
//		} 
//		return clientes;
//	}
	
	@Override
	public Cliente buscaPorCodigo(int codigo) {
		Cliente cliente = null;
		try {
			Connection con = conexao();
			PreparedStatement buscar = con.prepareStatement(BUSCAR);
			buscar.setInt(1, codigo);
			ResultSet resultadoBusca = buscar.executeQuery();
			if (resultadoBusca.next()) {
				cliente = extraiCliente(resultadoBusca);
			}
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
	public void apagar(Cliente cliente) {
		try {
			Connection con = conexao();
			PreparedStatement apagar = con.prepareStatement(APAGAR);
			apagar.setInt(1, cliente.getCodigo());
			apagar.executeUpdate();
			apagar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO APAGAR CLIENTE COM CODIGO ");
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
			atualizar.setInt(7, cliente.getViagemEmpresa());
			atualizar.setString(8, cliente.getTelefone());
			atualizar.setInt(9, cliente.getCodigo());
			atualizar.executeUpdate();
			atualizar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO ATUALIZAR CLIENTE COM CODIGO " + cliente.getCodigo());
			System.exit(0);
		} 
	}
	
	@Override
	public List<Cliente> buscarAniversariantes() {
		List<Cliente> clientes = new ArrayList<>();
		try {
			Connection con = conexao();
			String sql = BUSCAR_ANIVERSARIANTES_MÊS;
			System.out.println("SQL = " + sql);
			NamedParameterStatement buscarAniversariantes = new NamedParameterStatement(con, sql);
			ResultSet resultadoBusca = buscarAniversariantes.executeQuery();
			while (resultadoBusca.next()) {
				Cliente clienteResultado = extraiCliente(resultadoBusca);
				clientes.add(clienteResultado);
			}
			buscarAniversariantes.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO BUSCAR ANIVERSARIANTES DO MÊS.");
			System.exit(0);
		} 
		return clientes;
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
		LocalDate dataNascimento = resultadoBusca.getDate(4).toLocalDate();
		cliente.setDataNascimento(dataNascimento);
		cliente.setRg(resultadoBusca.getString(5));
		cliente.setEndereco(resultadoBusca.getString(6));
		cliente.setCidade(resultadoBusca.getString(7));
		cliente.setViagemEmpresa(resultadoBusca.getInt(8));
		cliente.setTelefone(resultadoBusca.getString(9));
		cliente.setAtivo(resultadoBusca.getString(10));
		return cliente;
	}
	
}
