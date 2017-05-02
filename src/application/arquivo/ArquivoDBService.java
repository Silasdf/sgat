package application.arquivo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import framework.NamedParameterStatement;
import sgat.entidades.Arquivo;

public class ArquivoDBService implements ArquivoService{
	
	final String INSERIR = "INSERT INTO arquivo(conteudoarquivo, nomearquivo, tipoarquivo) VALUES(?, ?, ?)";
	final String ATUALIZAR = "UPDATE arquivo SET conteudoarquivo=?, nomearquivo=?, tipoarquivo=? WHERE codigo = ?";
	final String BUSCAR = "SELECT codigo, conteudoarquivo, nomearquivo, tipoarquivo, ativo FROM arquivo WHERE codigo = ?";
	final String APAGAR = "UPDATE arquivo SET ativo = 'N' WHERE codigo = ?";
	
	final String BUSCAR_ARQUIVO = "SELECT * FROM arquivo WHERE ativo = 'S' ";
	
	private Arquivo arquivo;
	private static ArquivoService instance;
	
//	@Override
	public static ArquivoService getInstance(){
		if (instance == null) {
		instance = new ArquivoDBService();
		}
		return instance;
	}
	
	@Override
	public void salvar(Arquivo arquivo){
		try {
			Connection con = conexao();
			PreparedStatement salvar = con.prepareStatement(INSERIR);
			FileInputStream inputStream = new FileInputStream(arquivo.getConteudo());
			salvar.setBlob(1, inputStream);
			salvar.setString(2, arquivo.getNome());
			salvar.setString(3, arquivo.getTipo());
			salvar.executeUpdate();
			salvar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO CADASTRAR ARQUIVO");
			System.exit(0);
		}
	}
	
	@Override
	public List<Arquivo> buscarArquivo(Arquivo arquivo) {
		List<Arquivo> arquivos = new ArrayList<>();
		try {
			Connection con = conexao();
			String sql = BUSCAR_ARQUIVO;
			if ("doc".equals(arquivo.getTipo())){
				sql += " and tipoarquivo = 'doc'";
			}
//			if (arquivo.getConteudo() != null){
//				sql += " and conteudoarquivo = :conteudoarquivo";
//			}
//			if (!SgatUtills.isNullOrEmpty((arquivo.getNome()))){
//				sql += " and nomearquivo LIKE :nomearquivo";
//			}
//			if (!SgatUtills.isNullOrEmpty((arquivo.getTipo()))){
//				sql += " and tipoarquivo LIKE :tipoarquivo";
//			}
			System.out.println("SQL = " + sql);
			NamedParameterStatement buscarArquivo = new NamedParameterStatement(con, sql);
//			if (arquivo.getConteudo() != null){
//				FileInputStream inputStream = new FileInputStream(arquivo.getConteudo());
//				buscarArquivo.setBlob("conteudoarquivo", inputStream);
//			}
//			if (!SgatUtills.isNullOrEmpty((arquivo.getNome()))){
//				buscarArquivo.setString("nomearquivo", "%" + arquivo.getNome() + "%");
//			}
//			if (!SgatUtills.isNullOrEmpty((arquivo.getTipo()))){
//				buscarArquivo.setString("tipoarquivo", "%" + arquivo.getTipo() + "%");
//			}
			System.out.println("arquivo = " + arquivo);
			ResultSet resultadoBusca = buscarArquivo.executeQuery();
			while (resultadoBusca.next()) {
				Arquivo arquivoResultado = extraiArquivo(resultadoBusca);
				arquivos.add(arquivoResultado);
			}
			buscarArquivo.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO BUSCAR ARQUIVOS ESPECIFICOS.");
			System.exit(0);
		} 
		return arquivos;
	}
	
	@Override
	public Arquivo buscaPorCodigo(int codigo) {
		Arquivo arquivo = null;
		try {
			Connection con = conexao();
			PreparedStatement buscar = con.prepareStatement(BUSCAR);
			buscar.setInt(1, codigo);
			ResultSet resultadoBusca = buscar.executeQuery();
			resultadoBusca.next();
			arquivo = extraiArquivo(resultadoBusca);
			buscar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO BUSCAR ARQUIVO COM CODIGO " + codigo);
			System.exit(0);
		} 
		return arquivo;
	}
	
	@Override
	public void apagar(Arquivo arquivo) {
		try {
			Connection con = conexao();
			PreparedStatement apagar = con.prepareStatement(APAGAR);
			apagar.setInt(1, arquivo.getCodigo());
			apagar.executeUpdate();
			apagar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO APAGAR ARQUIVO COM CODIGO" + arquivo.getCodigo());
			System.exit(0);
		} 
	}
	
	@Override
	public void atualizar(Arquivo arquivo) {
		try {
			Connection con = conexao();
			PreparedStatement atualizar = con.prepareStatement(ATUALIZAR);
			FileInputStream inputStream = new FileInputStream(arquivo.getConteudo());
			atualizar.setBlob(1, inputStream);
			atualizar.setString(2, arquivo.getNome());
			atualizar.setString(3, arquivo.getTipo());
			atualizar.setInt(4, arquivo.getCodigo());
			atualizar.executeUpdate();
			atualizar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO ATUALIZAR ARQUIVO COM CODIGO " + arquivo.getCodigo());
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
	
	// extrai o objeto Arquivo do result set
	private Arquivo extraiArquivo(ResultSet resultadoBusca) throws SQLException, ParseException {
		Arquivo arquivo = new Arquivo();
		File file = new File("abc.doc");
		arquivo.setConteudo(file);
		arquivo.setCodigo(resultadoBusca.getInt(1));
		Blob blob = resultadoBusca.getBlob(2);
		InputStream in = blob.getBinaryStream();
		OutputStream out;
		try {
			out = new FileOutputStream(arquivo.getConteudo());
			byte[] buff = new byte[16777215];
			int len = 0;
			while ((len = in.read(buff)) != -1){
				out.write(buff, 0, len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		arquivo.setConteudo(resultadoBusca.getBlob(2));
		arquivo.setNome(resultadoBusca.getString(3));
		arquivo.setTipo(resultadoBusca.getString(4));
		arquivo.setAtivo(resultadoBusca.getString(5));
		return arquivo;
	}
	
	@Override
	public Arquivo getArquivo() {

		return this.arquivo;
	}

	@Override
	public void setArquivo(Arquivo arquivo) {

		this.arquivo = arquivo;
	}


}
