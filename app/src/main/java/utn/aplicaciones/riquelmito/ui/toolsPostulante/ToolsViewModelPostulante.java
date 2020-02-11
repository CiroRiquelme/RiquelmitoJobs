package utn.aplicaciones.riquelmito.ui.toolsPostulante;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ToolsViewModelPostulante extends ViewModel {

    private MutableLiveData<String> mText;

    public ToolsViewModelPostulante() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}