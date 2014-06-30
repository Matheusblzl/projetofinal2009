package br.com.estudojavamagazine.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.estudojavamagazine.domain.Categoria;
import br.com.estudojavamagazine.enumerate.ModoTela;
import br.com.estudojavamagazine.service.CategoriaService;
import br.com.estudojavamagazine.service.lang.CategoriaException;
import br.com.estudojavamagazine.service.util.ObjectUtil;

@ManagedBean
@RequestScoped
@Controller
public class CategoriaBean extends BaseBean {

    private static final long serialVersionUID = 1L;

    private String codigo;
    
    private ModoTela modoTela = ModoTela.Exibir;
    
    private Categoria categoria = new Categoria();
    
    @Autowired
    private CategoriaService categoriaService;
    
    public CategoriaBean() {
    }

    public List<Categoria> listar() {
    	return categoriaService.findAllCategorias();
    }
    
    public void createInstance() {
    	setModoTela(ModoTela.Inserir);
    	this.categoria = new Categoria();
    }
    
    public void loadInstance() {
    	setModoTela(ModoTela.Exibir);
    	if (ObjectUtil.isNotNull(codigo)) {
    		this.categoria = categoriaService.findCategoria(Long.parseLong(codigo));
    	} else if (ObjectUtil.isNotNull(this.categoria) && ObjectUtil.isNotNull(this.categoria.getCodigo())) {
    		this.categoria = categoriaService.findCategoria(Long.parseLong(codigo));
    	}
    }
    
    public void editInstance() {
    	loadInstance();
    	setModoTela(ModoTela.Editar);
    }
    
    public void excluirInstance() {
    	if (ObjectUtil.isNotNull(codigo)) {
    		try {
				categoriaService.removerCategoria(Long.parseLong(codigo));
				messageInfo("Categoria removida com sucesso!");
			} catch (NumberFormatException e) {
				messageErro("Erro ao obter código -- " + this.codigo);
			} catch (CategoriaException e) {
				messageErro(e.getMessage());
			}
    	}
    	setModoTela(ModoTela.Excluir);
    }
    
    public String persist() throws CategoriaException {
    	categoria = categoriaService.saveOrUpdate(categoria);
    	messageInfo("Categoria salva com sucesso!");
    	this.codigo = categoria.getCodigo().toString();
    	setModoTela(ModoTela.Exibir);
    	return "pretty:url-exibir-categoria";
    }
    
    // ==== GETTERS AND SETTERS ====
    
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public ModoTela getModoTela() {
		return modoTela;
	}

	public void setModoTela(ModoTela modoTela) {
		this.modoTela = modoTela;
	}

	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
