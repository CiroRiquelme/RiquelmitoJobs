package utn.aplicaciones.riquelmito.domain;
import java.util.Date;

public class Postulante {
    Integer idPostulante;
    String email;
    String contraseña;
    String nombre;
    String apellido;
    Integer dni;
    Date nacimiento;
    Sexo sexo;
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
