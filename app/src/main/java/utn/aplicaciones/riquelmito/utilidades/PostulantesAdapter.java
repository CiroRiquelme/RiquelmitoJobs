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

public class PostulantesAdapter extends RecyclerView.Adapter {

    private Context cont;
    private ArrayList <Trabajo> lstTrabajos;

    public PostulantesAdapter(Context context, ArrayList<Trabajo> listaTrabajos) {
        this.cont = context;
        this.lstTrabajos = listaTrabajos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(cont).inflate(R.layout.fila_postulante,null);

        return new PostulanteHolder(contentView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        /*Trabajo trabajo = lstTrabajos.get(position);
        PostulanteHolder hold = (PostulanteHolder) holder;
        switch (trabajo.getRubro()){
            case ATENCION_AL_PUBLICO:
                hold.ivFilaIconoTrabajo.setImageResource(R.drawable.atencion_al_publico);
                hold.tvFilaRubro.setText(cont.getString(R.string.rubro_atencion_al_publico));
                break;
            case COMUNICACIONES:
                hold.ivFilaIconoTrabajo.setImageResource(R.drawable.comunicaciones);
                hold.tvFilaRubro.setText(cont.getString(R.string.rubro_comunicaciones));
                break;
            case CONSTRUCCION:
                hold.ivFilaIconoTrabajo.setImageResource(R.drawable.construccion);
                hold.tvFilaRubro.setText(cont.getString(R.string.rubro_construccion));
                break;
            case ELECTRICIDAD:
                hold.ivFilaIconoTrabajo.setImageResource(R.drawable.electricidad);
                hold.tvFilaRubro.setText(cont.getString(R.string.rubro_electricidad));
                break;
            case INFORMATICA:
                hold.ivFilaIconoTrabajo.setImageResource(R.drawable.informatica);
                hold.tvFilaRubro.setText(cont.getString(R.string.rubro_informatica));
                break;
            case RRHH:
                hold.ivFilaIconoTrabajo.setImageResource(R.drawable.rrhh);
                hold.tvFilaRubro.setText(cont.getString(R.string.rubro_rrhh));
                break;
            case SALUD:
                hold.ivFilaIconoTrabajo.setImageResource(R.drawable.salud);
                hold.tvFilaRubro.setText(cont.getString(R.string.rubro_salud));
                break;
            case TRANSPORTE:
                hold.ivFilaIconoTrabajo.setImageResource(R.drawable.transporte);
                hold.tvFilaRubro.setText(cont.getString(R.string.rubro_transporte));
                break;
            default:
                hold.ivFilaIconoTrabajo.setImageResource(R.drawable.desconocido);
                hold.tvFilaRubro.setText(cont.getString(R.string.desconocido));
                break;
        }

        if(trabajo.getCargo()!=null)
            hold.tvFilaCargo.setText(trabajo.getCargo());
        else
            hold.tvFilaCargo.setText(cont.getString(R.string.desconocido));

        switch (trabajo.getDias()) {
            case LUN_VIE:
                hold.tvFilaDiasLaborales.setText(R.string.dias_lab_lun_viern);
                break;
            case LUN_SAB:
                hold.tvFilaDiasLaborales.setText(R.string.dias_lab_lun_sab);
                break;
            case MART_SAB:
                hold.tvFilaDiasLaborales.setText(R.string.dias_lab_mart_sab);
                break;
            case MART_DOM:
                hold.tvFilaDiasLaborales.setText(R.string.dias_lab_mart_domin);
                break;
            case FERIAD_DOM:
                hold.tvFilaDiasLaborales.setText(R.string.dias_lab_feriad_domin);
                break;
            case FERIAD_FINSEMANA:
                hold.tvFilaDiasLaborales.setText(R.string.dias_lab_feriad_find_seman);
                break;
            case A_TURNOS:
                hold.tvFilaDiasLaborales.setText(R.string.dias_lab_a_turnos);
                break;
            default:
                hold.tvFilaDiasLaborales.setText(R.string.desconocido);
                break;
        }

        switch(trabajo.getHorario()){
            case DIURNO:
                hold.tvFilaHorario.setText(R.string.horario_diurno);
                break;
            case NOCTURNO:
                hold.tvFilaHorario.setText(R.string.horario_nocturno);
                break;
            case DISCONTINUO:
                hold.tvFilaHorario.setText(R.string.horario_discontinuo);
                break;
            case ROTATIVO:
                hold.tvFilaHorario.setText(R.string.horario_rotativo);
                break;
            case MEDIA_MATUTINA:
                hold.tvFilaHorario.setText(R.string.horario_media_matutina);
                break;
            case MEDIA_VESPERTINA:
                hold.tvFilaHorario.setText(R.string.horario_media_vespertina);
                break;
            case MEDIA_NOCTURNA:
                hold.tvFilaHorario.setText(R.string.horario_media_nocturna);
                break;
            case MEDIA_ROTATIVA:
                hold.tvFilaHorario.setText(R.string.horario_media_rotativa);
                break;
            default:
                hold.tvFilaHorario.setText(R.string.desconocido);
                break;
        }

        if(trabajo.getSalario()!=null)
            hold.tvFilaSalario.setText("$" + trabajo.getSalario().toString());
        else
            hold.tvFilaSalario.setText(cont.getString(R.string.desconocido));*/
    }

    @Override
    public int getItemCount() {
        return lstTrabajos.size();
    }

    public static class PostulanteHolder extends RecyclerView.ViewHolder {
        private ImageView ivFilaIconoUser;
        private TextView tvFilaNombre;
        private TextView tvFilaEdad;
        private TextView tvFilaSexo;
        private TextView tvFilaCiudad;

        public PostulanteHolder(@NonNull View itemView) {
            super(itemView);
            ivFilaIconoUser = itemView.findViewById(R.id.ivFilaIconoUser);
            tvFilaNombre = itemView.findViewById(R.id.tvFilaNombre);
            tvFilaEdad = itemView.findViewById(R.id.tvFilaEdad);
            tvFilaSexo = itemView.findViewById(R.id.tvFilaSexo);
            tvFilaCiudad = itemView.findViewById(R.id.tvFilaCiudad);
        }
    }
}
