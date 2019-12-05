package ca.bcit.daily_fitness.ui.home;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import ca.bcit.daily_fitness.R;

public class HomeFragment extends Fragment implements SensorEventListener {

    private HomeViewModel homeViewModel;
    TextView tv_steps;
    SensorManager sensorManager;
    boolean running = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        tv_steps = root.findViewById(R.id.tv_steps);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        return root;
    }
    @Override
    public void onResume(){
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor!=null){
            sensorManager.registerListener(this,countSensor,sensorManager.SENSOR_DELAY_UI);
        } else{
            Context context = getActivity();
            CharSequence text = "Sensor not found";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
    @Override
    public void onPause(){
        super.onPause();
        running =false;
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        if(running){
            tv_steps.setText(String.valueOf(event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }
}