package co.edu.uniquindio.proyecto.Interfaces;

import co.edu.uniquindio.proyecto.Entidades.Administrador;

public interface AdministradorServicio {

    Administrador login (String email, String password) throws Exception;

    Administrador crear (String cedula, String nombre, String email, String password) throws Exception;


}
