package ca.bcit.daily_fitness.ui.diet;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import ca.bcit.daily_fitness.R;

public class DietFragment extends Fragment{

    private DietViewModel dietViewModel;
    private TextView under;
    private TextView normal;
    private TextView over;
    private TextView obese;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dietViewModel =
                ViewModelProviders.of(this).get(DietViewModel.class);
        View root = inflater.inflate(R.layout.fragment_diet, container, false);
        final TextView textView = root.findViewById(R.id.text_diet);
        dietViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        under = (TextView)root.findViewById(R.id.under);
        under.setText("Heavy food items that are more in calories.\n" +
                "Frequent consumption of food items which are rich in nutrients, it could be snacks, shakes or juices, or proper meals.\n" +
                "Adding extra ingredients that are high in calories to regular diet, for example, including eggs and bananas in morning breakfast, etc., can help in increasing the weight.\n" +
                "Consume protein supplements along with adequate amount of vegetables and fruits.\n" +
                "Eating calorie dense food and maintaining a balanced diet will help in gaining the weight.\n" +
                "However, the diet shouldn’t be started drastically and instead, should be implemented gradually so that the body is accustomed with it.");
        normal = root.findViewById(R.id.normal);
        normal.setText("As part of an overall healthy diet, choose whole-grain breads, pastas and cereals; fruits and vegetables; dairy products; lean protein sources; and nuts and seeds. Try smoothies and shakes. Don't fill up on diet soda, coffee and other drinks with few calories and little nutritional value.");
        over = root.findViewById(R.id.over);
        over.setText("Whole grains (whole wheat, steel cut oats, brown rice, quinoa)\n" +
                "Vegetables (a colorful variety-not potatoes)\n" +
                "Whole fruits (not fruit juices)\n" +
                "Nuts, seeds, beans, and other healthful sources of protein (fish and poultry)\n" +
                "Plant oils (olive and other vegetable oils)");
        obese = root.findViewById(R.id.obese);
        obese.setText("Reducing the likelihood of stopping for unhealthy restaurant or take-out food because there's nothing to eat at home.\n" +
                "Ensuring that your meals are balanced with the ideal amount of protein, complex carbohydrates and healthy fats, plus a good amount of vitamin- and mineral-containing foods, such as fruits and vegetables.\n" +
                "Allowing you to control the amount of sodium, saturated fat and other nutrients that are problematic in large quantities.");
        return root;
    }
}