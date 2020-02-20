package utn.aplicaciones.riquelmito.DTO;

import java.io.Serializable;

import utn.aplicaciones.riquelmito.domain.Rubro;

public class DatosTrabajosCercanosAUsuarioDTO implements Serializable {

    public Double radioDeBusqueda;
    private Rubro rubro;
    private Integer salario;

    public DatosTrabajosCercanosAUsuarioDTO(){

    }

    public Double getRadioDeBusqueda() { return radioDeBusqueda; }

    public Rubro getRubro() { return rubro; }

    public Integer getSalario() { return salario; }

    public void setRadioDeBusqueda(Double radioDeBusqueda) { this.radioDeBusqueda = radioDeBusqueda; }

    public void setRubro(Rubro rubro) { this.rubro = rubro; }

    public void setSalario(Integer salario) { this.salario = salario; }

}
