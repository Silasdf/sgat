package sgat.entidades;

public class Passageiro {
	
	private String observacaoOnibus;
	private String observacaoHotel;
	private Double valor;
	private Integer grupo;
	private Integer codigo;
	private Cliente cliente;
	private Hotel hotel;

	public String getObservacaoOnibus() {
		return observacaoOnibus;
	}
	public void setObservacaoOnibus(String observacaoOnibus) {
		this.observacaoOnibus = observacaoOnibus;
	}
	public String getObservacaoHotel() {
		return observacaoHotel;
	}
	public void setObservacaoHotel(String observacaoHotel) {
		this.observacaoHotel = observacaoHotel;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Integer getGrupo() {
		return grupo;
	}
	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	@Override
	public String toString() {
		return "Passageiro [observacaoOnibus=" + observacaoOnibus + ", observacaoHotel=" + observacaoHotel + ", valor="
				+ valor + ", grupo=" + grupo + ", codigo=" + codigo + ", cliente=" + cliente + ", hotel=" + hotel + "]";
	}
	
	@Override
	public boolean equals(Object object){
		
		if (object != null && object instanceof Passageiro){
			if (this.codigo == ((Passageiro) object).codigo){
				return true;
			}
		}
		return false;
	}
	
}
