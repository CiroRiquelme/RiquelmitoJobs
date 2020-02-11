package utn.aplicaciones.riquelmito.ui.toolsPostulante;

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

public class ToolsFragmentPostulante extends Fragment {

    private ToolsViewModelPostulante toolsViewModelPostulante;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModelPostulante =
                ViewModelProviders.of(this).get(ToolsViewModelPostulante.class);
        View root = inflater.inflate(R.layout.fragment_tools_postulante, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        toolsViewModelPostulante.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}