package utn.aplicaciones.riquelmito.ui.toolsEmpleador;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ToolsViewModelEmpleador extends ViewModel {

    private MutableLiveData<String> mText;

    public ToolsViewModelEmpleador() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}