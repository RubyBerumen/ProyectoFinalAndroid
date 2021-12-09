package baseDeDatos;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import controladoresDAO.ProyectoDAO;
import controladoresDAO.UsuarioDAO;
import entidades.Proyecto;
import entidades.Usuario;

@Database(entities = {Proyecto.class, Usuario.class},version = 1,exportSchema = false)

public abstract class EmpresaBD extends RoomDatabase {

    public abstract ProyectoDAO pDAO();
    public abstract UsuarioDAO uDAO();

    private static EmpresaBD INSTANCE;

    public static EmpresaBD gettAppDatabase(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    EmpresaBD.class,"@empresa").build();
        }
        return  INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE=null;
    }


}
