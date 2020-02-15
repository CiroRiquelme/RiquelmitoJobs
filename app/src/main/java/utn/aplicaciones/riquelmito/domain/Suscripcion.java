package utn.aplicaciones.riquelmito.domain;

public class Suscripcion {
    public Integer idSuscripcion;
    public Integer idTrabajo;
    public Integer idPostulante;
    public Integer idEmpleador;

    public Suscripcion(){}

    public Suscripcion(Integer idSuscripcion, Integer idTrabajo, Integer idPostulante, Integer idEmpleador) {
        this.idSuscripcion = idSuscripcion;
        this.idTrabajo = idTrabajo;
        this.idPostulante = idPostulante;
        this.idEmpleador = idEmpleador;
    }

    public Integer getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(Integer idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public Integer getIdTrabajo() {
        return idTrabajo;
    }

    public void setIdTrabajo(Integer idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    public Integer getIdPostulante() {
        return idPostulante;
    }

    public void setIdPostulante(Integer idPostulante) {
        this.idPostulante = idPostulante;
    }

    public Integer getIdEmpleador() {
        return idEmpleador;
    }

    public void setIdEmpleador(Integer idEmpleador) {
        this.idEmpleador = idEmpleador;
    }
}
