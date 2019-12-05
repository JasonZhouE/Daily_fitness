package ca.bcit.daily_fitness.ui.home;

import java.text.DateFormat;
import java.util.Date;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(DateFormat.getDateTimeInstance().format(new Date()));
    }

    public LiveData<String> getText() {
        return mText;
    }
}