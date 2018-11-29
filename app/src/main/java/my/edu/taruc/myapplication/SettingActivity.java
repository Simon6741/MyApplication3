package my.edu.taruc.myapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingActivity extends AppCompatActivity {

    private static  final PREF`

    private EditText editTextName;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale, radioButtonFemale;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        editTextName = findViewById(R.id.editTextName);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
    }
    @Override
    public void onResume() {
        super.onResume();
        //read the shared Preference file
        sharedPreferences = getSharedPreferences(PREF_FILE,MODE_PRIVATE);
        sharedPreferences = getPreferences(MODE_PRIVATE);

        String name;
        int gender;//default = -1, male = 1,female = 0
        name = sharedPreferences.getString("user_name","");
        gender = sharedPreferences.getInt("user_gender",-1);
        editTextName.setText(name);
        if(gender == 1){
            radioButtonMale.setChecked(true);
        }else if(gender ==0){
            radioButtonFemale.setChecked(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String name;
        int gender;
        name = editTextName.getText().toString();
        editor.putString("user_name",name);

        gender = radioGroupGender.getCheckedRadioButtonId();
        if(gender == R.id.radioGroupMale){
            editor.putInt("user_gender",1);

        } else if(gender == R.id.radioButtonFemale){
            editor.putInt("user_gender",0);
        }
        else {
            editor.putInt("user_gender",-1);
        }
        editor.apply();
    }
}
