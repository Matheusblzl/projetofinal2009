package br.com.estudojavamagazine.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import br.com.estudojavamagazine.domain.Produto;

@ManagedBean
@RequestScoped
@Controller("produtoBean")
@Component
public class ProdutoBean extends BaseBean {

    private static final long serialVersionUID = 1L;

    public ProdutoBean() {
    }
    
    public List<Produto> listar() {
        return new ArrayList<Produto>();
    }

}
