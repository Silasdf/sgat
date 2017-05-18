package application.onibus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import framework.NamedParameterStatement;
import framework.SgatUtills;
import sgat.entidades.Onibus;

public class OnibusDBService implements OnibusService {
	
	final String INSERIR = "INSERT INTO onibus(nomemotorista, valorporpoltrona, placaonibus, onibuscommultas, anoonibus, viagensrealizadas, telefone) VALUES(?, ?, ?, ?, ?, ?, ?)";
	final String ATUALIZAR = "UPDATE onibus SET nomemotorista=?, valorporpoltrona=?, placaonibus=?, onibuscommultas=?, anoonibus=?, viagensrealizadas=?, telefone=? WHERE codigo = ?";
	final String BUSCAR = "SELECT codigo, nomemotorista, valorporpoltrona, placaonibus, onibuscommultas, anoonibus, viagensrealizadas, telefone, ativo FROM onibus WHERE codigo = ?";
	final String APAGAR = "UPDATE onibus SET ativo = 'N' WHERE codigo = ?";
	
	final String BUSCAR_ONIBUS = "SELECT * FROM onibus WHERE ativo = 'S' ";
	
	private Onibus onibus;
	private static OnibusService instance;
	
//	@Override
	public static OnibusService getInstance(){
		if (instance == null) {
		instance = new OnibusDBService();
		}
		return instance;
	}
	
	@Override
	public void salvar(Onibus onibus){
		try {
			Connection con = conexao();
			PreparedStatement salvar = con.prepareStatement(INSERIR);
			salvar.setString(1, onibus.getNome());
			salvar.setDouble(2, onibus.getValorPorPoltrona());
			salvar.setString(3, onibus.getPlacaOnibus());
			salvar.setString(4, onibus.getOnibusComMultas());
			salvar.setInt(5, onibus.getAnoOnibus());
			salvar.setInt(6, onibus.getViagensRealizadas());
			salvar.setString(7, onibus.getTelefone());
			salvar.executeUpdate();
			salvar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO CADASTRAR ONIBUS");
			System.exit(0);
		}
	}
	
	@Override
	public List<Onibus> buscarOnibus(Onibus onibus) {
		List<Onibus> variosOnibus = new ArrayList<>();
		try {
			Connection con = conexao();
			String sql = BUSCAR_ONIBUS;
			if (!SgatUtills.isNullOrEmpty((onibus.getNome()))){
				sql += " and nomemotorista LIKE :nomemotorista";
			}
			if (onibus.getValorPorPoltrona()!= null){
				sql += " and valorporpoltrona = :valorporpoltrona";
			}
			if (!SgatUtills.isNullOrEmpty((onibus.getPlacaOnibus()))){
				sql += " and placaonibus LIKE :placaonibus";
			}
			if (!SgatUtills.isNullOrEmpty((onibus.getOnibusComMultas()))){
				sql += " and onibuscommultas = :onibuscommultas";
			}
			if (onibus.getAnoOnibus()!= null){
				sql += " and anoonibus = :anoonibus";
			}
			if (onibus.getViagensRealizadas()!= null){
				sql += " and viagensrealizadas = :viagensrealizadas";
			}
			if (!SgatUtills.isNullOrEmpty((onibus.getTelefone()))){
				sql += " and telefone = :telefone";
			}
			System.out.println("SQL = " + sql);
			NamedParameterStatement buscarOnibus = new NamedParameterStatement(con, sql);
			if (!SgatUtills.isNullOrEmpty((onibus.getNome()))){
				buscarOnibus.setString("nomemotorista", "%" + onibus.getNome() + "%");
			}
			if (onibus.getValorPorPoltrona()!= null){
				buscarOnibus.setDouble("valorporpoltrona", onibus.getValorPorPoltrona());
			}
			if (!SgatUtills.isNullOrEmpty((onibus.getPlacaOnibus()))){
				buscarOnibus.setString("placaonibus", "%" + onibus.getPlacaOnibus() + "%");
			}
			if (!SgatUtills.isNullOrEmpty((onibus.getOnibusComMultas()))){
				buscarOnibus.setString("onibuscommultas", onibus.getOnibusComMultas());
			}
			if (onibus.getAnoOnibus()!= null){
				buscarOnibus.setInt("anoonibus", onibus.getAnoOnibus());
			}
			if (onibus.getViagensRealizadas()!= null){
				buscarOnibus.setInt("viagensrealizadas", onibus.getViagensRealizadas());
			}
			if (!SgatUtills.isNullOrEmpty((onibus.getTelefone()))){
				buscarOnibus.setString("telefone", onibus.getTelefone());
			}
			System.out.println("onibus = " + onibus);
			ResultSet resultadoBusca = buscarOnibus.executeQuery();
			while (resultadoBusca.next()) {
				Onibus onibusResultado = extraiOnibus(resultadoBusca);
				variosOnibus.add(onibusResultado);
			}
			buscarOnibus.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO BUSCAR ONIBUS ESPECIFICOS.");
			System.exit(0);
		} 
		return variosOnibus;
	}
	
	@Override
	public Onibus buscaPorCodigo(int codigo) {
		Onibus onibus = null;
		try {
			Connection con = conexao();
			PreparedStatement buscar = con.prepareStatement(BUSCAR);
			buscar.setInt(1, codigo);
			ResultSet resultadoBusca = buscar.executeQuery();
			resultadoBusca.next();
			onibus = extraiOnibus(resultadoBusca);
			buscar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO BUSCAR ONIBUS COM CODIGO " + codigo);
			System.exit(0);
		} 
		return onibus;
	}
	
	@Override
	public void apagar(Onibus onibus) {
		try {
			Connection con = conexao();
			PreparedStatement apagar = con.prepareStatement(APAGAR);
			apagar.setInt(1, onibus.getCodigo());
			apagar.executeUpdate();
			apagar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO APAGAR ONIBUS COM CODIGO ");
			System.exit(0);
		} 
	}
	
	@Override
	public void atualizar(Onibus onibus) {
		try {
			Connection con = conexao();
			PreparedStatement atualizar = con.prepareStatement(ATUALIZAR);
			atualizar.setString(1, onibus.getNome());
			atualizar.setDouble(2, onibus.getValorPorPoltrona());
			atualizar.setString(3, onibus.getPlacaOnibus());
			atualizar.setString(4, onibus.getOnibusComMultas());
			atualizar.setInt(5, onibus.getAnoOnibus());
			atualizar.setInt(6, onibus.getViagensRealizadas());
			atualizar.setString(7, onibus.getTelefone());
			atualizar.setInt(8, onibus.getCodigo());
			atualizar.executeUpdate();
			atualizar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO ATUALIZAR ONIBUS COM CODIGO " + onibus.getCodigo());
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
	
	// extrai o objeto Onibus do result set
	private Onibus extraiOnibus(ResultSet resultadoBusca) throws SQLException, ParseException {
		Onibus onibus = new Onibus();
		onibus.setCodigo(resultadoBusca.getInt(1));
		onibus.setNome(resultadoBusca.getString(2));
		onibus.setValorPorPoltrona(resultadoBusca.getDouble(3));
		onibus.setPlacaOnibus(resultadoBusca.getString(4));
		onibus.setOnibusComMultas(resultadoBusca.getString(5));
		onibus.setAnoOnibus(resultadoBusca.getInt(6));
		onibus.setViagensRealizadas(resultadoBusca.getInt(7));
		onibus.setTelefone(resultadoBusca.getString(8));
		onibus.setAtivo(resultadoBusca.getString(9));
		return onibus;
	}
	
	@Override
	public Onibus getOnibus() {

		return this.onibus;
	}

	@Override
	public void setOnibus(Onibus onibus) {

		this.onibus = onibus;
	}

}
