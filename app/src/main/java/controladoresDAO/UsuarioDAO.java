package controladoresDAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import entidades.Usuario;

@Dao
public interface UsuarioDAO {

    @Query("SELECT * FROM Usuario WHERE usuario=:u")
    public Usuario buscarUsuario(String u);

    @Insert
    public void agregarUsuario (Usuario usuario);

}
