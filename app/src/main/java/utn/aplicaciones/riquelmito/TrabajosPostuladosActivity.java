package utn.aplicaciones.riquelmito;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import utn.aplicaciones.riquelmito.domain.DiasLaborales;
import utn.aplicaciones.riquelmito.domain.HorarioLaboral;
import utn.aplicaciones.riquelmito.domain.Rubro;
import utn.aplicaciones.riquelmito.domain.Trabajo;
import utn.aplicaciones.riquelmito.utilidades.TrabajoDesdePostulanteAdapter;

public class TrabajosPostuladosActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TrabajoDesdePostulanteAdapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabajos_postulados);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.rvTrabajosPostulados);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        adapter = new TrabajoDesdePostulanteAdapter( this, cargarTrabajosDePrueba() );
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToInfoTrabajoDesdeTrabajosPostulados( adapter.getListaTrabajos().get(recyclerView.getChildAdapterPosition(view)) );
            }
        });

        recyclerView.setAdapter(adapter);
    }

    public void goToInfoTrabajoDesdeTrabajosPostulados(Trabajo trabajo){
        Intent intent = new Intent(this, InfoTrabajoDesdePostulanteActivity.class);

        //Pasa una instancia de Trabajo a la actividad
        Bundle bundle = new Bundle();
        bundle.putSerializable("trabajo",trabajo);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    //TODO: Eliminar este método una vez que se termine de probar
    private ArrayList<Trabajo> cargarTrabajosDePrueba(){
        ArrayList<Trabajo> arre = new ArrayList<Trabajo>();

        arre.add( new Trabajo(Rubro.ATENCION_AL_PUBLICO,"Recepcionista","Una descripción","Un perfil de empleado.","Alguna experiencia del usuario O.O", DiasLaborales.LUN_VIE,HorarioLaboral.DISCONTINUO,17000, -34.6080556,-58.3702778));
        arre.add( new Trabajo(Rubro.COMUNICACIONES,"Asdsasd asdsa assda as","Una descripción","Un perfil de empleado.","Alguna experiencia del usuario O.O", DiasLaborales.LUN_SAB,HorarioLaboral.DIURNO,35000, -34.6080556,-58.3702778));
        arre.add( new Trabajo(Rubro.CONSTRUCCION,"Albañil","Una descripción","Un perfil de empleado.","Alguna experiencia del usuario O.O", DiasLaborales.MART_SAB,HorarioLaboral.ROTATIVO,17000, -34.6080556,-58.3702778));
        arre.add( new Trabajo(Rubro.ELECTRICIDAD,"Electricista ...","Una descripción","Un perfil de empleado.","Alguna experiencia del usuario O.O", DiasLaborales.MART_DOM,HorarioLaboral.MEDIA_ROTATIVA,17000, -34.6080556,-58.3702778));
        arre.add( new Trabajo(Rubro.INFORMATICA,"Recepcionista","Una descripción","Un perfil de empleado.","Alguna experiencia del usuario O.O", DiasLaborales.FERIAD_DOM,HorarioLaboral.NOCTURNO,17000, -34.6080556,-58.3702778));
        arre.add( new Trabajo(Rubro.RRHH,"Recepcionista","Una descripción","Un perfil de empleado.","Alguna experiencia del usuario O.O", DiasLaborales.FERIAD_FINSEMANA,HorarioLaboral.MEDIA_MATUTINA,17000, -34.6080556,-58.3702778));
        arre.add( new Trabajo(Rubro.SALUD,"Recepcionista","Una descripción","Un perfil de empleado.","Alguna experiencia del usuario O.O", DiasLaborales.A_TURNOS,HorarioLaboral.MEDIA_VESPERTINA,17000, -34.6080556,-58.3702778));
        arre.add( new Trabajo(Rubro.TRANSPORTE,"Recepcionista","Una descripción","Un perfil de empleado.","Alguna experiencia del usuario O.O", DiasLaborales.LUN_VIE,HorarioLaboral.MEDIA_NOCTURNA,17000, -34.6080556,-58.3702778));

        return arre;
    }


    //Esta función permite que el botón de 'volver atrás' de la barra superior funcione
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
