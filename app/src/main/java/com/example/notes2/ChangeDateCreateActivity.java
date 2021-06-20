package com.example.notes2;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ChangeDateCreateActivity extends AppCompatActivity {

    public static final String KEY_RESULT_CHANGE_DATE_CREATED = "KEY_RESULT_CHANGE_DATE_CREATED";
    private DatePicker datePicker;
    private GregorianCalendar gCalenar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_date_create);
        datePicker = findViewById(R.id.datePicker);
        Button btnOk = findViewById(R.id.btnOk);
        Button btnCancel = findViewById(R.id.btnCancel);
        gCalenar = new GregorianCalendar();

        Date currentDate = (Date) getIntent().getSerializableExtra(KEY_RESULT_CHANGE_DATE_CREATED);
        gCalenar.setTime(currentDate);
        datePicker.updateDate(gCalenar.get(Calendar.YEAR), gCalenar.get(Calendar.MONTH), gCalenar.get(Calendar.DAY_OF_MONTH));

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(KEY_RESULT_CHANGE_DATE_CREATED,
                        new GregorianCalendar(
                                datePicker.getYear(),
                                datePicker.getMonth(),
                                datePicker.getDayOfMonth()).
                                getTime());
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}