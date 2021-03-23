package com.artemispocket.jsfclasses;

import com.artemispocket.entities.CertEstadisticas;
import com.artemispocket.jsfclasses.util.JsfUtil;
import com.artemispocket.jsfclasses.util.JsfUtil.PersistAction;
import com.artemispocket.beans.CertEstadisticasFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("certEstadisticasController")
@SessionScoped
public class CertEstadisticasController implements Serializable {

    @EJB
    private com.artemispocket.beans.CertEstadisticasFacade ejbFacade;
    private List<CertEstadisticas> items = null;
    private CertEstadisticas selected;

    public CertEstadisticasController() {
    }

    public CertEstadisticas getSelected() {
        return selected;
    }

    public void setSelected(CertEstadisticas selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CertEstadisticasFacade getFacade() {
        return ejbFacade;
    }

    public CertEstadisticas prepareCreate() {
        selected = new CertEstadisticas();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CertEstadisticasCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CertEstadisticasUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CertEstadisticasDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CertEstadisticas> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public CertEstadisticas getCertEstadisticas(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<CertEstadisticas> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CertEstadisticas> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CertEstadisticas.class)
    public static class CertEstadisticasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CertEstadisticasController controller = (CertEstadisticasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "certEstadisticasController");
            return controller.getCertEstadisticas(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof CertEstadisticas) {
                CertEstadisticas o = (CertEstadisticas) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CertEstadisticas.class.getName()});
                return null;
            }
        }

    }

}
