package utn.aplicaciones.riquelmito.domain;

import androidx.annotation.NonNull;

import utn.aplicaciones.riquelmito.R;

public enum Sexo {
    FEMENINO,MASCULINO,OTRO;

    @NonNull
    @Override
    public String toString() {
        switch (this){
            case FEMENINO:
                return AdministradorDeSesion.context.getString(R.string.sexo_femenino);
            case MASCULINO:
                return AdministradorDeSesion.context.getString(R.string.sexo_masculino);
            case OTRO:
            default:
                return AdministradorDeSesion.context.getString(R.string.sexo_otro);
        }
    }
}
