package br.com.estudo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import br.com.estudo.lang.FacadeException;
import br.com.estudo.lang.ValidatedLoginSenhaException;
import br.com.estudo.lang.ValidatedTokenException;
import br.com.estudo.modelo.Ponto;
import br.com.estudo.modelo.ReturnMessage;
import br.com.estudo.modelo.Usuario;

@Component
@Path("service")
public class ServiceResource implements Serializable {
    
    private static final long serialVersionUID = -6759058635753438873L;

    private static final Log logger = LogFactory.getLog("ServiceResource");

	@Inject
    private Facade facade;
    
	@GET()
	@Path("/teste")
	@Produces({MediaType.APPLICATION_JSON + ";charset=utf-8"})
	public String sayHello() {
	    logger.info("Teste serviço!");
	    return "Hello World!";
	}
	
	@GET()
    @Path("/login/{login}/{senha}")
    @Produces({MediaType.APPLICATION_JSON + ";charset=utf-8"})
    public ReturnMessage checkLogin(@PathParam("login") String login, @PathParam("senha") String senha) {
	    logger.info("Check de login e senha.");
		ReturnMessage returnMessage = null;
		
		Usuario usuario = null;
		
		try {
			usuario = facade.getUsuarioByLogin(login);

			if (usuario != null && login.equals(usuario.getLogin()) && senha.equals(usuario.getSenha())) {
				returnMessage = new ReturnMessage(true, "sucesso", facade.gerarToken(login,"12/04/2014"));
			} else {
				returnMessage = new ReturnMessage(false, "Login ou senha inválidos", "");
			}
		} catch (FacadeException e) {
			e.printStackTrace();
			returnMessage = new ReturnMessage(false, e.getMessage(), "");
		}
		
		return returnMessage;
    }
	
	@GET()
    @Path("/pontos/{login}/{token}")
    @Produces({MediaType.APPLICATION_JSON + ";charset=utf-8"})
    public List<Ponto> getPontosUsusario(@PathParam("login") String login, @PathParam("token") String token) {
	    logger.info("Obtendo pontos do usuário.");
	    List<Ponto> erroList = new ArrayList<Ponto>();
	    Ponto pontoErro = new Ponto();
	    pontoErro.setStatusEnvio(Boolean.FALSE);
	    
	    try {
            return facade.getPontosUsuario(login, token);
        } catch (FacadeException e) {
            e.printStackTrace();
            logger.error(e);
            pontoErro.setMsgEnvio(e.getMessage());
        } catch (ValidatedLoginSenhaException e) {
            logger.error(e);
            e.printStackTrace();
            pontoErro.setMsgEnvio(e.getMessage());
        } catch (ValidatedTokenException e) {
            logger.error(e);
            e.printStackTrace();
            pontoErro.setMsgEnvio(e.getMessage());
        }
	    
	    erroList.add(pontoErro);
	    
	    return erroList;
    }
}
