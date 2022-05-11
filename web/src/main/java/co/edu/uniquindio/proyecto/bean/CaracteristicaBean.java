package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.Entidades.Caracteristica;
import co.edu.uniquindio.proyecto.Entidades.Ciudad;
import co.edu.uniquindio.proyecto.Interfaces.CaracteristicaServicio;
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
public class CaracteristicaBean implements Serializable {

    @Autowired
    CaracteristicaServicio caracteristicaServicio;

    private String contenido;

    private String buscar;

    private List<Caracteristica> buscadas;

    private List<Caracteristica> caracteristicas;

    @PostConstruct
    public void inicializar(){
        this.caracteristicas = caracteristicaServicio.listar();
    }

    public String IngresarCaracteristica(){
        try {
            caracteristicaServicio.agregarCaracteristica(contenido);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "alerta", "Caracteristica agregada");
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
            return "GestionarCaracteristica.xhtml?faces-redirect=true";
        }catch (Exception e){
            e.printStackTrace();
            e.printStackTrace();
            FacesMessage msg1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg1);
        }
        return null;
    }

    public String eliminar(Integer id){
        try{
            caracteristicaServicio.eliminarCaracteristica(id);
            return "GestionarCaracteristica.xhtml?faces-redirect=true";
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String llevarParaActualizar(Integer caracteristicaCodigo){
        return "ActualizarCaracteristica.xhtml?faces-redirect=true&amp;codigo="+caracteristicaCodigo;
    }

    public void buscarCaracteristica(){
        try{
            caracteristicas = caracteristicaServicio.buscarCaracteristicasPorContenido(buscar);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
