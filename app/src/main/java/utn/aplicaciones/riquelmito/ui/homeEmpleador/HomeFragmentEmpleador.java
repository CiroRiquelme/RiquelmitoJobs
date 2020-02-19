package utn.aplicaciones.riquelmito.ui.homeEmpleador;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import utn.aplicaciones.riquelmito.R;

public class HomeFragmentEmpleador extends Fragment {

    private HomeViewModelEmpleador homeViewModelEmpleador;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModelEmpleador =
                ViewModelProviders.of(this).get(HomeViewModelEmpleador.class);
        View root = inflater.inflate(R.layout.fragment_home_empleador, container, false);
        return root;
    }
}