package gr313.yanev.lab05;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    EditText textName;
    CheckBox check1;
    CheckBox check2;
    Button btnOk;
    Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textName = findViewById(R.id.name);

        check1 = findViewById(R.id.check1);
        check2 = findViewById(R.id.check2);

        btnOk = findViewById(R.id.btnOk);
        btnClose = findViewById(R.id.btnClose);

        applyDataFromTheIntent();
    }

    private void applyDataFromTheIntent() {
        Bundle main = getIntent().getExtras();

        if (main == null) {
            createMessage("No data!");
            return;
        }

        // createMessage("Success");
        String name = main.getString("name");
        boolean check1State = main.getBoolean("check1");
        boolean check2State = main.getBoolean("check2");

        textName.setText(name);
        check1.setChecked(check1State);
        check2.setChecked(check2State);
    }

    public void onOkClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("name", textName.getText().toString());
        i.putExtra("check1", check1.isChecked());
        i.putExtra("check2", check2.isChecked());

        setResult(RESULT_OK, i);
        finish();
    }

    public void onCloseClick(View v) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.custom_alert_dialog, null);

        new AlertDialog.Builder(this)
                .setView(view)
                .setMessage("You sure you wanna close this activity?")

                .setPositiveButton("Yes", (dialogInterface, i) -> {
                    setResult(RESULT_OK);
                    finish();
                })

                .setNegativeButton("No", (dialogInterface, i) -> {
                    // Nothing
                })

                .show();
    }

    private void createMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}