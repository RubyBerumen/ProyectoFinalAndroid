package entidades;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Usuario {
    @NonNull
    @PrimaryKey
    private String usuario;

    @NonNull
    private String contraseña;

    public Usuario(@NonNull String usuario, @NonNull String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    @NonNull
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(@NonNull String usuario) {
        this.usuario = usuario;
    }

    @NonNull
    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(@NonNull String contraseña) {
        this.contraseña = contraseña;
    }
}
