package utn.aplicaciones.riquelmito.utilidades;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import utn.aplicaciones.riquelmito.R;
import utn.aplicaciones.riquelmito.domain.Trabajo;

public class AdministradorDeCargaDeInterfaces {

    public static  StringBuffer getFilaRubro(Context cont, Trabajo trabajo){
        if(trabajo.getRubro()==null)
            return new StringBuffer(cont.getString(R.string.desconocido));

        switch (trabajo.getRubro()){
            case ATENCION_AL_PUBLICO:
                return new StringBuffer(cont.getString(R.string.rubro_atencion_al_publico));
            case COMUNICACIONES:
                return new StringBuffer(cont.getString(R.string.rubro_comunicaciones));
            case CONSTRUCCION:
                return new StringBuffer(cont.getString(R.string.rubro_construccion));
            case ELECTRICIDAD:
                return new StringBuffer(cont.getString(R.string.rubro_electricidad));
            case INFORMATICA:
                return new StringBuffer(cont.getString(R.string.rubro_informatica));
            case RRHH:
                return new StringBuffer(cont.getString(R.string.rubro_rrhh));
            case SALUD:
                return new StringBuffer(cont.getString(R.string.rubro_salud));
            case TRANSPORTE:
                return new StringBuffer(cont.getString(R.string.rubro_transporte));
            default:
                return new StringBuffer(cont.getString(R.string.desconocido));
        }
    }

    public static int getIdIconoRubro(Context cont, Trabajo trabajo){
        if( trabajo.getRubro()==null )
            return R.drawable.desconocido;

        switch (trabajo.getRubro()){
            case ATENCION_AL_PUBLICO:
                return R.drawable.atencion_al_publico;
            case COMUNICACIONES:
                return R.drawable.comunicaciones;
            case CONSTRUCCION:
                return R.drawable.construccion;
            case ELECTRICIDAD:
                return R.drawable.electricidad;
            case INFORMATICA:
                return R.drawable.informatica;
            case RRHH:
                return R.drawable.rrhh;
            case SALUD:
                return R.drawable.salud;
            case TRANSPORTE:
                return R.drawable.transporte;
            default:
                return R.drawable.desconocido;
        }
    }

    //Fila horario "Lun - Vie; Horario: Diurno"
    public static StringBuffer getFilaHorario(Context cont, Trabajo trabajo){
        StringBuffer filaHorario = new StringBuffer();

        if (trabajo.getDias()!=null){
            switch (trabajo.getDias()) {
                case LUN_VIE:
                    filaHorario.append(cont.getString(R.string.dias_lab_lun_viern));
                    break;
                case LUN_SAB:
                    filaHorario.append(cont.getString(R.string.dias_lab_lun_sab));
                    break;
                case MART_SAB:
                    filaHorario.append(cont.getString(R.string.dias_lab_mart_sab));
                    break;
                case MART_DOM:
                    filaHorario.append(cont.getString(R.string.dias_lab_mart_domin));
                    break;
                case FERIAD_DOM:
                    filaHorario.append(cont.getString(R.string.dias_lab_feriad_domin));
                    break;
                case FERIAD_FINSEMANA:
                    filaHorario.append(cont.getString(R.string.dias_lab_feriad_find_seman));
                    break;
                case A_TURNOS:
                    filaHorario.append(cont.getString(R.string.dias_lab_a_turnos));
                    break;
                default:
                    filaHorario.append(cont.getString(R.string.desconocido));
                    break;
            }

            filaHorario.append(" ; ");
            filaHorario.append(cont.getString(R.string.fila_horario));
            filaHorario.append(' ');
        }

        if(trabajo.getHorario()!=null)
            switch(trabajo.getHorario()){
                case DIURNO:
                    filaHorario.append(cont.getString(R.string.horario_diurno));
                    break;
                case NOCTURNO:
                    filaHorario.append(cont.getString(R.string.horario_nocturno));
                    break;
                case DISCONTINUO:
                    filaHorario.append(cont.getString(R.string.horario_discontinuo));
                    break;
                case ROTATIVO:
                    filaHorario.append(cont.getString(R.string.horario_rotativo));
                    break;
                case MEDIA_MATUTINA:
                    filaHorario.append(cont.getString(R.string.horario_media_matutina));
                    break;
                case MEDIA_VESPERTINA:
                    filaHorario.append(cont.getString(R.string.horario_media_vespertina));
                    break;
                case MEDIA_NOCTURNA:
                    filaHorario.append(cont.getString(R.string.horario_media_nocturna));
                    break;
                case MEDIA_ROTATIVA:
                    filaHorario.append(cont.getString(R.string.horario_media_rotativa));
                    break;
                default:
                    filaHorario.append(cont.getString(R.string.desconocido));
                    break;
            }

        return filaHorario;
    }


    //Fila salario "Sueldo estimado: $xxxxx"
    public static StringBuffer getFilaSalario(Context cont, Trabajo trabajo){
        StringBuffer filaSalario = new StringBuffer();

        filaSalario.append(cont.getString(R.string.fila_salario_estimado));
        filaSalario.append(' ');
        filaSalario.append('$');
        if(trabajo.getSalario()!=null)
            filaSalario.append(trabajo.getSalario().toString());
        else
            filaSalario.append(cont.getString(R.string.desconocido));

        return filaSalario;
    }


    public static StringBuffer getFilaFechaAlta(Context cont, Trabajo trabajo){
        StringBuffer filaFechaAlta = new StringBuffer();

        filaFechaAlta.append(cont.getString(R.string.fila_fecha_publicacion));
        filaFechaAlta.append(' ');
        if(trabajo.getFechaAlta()!=null)
            filaFechaAlta.append(trabajo.getFechaAltaString());
        else
            filaFechaAlta.append(cont.getString(R.string.desconocido));
        filaFechaAlta.append("hs");

        return filaFechaAlta;
    }

}
