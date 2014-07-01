package br.com.estudojavamagazine.converters;

import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.estudojavamagazine.domain.Categoria;
import br.com.estudojavamagazine.service.CategoriaService;

//@FacesConverter("categoriaConverter")
@Component
@RequestScoped
public class CategoriaConverter implements Converter {

	@Autowired
    private CategoriaService categoriaService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null && value.trim().length() > 0) {
            return categoriaService.findByName(value);
        } else {
            return null;
        }
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if(object != null) {
            return String.valueOf(((Categoria) object).getNome());
        } else {
            return null;
        }
	}

}
