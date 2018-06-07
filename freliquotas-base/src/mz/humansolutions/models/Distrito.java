package mz.humansolutions.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Distrito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable =false, unique=true)
	private String nome;
	
	@OneToMany(mappedBy="distrito")
	private List<User> users = new ArrayList<>();
	
	@OneToMany(mappedBy="distrito")
	private List<Membro> membros = new ArrayList<>();

	@Column(nullable=false, columnDefinition="bit")
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
	
	public List<Membro> getMembros() {
		return membros;
	}

	public void setMembros(List<Membro> membros) {
		this.membros = membros;
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
