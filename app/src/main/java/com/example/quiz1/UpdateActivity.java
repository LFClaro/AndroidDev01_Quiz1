package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    public static final String EXTRA_RETURN =
            "com.example.android.quiz1.extra.RETURN";

    EditText nameEditText;
    EditText idEditText;
    EditText ageEditText;
    EditText genderEditText;
    EditText gpaEditText;
    EditText coursesEditTextName;
    EditText coursesEditTextCode;
    Button saveButton;
    Button cancelButton;

    Student student;
    ArrayList<Course> courses = new ArrayList<Course>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent = getIntent();
        student = (Student)intent.getSerializableExtra(StudentActivity.EXTRA_STUDENT);

        nameEditText = findViewById(R.id.tvNameEdit);
        idEditText = findViewById(R.id.tvIDEdit);
        ageEditText = findViewById(R.id.tvAgeEdit);
        genderEditText = findViewById(R.id.tvGenderEdit);
        gpaEditText = findViewById(R.id.tvGPAEdit);
        coursesEditTextName = findViewById(R.id.tvCoursesEditName);
        coursesEditTextCode = findViewById(R.id.tvCoursesEditCode);
        saveButton = findViewById(R.id.buttonSave);
        cancelButton = findViewById(R.id.buttonCancel);

        updateStudentUI();
    }

    private void updateStudentUI() {
        nameEditText.setText(student.getName());
        idEditText.setText(student.getId());
        ageEditText.setText(String.valueOf(student.getAge()));
        genderEditText.setText(String.valueOf(student.getGender()));
        gpaEditText.setText(String.valueOf(student.getGpa()));
        for (Course c : student.getCourses()) {
            coursesEditTextName.append(String.valueOf(c.getCourseName()) + "\n");
            coursesEditTextCode.append(String.valueOf(c.getCourseCode()) + "\n");
        }
    }

    public void updateSave(View view) {
        student.setName(nameEditText.getText().toString());
        student.setId(idEditText.getText().toString());
        student.setAge(Integer.parseInt(ageEditText.getText().toString()));
        student.setGender(genderEditText.getText().charAt(0));
        student.setGpa(Float.parseFloat(gpaEditText.getText().toString()));

        for (int i = 0; i < coursesEditTextName.getLineCount()-1 ; i++) {
            int startPosName = coursesEditTextName.getLayout().getLineStart(i);
            int endPosName = coursesEditTextName.getLayout().getLineEnd(i);
            int startPosCode = coursesEditTextCode.getLayout().getLineStart(i);
            int endPosCode = coursesEditTextCode.getLayout().getLineEnd(i);

            String courseName = coursesEditTextName.getText().toString().substring(startPosName, endPosName).replace(System.getProperty("line.separator"),"" );
            String courseCode = coursesEditTextCode.getText().toString().substring(startPosCode, endPosCode).replace(System.getProperty("line.separator"),"" );

            courses.add(new Course(courseName, courseCode));
        }

        student.setCourses(courses);
        Intent returnIntent = new Intent(this, StudentActivity.class);
        Student sendObjStudent = student;
        returnIntent.putExtra(EXTRA_RETURN, sendObjStudent);
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    public void updateCancel(View view) {
        Intent returnIntent = new Intent(this, StudentActivity.class);
        setResult(RESULT_CANCELED, returnIntent);
        finish();
    }
}