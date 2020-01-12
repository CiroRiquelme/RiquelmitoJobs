package utn.aplicaciones.riquelmito;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

import utn.aplicaciones.riquelmito.domain.Usuario;
import utn.aplicaciones.riquelmito.utilidades.PostulantesAdapter;

public class UsuariosPostuladosATrabajoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios_postulados_a_trabajo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.rvUsuariosPostuladosATrabajo);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        adapter = new PostulantesAdapter( this, cargarPostulantes() );
        recyclerView.setAdapter(adapter);
    }

    //TODO: Eliminar este método una vez que se termine de probar
    private ArrayList<Usuario> cargarPostulantes() {
        ArrayList<Usuario> arre = new ArrayList<Usuario>();

        //TODO: cargar para hacer pruebas

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
