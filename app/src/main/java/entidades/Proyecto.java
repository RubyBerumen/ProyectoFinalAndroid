package entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Proyecto {

    @NonNull
    @ColumnInfo(name="nombre")
    private String nombreProyecto;

    @NonNull
    @PrimaryKey
    private int numProyecto;

    @NonNull
    @ColumnInfo(name="ubicacion")
    private String ubicaciónProyecto;

    @NonNull
    @ColumnInfo(name="numDepartamento")
    private byte numDptoProyecto;

    @NonNull
    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(@NonNull String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public int getNumProyecto() {
        return numProyecto;
    }

    public void setNumProyecto(int numProyecto) {
        this.numProyecto = numProyecto;
    }

    public Proyecto(@NonNull String nombreProyecto, int numProyecto, @NonNull String ubicaciónProyecto, byte numDptoProyecto) {
        this.nombreProyecto = nombreProyecto;
        this.numProyecto = numProyecto;
        this.ubicaciónProyecto = ubicaciónProyecto;
        this.numDptoProyecto = numDptoProyecto;
    }

    @NonNull
    public String getUbicaciónProyecto() {
        return ubicaciónProyecto;
    }

    public void setUbicaciónProyecto(@NonNull String ubicaciónProyecto) {
        this.ubicaciónProyecto = ubicaciónProyecto;
    }

    public byte getNumDptoProyecto() {
        return numDptoProyecto;
    }

    public void setNumDptoProyecto(byte numDptoProyecto) {
        this.numDptoProyecto = numDptoProyecto;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "nombreProyecto='" + nombreProyecto + '\'' +
                ", numProyecto=" + numProyecto +
                ", ubicaciónProyecto='" + ubicaciónProyecto + '\'' +
                ", numDptoProyecto=" + numDptoProyecto +
                '}';
    }
}
