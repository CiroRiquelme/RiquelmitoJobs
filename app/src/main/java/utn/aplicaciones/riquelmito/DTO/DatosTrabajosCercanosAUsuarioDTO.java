package utn.aplicaciones.riquelmito.DTO;

import java.io.Serializable;

public class DatosTrabajosCercanosAUsuarioDTO implements Serializable {

    //TODO agregar parámetro de rubro para búsqueda
    public Double radioDeBusqueda;

    public DatosTrabajosCercanosAUsuarioDTO(){

    }

    public Double getRadioDeBusqueda() { return radioDeBusqueda; }

    public void setRadioDeBusqueda(Double radioDeBusqueda) { this.radioDeBusqueda = radioDeBusqueda; }

}
