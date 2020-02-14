package utn.aplicaciones.riquelmito.domain;

import utn.aplicaciones.riquelmito.R;

public enum QuienVeMiCV {
    TODOS,SOLO_YO;

    @Override
    public String toString(){
        switch (this){
            case TODOS:
                return AdministradorDeSesion.context.getString(R.string.quienvemicv_todos);
            case SOLO_YO:
                return AdministradorDeSesion.context.getString(R.string.quienvemicv_solo_yo);
            default:
                return null;
        }
    }

    public String quienvemicvAIdentificador(){
        switch (this){
            case TODOS:
                return "TODOS";
            case SOLO_YO:
                return "SOLO_YO";
            default:
                return null;
        }
    }

    public static QuienVeMiCV identificadorAQuienvemicv(String identificador){
        switch (identificador){
            case "TODOS":
                return TODOS;
            case "SOLO_YO":
                return SOLO_YO;
            default:
                return null;
        }
    }
}
