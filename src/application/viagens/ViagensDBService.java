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

import application.clientes.ClientesDBService;
import application.clientes.ClientesService;
import framework.NamedParameterStatement;
import framework.SgatUtills;
import sgat.entidades.Cliente;
import sgat.entidades.Passageiro;
import sgat.entidades.Viagem;

public class ViagensDBService implements ViagensService{
	
	final String INSERIR = "INSERT INTO viagem(nomeviagem, dataida, datavolta, embarque, hospedagem) VALUES(?, ?, ?, ?, ?)";
	final String ATUALIZAR = "UPDATE viagem SET nomeviagem=?, dataida=?, datavolta=?, embarque=?, hospedagem=? WHERE codigo = ?";
	final String BUSCAR = "SELECT codigo, nomeviagem, dataida, datavolta, embarque, hospedagem, ativo FROM viagem WHERE codigo = ?";
	final String APAGAR = "UPDATE viagem SET ativo = 'N' WHERE codigo = ?";
	
	final String BUSCAR_VIAGENS = "SELECT * FROM viagem WHERE ativo = 'S' ";
	
	final String INSERIR_PASSAGEIROS = "INSERT INTO participanteviagem(codigoviagem, codigocliente, observacaoonibus, observacaohotel, valorvenda, grupo) VALUES(?, ?, ?, ?, ?, ?)";
	
	final String BUSCAR_PASSAGEIROS = "SELECT * FROM participanteviagem WHERE codigoviagem = ? ";
	
	final String APAGAR_PASSAGEIROS = "DELETE FROM participanteviagem WHERE codigo = ?";
	
	final String ATUALIZAR_PASSAGEIROS = "UPDATE participanteviagem SET observacaoonibus=?, observacaohotel=?, valorvenda=?, grupo=? WHERE codigo = ?";
	
	private Viagem viagem;
	private static ViagensService instance;
	
	private ClientesService clientesService;
	
	private ViagensDBService(){
		clientesService = ClientesDBService.getInstance();
	}
	
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

	private void salvarPassageiro(Passageiro passageiro, Viagem viagem) {
		try {
			Connection con = conexao();
			PreparedStatement salvar = con.prepareStatement(INSERIR_PASSAGEIROS);
			salvar.setInt(1, viagem.getCodigo());
			salvar.setInt(2, passageiro.getCliente().getCodigo());
			salvar.setString(3, passageiro.getObservacaoOnibus());
			salvar.setString(4, passageiro.getObservacaoHotel());
			salvar.setDouble(5, passageiro.getValor());
			salvar.setInt(6, passageiro.getGrupo());
			salvar.executeUpdate();
			salvar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO CADASTRAR PASSAGEIRO");
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
				List<Passageiro> passageirosViagem = buscarPassageiros(viagemResultado.getCodigo());
				viagemResultado.getPassageiros().addAll(passageirosViagem);
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
	
	private List<Passageiro> buscarPassageiros(int codigoViagem){
		List<Passageiro> passageiros = new ArrayList<>();
		try {
			Connection con = conexao();
			PreparedStatement buscarPassageiros = con.prepareStatement(BUSCAR_PASSAGEIROS);
			System.out.println("Codigo da viagem = " + codigoViagem);
			buscarPassageiros.setInt(1, codigoViagem);
			ResultSet resultadoBusca = buscarPassageiros.executeQuery();
			while (resultadoBusca.next()) {
				Passageiro passageiroResultado = extraiPassageiro(resultadoBusca);
				passageiros.add(passageiroResultado);
			}
			buscarPassageiros.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO BUSCAR PASSAGEIROS ESPECIFICOS.");
			System.exit(0);
		} 
		return passageiros;
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
			List<Passageiro> passageirosViagem = buscarPassageiros(viagem.getCodigo());
			viagem.getPassageiros().addAll(passageirosViagem);
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

	private void apagarPassageiro(Passageiro passageiro) {
		try {
			Connection con = conexao();
			PreparedStatement apagarPassageiro = con.prepareStatement(APAGAR_PASSAGEIROS);
			apagarPassageiro.setInt(1, passageiro.getCodigo());
			apagarPassageiro.executeUpdate();
			apagarPassageiro.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO APAGAR PASSAGEIRO COM CODIGO" + passageiro.getCodigo());
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
//			for (Passageiro passageiro : viagem.getPassageiros()){
//				salvarPassageiro(passageiro, viagem);
//			}
			persistirPassageiros(viagem);
			atualizar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO ATUALIZAR VIAGEM COM CODIGO " + viagem.getCodigo());
			System.exit(0);
		} 
	}
	
	private void atualizarPassageiro(Passageiro passageiro) {
		try {
			Connection con = conexao();
			PreparedStatement atualizarPassageiro = con.prepareStatement(ATUALIZAR_PASSAGEIROS);
			atualizarPassageiro.setString(1, passageiro.getObservacaoOnibus());
			atualizarPassageiro.setString(2, passageiro.getObservacaoHotel());
			atualizarPassageiro.setDouble(3, passageiro.getValor());
			atualizarPassageiro.setInt(4, passageiro.getGrupo());
			atualizarPassageiro.setInt(5, passageiro.getCodigo());
			atualizarPassageiro.executeUpdate();
			atualizarPassageiro.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO ATUALIZAR PASSAGEIRO COM CODIGO " + passageiro.getCodigo());
			System.exit(0);
		} 
	}
	/*
	 * Metodo compara os valores que estão na tela com os valores que estão no banco.
	 * se houver diferença entre esses valores true.
	 */
	private boolean comparaPassageiros(Passageiro passageiroTela, Passageiro passageiroBanco){
		if (passageiroTela == null || passageiroBanco == null ){
			return false;
		}
		if (passageiroTela.getCliente() == null || passageiroBanco.getCliente() == null ){
			return false;
		} else {
			if (!passageiroTela.getCliente().getCodigo().equals(passageiroBanco.getCliente().getCodigo())){
				return true;
			}
		}
		if (!passageiroTela.getObservacaoOnibus().equals(passageiroBanco.getObservacaoOnibus())){
			return true;
		}
		if (!passageiroTela.getObservacaoHotel().equals(passageiroBanco.getObservacaoHotel())){
			return true;
		}
		if (!passageiroTela.getValor().equals(passageiroBanco.getValor())){
			return true;
		}
		if (!passageiroTela.getGrupo().equals(passageiroBanco.getGrupo())){
			return true;
		}
		return false;
	}
	
	private void persistirPassageiros(Viagem viagem){
		Viagem viagemRetorna = buscaPorCodigo(viagem.getCodigo());
		
		for (Passageiro passageiro : viagem.getPassageiros()){
			if (viagemRetorna.getPassageiros().contains(passageiro)){
				Passageiro passageiroBanco = viagemRetorna.getPassageiros().get(viagemRetorna.getPassageiros().indexOf(passageiro));
				boolean passageiroComAlteracao = comparaPassageiros(passageiro, passageiroBanco);
				if (passageiroComAlteracao){
					atualizarPassageiro(passageiro);
				}
				System.out.println("Está na tela e no banco então não faz nada");
			} else {
				salvarPassageiro(passageiro, viagem);
				System.out.println("Incluir pois não está no banco");
			}
		}
		
		for (Passageiro passageiro : viagemRetorna.getPassageiros()){
			if (viagem.getPassageiros().contains(passageiro)){
				System.out.println("Está na tela e no banco então não faz nada - Segunda validação");
			} else {
				apagarPassageiro(passageiro);
				System.out.println("Exclui pois está no banco e não na tela");
			}
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
	
	// extrai o objeto Passageiro do result set
	public Passageiro extraiPassageiro(ResultSet resultadoBusca) throws SQLException, ParseException {
		Passageiro passageiro = new Passageiro();
		passageiro.setCodigo(resultadoBusca.getInt(1));
//		passageiro.setCliente(Cliente).setCodigo(resultadoBusca.getInt(2));
		int codigoCliente = resultadoBusca.getInt(3);
		Cliente cliente = clientesService.buscaPorCodigo(codigoCliente);
		passageiro.setCliente(cliente);
		passageiro.setObservacaoOnibus(resultadoBusca.getString(4));
		passageiro.setObservacaoHotel(resultadoBusca.getString(5));
		passageiro.setValor(resultadoBusca.getDouble(6));
		passageiro.setGrupo(resultadoBusca.getInt(7));
		return passageiro;
	}

}
