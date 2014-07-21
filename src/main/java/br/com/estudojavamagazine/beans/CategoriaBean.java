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
		if (ObjectUtil.isNotNull(getCodigo())) {
			this.categoria = categoriaService.findCategoria(Long
					.parseLong(getCodigo()));
		} else if (ObjectUtil.isNotNull(this.categoria)
				&& ObjectUtil.isNotNull(this.categoria.getCodigo())) {
			this.categoria = categoriaService.findCategoria(Long
					.parseLong(getCodigo()));
		}
	}

	public void editInstance() {
		loadInstance();
		setModoTela(ModoTela.Editar);
	}

	public void excluirInstance() {
		if (ObjectUtil.isNotNull(getCodigo())) {
			try {
				categoriaService.removerCategoria(Long.parseLong(getCodigo()));
				messageInfo("Categoria removida com sucesso!");
			} catch (NumberFormatException e) {
				messageErro("Erro ao obter código -- " + this.getCodigo());
			} catch (CategoriaException e) {
				messageErro(e.getMessage());
			}
		}
		setModoTela(ModoTela.Excluir);
	}

	public String persist() {
		try {
			categoria = categoriaService.saveOrUpdate(categoria);
		} catch (CategoriaException e) {
			e.printStackTrace();
			messageErro(e.getMessage());
		}
		messageInfo("Categoria salva com sucesso!");
		setCodigo(categoria.getCodigo().toString());
		setModoTela(ModoTela.Exibir);
		return "pretty:url-exibir-categoria";
	}

	// ==== GETTERS AND SETTERS ====
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
