package br.com.estudojavamagazine.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ManagedBean
@RequestScoped
@Controller("homeBean")
@Component
@URLMappings(mappings = {
        @URLMapping(id="annotationUrlHome", pattern="/homeAnnotation", viewId="/pages/home.jsf"),
        @URLMapping(id="annotationViewItem", pattern="/storeAnnotation/item/#{iid : homeBean.itemId}", viewId="/pages/itens.xhtml", onPostback=false)
        })
public class HomeBean extends BaseBean {

    private static final long serialVersionUID = 1L;

    private String param;
    private String data;
    
    private String itemId;
    private String category;
    
    public HomeBean() {
        
    }

    public String editarItem() {
        return "pretty:viewItem/" + itemId;
    }
    
    @URLAction(mappingId="annotationViewItem")
    public String loadItem() {
        if ( itemId != null && !itemId.equals("0")) {
            category = String.format("Item carregado[%s]... Categoria!", itemId);
            messageInfo("Carregando Item");
            return null;
        }

        messageErro("Código do item não informado.");
        
        // Add a message here, "The item {..} could not be found."
        return "pretty:url-home";
    }
    
    public void metodo() {
        param = String.format("Método chamado... param[%s].", param);
    }
    
    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
