package org.getfin.controlador;

import org.getfin.modelos.Usuario;
import org.getfin.servicios.GenericServiceImpl;
import org.getfin.servicios.IGenericService;
import org.getfin.util.HibernateUtil;

import java.util.List;

public class UsuarioController {

    private static UsuarioController instance;

    // Reutilizamos el mismo servicio para evitar múltiples instancias
    private final IGenericService<Usuario> usuarioService;

    private UsuarioController() {
        usuarioService = new GenericServiceImpl<>(Usuario.class, HibernateUtil.getSessionFactory());
    }

    public static UsuarioController getInstance() {
        if (instance == null) {
            instance = new UsuarioController();
        }
        return instance;
    }

    public void guardaUsuario(Usuario usuario) {
        usuarioService.save(usuario);
    }

    public void eliminaUsuario(Usuario usuario) {
        usuarioService.delete(usuario);
    }

    public void editaUsuario(Usuario usuario) {
        usuarioService.update(usuario);
    }

    public List<Usuario> getUsuarios() {
        return usuarioService.getAll();
    }

    // 🚀 Nuevo método: obtener usuario por nombre
    public Usuario getUsuarioPorNombre(String nombreUsuario) {
        return usuarioService.getByName(nombreUsuario);
    }
}
