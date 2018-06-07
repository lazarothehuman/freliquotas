package mz.humansolutions.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Pagamento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private Double valor;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Mes mes;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoPagamento tipoPagamento;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Membro membro;
	
	@Column(nullable = false, columnDefinition="bit")
	private Boolean active = true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Mes getMes() {
		return mes;
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	

}
