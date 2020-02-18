package utn.aplicaciones.riquelmito;

import com.google.firebase.database.FirebaseDatabase;

public class Riquelminio extends  android.app.Application {

    @Override
    public void onCreate(){
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
