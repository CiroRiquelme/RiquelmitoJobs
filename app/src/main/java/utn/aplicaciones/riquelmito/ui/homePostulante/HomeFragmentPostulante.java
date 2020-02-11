package utn.aplicaciones.riquelmito.ui.homePostulante;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import utn.aplicaciones.riquelmito.R;

public class HomeFragmentPostulante extends Fragment {

    private HomeViewModelPostulante homeViewModelPostulante;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModelPostulante =
                ViewModelProviders.of(this).get(HomeViewModelPostulante.class);
        View root = inflater.inflate(R.layout.fragment_home_postulante, container, false);
        return root;
    }
}