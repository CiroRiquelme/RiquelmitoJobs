package utn.aplicaciones.riquelmito.domain;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Postulante {
    private Integer idPostulante;
    private String email;
    private String contraseña;
    private String nombre;
    private String apellido;
    private Integer dni;
    private Date nacimiento;
    private Sexo sexo;

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public Integer getEdad(){
        return 1000;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    String provincia;
    String ciudad;
    String telefono;
    Double lat = 0.0;
    Double lng = 0.0;

    //Perfíl profesional
    String experiencia;
    String formacion;
    String idiomas;
    //TODO: guardar CV
    //TODO: Quién puede ver mi CV
}
