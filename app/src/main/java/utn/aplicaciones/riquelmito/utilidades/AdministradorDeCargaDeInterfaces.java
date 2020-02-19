package utn.aplicaciones.riquelmito.utilidades;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utn.aplicaciones.riquelmito.R;
import utn.aplicaciones.riquelmito.domain.AdministradorDeSesion;
import utn.aplicaciones.riquelmito.domain.Trabajo;
import utn.aplicaciones.riquelmito.domain.Usuario;

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


    //Devuelve la fila de fecha de creación de un trabajo que se usa en las filas de los RecyclerView de trabajo
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


    public static int getIdIconoSexo (Context cont, Usuario usuario){
        if(usuario.getSexo() == null)
            return R.drawable.sexo_otro;

        switch(usuario.getSexo()){
            case MASCULINO:
                return R.drawable.sexo_masculino;
            case FEMENINO:
                return R.drawable.sexo_femenino;
            default:
                return R.drawable.sexo_otro;
        }
    }

    public static StringBuffer getFilaNombreUsuario(Context cont, Usuario postulante){
        StringBuffer filaNombre = new StringBuffer();

        if(postulante.getApellido() != null)
            filaNombre.append(postulante.getApellido());

        if(postulante.getNombre() != null){
            if(filaNombre.length() > 0)
                filaNombre.append(',');
            filaNombre.append(postulante.getNombre());
        }

        if(filaNombre.length() == 0){
            filaNombre.append(cont.getString(R.string.fila_nombre_postulante_desconocido));
        }

        return filaNombre;
    }

    public static StringBuffer getFilaRecidencia(Context cont, Usuario postulante){
        StringBuffer filaRecidencia = new StringBuffer();

        if(postulante.getCiudad() != null)
            filaRecidencia.append(postulante.getCiudad());

        if(postulante.getProvincia() != null){
            if(filaRecidencia.length() == 0){
                filaRecidencia.append(postulante.getProvincia());
            }
            else{
                filaRecidencia.append('(');
                filaRecidencia.append(postulante.getProvincia());
                filaRecidencia.append(')');
            }
        }

        if(filaRecidencia.length() == 0){
            filaRecidencia.append(cont.getString(R.string.fila_ubicacion_desconocida));
        }

        return filaRecidencia;
    }

    public static StringBuffer getFilaSexoEdad(Context cont, Usuario postulante){
        StringBuffer filaSexoEdad = new StringBuffer();

        filaSexoEdad.append(cont.getString(R.string.fila_sexo_postulante));
        filaSexoEdad.append(' ');
        if(postulante.getSexo() != null)
            filaSexoEdad.append(postulante.getSexo().toString());
        else
            filaSexoEdad.append('-');
        filaSexoEdad.append(';');
        filaSexoEdad.append(' ');
        filaSexoEdad.append(cont.getString(R.string.fila_edad_postulante));
        filaSexoEdad.append(' ');
        if(postulante.getEdad() != null && postulante.getEdad() != 0){
            filaSexoEdad.append(postulante.getEdad());
            filaSexoEdad.append(' ');
            filaSexoEdad.append(cont.getString(R.string.fila_anios_postulante));
        }
        else
            filaSexoEdad.append('-');

        return filaSexoEdad;
    }

    /***
     * Verifica si un Email es valido
     * @param email: String de la dirección de email
     * @return true - si el formato de 'email' corresponde al de un email valido. false - en otro caso
     */
    public static boolean esEmailValido(String email){
        String expression = "^[a-zA-Z0-9]([\\w\\.-]?[a-zA-Z0-9])*@[a-zA-Z0-9]([-_]?[a-zA-Z0-9])+\\.[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /***
     * Convierte un Date en un String de formato 'dd/mm/yyyy'
     * @param date: instancia de clase Date
     * @return String correspondiente al Date pasado por valor. En caso de excepción retorna 'null'
     */
    public static String dateToString(Date date){
        try{
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.format(AdministradorDeSesion.postulante.getNacimiento());
        }
        catch (Exception e){
            return null;
        }
    }

    /***
     * Convierte un String de fecha en formato 'dd/mm/yyyy' a instancia de clase Date
     * @param fecha: String de fecha en formato 'dd/mm/yyyy'
     * @return Instancia de Date correspondiente al String pasado por valor. Si el String no tiene el formato correcto retorna 'null'
     */
    public static Date stringToDate(String fecha){
        try{
            DateFormat fechaParser = new SimpleDateFormat("dd/mm/yyyy");
            return fechaParser.parse(fecha);
        }
        catch(Exception e){
            return null;
        }
    }

}
