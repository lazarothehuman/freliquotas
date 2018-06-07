package mz.humansolutions.models.dao;

import java.util.List;

import mz.humansolutions.models.Distrito;

public interface DistritoDao {

	void create(Distrito distrito);

	Distrito find(Long id, String nome);

	void update(Distrito distrito);

	List<Distrito> find(Boolean active);

	List<Distrito> findDistritos(String nome, Boolean active);

}
