package utn.aplicaciones.riquelmito.ui.homePostulante;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModelPostulante extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModelPostulante() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}