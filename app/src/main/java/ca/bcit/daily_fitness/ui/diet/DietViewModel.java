package ca.bcit.daily_fitness.ui.diet;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DietViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DietViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Recommended Diet ");
    }

    public LiveData<String> getText() {
        return mText;
    }
}