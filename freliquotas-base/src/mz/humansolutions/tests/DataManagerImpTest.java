package mz.humansolutions.tests;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Distrito;
import mz.humansolutions.models.Membro;
import mz.humansolutions.models.Mes;
import mz.humansolutions.models.Pagamento;
import mz.humansolutions.models.Profile;
import mz.humansolutions.models.TipoPagamento;
import mz.humansolutions.models.Transaccao;
import mz.humansolutions.models.User;

public class DataManagerImpTest {
	
	DataManager dataManager = new DataManagerImp();

	@Test
	public void testCreateProfile() {//runned
		Profile profile = new Profile();
		profile.setProfilename("Administrador informatico");
		dataManager.createProfile(profile);
		Assert.assertNotNull(profile.getId());
	}
	
	@Test
	public void testFindProfileById() {
		Profile profile = dataManager.findProfile(1l);
		Assert.assertNotNull(profile);
		Assert.assertEquals("Administrador informatico", profile.getProfilename());
	}
	
	@Test
	public void testCreateDistrito() {//done
		
		Distrito distrito = new Distrito();
		distrito.setNome("Secretariado da matola");
		distrito.setActive(false);
		dataManager.createDistrito(distrito);
		Assert.assertNotNull(distrito.getId());
	}
	
	@Test
	public void testFindDistrito() {// works
		Distrito distrito = dataManager.findDistrito(null, "Secretariado da cidade");
		Assert.assertNotNull(distrito);
		Assert.assertEquals("Secretariado da cidade", distrito.getNome());
	}
	
	
	@Test
	public void testCreateUser() {// done
		
		Profile profile = dataManager.findProfile("Administrador informatico");
		Distrito distrito = dataManager.findDistrito(null, "Secretariado da cidade");
		User user = new User();
		user.setUsername("lazaro");
		user.setName("Lazaro Mathe Junior");
		user.setProfile(profile);
		user.setDistrito(distrito);
		user.setPassword("1234");
		profile.addUser(user);
		
		try {
			dataManager.createUser(user);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(user.getId());
	}
	
	@Test
	public void testCreateTransaction() {
		Transaccao transaction = new Transaccao();
		transaction.setCode(201l);
		transaction.setUrl("/application/forms/Add-Membro.fxml");
		dataManager.createTransaction(transaction);
		Assert.assertNotNull(transaction.getId());
	}
	
	@Test
	public void testFindTransactionByCode() {
		Transaccao transaccao = dataManager.findTransaccao(201l);
		Assert.assertNotNull(transaccao);
		Assert.assertEquals("/application/forms/Add-Membro.fxml", transaccao.getUrl());
	}
	
	@Test
	public void testCreateTransactionProfile() {//failed
		Profile profile = dataManager.findProfile(1l);
		Transaccao transaccao = dataManager.findTransaccao(201l);
		profile.getTransaccoes().add(transaccao);
		transaccao.addProfile(profile);
		dataManager.updateProfile(profile);
		dataManager.updateTransaccao(transaccao);
		Assert.assertEquals(2, profile.getTransaccoes().size());
		Assert.assertEquals(2, transaccao.getProfiles().size());
	}
	
	@Test
	public void testCreateMembro() {//works
		Distrito distrito = dataManager.findDistrito(1l, null);
		Membro membro = new Membro();
		membro.setNome("Maria Luisa Sales Lucas");
		membro.setTelefone("825004957");
		membro.setDistrito(distrito);
		distrito.getMembros().add(membro);
		dataManager.createMembro(membro);
		dataManager.updateDistrito(distrito);
		Assert.assertNotNull(membro.getId());
		Assert.assertEquals(1, distrito.getMembros().size());
	}
	
	@Test
	public void testFindActiveDistritos() {// works
		List<Distrito> distritos = dataManager.findDistritos(Boolean.FALSE);
		Assert.assertNotNull(distritos);
		Assert.assertEquals(1, distritos.size());
	}
	
	@Test
	public void testCreatePagamento() {// works
		Membro membro = dataManager.findMembros(Boolean.TRUE).get(0);
		Pagamento pagamento = new Pagamento();
		pagamento.setMembro(membro);
		pagamento.setMes(Mes.Maio);
		pagamento.setTipoPagamento(TipoPagamento.Quota);
		pagamento.setValor(2500d);
		
		
		membro.getPagamentos().add(pagamento);
		dataManager.updateMembro(membro);
		Assert.assertNotNull(membro.getPagamentos().get(0));
		Assert.assertEquals(1, membro.getPagamentos().size());
	}
	
	@Test
	public void testFindDistritos() {// works
		List<Distrito> distritos = dataManager.findDistritos("Secretariado da cidade", Boolean.TRUE);
		Assert.assertNotNull(distritos);
		Assert.assertEquals(1, distritos.size());
	}
	
	
	@Test
	public void testFindMembroWithMultipleParameters() {
		Distrito distrito = dataManager.findDistrito(null, "Secretariado da cidade");
		List<Membro> membros = dataManager.findMembros(null, null, null, distrito, null);
		Assert.assertNotNull(membros);
		Assert.assertEquals(4, membros.size());
	}
	

}
