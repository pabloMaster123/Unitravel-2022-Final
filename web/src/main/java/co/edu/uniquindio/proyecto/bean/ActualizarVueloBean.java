package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.Entidades.Ciudad;
import co.edu.uniquindio.proyecto.Interfaces.CiudadServicio;
import co.edu.uniquindio.proyecto.Interfaces.VueloServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@ViewScoped
@Component
@Getter
@Setter
public class ActualizarVueloBean implements Serializable {

    @Autowired
    private VueloServicio vueloServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Value("#{param['codigo']}")
    private Integer codigo;

    private List<Ciudad> ciudades;

    private Ciudad ciudadOrigen;

    private Ciudad ciudadDestino;

    private Integer cantidadSillas;

    private LocalDate fecha;

    @PostConstruct
    public void inicializar(){
        this.ciudades = ciudadServicio.listar();
    }

    public String actualizarVuelo(){
        try {
            vueloServicio.actualizarVuelo(codigo,ciudadOrigen,ciudadDestino,cantidadSillas,fecha);
            return "/administrador/GestionarVuelo.xhtml?faces-redirect=true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
