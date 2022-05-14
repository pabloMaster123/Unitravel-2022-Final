package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.Entidades.Ciudad;
import co.edu.uniquindio.proyecto.Interfaces.CiudadServicio;
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
import java.time.LocalDate;
import java.util.List;

@Component
@ViewScoped
@Getter
@Setter
public class RegistrarVueloBean implements Serializable {

    @Autowired
    private VueloServicio vueloServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    private List<Ciudad> ciudades;

    private Ciudad ciudadOrigen;

    private Ciudad ciudadDestino;

    private Integer cantidadSillas;

    private LocalDate fecha;

    @PostConstruct
    public void inicializar(){
        this.ciudades = ciudadServicio.listar();
    }

    public String registrarVuelo(){
        try {
            vueloServicio.agregarVuelo(ciudadOrigen,ciudadDestino,cantidadSillas,fecha);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta", "Registro Exitoso!");
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
            return "/administrador/GestionarVuelo.xhtml?faces-redirect=true";
        }catch (Exception e){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        }
        return null;
    }

}
