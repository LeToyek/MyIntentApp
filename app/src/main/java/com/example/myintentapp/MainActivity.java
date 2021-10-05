package com.example.myintentapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvResult;
    final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
    result -> {
        if (result.getResultCode() == MoveForResultActivity.RESULT_CODE && result.getData() != null){
            int selectedValue = result.getData().getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE,0);
            tvResult.setText(String.format("Hasil : %s",selectedValue));
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnMoveActivity = findViewById(R.id.btn_move_activity);
        Button btnMoveActivityData = findViewById(R.id.btn_move_activity_data);
        Button btnMoveActivityObject = findViewById(R.id.btn_move_activity_object);
        Button btnDialPhone = findViewById(R.id.btn_dial_number);
        Button btnMoveResult = findViewById(R.id.btn_move_for_result);

        tvResult = findViewById(R.id.tv_result);

        btnMoveResult.setOnClickListener(this);
        btnDialPhone.setOnClickListener(this);
        btnMoveActivityObject.setOnClickListener(this);
        btnMoveActivityData.setOnClickListener(this);
        btnMoveActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_move_activity){
            Intent moveIntent = new Intent(MainActivity.this,MoveActivity.class);
            startActivity(moveIntent);
        }else if(view.getId() == R.id.btn_move_activity_data){
            Intent moveDataIntent = new Intent(MainActivity.this,MoveActivityData.class);
            moveDataIntent.putExtra(MoveActivityData.EXTRA_NAME,"Toyek");
            moveDataIntent.putExtra(MoveActivityData.EXTRA_AGE,17);
            startActivity(moveDataIntent);
        }else if(view.getId() == R.id.btn_move_activity_object){
            Person person = new Person();
            person.setName("Jatmiko");
            person.setAge(17);
            person.setEmail("Jatmiko@gmail.com");
            person.setCity("Singosari");

            Intent moveObjectIntent = new Intent(MainActivity.this,MoveWithObjectActivity.class);
            moveObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON,person);
            startActivity(moveObjectIntent);
        }else if(view.getId() == R.id.btn_dial_number){
            String phoneNumber = "";
            Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
            startActivity(dialPhoneIntent);
        }else if(view.getId() == R.id.btn_move_for_result){
            Intent moveForResult = new Intent(MainActivity.this,MoveForResultActivity.class);
            resultLauncher.launch(moveForResult);
        }
    }
}
