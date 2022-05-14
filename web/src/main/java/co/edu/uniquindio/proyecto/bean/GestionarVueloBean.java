package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.Entidades.Vuelo;
import co.edu.uniquindio.proyecto.Interfaces.VueloServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
@Getter
@Setter
public class GestionarVueloBean implements Serializable {

    @Autowired
    private VueloServicio vueloServicio;

    private Integer codigoVuelo;

    private List<Vuelo> vuelos;

    @PostConstruct
    public void inicializar() {
        this.vuelos = vueloServicio.listar();
    }

    public String agregarVuelo() {
        return "/administrador/RegistrarVuelos.xhtml?faces-redirect=true";
    }

    public String eliminarVuelo(Integer codigoVuelo) {
        try {
            vueloServicio.eliminarVuelo(codigoVuelo);
            return "/administrador/GestionarVuelo.xhtml?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void buscarVuelo() {
        try {
            vuelos.clear();
            Vuelo vuelo = vueloServicio.buscarVueloPorCodigo(codigoVuelo);
            vuelos.add(vuelo);
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        }
    }

    public String actualizarVuelo(Integer codigoVuelo) {
        try {
            return "/administrador/ActualizarVuelo.xhtml?faces-redirect=true&amp;codigo=" + codigoVuelo;
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        }
        return null;
    }
}
