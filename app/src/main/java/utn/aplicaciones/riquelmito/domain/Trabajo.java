package utn.aplicaciones.riquelmito.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Trabajo {
    private Integer idTrabajo;
    private Rubro rubro;
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

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public Trabajo(Rubro rubro, String cargo, DiasLaborales dias, HorarioLaboral horario, Integer salario) {
        this.rubro = rubro;
        this.cargo = cargo;
        this.dias = dias;
        this.horario = horario;
        this.salario = salario;
        this.fechaAlta = Calendar.getInstance().getTime();
    }

    public Integer getIdTrabajo() {
        return idTrabajo;
    }

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
