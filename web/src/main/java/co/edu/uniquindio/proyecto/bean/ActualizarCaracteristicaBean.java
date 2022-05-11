package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.Entidades.Caracteristica;
import co.edu.uniquindio.proyecto.Interfaces.CaracteristicaServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
@Getter
@Setter
public class ActualizarCaracteristicaBean implements Serializable  {

    @Autowired
    private CaracteristicaServicio caracteristicaServicio;

    private Caracteristica caracteristica;

    private String contenido;

    @Value("#{param['codigo']}")
    private Integer codigo;

    private List<Caracteristica> caracteristicas;

    @PostConstruct
    public void inicializar(){
        this.caracteristicas = new ArrayList<Caracteristica>();
        try{
            this.caracteristica = caracteristicaServicio.buscarCaracteristica(codigo);
        }catch(Exception e){
            e.printStackTrace();
        }
        this.caracteristicas.add(caracteristica);
    }

    public String actualizar(){
        try{
            caracteristicaServicio.actualizarCaracteristica(caracteristica.getCodigo(), contenido);
            return "GestionarCaracteristica.xhtml?faces-redirect=true";
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
