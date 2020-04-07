package com.example.colorpickerapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;
import com.jaredrummler.android.colorpicker.ColorShape;

public class MainActivity extends AppCompatActivity implements ColorPickerDialogListener {

    Button firstButton,secondButton;
    TextView firstText,secondText;
    private static final int firstId = 1,secondId = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstButton = findViewById(R.id.firstButton);
        secondButton = findViewById(R.id.secondButton);

        firstText = findViewById(R.id.firstText);
        secondText = findViewById(R.id.secondText);

    }

    @Override
    protected void onResume() {
        setBackgroundColorFromSettingsActivity();
        super.onResume();
    }

    @Override
    public void onColorSelected(int dialogId, int color) {
        switch (dialogId) { // смотрим, какая кнопка нажата
            case firstId:
                firstText.setTextColor(color);
                break;
            case secondId:
                secondText.setTextColor(color);
                break;
        }
    }

    @Override
    public void onDialogDismissed(int dialogId) {
        Toast.makeText(this, "Dialog dismissed", Toast.LENGTH_SHORT).show();
    }

    private void createColorPickerDialog(int id) {
        ColorPickerDialog.newBuilder()
                .setDialogTitle(R.string.choose_color)
                .setColor(Color.RED)
                .setDialogType(ColorPickerDialog.TYPE_PRESETS)
                .setAllowCustom(true)
                .setAllowPresets(true)
                .setPresetsButtonText(R.string.presets_button_text)
                .setCustomButtonText(R.string.custom)
                .setColorShape(ColorShape.SQUARE)
                .setDialogId(id)
                .show(this);
// полный список атрибутов класса ColorPickerDialog смотрите ниже
    }
    public void onClickButton(View view) {
        switch (view.getId()) {
            case R.id.firstButton:
                createColorPickerDialog(firstId);
                break;
            case R.id.secondButton:
                createColorPickerDialog(secondId);
                break;
        }
    }

    public void openSettingsActivity(View view) {
        startActivity(new Intent(this,SettingsActivity.class));
    }

    private void setBackgroundColorFromSettingsActivity() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        int color = sp.getInt("color_picker",Color.GREEN);

        RelativeLayout layout = findViewById(R.id.activityMain);
        layout.setBackgroundColor(color);
    }

}
