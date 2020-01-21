package utn.aplicaciones.riquelmito.domain;

import androidx.annotation.NonNull;

import utn.aplicaciones.riquelmito.R;

public enum DiasLaborales {
    LUN_VIE,LUN_SAB,MART_SAB,MART_DOM,FERIAD_DOM,FERIAD_FINSEMANA,A_TURNOS;

    @NonNull
    @Override
    public String toString() {
        switch (this){
            case LUN_VIE:
                return AdministradorDeSesion.context.getString(R.string.dias_lab_lun_viern);
            case LUN_SAB:
                return AdministradorDeSesion.context.getString(R.string.dias_lab_lun_sab);
            case MART_SAB:
                return AdministradorDeSesion.context.getString(R.string.dias_lab_mart_sab);
            case MART_DOM:
                return AdministradorDeSesion.context.getString(R.string.dias_lab_mart_domin);
            case FERIAD_DOM:
                return AdministradorDeSesion.context.getString(R.string.dias_lab_feriad_domin);
            case FERIAD_FINSEMANA:
                return AdministradorDeSesion.context.getString(R.string.dias_lab_feriad_find_seman);
            case A_TURNOS:
                return AdministradorDeSesion.context.getString(R.string.dias_lab_a_turnos);
            default:
                return AdministradorDeSesion.context.getString(R.string.desconocido);
        }
    }
}