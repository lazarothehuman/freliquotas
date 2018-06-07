package mz.humansolutions.models.dao;

import java.util.List;

import mz.humansolutions.models.Distrito;
import mz.humansolutions.models.Membro;

public interface MembroDao {

	void create(Membro membro);

	void update(Membro membro);

	List<Membro> find(Boolean active);

	List<Membro> find(String nome, String telefone, String bi, Distrito distrito, Boolean active);

	Membro find(Long id, String nome);

}
