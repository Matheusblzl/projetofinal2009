package br.com.estudojavamagazine.converters;

import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.estudojavamagazine.service.CategoriaService;
import br.com.estudojavamagazine.service.util.ObjectUtil;

// @FacesConverter("categoriaConverter")
@Component
@RequestScoped
public class CategoriaConverter implements Converter {

    @Autowired
    private CategoriaService categoriaService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            return value;
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        if (ObjectUtil.isNotNull(object)) {
            return String.valueOf(object);
        }
        return null;
    }

}
