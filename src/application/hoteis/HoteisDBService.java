package application.hoteis;

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
import sgat.entidades.Hotel;

public class HoteisDBService implements HoteisService{
	
	final String INSERIR = "INSERT INTO hotel(nomehotel, cnpj, valorduploporpessoa, valortriploporpessoa, valorquadruploporpessoa, enderecohotel, cidadehotel, telefonehotel) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	final String ATUALIZAR = "UPDATE hotel SET nomehotel=?, cnpj=?, valorduploporpessoa=?, valortriploporpessoa=?, valorquadruploporpessoa=?, enderecohotel=?, cidadehotel=?, telefonehotel=? WHERE codigo = ?";
	final String BUSCAR = "SELECT codigo, nomehotel, cnpj, valorduploporpessoa, valortriploporpessoa, valorquadruploporpessoa, enderecohotel, cidadehotel, telefonehotel, ativo FROM hotel WHERE codigo = ?";
	final String APAGAR = "UPDATE hotel SET ativo = 'N' WHERE codigo = ?";
	
	final String BUSCAR_HOTEIS = "SELECT * FROM hotel WHERE ativo = 'S' ";
	
	private Hotel hotel;
	private Hotel hotelSelecionado;
	private static HoteisService instance;
	
//	@Override
	public static HoteisService getInstance(){
		if (instance == null) {
		instance = new HoteisDBService();
		}
		return instance;
	}
	
	@Override
	public Hotel getHotel() {

		return this.hotel;
	}

	@Override
	public void setHotel(Hotel hotel) {

		this.hotel = hotel;
	}
	
	@Override
	public Hotel getHotelSelecionado() {

		return this.hotelSelecionado;
	}

	@Override
	public void setHotelSelecionado(Hotel hotelSelecionado) {

		this.hotelSelecionado = hotelSelecionado;
	}
	
	@Override
	public void salvar(Hotel hotel){
		try {
			Connection con = conexao();
			PreparedStatement salvar = con.prepareStatement(INSERIR);
			salvar.setString(1, hotel.getNome());
			salvar.setString(2, hotel.getCnpj());
			salvar.setDouble(3, hotel.getValorDuploPorPessoa());
			salvar.setDouble(4, hotel.getValorTriploPorPessoa());
			salvar.setDouble(5, hotel.getValorQuadruploPorPessoa());
			salvar.setString(6, hotel.getEndereco());
			salvar.setString(7, hotel.getCidade());
			salvar.setString(8, hotel.getTelefone());
			salvar.executeUpdate();
			salvar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO CADASTRAR HOTEL");
			System.exit(0);
		}
	}
	
	@Override
	public List<Hotel> buscarHoteis(Hotel hotel) {
		List<Hotel> hoteis = new ArrayList<>();
		try {
			Connection con = conexao();
			String sql = BUSCAR_HOTEIS;
			if (!SgatUtills.isNullOrEmpty((hotel.getNome()))){
				sql += " and nomehotel LIKE :nomehotel";
			}
			if (!SgatUtills.isNullOrEmpty((hotel.getCnpj()))){
				sql += " and cnpj = :cnpj";
			}
			if (hotel.getValorDuploPorPessoa()!= null){
				sql += " and valorduploporpessoa = :valorduploporpessoa";
			}
			if (hotel.getValorTriploPorPessoa()!= null){
				sql += " and valortriploporpessoa = :valortriploporpessoa";
			}
			if (hotel.getValorQuadruploPorPessoa()!= null){
				sql += " and valorquadruploporpessoa = :valorquadruploporpessoa";
			}
			if (!SgatUtills.isNullOrEmpty((hotel.getEndereco()))){
				sql += " and enderecohotel LIKE :enderecohotel";
			}
			if (!SgatUtills.isNullOrEmpty((hotel.getCidade()))){
				sql += " and cidadehotel LIKE :cidadehotel";
			}
			if (!SgatUtills.isNullOrEmpty((hotel.getTelefone()))){
				sql += " and telefonehotel LIKE :telefonehotel";
			}
			System.out.println("SQL = " + sql);
			NamedParameterStatement buscarHoteis = new NamedParameterStatement(con, sql);
			if (!SgatUtills.isNullOrEmpty((hotel.getNome()))){
				buscarHoteis.setString("nomehotel", "%" + hotel.getNome() + "%");
			}
			if (!SgatUtills.isNullOrEmpty((hotel.getCnpj()))){
				buscarHoteis.setString("cnpj", hotel.getCnpj());
			}
			if (hotel.getValorDuploPorPessoa()!= null){
				buscarHoteis.setDouble("valorduploporpessoa", hotel.getValorDuploPorPessoa());
			}
			if (hotel.getValorTriploPorPessoa()!= null){
				buscarHoteis.setDouble("valortriploporpessoa", hotel.getValorTriploPorPessoa());
			}
			if (hotel.getValorQuadruploPorPessoa()!= null){
				buscarHoteis.setDouble("valorquadruploporpessoa", hotel.getValorQuadruploPorPessoa());
			}
			if (!SgatUtills.isNullOrEmpty((hotel.getEndereco()))){
				buscarHoteis.setString("enderecohotel", "%" + hotel.getEndereco() + "%");
			}
			if (!SgatUtills.isNullOrEmpty((hotel.getCidade()))){
				buscarHoteis.setString("cidadehotel", "%" + hotel.getCidade() + "%");
			}
			if (!SgatUtills.isNullOrEmpty((hotel.getTelefone()))){
				buscarHoteis.setString("telefonehotel", "%" + hotel.getTelefone() + "%");
			}
			System.out.println("hotel = " + hotel);
			ResultSet resultadoBusca = buscarHoteis.executeQuery();
			while (resultadoBusca.next()) {
				Hotel hotelResultado = extraiHotel(resultadoBusca);
				hoteis.add(hotelResultado);
			}
			buscarHoteis.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO BUSCAR HOTEIS ESPECIFICOS.");
			System.exit(0);
		} 
		return hoteis;
	}
	
	@Override
	public Hotel buscaPorCodigo(int codigo) {
		Hotel hotel = null;
		try {
			Connection con = conexao();
			PreparedStatement buscar = con.prepareStatement(BUSCAR);
			buscar.setInt(1, codigo);
			ResultSet resultadoBusca = buscar.executeQuery();
			if (resultadoBusca.next()){
				hotel = extraiHotel(resultadoBusca);
			}
			buscar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO BUSCAR HOTEL COM CODIGO " + codigo);
			System.exit(0);
		} 
		return hotel;
	}
	
	@Override
	public void apagar(Hotel hotel) {
		try {
			Connection con = conexao();
			PreparedStatement apagar = con.prepareStatement(APAGAR);
			apagar.setInt(1, hotel.getCodigo());
			apagar.executeUpdate();
			apagar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO APAGAR HOTEL COM CODIGO ");
			System.exit(0);
		} 
	}
	
	@Override
	public void atualizar(Hotel hotel) {
		try {
			Connection con = conexao();
			PreparedStatement atualizar = con.prepareStatement(ATUALIZAR);
			atualizar.setString(1, hotel.getNome());
			atualizar.setString(2, hotel.getCnpj());
			atualizar.setDouble(3, hotel.getValorDuploPorPessoa());
			atualizar.setDouble(4, hotel.getValorTriploPorPessoa());
			atualizar.setDouble(5, hotel.getValorQuadruploPorPessoa());
			atualizar.setString(6, hotel.getEndereco());
			atualizar.setString(7, hotel.getCidade());
			atualizar.setString(8, hotel.getTelefone());
			atualizar.setInt(9, hotel.getCodigo());
			atualizar.executeUpdate();
			atualizar.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR AO ATUALIZAR HOTEL COM CODIGO " + hotel.getCodigo());
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
	
	// extrai o objeto Hotel do result set
	private Hotel extraiHotel(ResultSet resultadoBusca) throws SQLException, ParseException {
		Hotel hotel = new Hotel();
		hotel.setCodigo(resultadoBusca.getInt(1));
		hotel.setNome(resultadoBusca.getString(2));
		hotel.setCnpj(resultadoBusca.getString(3));
		hotel.setValorDuploPorPessoa(resultadoBusca.getDouble(4));
		hotel.setValorTriploPorPessoa(resultadoBusca.getDouble(5));
		hotel.setValorQuadruploPorPessoa(resultadoBusca.getDouble(6));
		hotel.setEndereco(resultadoBusca.getString(7));
		hotel.setCidade(resultadoBusca.getString(8));
		hotel.setTelefone(resultadoBusca.getString(9));
		hotel.setAtivo(resultadoBusca.getString(10));
		return hotel;
	}

}
