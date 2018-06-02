public class Aparelhos {
	private String comodo;
	private String aparelho;
	private Integer potencia;
	private Integer quantidade;
	private float horas;
	private float total;
	private float mensal;
	
	public void setComodo(String _comodo) {
		this.comodo = _comodo;
	}
	
	public void setAparelho(String _aparelho) {
		this.aparelho = _aparelho;
	}
	
	public void setPotencia(Integer _potencia){
		if (_potencia < 0) {
			this.potencia = Math.abs(_potencia);
		} 
		if (_potencia >= 0) {
			this.potencia = _potencia;			
		}
	}

	public void setQuantidade(Integer _quantidade) {
		if (_quantidade < 0) {
			this.quantidade = Math.abs(_quantidade);
		} 
		if (_quantidade >= 0) {
			this.quantidade = _quantidade;			
		}
	}
	
	public void setHoras(Float _horas) {
		if (_horas < 0) {
			this.horas = Math.abs(_horas);
		} 
		if (_horas >= 0) {
			this.horas = _horas;			
		}
		if (_horas > 24) {
			this.horas = 24;
		}
	}
	
	public void setTotal(Integer _potencia, Integer _quantidade, float _horas) {
		if (_horas < 0) {
			_horas = Math.abs(_horas);
		} 

		if (_horas > 24) {
			_horas = 24;
		}
		if (_potencia < 0) {
			_potencia = Math.abs(_potencia);
		}
		
		this.total = (((_potencia * _horas * 30)/1000) * _quantidade);
	}
	
	public void setMensal(Float _total, Float _valor) {
		this.mensal = (_total * _valor);
	}
	

	
	public String getComodo() {
		return comodo;
	}
	public String getAparelho() {
		return aparelho;
	}
	public Integer getPotencia() {
		return potencia;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public Float getHoras() {
		return horas;
	}
	public Float getTotal() {
		return total;
	}
	public Float getMensal() {
		return mensal;
	}
	
}
