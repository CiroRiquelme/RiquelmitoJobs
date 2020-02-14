package utn.aplicaciones.riquelmito.domain;
import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Usuario implements Serializable {
    private Integer idPostulante;
    private String email;
    private String contrasenia;
    private String nombre;
    private String apellido;
    private Integer dni;
    private Date nacimiento;
    private Sexo sexo;
    private TipoDeUsuario tipoUsuario = TipoDeUsuario.EMPLEADO;

    String provincia;
    String ciudad;
    String telefono;
    Double lat = 0.0;
    Double lng = 0.0;

    //Perfíl profesional
    String experiencia;
    String formacion;
    String idiomas;
    QuienVeMiCV quienVeMiCV = QuienVeMiCV.TODOS;

    public Usuario() {
    }

    public Usuario(Integer idPostulante, String email, String contrasenia, String nombre, String apellido, Integer dni, Date nacimiento, Sexo sexo, String provincia, String ciudad, String telefono, Double lat, Double lng, String experiencia, String formacion, String idiomas) {
        this.idPostulante = idPostulante;
        this.email = email;
        this.contrasenia = contrasenia;
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

    @Exclude
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

    public String getContrasenia() { return contrasenia; }

    public Integer getDni() { return dni; }

    public String getTelefono() { return telefono; }

    public String getExperiencia() { return experiencia; }

    public String getFormacion() { return formacion; }

    public String getIdiomas() { return idiomas; }

    public QuienVeMiCV getQuienVeMiCV() { return quienVeMiCV; }

    public TipoDeUsuario getTipoUsuario() { return tipoUsuario; }

    public void setIdPostulante(Integer idPostulante) {
        this.idPostulante = idPostulante;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
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

    public void setTipoUsuario(TipoDeUsuario tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    public  void setQuienVeMiCV(QuienVeMiCV quien) { this.quienVeMiCV = quien; }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("idPostulante", idPostulante);
        result.put("email", email);
        result.put("contrasenia", contrasenia);
        result.put("nombre", nombre);
        result.put("apellido", apellido);
        result.put("dni", dni);
        result.put("nacimiento", nacimiento);
        result.put("sexo", sexo);
        result.put("tipoUsuario", tipoUsuario);
        result.put("provincia", provincia);
        result.put("ciudad", ciudad);
        result.put("telefono", telefono);
        result.put("lat", lat);
        result.put("lng", lng);
        result.put("experiencia", experiencia);
        result.put("formacion", formacion);
        result.put("idiomas", idiomas);
        result.put("quienVeMiCV", quienVeMiCV);

        return result;
    }

}
