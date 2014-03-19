package br.com.estudo.serviceImpl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;

import br.com.estudo.Facade;
import br.com.estudo.dao.PontoDao;
import br.com.estudo.dao.UsuarioDao;
import br.com.estudo.lang.DaoException;
import br.com.estudo.lang.FacadeException;
import br.com.estudo.lang.ValidatedLoginSenhaException;
import br.com.estudo.lang.ValidatedTokenException;
import br.com.estudo.modelo.Ponto;
import br.com.estudo.modelo.Usuario;

@Service("facade")
public class FacadeImpl implements Facade {

    private static final long serialVersionUID = 1L;
    
    private static final Log logger = LogFactory.getLog("FacadeImpl");

    @Autowired
    private UsuarioDao usuarioDao;
    
    @Autowired
    private PontoDao pontoDao;
    
    @Override
    public Usuario getUsuarioPorId(Long id) throws FacadeException {
    	try {
			return usuarioDao.searchById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			logger.error(e);
			throw new FacadeException(e);
		}
    }
    
    @Override
	public void salvarUsuario(Usuario usuario) throws FacadeException {
    	try {
			usuarioDao.save(usuario);
		} catch (DaoException e) {
			e.printStackTrace();
			logger.error(e);
			throw new FacadeException(e);
		}
    }
    
    @Override
	public Usuario getUsuarioByLogin(String login) throws FacadeException {
    	try {
			return usuarioDao.searchByLogin(login);
		} catch (DaoException e) {
			e.printStackTrace();
			logger.error(e);
			throw new FacadeException(e);
		}
    }
    
    @Override
	public String gerarToken(String... values) {
        if (values == null || values.length == 0) {
            logger.error("Parametros não podem ser nulos ou vazios!");
            throw new IllegalArgumentException("Parametros não podem ser nulos ou vazios!");
        }
        String keySource = null;
        
        for (String value : values) {
            keySource += value;
        }

        byte[] tokenByte = Base64.encode(keySource.getBytes());
        
        String token = new String(tokenByte);
        
        return token;
    }

    @Override
    public List<Ponto> getPontosUsuario(String login, String token) throws FacadeException, ValidatedLoginSenhaException, ValidatedTokenException {
        // Validar token e obter usuário
        Usuario usuario = null;
        try {
            usuario = getUsuarioByLogin(login);
            if (usuario == null) {
                throw new ValidatedLoginSenhaException("Login ou senha inválidos!");
            } else if (!usuario.getToken().equals(token)) {
                throw new ValidatedTokenException("Token inválidos!");
            }
            return getPontosUsuario(usuario);
        } catch (FacadeException e) {
            e.printStackTrace();
            logger.error(e);
            throw new FacadeException(e);
        }
    }
    
    @Override
    public List<Ponto> getPontosUsuario(Usuario usuario) throws FacadeException {
        try {
            return pontoDao.findAllPontosByIdUsuario(usuario);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new FacadeException(e);
        }
    }
    
//    public static void main(String[] args) {
//        FacadeImpl facadeImpl = new FacadeImpl();
//        System.out.println(facadeImpl.gerarToken("admin@pinngo.com","12/04/2014"));
//    }
}
