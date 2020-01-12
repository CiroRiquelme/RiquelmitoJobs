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
import utn.aplicaciones.riquelmito.domain.Usuario;

public class PostulantesAdapter extends RecyclerView.Adapter {

    private Context cont;
    private ArrayList <Usuario> lstPostulantes;

    public PostulantesAdapter(Context context, ArrayList<Usuario> listaPostulantes) {
        this.cont = context;
        this.lstPostulantes = listaPostulantes;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(cont).inflate(R.layout.fila_postulante,null);

        return new PostulanteHolder(contentView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Usuario postulante = lstPostulantes.get(position);
        PostulanteHolder hold = (PostulanteHolder) holder;
        String separadorAtributo = ", ";
        String delimitadorAtributo = ". ";
        StringBuffer lineaDescripcion1 = new StringBuffer();
        StringBuffer lineaDescripcion2 = new StringBuffer();

        if( postulante.getNombre()!=null || postulante.getApellido()!=null ){
            StringBuffer tituloFila = new StringBuffer();
            tituloFila.append( postulante.getApellido() );
            tituloFila.append( separadorAtributo );
            tituloFila.append( postulante.getNombre() );
            hold.tvFilaPostulanteNombre.setText( tituloFila );
        }
        else{
            hold.tvFilaPostulanteNombre.setText(cont.getString(R.string.desconocido));
        }

        //Rellena linea 1: "Edad: xx. Sexo: xxxxxxx."
        lineaDescripcion1.append(cont.getString(R.string.fila_edad_postulante));
        lineaDescripcion1.append(' ');
        lineaDescripcion1.append(postulante.getEdad());
        lineaDescripcion1.append(cont.getString(R.string.fila_anios_postulante));
        lineaDescripcion1.append(delimitadorAtributo);

        lineaDescripcion1.append(cont.getString(R.string.fila_sexo_postulante));
        lineaDescripcion1.append(' ');
        switch (postulante.getSexo()){
            case FEMENINO:
                hold.ivFilaIconoUser.setImageResource(R.drawable.sexo_femenino);
                lineaDescripcion1.append(cont.getString(R.string.sexo_femenino));
                break;
            case MASCULINO:
                hold.ivFilaIconoUser.setImageResource(R.drawable.sexo_masculino);
                lineaDescripcion1.append(cont.getString(R.string.sexo_masculino));
                break;
            default:
                hold.ivFilaIconoUser.setImageResource(R.drawable.sexo_otro);
                lineaDescripcion1.append(cont.getString(R.string.sexo_otro));
                break;
        }
        hold.tvFilaPostulanteDescripcion1.setText(lineaDescripcion1);

        //Rellena linea 2: "Ciudad: xxxxxxxxxxx, XXXXXXXXX."
        lineaDescripcion2.append(cont.getString(R.string.fila_ciudad_postulante));
        lineaDescripcion2.append(' ');
        if ( postulante.getCiudad()!=null ) {
            lineaDescripcion2.append(postulante.getCiudad());
        }
        else{
            lineaDescripcion2.append(cont.getString(R.string.desconocido));
        }
        lineaDescripcion2.append('(');
        if ( postulante.getProvincia()!=null ) {
            lineaDescripcion2.append(postulante.getProvincia());
        }
        else{
            lineaDescripcion2.append(cont.getString(R.string.desconocido));
        }
        lineaDescripcion2.append(").");
        hold.tvFilaPostulanteDescripcion2.setText(lineaDescripcion2);
    }

    @Override
    public int getItemCount() {
        return lstPostulantes.size();
    }

    public static class PostulanteHolder extends RecyclerView.ViewHolder {
        private ImageView ivFilaIconoUser;
        private TextView tvFilaPostulanteNombre;
        private TextView tvFilaPostulanteDescripcion1;
        private TextView tvFilaPostulanteDescripcion2;

        public PostulanteHolder(@NonNull View itemView) {
            super(itemView);
            ivFilaIconoUser = itemView.findViewById(R.id.ivFilaIconoUser);
            tvFilaPostulanteNombre = itemView.findViewById(R.id.tvFilaPostulanteNombre);
            tvFilaPostulanteDescripcion1 = itemView.findViewById(R.id.tvFilaPostulanteDescripcion1);
            tvFilaPostulanteDescripcion2 = itemView.findViewById(R.id.tvFilaPostulanteDescripcion2);
        }
    }
}
