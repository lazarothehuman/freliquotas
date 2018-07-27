package mz.humansolutions.managers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;

import mz.humansolutions.models.Distrito;
import mz.humansolutions.models.Membro;
import mz.humansolutions.models.Profile;
import mz.humansolutions.models.Transaccao;
import mz.humansolutions.models.User;
import mz.humansolutions.models.dao.DistritoDao;
import mz.humansolutions.models.dao.MembroDao;
import mz.humansolutions.models.dao.ProfileDao;
import mz.humansolutions.models.dao.SessionHelperDao;
import mz.humansolutions.models.dao.TransaccaoDao;
import mz.humansolutions.models.dao.UserDao;
import mz.humansolutions.models.dao.jpa.DistritoJpaDao;
import mz.humansolutions.models.dao.jpa.MembroJpaDao;
import mz.humansolutions.models.dao.jpa.ProfileJpaDao;
import mz.humansolutions.models.dao.jpa.SessionHelperJpaDao;
import mz.humansolutions.models.dao.jpa.TransaccaoJpaDao;
import mz.humansolutions.models.dao.jpa.UserJpaDao;
import mz.humansolutions.utils.ProtectedConfigFile;

public class DataManagerImp implements DataManager {

	ProfileDao profileDao = new ProfileJpaDao();
	UserDao userDao = new UserJpaDao();
	DistritoDao distritoDao = new DistritoJpaDao();
	SessionHelperDao sessionHelperDao = new SessionHelperJpaDao();
	TransaccaoDao transaccaoDao = new TransaccaoJpaDao();
	MembroDao membroDao = new MembroJpaDao();

	@Override
	public void createUser(User user) throws UnsupportedEncodingException, GeneralSecurityException {
		if (user != null) {
			user.setPassword(encryptPassword(user.getPassword()));
			userDao.create(user);
		}
	}

	@Override
	public User findUser(String username) {
		return userDao.find(username);
	}

	@Override
	public List<User> findAllUsers(Boolean active) {
		return userDao.findAll(active);
	}

	@Override
	public boolean checkPassword(String password, String pass) throws GeneralSecurityException, IOException {
		String p = ProtectedConfigFile.decrypt(password);
		if (p.equals(pass))
			return true;
		else
			return false;
	}

	@Override
	public String encryptPassword(String password) throws UnsupportedEncodingException, GeneralSecurityException {
		return ProtectedConfigFile.encrypt(password);
	}

	@Override
	public User findUser(Long id) {
		return userDao.find(id);
	}

	@Override
	public void setSelectedUser(User user) {
		if (user != null)
			sessionHelperDao.set(user);

	}

	@Override
	public User findCurrentUser() {
		return sessionHelperDao.get();
	}

	@Override
	public void updateUser(User user) throws UnsupportedEncodingException, GeneralSecurityException {
		if (user != null) {
			if (user.getPassword().length() != 128)
				user.setPassword(encryptPassword(user.getPassword()));
			userDao.update(user);
		}

	}

	@Override
	public Profile findProfile(String profilename) {
		return profileDao.findByName(profilename);
	}

	@Override
	public List<Profile> findProfiles(Boolean active) {
		return profileDao.find(Boolean.TRUE);
	}

	@Override
	public void createProfile(Profile profile) {
		if (profile != null) {
			profileDao.create(profile);
		}

	}

	@Override
	public Profile findProfile(Long id) {
		return profileDao.find(id);
	}

	@Override
	public void createDistrito(Distrito distrito) {
		if (distrito != null)
			distritoDao.create(distrito);

	}

	@Override
	public Distrito findDistrito(Long id, String nome) {
		return distritoDao.find(id, nome);
	}

	@Override
	public Transaccao findTransaccao(Long code) {
		return transaccaoDao.find(code);
	}

	@Override
	public void createTransaction(Transaccao transaction) {
		if (transaction != null) {
			transaccaoDao.create(transaction);
		}

	}

	@Override
	public void updateProfile(Profile profile) {
		if (profile != null)
			profileDao.update(profile);

	}

	@Override
	public void updateTransaccao(Transaccao transaccao) {
		transaccaoDao.update(transaccao);

	}

	@Override
	public void createMembro(Membro membro) {
		if (membro != null) {
			if (!membro.getTelefone().contains("+258"))
				membro.setTelefone("+258" + membro.getTelefone());
			membroDao.create(membro);
		}
	}

	@Override
	public void updateDistrito(Distrito distrito) {
		if (distrito != null)
			distritoDao.update(distrito);

	}

	@Override
	public List<Distrito> findDistritos(Boolean active) {
		return distritoDao.find(active);
	}

	@Override
	public void updateMembro(Membro membro) {
		if (membro != null)
			membroDao.update(membro);

	}

	@Override
	public List<Membro> findMembros(Boolean active) {
		return membroDao.find(active);
	}

	@Override
	public List<Distrito> findDistritos(String nome, Boolean active) {
		return distritoDao.findDistritos(nome, active);
	}

	@Override
	public List<Membro> findMembros(String nome, String telefone, String bi, Distrito distrito, Boolean active, Boolean paidAllYear) {
		return membroDao.find(nome, telefone, bi, distrito, active, paidAllYear);
	}

	@Override
	public List<User> findUsers(String username, String nome, Profile profile, Boolean active, Distrito distrito) {
		return userDao.find(username, nome, profile, active, distrito);
	}

	@Override
	public Membro findMembro(Long id, String nome) {
		return membroDao.find(id, nome);
	}

}
