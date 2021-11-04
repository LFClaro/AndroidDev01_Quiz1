package com.example.quiz1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {

    public static final String EXTRA_STUDENT =
            "com.example.android.quiz1.extra.STUDENT";

    TextView nameTextView;
    TextView idTextView;
    TextView ageTextView;
    TextView genderTextView;
    TextView gpaTextView;
    TextView coursesTextView;
    Button editButton;

    ActivityResultLauncher<Intent> activityResultLauncher;

    Student student;
    ArrayList<Course> courses = new ArrayList<Course>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        nameTextView = findViewById(R.id.tvNameDisplay);
        idTextView = findViewById(R.id.tvIDDisplay);
        ageTextView = findViewById(R.id.tvAgeDisplay);
        genderTextView = findViewById(R.id.tvGenderDisplay);
        gpaTextView = findViewById(R.id.tvGPADisplay);
        coursesTextView = findViewById(R.id.tvCoursesDisplay);
        editButton = findViewById(R.id.buttonEdit);

        courses.add(new Course("Android Dev 1", "ITE5333"));
        courses.add(new Course("iOS Dev 1", "ITE5334"));
        courses.add(new Course("Web App with PHP", "ITE5330"));

        student = new Student("N01459385", "Luiz Claro", 37, 'M', 88, courses);

        activityResultLauncher =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent receiveData = result.getData();
                            student = (Student)receiveData.getSerializableExtra(UpdateActivity.EXTRA_RETURN);
                            updateStudentUI();
                        }
                    }
                });

        updateStudentUI();
    }

    private void updateStudentUI() {
        nameTextView.setText(student.getName());
        idTextView.setText(student.getId());
        ageTextView.setText(String.valueOf(student.getAge()));
        genderTextView.setText(String.valueOf(student.getGender()));
        gpaTextView.setText(String.valueOf(student.getGpa()));
        coursesTextView.setText("");
        for (Course c : student.getCourses()) {
            coursesTextView.append(String.valueOf(c.getCourseName()) + " (" + c.courseCode + ")" + "\n");
        }
    }

    public void editClicked(View view) {
        Intent intent = new Intent(this, UpdateActivity.class);
        Student sendObjStudent = student;
        intent.putExtra(EXTRA_STUDENT, sendObjStudent);
        activityResultLauncher.launch(intent);
    }
}