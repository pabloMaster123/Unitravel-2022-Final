package co.edu.uniquindio.proyecto.Servicios;

import co.edu.uniquindio.proyecto.Entidades.Administrador;
import co.edu.uniquindio.proyecto.Interfaces.AdministradorServicio;
import co.edu.uniquindio.proyecto.Repositorios.AdministradorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorServicioImpl implements AdministradorServicio {

    @Autowired
    private AdministradorRepo administradorRepo;

    @Override
    public Administrador login(String email, String password) throws Exception {
        return administradorRepo.findByEmailAndPassword(email, password).orElseThrow(() -> new Exception("Datos incorrectos."));
    }

    @Override
    public Administrador crear(String cedula, String nombre, String email, String password) throws Exception {
        if(!administradorRepo.existsById(cedula)){
            if(!administradorRepo.findByEmail(email).isPresent()){
                Integer codigo;
                if(!administradorRepo.findAll().isEmpty()){
                    codigo = administradorRepo.findAll().size()+1;

                } else {
                    codigo = 1;
                }
                return administradorRepo.save(new Administrador(cedula, nombre, email, password, codigo));
            } else {
                throw new Exception("Ya existe un administrador registrado con este email");
            }
        } else {
            throw new Exception("Ya existe un administrador registrado con la cedula indicada.");
        }
    }
}
