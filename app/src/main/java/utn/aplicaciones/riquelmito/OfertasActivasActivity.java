package utn.aplicaciones.riquelmito;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

import utn.aplicaciones.riquelmito.domain.DiasLaborales;
import utn.aplicaciones.riquelmito.domain.HorarioLaboral;
import utn.aplicaciones.riquelmito.domain.Rubro;
import utn.aplicaciones.riquelmito.domain.Trabajo;
import utn.aplicaciones.riquelmito.utilidades.TrabajoDesdeEmpleadorAdapter;

public class OfertasActivasActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertas_activas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.rvOfertasActivas);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        adapter = new TrabajoDesdeEmpleadorAdapter( this, cargarTrabajosDePrueba() );
        recyclerView.setAdapter(adapter);
    }

    //TODO: Eliminar este método una vez que se termine de probar
    private ArrayList<Trabajo> cargarTrabajosDePrueba(){
        ArrayList<Trabajo> arre = new ArrayList<Trabajo>();

        arre.add( new Trabajo(Rubro.ATENCION_AL_PUBLICO,"Recepcionista", DiasLaborales.LUN_VIE, HorarioLaboral.DISCONTINUO,17000));
        arre.add( new Trabajo(Rubro.COMUNICACIONES,"Asdsasd asdsa assda as",DiasLaborales.LUN_SAB,HorarioLaboral.DIURNO,35000));
        arre.add( new Trabajo(Rubro.CONSTRUCCION,"Albañil",DiasLaborales.MART_SAB,HorarioLaboral.ROTATIVO,17000));
        arre.add( new Trabajo(Rubro.ELECTRICIDAD,"Electricista ...",DiasLaborales.MART_DOM,HorarioLaboral.MEDIA_ROTATIVA,17000));
        arre.add( new Trabajo(Rubro.INFORMATICA,"Recepcionista",DiasLaborales.FERIAD_DOM,HorarioLaboral.NOCTURNO,17000));
        arre.add( new Trabajo(Rubro.RRHH,"Recepcionista",DiasLaborales.FERIAD_FINSEMANA,HorarioLaboral.MEDIA_MATUTINA,17000));
        arre.add( new Trabajo(Rubro.SALUD,"Recepcionista",DiasLaborales.A_TURNOS,HorarioLaboral.MEDIA_VESPERTINA,17000));
        arre.add( new Trabajo(Rubro.TRANSPORTE,"Recepcionista",DiasLaborales.LUN_VIE,HorarioLaboral.MEDIA_NOCTURNA,17000));

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
