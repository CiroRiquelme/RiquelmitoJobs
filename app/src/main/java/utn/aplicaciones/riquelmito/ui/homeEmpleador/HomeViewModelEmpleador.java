package utn.aplicaciones.riquelmito.ui.homeEmpleador;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModelEmpleador extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModelEmpleador() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}