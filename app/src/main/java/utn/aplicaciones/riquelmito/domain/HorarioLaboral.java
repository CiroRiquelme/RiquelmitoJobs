package utn.aplicaciones.riquelmito.domain;

import androidx.annotation.NonNull;

import utn.aplicaciones.riquelmito.R;

public enum HorarioLaboral {
    DIURNO,NOCTURNO,DISCONTINUO,ROTATIVO,MEDIA_MATUTINA,MEDIA_VESPERTINA,MEDIA_NOCTURNA,MEDIA_ROTATIVA;

    @NonNull
    @Override
    public String toString() {
        switch (this){
            case DIURNO:
                return AdministradorDeSesion.context.getString(R.string.horario_diurno);
            case NOCTURNO:
                return AdministradorDeSesion.context.getString(R.string.horario_nocturno);
            case DISCONTINUO:
                return AdministradorDeSesion.context.getString(R.string.horario_discontinuo);
            case ROTATIVO:
                return AdministradorDeSesion.context.getString(R.string.horario_rotativo);
            case MEDIA_MATUTINA:
                return AdministradorDeSesion.context.getString(R.string.horario_media_matutina);
            case MEDIA_VESPERTINA:
                return AdministradorDeSesion.context.getString(R.string.horario_media_vespertina);
            case MEDIA_NOCTURNA:
                return AdministradorDeSesion.context.getString(R.string.horario_media_nocturna);
            case MEDIA_ROTATIVA:
                return AdministradorDeSesion.context.getString(R.string.horario_media_rotativa);
            default:
                return AdministradorDeSesion.context.getString(R.string.desconocido);
        }
    }
}