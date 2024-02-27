package com.ile.vjezba1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zad_2_2);

        //2.1

        /*Button button = findViewById(R.id.submit_button);
        EditText first_number = findViewById(R.id.first_number_input);
        EditText second_number = findViewById(R.id.second_number_input);
        EditText operator = findViewById(R.id.operator_input);
        TextView final_result = findViewById(R.id.result);

        button.setOnClickListener(view -> {
            if(validateValues(first_number, second_number, operator.getText().toString())) {
                float result = 0.0f;
                float first = Float.parseFloat(first_number.getText().toString());
                float second = Float.parseFloat(second_number.getText().toString());
                switch (operator.getText().toString()) {
                    case "+":
                        result = first + second;
                        break;
                    case "-":
                        result = first - second;
                        break;
                    case "*":
                        result = first * second;
                        break;
                    case "/":
                        result = first / second;
                        break;
                    default:
                        System.out.println("ERROR: Wrong operator used!");
                        break;
                }
                final_result.setText(String.valueOf(result));
            }
        });*/
        //2.2
        EditText first_number = findViewById(R.id.first_number_input);
        EditText second_number = findViewById(R.id.second_number_input);
        Spinner operator_input = findViewById(R.id.operator_spinner);

        String[] spinnerData = {"Please select an operation", "+", "-", "*", "/"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerData);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operator_input.setAdapter(adapter);

        operator_input.setSelection(0,false);
        operator_input.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (validateValues(first_number, second_number, operator_input.getSelectedItem().toString())) {
                        float result = 0.0f;
                        float first = Float.parseFloat(first_number.getText().toString());
                        float second = Float.parseFloat(second_number.getText().toString());
                        switch (operator_input.getSelectedItem().toString()) {
                            case "+":
                                result = first + second;
                                break;
                            case "-":
                                result = first - second;
                                break;
                            case "*":
                                result = first * second;
                                break;
                            case "/":
                                result = first / second;
                                break;
                            default:
                                System.out.println("ERROR: Wrong operator used!");
                                break;
                        }
                        sendResult(result);
                    }
                }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                showToastMessage("Please select an operation!");
            }
        });
    }

    Boolean validateValues(EditText first_number, EditText second_number, String operator) {
        if(first_number.getText().toString().matches("")) {
            showToastMessage("First number is empty!");
            return false;
        }
        if(second_number.getText().toString().matches("")) {
            showToastMessage("Second number is empty!");
            return false;
        }
        if(operator.matches("")) {
            showToastMessage("Operator is empty!");
            return false;
        }
        if(!operator.matches("[-+*/]")) {
            showToastMessage("Operator is not of allowed operation!");
            return false;
        }
        if((first_number.getText().toString().matches("0") || second_number.getText().toString().matches("0")) && operator.matches("/")) {
            showToastMessage("Division with 0 is not allowed!");
            return false;
        }
        return true;
    }

    void showToastMessage(String message) {
        Toast toast = Toast.makeText(this, (CharSequence) message, Toast.LENGTH_SHORT);
        toast.show();
    }

    void sendResult(float result) {
        Intent intent = new Intent(this, Zadatak_2_Rezultat.class);
        intent.putExtra("result", String.valueOf(result));
        startActivity(intent);
    }
}