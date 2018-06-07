package mz.humansolutions.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Membro {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable =false)
	private String nome;
	
	@Column(nullable =false)
	private String telefone;
	
	@Column
	private String numeroCartao;
	
	@Column
	private String bi;
	
	@Column
	private String email;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Distrito distrito;
	
	@OneToMany(mappedBy="membro",cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	private List<Pagamento> pagamentos = new ArrayList<>();
	
	@Column(nullable =false, columnDefinition="bit")
	private Boolean active = true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getBi() {
		return bi;
	}

	public void setBi(String bi) {
		this.bi = bi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return this.nome;
	}
	
	
	
	

}
