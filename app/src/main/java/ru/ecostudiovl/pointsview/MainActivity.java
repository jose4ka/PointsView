package ru.ecostudiovl.pointsview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity {

    private Button btnRedraw;
    private PointsView pointsView;
    private CheckBox stroke, internalNetworks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pointsView = findViewById(R.id.myPointsView);

        btnRedraw = findViewById(R.id.btnRedraw);
        btnRedraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointsView.invalidate();
            }
        });

        stroke = findViewById(R.id.cbStroke);
        stroke.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pointsView.setDrawStroke(isChecked);
            }
        });

        internalNetworks = findViewById(R.id.cbInternalNetworks);
        internalNetworks.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pointsView.setDrawInternalNetworks(isChecked);
            }
        });

    }
}