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

    public Postulante() {
    }

    public Postulante(Integer idPostulante, String email, String contraseña, String nombre, String apellido, Integer dni, Date nacimiento, Sexo sexo, String provincia, String ciudad, String telefono, Double lat, Double lng, String experiencia, String formacion, String idiomas) {
        this.idPostulante = idPostulante;
        this.email = email;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.nacimiento = nacimiento;
        this.sexo = sexo;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.lat = lat;
        this.lng = lng;
        this.experiencia = experiencia;
        this.formacion = formacion;
        this.idiomas = idiomas;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

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

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }
}
