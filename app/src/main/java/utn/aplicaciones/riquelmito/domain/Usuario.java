package utn.aplicaciones.riquelmito.domain;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Usuario implements Serializable {
    private Integer idPostulante;
    private String email;
    private String contraseña;
    private String nombre;
    private String apellido;
    private Integer dni;
    private Date nacimiento;
    private Sexo sexo;

    //TODO agregar tipo de usuario

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

    public Usuario() {
    }

    public Usuario(Integer idPostulante, String email, String contraseña, String nombre, String apellido, Integer dni, Date nacimiento, Sexo sexo, String provincia, String ciudad, String telefono, Double lat, Double lng, String experiencia, String formacion, String idiomas) {
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
    }   //TODO Devolver la edad real

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

    public Integer getIdPostulante() { return idPostulante; }

    public String getEmail() { return email; }

    public String getContraseña() { return contraseña; }

    public Integer getDni() { return dni; }

    public String getTelefono() { return telefono; }

    public String getExperiencia() { return experiencia; }

    public String getFormacion() { return formacion; }

    public String getIdiomas() { return idiomas; }

    public void setIdPostulante(Integer idPostulante) {
        this.idPostulante = idPostulante;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public void setFormacion(String formacion) {
        this.formacion = formacion;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

}
