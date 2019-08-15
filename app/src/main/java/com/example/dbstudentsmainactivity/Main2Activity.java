package com.example.dbstudentsmainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private StudentUtility studentUtility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button insertButton = (Button) findViewById(R.id.insertButton);
        Button readButton   = (Button) findViewById(R.id.readButton);
        Button updateButton = (Button) findViewById(R.id.updateButton);
        Button deleteButton = (Button) findViewById(R.id.deleteButton);

        insertButton.setOnClickListener(this);
        readButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);

        studentUtility = new StudentUtility(this);
        studentUtility.open();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.insertButton:
                insertRecords();
                break;
            case R.id.readButton:
                readRecords();
                break;
            case R.id.updateButton:
                updateRecords();
                break;
            case R.id.deleteButton:
                deleteRecords();
                break;
        }
    }
    private void insertRecords() {
        studentUtility.addStudent
                (new Student("Vipul", "27"));
        studentUtility.addStudent
                (new Student("Vishal", "29"));
        studentUtility.addStudent
                (new Student("Vikas", "28"));
    }

    private void readRecords() {
        studentUtility.listStudents();
    }

    private void updateRecords() {
        studentUtility.updateStudent(new Student("Vipul", "31"));
    }

    private void deleteRecords() {
        studentUtility.deleteStudent(new Student("Vipul", null));
    }

    @Override
    protected void onDestroy() {
        studentUtility.close();
        super.onDestroy();
    }
}
