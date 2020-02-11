package utn.aplicaciones.riquelmito.ui.toolsEmpleador;

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

public class ToolsFragmentEmpleador extends Fragment {

    private ToolsViewModelEmpleador toolsViewModelEmpleador;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModelEmpleador =
                ViewModelProviders.of(this).get(ToolsViewModelEmpleador.class);
        View root = inflater.inflate(R.layout.fragment_tools_empleador, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        toolsViewModelEmpleador.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}