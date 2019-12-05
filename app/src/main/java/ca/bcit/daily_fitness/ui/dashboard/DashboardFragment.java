package ca.bcit.daily_fitness.ui.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import ca.bcit.daily_fitness.R;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private EditText height;
    private EditText weight;
    private TextView BMI;
    private TextView body;

    SharedPreferences sharedpreferences;
    private static final String mypreference = "mypref";
    private static final String Weight = "weightKey";
    private static final String Height = "heightKey";
    private static final String BMIKEY = "bmiKey";
    private static final String BODY = "bodyKey";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        height = (EditText) root.findViewById(R.id.height);
        weight = (EditText) root.findViewById(R.id.weight);
        BMI = (TextView) root.findViewById(R.id.BMI);
        body = (TextView) root.findViewById(R.id.body);

        if (savedInstanceState != null) {
            //Restore the fragment's state here
            sharedpreferences = getActivity().getSharedPreferences(mypreference,
                    Context.MODE_PRIVATE);
            if (sharedpreferences.contains(Weight)) {
                weight.setText(sharedpreferences.getString(Weight, ""));
            }
            if (sharedpreferences.contains(Height)) {
                height.setText(sharedpreferences.getString(Height, ""));

            }
            if (sharedpreferences.contains(BMIKEY)) {
                BMI.setText(sharedpreferences.getString(BMIKEY, ""));

            }
            if (sharedpreferences.contains(BODY)) {
                body.setText(sharedpreferences.getString(BODY, ""));
            }
        }
        TextView title = root.findViewById(R.id.bmi_title);
        title.setText("Understanding Your Body Mass Index\n");
        ExpandableTextView t = (ExpandableTextView) root.findViewById(R.id.expand_text_view);
        t.setText("If your BMI is below 18.5: Your BMI is considered underweight. Keep in mind that an underweight BMI calculation may pose certain health risks. Please consult with your healthcare provider for more information about BMI calculations.\n");
        ExpandableTextView t2 =(ExpandableTextView) root.findViewById(R.id.expand_text_view2);
        t2.setText("If your BMI is between 18.5-24.9: Your BMI is considered normal. This healthy weight helps reduce your risk of serious health conditions and means you’re close to your fitness goals.\n");
        ExpandableTextView t3 =(ExpandableTextView)root.findViewById(R.id.expand_text_view3);
        t3.setText( "If your BMI is between 25-29.9: Your BMI is considered overweight. Being overweight may increase your risk of cardiovascular disease. Consult with your healthcare provider and consider making lifestyle changes through healthy eating and fitness to improve your health.\n");
        ExpandableTextView t4 = (ExpandableTextView)root.findViewById(R.id.expand_text_view4);
        t4.setText("If your BMI is above 30: Your BMI is considered obese. People with obesity are at increased risk for many diseases and health conditions, including cardiovascular disease, high blood pressure (Hypertension), Type 2 diabetes, breathing problems and more. Consult with your healthcare provider and consider making lifestyle changes through healthy eating and fitness to improve your overall health and quality of life.");

        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                calculateBMI();
            }
        });
        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
        //Save the fragment's state here
        String w = weight.getText().toString();
        String h = height.getText().toString();
        String b = BMI.getText().toString();
        String bc = body.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Weight, w);
        editor.putString(Height, h);
        editor.putString(BMIKEY, b);
        editor.putString(BODY, bc);
        editor.commit();
    }
    @Override
    public  void onResume(){
        super.onResume();
        sharedpreferences = getActivity().getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Weight)) {
            weight.setText(sharedpreferences.getString(Weight, ""));
        }
        if (sharedpreferences.contains(Height)) {
            height.setText(sharedpreferences.getString(Height, ""));
        }
        if (sharedpreferences.contains(BMIKEY)) {
            BMI.setText(sharedpreferences.getString(BMIKEY, ""));
        }
        if (sharedpreferences.contains(BODY)) {
            body.setText(sharedpreferences.getString(BODY, ""));
        }
    }

    private void calculateBMI() {
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();
        String bodyCondition;
        if (heightStr != null && !"".equals(heightStr)&&heightStr.matches("^[1-9]\\d*$")
                && weightStr != null && !"".equals(weightStr)&&weightStr.matches("^[1-9]\\d*$")) {
            float heightValue = Float.parseFloat(heightStr) / 100;
            float weightValue = Float.parseFloat(weightStr);
            float bmi = weightValue / (heightValue * heightValue);
            String result = Float.toString(bmi);
            BMI.setText(result);
            if(bmi<18.5){
                bodyCondition = "Underweight";
                body.setText(bodyCondition);
            } else if(18.5<=bmi && bmi<25){
                bodyCondition = "Normal";
                body.setText(bodyCondition);
            } else if(25<=bmi && bmi <30){
                bodyCondition = "Overweight";
                body.setText(bodyCondition);
            } else {
                bodyCondition = "Obese";
                body.setText(bodyCondition);
            }

            String w = weight.getText().toString();
            String h = height.getText().toString();
            String b = BMI.getText().toString();
            String bc = body.getText().toString();
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(Weight, w);
            editor.putString(Height, h);
            editor.putString(BMIKEY, b);
            editor.putString(BODY,bc);
            editor.commit();

        } else {
            Toast.makeText(getActivity(),"Weight and Height shoulf be positive integers",Toast.LENGTH_SHORT).show();
        }
        closeKeyboard();
    }

    private void closeKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}