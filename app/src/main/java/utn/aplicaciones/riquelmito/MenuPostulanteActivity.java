package utn.aplicaciones.riquelmito;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;

import utn.aplicaciones.riquelmito.domain.AdministradorDeSesion;
import utn.aplicaciones.riquelmito.utilidades.AdministradorDeCargaDeInterfaces;

public class MenuPostulanteActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_postulante);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view_postulante);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home_postulante, R.id.nav_tools_postulante)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent activity;

                switch(menuItem.getItemId()){
                    case R.id.nav_buscar_trabajo:
                        activity = new Intent(MenuPostulanteActivity.this, BuscarTrabajoActivity.class);
                        startActivity(activity);
                        drawer.closeDrawer(GravityCompat.START);    //Cierra el NavigationView
                        break;
                    case R.id.nav_trabajos_postulados:
                        activity = new Intent(MenuPostulanteActivity.this, TrabajosPostuladosActivity.class);
                        startActivity(activity);
                        drawer.closeDrawer(GravityCompat.START);    //Cierra el NavigationView
                        break;
                    case R.id.nav_trabajos_de_interes:
                    case R.id.nav_preferencias_postulante:
                        AlertDialog.Builder builder = new AlertDialog.Builder(MenuPostulanteActivity.this);
                        builder.setTitle(MenuPostulanteActivity.this.getString(R.string.title_dialogo_funcionalidad_no_desarrollada))
                                .setMessage(MenuPostulanteActivity.this.getString(R.string.dialogo_funcionalidad_no_desarrollada))
                                .setNeutralButton(R.string.opcion_ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //No es necesario hacer nada
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        break;
                    case R.id.nav_edit_perfil_usuario:
                        activity = new Intent(MenuPostulanteActivity.this, PerfilUsuarioPostulanteActivity.class);
                        startActivity(activity);
                        drawer.closeDrawer(GravityCompat.START);    //Cierra el NavigationView
                        break;
                    case R.id.nav_edit_perfil_prof:
                        activity = new Intent(MenuPostulanteActivity.this, PerfilProfesionalActivity.class);
                        startActivity(activity);
                        drawer.closeDrawer(GravityCompat.START);    //Cierra el NavigationView
                        break;
                    case R.id.nav_cerrar_sesion:
                        activity = new Intent(MenuPostulanteActivity.this, MainActivity.class);
                        startActivity(activity);
                        AdministradorDeSesion.cerrarSesion();
                        finish();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        View headerView = navigationView.getHeaderView(0);
        TextView navTitle = (TextView) headerView.findViewById(R.id.tvNavHeaderPostulanteTitulo);
        navTitle.setText(AdministradorDeCargaDeInterfaces.getFilaNombreUsuario(this, AdministradorDeSesion.postulante));
        TextView navSubtitle = (TextView) headerView.findViewById(R.id.tvNavHeaderPostulanteSubtitulo);
        navSubtitle.setText(getString(R.string.navigation_view_subtitle));

        try {
            AdministradorDeSesion.buscarUsuario();

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_postulante, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
