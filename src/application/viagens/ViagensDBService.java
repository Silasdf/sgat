package application.viagens;

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
import sgat.entidades.Viagem;

public class ViagensDBService implements ViagensService{
	
	final String INSERIR = "INSERT INTO viagem(nomeviagem, dataida, datavolta, embarque, hospedagem) VALUES(?, ?, ?, ?, ?)";
	final String ATUALIZAR = "UPDATE viagem SET nomeviagem=?, dataida=?, datavolta=?, embarque=?, hospedagem=? WHERE codigo = ?";
	final String BUSCAR = "SELECT nomeviagem, dataida, datavolta, embarque, hospedagem, ativo FROM cliente WHERE CODIGO = ?";
	final String APAGAR = "UPDATE viagem SET ativo = 'N' WHERE codigo = ?";
	
	final String BUSCAR_VIAGENS = "SELECT * FROM viagem WHERE ativo = 'S' ";
	
	private Viagem viagem;
	private static ViagensService instance;
	
	public static ViagensService getInstance(){
		if (instance == null) {
		instance = new ViagensDBService();
		}
		return instance;
	}
	
	@Override
	public Viagem getViagem() {

		return this.viagem;
	}

	@Override
	public void setViagem(Viagem viagem) {

		this.viagem = viagem;
	}
	
	@Override
	public void salvar(Viagem viagem) {
		try {
			Connection con = conexao();
			PreparedStatement salvar = con.prepareStatement(INSERIR);
			salvar.setString(1, viagem.getNome());
			salvar.setDate(2, java.sql.Date.valueOf(viagem.getDataIda()));
			salvar.setDate(3, java.sql.Date.valueOf(viagem.getDataVolta()));
			salvar.setString(4, viagem.getEmbarque());
			salvar.setString(5, viagem.getHospedagem());
			salvar.executeUpdate();
			salvar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO CADASTRAR VIAGEM");
			System.exit(0);
		} 
	}

	@Override
	public List<Viagem> buscarViagens(Viagem viagem) {
		List<Viagem> viagens = new ArrayList<>();
		try {
			Connection con = conexao();
			String sql = BUSCAR_VIAGENS;
			if (!SgatUtills.isNullOrEmpty((viagem.getNome()))){
				sql += " and nomeviagem LIKE :nomeviagem";
			}
			if (viagem.getDataIda()!= null){
				sql += " and dataida = :dataida";
			}
			if (viagem.getDataVolta()!= null){
				sql += " and datavolta = :datavolta";
			}
			if (!SgatUtills.isNullOrEmpty((viagem.getEmbarque()))){
				sql += " and embarque LIKE :embarque";
			}
			if (!SgatUtills.isNullOrEmpty((viagem.getHospedagem()))){
				sql += " and hospedagem LIKE :hospedagem";
			}
			System.out.println("SQL = " + sql);
			NamedParameterStatement buscarViagens = new NamedParameterStatement(con, sql);
			if (!SgatUtills.isNullOrEmpty((viagem.getNome()))){
				buscarViagens.setString("nomeviagem", "%" + viagem.getNome() + "%");
			}
			if (viagem.getDataIda()!= null){
				Timestamp timestamp = Timestamp.valueOf(viagem.getDataIda().atStartOfDay());
				buscarViagens.setTimestamp("dataida", timestamp);
			}
			if (viagem.getDataVolta()!= null){
				Timestamp timestamp = Timestamp.valueOf(viagem.getDataVolta().atStartOfDay());
				buscarViagens.setTimestamp("datavolta", timestamp);
			}
			if (!SgatUtills.isNullOrEmpty((viagem.getEmbarque()))){
				buscarViagens.setString("embarque", "%" + viagem.getEmbarque() + "%");
			}
			if (!SgatUtills.isNullOrEmpty((viagem.getHospedagem()))){
				buscarViagens.setString("hospedagem", "%" + viagem.getHospedagem() + "%");
			}
			System.out.println("viagem = " + viagem);
			ResultSet resultadoBusca = buscarViagens.executeQuery();
			while (resultadoBusca.next()) {
				Viagem viagemResultado = extraiViagem(resultadoBusca);
				viagens.add(viagemResultado);
			}
			buscarViagens.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO BUSCAR VIAGENS ESPECIFICAS.");
			System.exit(0);
		} 
		return viagens;
	}
	
	@Override
	public Viagem buscaPorCodigo(int codigo) {
		Viagem viagem = null;
		try {
			Connection con = conexao();
			PreparedStatement buscar = con.prepareStatement(BUSCAR);
			buscar.setInt(1, codigo);
			ResultSet resultadoBusca = buscar.executeQuery();
			resultadoBusca.next();
			viagem = extraiViagem(resultadoBusca);
			buscar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO BUSCAR VIAGEM COM CODIGO " + codigo);
			System.exit(0);
		} 
		return viagem;
	}
	
	@Override
	public void apagar(Viagem viagem) {
		try {
			Connection con = conexao();
			PreparedStatement apagar = con.prepareStatement(APAGAR);
			apagar.setInt(1, viagem.getCodigo());
			apagar.executeUpdate();
			apagar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO APAGAR VIAGEM COM CODIGO" + viagem.getCodigo());
			System.exit(0);
		} 
	}
	
	@Override
	public void atualizar(Viagem viagem) {
		try {
			Connection con = conexao();
			PreparedStatement atualizar = con.prepareStatement(ATUALIZAR);
			atualizar.setString(1, viagem.getNome());
			atualizar.setDate(2, java.sql.Date.valueOf(viagem.getDataIda()));
			atualizar.setDate(3, java.sql.Date.valueOf(viagem.getDataVolta()));
			atualizar.setString(4, viagem.getEmbarque());
			atualizar.setString(5, viagem.getHospedagem());
			atualizar.setInt(6, viagem.getCodigo());
			atualizar.executeUpdate();
			atualizar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO ATUALIZAR VIAGEM COM CODIGO " + viagem.getCodigo());
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

	// extrai o objeto Viagem do result set
	private Viagem extraiViagem(ResultSet resultadoBusca) throws SQLException, ParseException {
		Viagem viagem = new Viagem();
		viagem.setCodigo(resultadoBusca.getInt(1));
		viagem.setNome(resultadoBusca.getString(2));
		LocalDate dataIda = resultadoBusca.getDate(3).toLocalDate();
		viagem.setDataIda(dataIda);
		LocalDate dataVolta = resultadoBusca.getDate(4).toLocalDate();
		viagem.setDataVolta(dataVolta);
		viagem.setEmbarque(resultadoBusca.getString(5));
		viagem.setHospedagem(resultadoBusca.getString(6));
		viagem.setAtivo(resultadoBusca.getString(7));
		return viagem;
	}

}
