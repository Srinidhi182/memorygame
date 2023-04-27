package com.example.memoryapp;

import static com.example.memoryapp.R.id.tv_level;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tv_level, tv_number;
    EditText et_number;
    Button b_confirm;
    int currentlevel =1;

    Random r;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_level =findViewById(R.id.tv_level);
        tv_number =findViewById(R.id.tv_number);
        et_number =findViewById(R.id.et_number);
        b_confirm =findViewById(R.id.b_confirm);

        r = new Random();

//        hide the input and the button and show the number
        et_number.setVisibility(View.GONE);
        b_confirm.setVisibility(View.GONE);
        tv_number.setVisibility(View.VISIBLE);

        int currentLevel = 1;
        final String[] generateNumber = new String[1];

// dsiplay the current level
        tv_level.setText("Level" +currentlevel);

//  display random number according to the level

//        display the elements after two second and hide the number
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                et_number.setVisibility(View.VISIBLE);
                b_confirm.setVisibility(View.VISIBLE);
                tv_number.setVisibility(View.GONE);

                et_number.requestFocus();
            }
        }, 5000);

        generateNumber[0] = generateNumber(currentlevel);
        tv_number.setText(generateNumber[0]);

        b_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                check if the numbers are the same
                if (generateNumber[0].equals(et_number.getText().toString())) {
//                hide the input and the button and show the number
                    et_number.setVisibility(View.GONE);
                    b_confirm.setVisibility(View.GONE);
                    tv_number.setVisibility(View.VISIBLE);

//                remove text from input
                    et_number.setText("");

//                  increase the level
                    currentlevel++;

                    // dsiplay the current level
                    tv_level.setText("Level" +currentlevel);

                    //display random number according to the level
                    generateNumber[0] = generateNumber(currentlevel);
                    tv_number.setText(generateNumber[0]);

//               display the elements after two second and hide the number
                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run(){
                            et_number.setVisibility(View.VISIBLE);
                            b_confirm.setVisibility(View.VISIBLE);
                            tv_number.setVisibility(View.GONE);

                            et_number.requestFocus();
                        }
                    }, 5000);
                }
                else{
                    tv_level.setText("game over ! The number was  " + generateNumber[0]);
                    b_confirm.setEnabled(false);
                }
            }
        });
    }

    private String generateNumber(int digits) {
        String output ="";
        for(int i =0; i<=digits; i++) {
            int randomDigit = r.nextInt(10);
            output =output + "" + randomDigit;
        }
        return output;
    }
}