package utn.aplicaciones.riquelmito.domain;

import androidx.annotation.NonNull;

import utn.aplicaciones.riquelmito.R;

public enum TipoDeUsuario {
    EMPLEADOR,EMPLEADO;

    @NonNull
    @Override
    public String toString() {
        switch (this){
            case EMPLEADO:
                return AdministradorDeSesion.context.getString(R.string.tipo_usuario_postulante);
            case EMPLEADOR:
                return AdministradorDeSesion.context.getString(R.string.tipo_usuario_empleador);
            default:
                return AdministradorDeSesion.context.getString(R.string.desconocido);
        }
    }


    public String tipoUsuarioAIdentificador(){
        switch (this){
            case EMPLEADO:
                return "EMPLEADO";
            case EMPLEADOR:
                return "EMPLEADOR";
            default:
                return null;
        }
    }

    public static TipoDeUsuario identificadorATipoUsuario(String identificador){
        switch (identificador){
            case "EMPLEADO":
                return EMPLEADO;
            case "EMPLEADOR":
                return EMPLEADOR;
            default:
                return null;
        }
    }
}
