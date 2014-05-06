package br.com.estudojavamagazine.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.estudojavamagazine.domain.Categoria;

@ManagedBean
@RequestScoped
public class ProdutoBean extends BaseBean {

    private static final long serialVersionUID = 1L;

    public ProdutoBean() {
    }
    
    public List<Categoria> listar() {
        return new ArrayList<Categoria>();
    }

}
