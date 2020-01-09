package utn.aplicaciones.riquelmito.utilidades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import utn.aplicaciones.riquelmito.R;
import utn.aplicaciones.riquelmito.domain.Trabajo;

public class TrabajoDesdeEmpleadorAdapter extends RecyclerView.Adapter {

    private Context cont;
    private ArrayList <Trabajo> lstTrabajos;

    public TrabajoDesdeEmpleadorAdapter(Context context, ArrayList<Trabajo> listaTrabajos) {
        this.cont = context;
        this.lstTrabajos = listaTrabajos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(cont).inflate(R.layout.fila_trabajo_empleador,null);

        return new TrabajosDesdeEmpleadorHolder(contentView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Trabajo trabajo = lstTrabajos.get(position);
        TrabajosDesdeEmpleadorHolder hold = (TrabajosDesdeEmpleadorHolder) holder;
        StringBuffer filaHorario = new StringBuffer();
        StringBuffer filaFechaAlta = new StringBuffer();

        switch (trabajo.getRubro()){
            case ATENCION_AL_PUBLICO:
                hold.ivFilaTrabEmpleadorIconoTrabajo.setImageResource(R.drawable.atencion_al_publico);
                hold.tvFilaTrabEmpleadorRubro.setText(cont.getString(R.string.rubro_atencion_al_publico));
                break;
            case COMUNICACIONES:
                hold.ivFilaTrabEmpleadorIconoTrabajo.setImageResource(R.drawable.comunicaciones);
                hold.tvFilaTrabEmpleadorRubro.setText(cont.getString(R.string.rubro_comunicaciones));
                break;
            case CONSTRUCCION:
                hold.ivFilaTrabEmpleadorIconoTrabajo.setImageResource(R.drawable.construccion);
                hold.tvFilaTrabEmpleadorRubro.setText(cont.getString(R.string.rubro_construccion));
                break;
            case ELECTRICIDAD:
                hold.ivFilaTrabEmpleadorIconoTrabajo.setImageResource(R.drawable.electricidad);
                hold.tvFilaTrabEmpleadorRubro.setText(cont.getString(R.string.rubro_electricidad));
                break;
            case INFORMATICA:
                hold.ivFilaTrabEmpleadorIconoTrabajo.setImageResource(R.drawable.informatica);
                hold.tvFilaTrabEmpleadorRubro.setText(cont.getString(R.string.rubro_informatica));
                break;
            case RRHH:
                hold.ivFilaTrabEmpleadorIconoTrabajo.setImageResource(R.drawable.rrhh);
                hold.tvFilaTrabEmpleadorRubro.setText(cont.getString(R.string.rubro_rrhh));
                break;
            case SALUD:
                hold.ivFilaTrabEmpleadorIconoTrabajo.setImageResource(R.drawable.salud);
                hold.tvFilaTrabEmpleadorRubro.setText(cont.getString(R.string.rubro_salud));
                break;
            case TRANSPORTE:
                hold.ivFilaTrabEmpleadorIconoTrabajo.setImageResource(R.drawable.transporte);
                hold.tvFilaTrabEmpleadorRubro.setText(cont.getString(R.string.rubro_transporte));
                break;
            default:
                hold.ivFilaTrabEmpleadorIconoTrabajo.setImageResource(R.drawable.desconocido);
                hold.tvFilaTrabEmpleadorRubro.setText(cont.getString(R.string.desconocido));
                break;
        }

        if(trabajo.getCargo()!=null)
            hold.tvFilaTrabEmpleadorCargo.setText(trabajo.getCargo());
        else
            hold.tvFilaTrabEmpleadorCargo.setText(cont.getString(R.string.desconocido));

        //Fila horario "Lun - Vie; Horario: Diurno"
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
        hold.tvFilaTrabEmpleadorHorario.setText(filaHorario);

        filaFechaAlta.append(cont.getString(R.string.fila_fecha_publicacion));
        filaFechaAlta.append(' ');
        if(trabajo.getFechaAlta()!=null)
            filaFechaAlta.append(trabajo.getFechaAltaString());
        else
            filaFechaAlta.append(cont.getString(R.string.desconocido));
        filaFechaAlta.append("hs");
        hold.tvFilaTrabEmpleadorPublicacion.setText(filaFechaAlta);
    }

    @Override
    public int getItemCount() {
        return lstTrabajos.size();
    }


    //------HOLDER CLASS-------
    public static class TrabajosDesdeEmpleadorHolder extends RecyclerView.ViewHolder {
        private ImageView ivFilaTrabEmpleadorIconoTrabajo;
        private TextView tvFilaTrabEmpleadorRubro;
        private TextView tvFilaTrabEmpleadorCargo;
        private TextView tvFilaTrabEmpleadorHorario;
        private TextView tvFilaTrabEmpleadorPublicacion;

        public TrabajosDesdeEmpleadorHolder(@NonNull View itemView) {
            super(itemView);
            ivFilaTrabEmpleadorIconoTrabajo = itemView.findViewById(R.id.ivFilaTrabEmpleadorIconoTrabajo);
            tvFilaTrabEmpleadorRubro = itemView.findViewById(R.id.tvFilaTrabEmpleadorRubro);
            tvFilaTrabEmpleadorCargo = itemView.findViewById(R.id.tvFilaTrabEmpleadorCargo);
            tvFilaTrabEmpleadorHorario = itemView.findViewById(R.id.tvFilaTrabEmpleadorHorario);
            tvFilaTrabEmpleadorPublicacion = itemView.findViewById(R.id.tvFilaTrabEmpleadorPublicacion);
        }
    }
}
