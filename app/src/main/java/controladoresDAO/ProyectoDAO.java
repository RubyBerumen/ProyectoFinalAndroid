package controladoresDAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import entidades.Proyecto;

@Dao
public interface ProyectoDAO {

    //Altas
    @Insert
    public void insertarProyecto(Proyecto proyecto);

    //Bajas
    @Query("DELETE FROM Proyecto WHERE numProyecto= :nump")
    public void eliminarProyecto(int nump);

    //Cambios
    @Query("UPDATE Proyecto SET nombre=:n, ubicacion=:u, numDepartamento=:nd WHERE numProyecto=:nump")
    public void modificarProyecto(int nump, String n, String u, Byte nd);

    //Consultas
    @Query("SELECT * FROM Proyecto")
    public List<Proyecto> obtenerTodos();

    @Query("SELECT * FROM Proyecto WHERE nombre LIKE :n")
    public List<Proyecto> buscarpNombre (String n);

    @Query("SELECT * FROM Proyecto WHERE numProyecto LIKE :nump")
    public List<Proyecto> buscarPorNumP (int nump);

}
