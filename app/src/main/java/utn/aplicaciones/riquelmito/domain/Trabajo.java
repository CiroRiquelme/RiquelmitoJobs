package utn.aplicaciones.riquelmito.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Trabajo implements Serializable {
    private Integer idTrabajo;
    public Integer idEmpleador;
    public Rubro rubro;
    private String cargo;
    private String descripcionCargo;
    private String perfilEmpleado;
    private String experienciaEmpleado;
    private DiasLaborales dias;
    private HorarioLaboral horario;
    private Integer salario = 0;
    private Double lat = 0.0;
    private Double lng = 0.0;
    private Date fechaAlta;
    private Date fechaCierre;
    public List<Integer> idsPostulantes;

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public Trabajo(){
        fechaAlta = Calendar.getInstance().getTime();
    }

    public Trabajo(Integer idTrabajo, Rubro rubro, String cargo, DiasLaborales dias, HorarioLaboral horario, Integer salario) {
        this();
        this.idTrabajo = idTrabajo;
        this.rubro = rubro;
        this.cargo = cargo;
        this.dias = dias;
        this.horario = horario;
        this.salario = salario;
        idsPostulantes = new ArrayList<>();
        idsPostulantes.add(22);
        idsPostulantes.add(11);
    }



    public Trabajo(Rubro rubro, String cargo, String descripcionCargo, String perfilEmpleado, String experienciaEmpleado, DiasLaborales dias, HorarioLaboral horario, Integer salario, Double lat, Double lng) {
        this();
        this.rubro = rubro;
        this.cargo = cargo;
        this.descripcionCargo = descripcionCargo;
        this.perfilEmpleado = perfilEmpleado;
        this.experienciaEmpleado = experienciaEmpleado;
        this.dias = dias;
        this.horario = horario;
        this.salario = salario;
        this.lat = lat;
        this.lng = lng;
    }

    public Trabajo(Integer idTrabajo, Rubro rubro, String cargo, String descripcionCargo, String perfilEmpleado, String experienciaEmpleado, DiasLaborales dias, HorarioLaboral horario, Integer salario, Double lat, Double lng) {
        this();
        this.idTrabajo = idTrabajo;
        this.idEmpleador = idEmpleador;
        this.rubro = rubro;
        this.cargo = cargo;
        this.descripcionCargo = descripcionCargo;
        this.perfilEmpleado = perfilEmpleado;
        this.experienciaEmpleado = experienciaEmpleado;
        this.dias = dias;
        this.horario = horario;
        this.salario = salario;
        this.lat = lat;
        this.lng = lng;
    }

    public List<Integer> getIdsPostulantes() {
        return idsPostulantes;
    }

    public Integer getIdTrabajo() {
        return idTrabajo;
    }

    public Integer getIdEmpleador() { return idEmpleador; }

    public Rubro getRubro() {
        return rubro;
    }

    public String getCargo() {
        return cargo;
    }

    public String getDescripcionCargo() {
        return descripcionCargo;
    }

    public String getPerfilEmpleado() {
        return perfilEmpleado;
    }

    public String getExperienciaEmpleado() {
        return experienciaEmpleado;
    }

    public DiasLaborales getDias() {
        return dias;
    }

    public HorarioLaboral getHorario() {
        return horario;
    }

    public Integer getSalario() {
        return salario;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setIdTrabajo(Integer id){
        idTrabajo = id;
    }

    public void setIdEmpleador(Integer idEmpleador) { this.idEmpleador = idEmpleador; }

    public void setIdsPostulantes(List<Integer> idsPostulantes) {
        this.idsPostulantes = idsPostulantes;
    }

    public void setCargo(String cargo) { this.cargo = cargo; }

    public void setLat(Double lat) { this.lat = lat; }

    public void setLng(Double lng) { this.lng = lng; }

    public String getFechaAltaString () {
        if (fechaAlta == null)
            return "~null~";
        SimpleDateFormat formatoArg = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return formatoArg.format(fechaAlta);
    }

    public String getFechaBajaString () {
        if (fechaCierre == null)
            return "~active~";
        SimpleDateFormat formatoArg = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return formatoArg.format(fechaAlta);
    }
}
