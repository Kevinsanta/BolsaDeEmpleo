package centralAspirantes.mundo;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CentralAspirantes {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Lista de aspirantes
     */
    private ArrayList<Aspirante> aspirantes;

    public CentralAspirantes() throws Exception {
        ConnectionSource source = ObtenerConnectionSource();
        TableUtils.createTableIfNotExists(source, Aspirante.class);

        //agregarAspirante(new Aspirante(1000, "Kevin",21,1,"Ingeniero",123));

        darAspirantes();
    }

    private ConnectionSource ObtenerConnectionSource() throws SQLException {
        String databaseUrl = "jdbc:h2:file:./data/basedatosaspirantes";
        ConnectionSource source = new JdbcConnectionSource(databaseUrl);
        return source;
    }

    /**
     * Devuelve una lista con los aspirantes de la central
     */
    public ArrayList<Aspirante> darAspirantes() throws Exception {
        ConnectionSource source = ObtenerConnectionSource();

        aspirantes = new ArrayList<>();

        Dao<Aspirante, Integer> aspiranteDao = DaoManager.createDao(source, Aspirante.class);

        aspiranteDao.forEach(aspiranteBd -> {
            int cedula = aspiranteBd.darCedula();
            String nombreCompleto = aspiranteBd.darNombreCompleto();
            int edad = aspiranteBd.darEdad();
            int experiencia = aspiranteBd.darExperiencia();
            String profesion = aspiranteBd.darProfesion();
            int telefono = aspiranteBd.darTelefono();

            Aspirante aspirante = new Aspirante(cedula, nombreCompleto, edad, experiencia, profesion, telefono);

            aspirantes.add(aspirante);
        });

        source.close();

        return aspirantes;
    }

    /**
     * Retorna el número de aspirantes
     *
     * @return El número de aspirantes
     */
    public int darNumeroAspirantes() {
        return aspirantes.size();
    }

    public Aspirante localizar(int cedula) throws Exception {
        ConnectionSource source = ObtenerConnectionSource();
        Dao<Aspirante, Integer> aspiranteDao = DaoManager.createDao(source, Aspirante.class);
        Aspirante aspirante = aspiranteDao.queryForId(cedula);
        source.close();
        return aspirante;
    }

    public Aspirante localizarPorNombre(String nombre) throws Exception {
        ConnectionSource source = ObtenerConnectionSource();
        Dao<Aspirante, Integer> aspiranteDao = DaoManager.createDao(source, Aspirante.class);
        List<Aspirante> aspirantes = aspiranteDao.queryForEq("nombreCompleto", nombre);
        Aspirante aspirante = null;
        if(aspirantes.size()>0){
            aspirante = aspirantes.get(0);
        }
        source.close();
        return aspirante;
    }

    public void agregarAspirante(Aspirante aspirante) throws Exception {
        if(aspirante != null)
        {
            ConnectionSource source = ObtenerConnectionSource();
            Dao<Aspirante, Integer> aspiranteDao = DaoManager.createDao(source, Aspirante.class);
            aspiranteDao.create(aspirante);
            source.close();
        }
    }

    public void eliminarAspirante(int cedula) throws Exception {
        ConnectionSource source = ObtenerConnectionSource();
        Dao<Aspirante, Integer> aspiranteDao = DaoManager.createDao(source, Aspirante.class);
        aspiranteDao.deleteById(cedula);
        source.close();
    }

    public ArrayList<Aspirante> ordenarPorExperiencia() throws SQLException {
        aspirantes = new ArrayList<>();
        ConnectionSource source = ObtenerConnectionSource();
        Dao<Aspirante, Integer> aspiranteDao = DaoManager.createDao(source, Aspirante.class);
        QueryBuilder<Aspirante, Integer> queryBuilder = aspiranteDao.queryBuilder();
        PreparedQuery<Aspirante> preparedQuery = queryBuilder.orderBy("experiencia", true).prepare();
        List<Aspirante> aspirantesOrdenados = aspiranteDao.query(preparedQuery);
        aspirantes = new ArrayList<Aspirante>(aspirantesOrdenados);
        return aspirantes;
    }

    public ArrayList<Aspirante> ordenarPorEdad() throws SQLException {
        aspirantes = new ArrayList<>();
        ConnectionSource source = ObtenerConnectionSource();
        Dao<Aspirante, Integer> aspiranteDao = DaoManager.createDao(source, Aspirante.class);
        QueryBuilder<Aspirante, Integer> queryBuilder = aspiranteDao.queryBuilder();
        PreparedQuery<Aspirante> preparedQuery = queryBuilder.orderBy("edad", true).prepare();
        List<Aspirante> aspirantesOrdenados = aspiranteDao.query(preparedQuery);
        aspirantes = new ArrayList<Aspirante>(aspirantesOrdenados);
        return aspirantes;
    }

    public ArrayList<Aspirante> ordenarPorProfesion() throws SQLException {
        aspirantes = new ArrayList<>();
        ConnectionSource source = ObtenerConnectionSource();
        Dao<Aspirante, Integer> aspiranteDao = DaoManager.createDao(source, Aspirante.class);
        QueryBuilder<Aspirante, Integer> queryBuilder = aspiranteDao.queryBuilder();
        PreparedQuery<Aspirante> preparedQuery = queryBuilder.orderBy("profesion", true).prepare();
        List<Aspirante> aspirantesOrdenados = aspiranteDao.query(preparedQuery);
        aspirantes = new ArrayList<Aspirante>(aspirantesOrdenados);
        return aspirantes;
    }

    public Aspirante obtenerAspiranteConMayorExperiencia() throws SQLException {
        ConnectionSource source = ObtenerConnectionSource();
        Dao<Aspirante, Integer> aspiranteDao = DaoManager.createDao(source, Aspirante.class);
        QueryBuilder<Aspirante, Integer> queryBuilder = aspiranteDao.queryBuilder();
        PreparedQuery<Aspirante> preparedQuery = queryBuilder.orderBy("experiencia", false).prepare();
        List<Aspirante> aspirantesOrdenados = aspiranteDao.query(preparedQuery);
        Aspirante aspirante = null;
        if(aspirantesOrdenados.size()>0){
            aspirante = aspirantesOrdenados.get(0);
        }
        return aspirante;
    }

    public Aspirante obtenerAspiranteMasJoven() throws SQLException {
        ConnectionSource source = ObtenerConnectionSource();
        Dao<Aspirante, Integer> aspiranteDao = DaoManager.createDao(source, Aspirante.class);
        QueryBuilder<Aspirante, Integer> queryBuilder = aspiranteDao.queryBuilder();
        PreparedQuery<Aspirante> preparedQuery = queryBuilder.orderBy("edad", true).prepare();
        List<Aspirante> aspirantesOrdenados = aspiranteDao.query(preparedQuery);
        Aspirante aspirante = null;
        if(aspirantesOrdenados.size()>0){
            aspirante = aspirantesOrdenados.get(0);
        }
        return aspirante;
    }

    public void eliminarAspirantesConMenosExperiencia(int experiencia) throws SQLException {
        ConnectionSource source = ObtenerConnectionSource();
        Dao<Aspirante, Integer> aspiranteDao = DaoManager.createDao(source, Aspirante.class);
        DeleteBuilder<Aspirante, Integer> deleteBuilder = aspiranteDao.deleteBuilder();
        deleteBuilder.where().lt("experiencia", experiencia);
        deleteBuilder.delete();
    }

    public double obtenerPromedioEdadAspirantes(){
        int sumaEdades = 0;
        for (int i = 0; i < aspirantes.size(); i++) {
            sumaEdades += aspirantes.get(i).darEdad();
        }
        return sumaEdades / (double) aspirantes.size();
    }
}
