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
import utn.aplicaciones.riquelmito.domain.Rubro;
import utn.aplicaciones.riquelmito.domain.Trabajo;

public class TrabajoDesdePostulanteAdapter extends RecyclerView.Adapter {

    private Context cont;
    private ArrayList <Trabajo> lstTrabajos;

    public TrabajoDesdePostulanteAdapter(Context context, ArrayList<Trabajo> listaTrabajos) {
        this.cont = context;
        this.lstTrabajos = listaTrabajos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(cont).inflate(R.layout.fila_trabajo_postulante,null);

        return new TrabajosDesdePostulanteHolder(contentView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Trabajo trabajo = lstTrabajos.get(position);
        TrabajosDesdePostulanteHolder hold = (TrabajosDesdePostulanteHolder) holder;
        StringBuffer filaHorario = new StringBuffer();
        StringBuffer filaSalario = new StringBuffer();

        switch (trabajo.getRubro()){
            case ATENCION_AL_PUBLICO:
                hold.ivFilaTrabPostulanteIconoTrabajo.setImageResource(R.drawable.atencion_al_publico);
                hold.tvFilaTrabPostulanteRubro.setText(cont.getString(R.string.rubro_atencion_al_publico));
                break;
            case COMUNICACIONES:
                hold.ivFilaTrabPostulanteIconoTrabajo.setImageResource(R.drawable.comunicaciones);
                hold.tvFilaTrabPostulanteRubro.setText(cont.getString(R.string.rubro_comunicaciones));
                break;
            case CONSTRUCCION:
                hold.ivFilaTrabPostulanteIconoTrabajo.setImageResource(R.drawable.construccion);
                hold.tvFilaTrabPostulanteRubro.setText(cont.getString(R.string.rubro_construccion));
                break;
            case ELECTRICIDAD:
                hold.ivFilaTrabPostulanteIconoTrabajo.setImageResource(R.drawable.electricidad);
                hold.tvFilaTrabPostulanteRubro.setText(cont.getString(R.string.rubro_electricidad));
                break;
            case INFORMATICA:
                hold.ivFilaTrabPostulanteIconoTrabajo.setImageResource(R.drawable.informatica);
                hold.tvFilaTrabPostulanteRubro.setText(cont.getString(R.string.rubro_informatica));
                break;
            case RRHH:
                hold.ivFilaTrabPostulanteIconoTrabajo.setImageResource(R.drawable.rrhh);
                hold.tvFilaTrabPostulanteRubro.setText(cont.getString(R.string.rubro_rrhh));
                break;
            case SALUD:
                hold.ivFilaTrabPostulanteIconoTrabajo.setImageResource(R.drawable.salud);
                hold.tvFilaTrabPostulanteRubro.setText(cont.getString(R.string.rubro_salud));
                break;
            case TRANSPORTE:
                hold.ivFilaTrabPostulanteIconoTrabajo.setImageResource(R.drawable.transporte);
                hold.tvFilaTrabPostulanteRubro.setText(cont.getString(R.string.rubro_transporte));
                break;
            default:
                hold.ivFilaTrabPostulanteIconoTrabajo.setImageResource(R.drawable.desconocido);
                hold.tvFilaTrabPostulanteRubro.setText(cont.getString(R.string.desconocido));
                break;
        }

        if(trabajo.getCargo()!=null)
            hold.tvFilaTrabPostulanteCargo.setText(trabajo.getCargo());
        else
            hold.tvFilaTrabPostulanteCargo.setText(cont.getString(R.string.desconocido));

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
        hold.tvFilaTrabPostulanteHorario.setText(filaHorario);

        //Fila salario "Sueldo estimado: $xxxxx"
        filaSalario.append(cont.getString(R.string.fila_salario_estimado));
        filaSalario.append(' ');
        filaSalario.append('$');
        if(trabajo.getSalario()!=null)
            filaSalario.append(trabajo.getSalario().toString());
        else
            filaHorario.append(cont.getString(R.string.desconocido));
        hold.tvFilaTrabPostulanteSalario.setText(filaSalario);
    }

    @Override
    public int getItemCount() {
        return lstTrabajos.size();
    }

    public static class TrabajosDesdePostulanteHolder extends RecyclerView.ViewHolder {
        private ImageView ivFilaTrabPostulanteIconoTrabajo;
        private TextView tvFilaTrabPostulanteRubro;
        private TextView tvFilaTrabPostulanteCargo;
        private TextView tvFilaTrabPostulanteHorario;
        private TextView tvFilaTrabPostulanteSalario;

        public TrabajosDesdePostulanteHolder(@NonNull View itemView) {
            super(itemView);
            ivFilaTrabPostulanteIconoTrabajo = itemView.findViewById(R.id.ivFilaTrabPostulanteIconoTrabajo);
            tvFilaTrabPostulanteRubro = itemView.findViewById(R.id.tvFilaTrabPostulanteRubro);
            tvFilaTrabPostulanteCargo = itemView.findViewById(R.id.tvFilaTrabPostulanteCargo);
            tvFilaTrabPostulanteHorario = itemView.findViewById(R.id.tvFilaTrabPostulanteHorario);
            tvFilaTrabPostulanteSalario = itemView.findViewById(R.id.tvFilaTrabPostulanteSalario);
        }
    }
}
