package gr313.yanev.lab05;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText textName;
    CheckBox check1;
    CheckBox check2;
    Button btnOpenSecondActivity;
    Button btnOpenDialog;
    Button btnExitApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textName = findViewById(R.id.name);

        check1 = findViewById(R.id.check1);
        check2 = findViewById(R.id.check2);

        btnOpenSecondActivity = findViewById(R.id.btnOpenSecondActivity);
        btnOpenDialog = findViewById(R.id.btnOpenDialog);
        btnExitApp = findViewById(R.id.btnExitApp);
    }


    @Override
    protected void onActivityResult(int requestCode, int result, @Nullable Intent data) {

        if (data == null) {
            Toast.makeText(this, "No data!", Toast.LENGTH_LONG).show();
            return;
        }

        String name = data.getStringExtra("name");
        boolean check1State = data.getBooleanExtra("check1", false);
        boolean check2State = data.getBooleanExtra("check2", false);

        textName.setText(name);
        check1.setChecked(check1State);
        check2.setChecked(check2State);

        super.onActivityResult(requestCode, result, data);
    }

    public void openSecondActivity(View v) {
        // TODO
        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra("name", textName.getText().toString());
        i.putExtra("check1", check1.isChecked());
        i.putExtra("check2", check2.isChecked());
        startActivityForResult(i, 2);
    }

    public void openDialog(View v) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_dialog, null);
        dialogBuilder.setView(dialogView);

        EditText dialogName = dialogView.findViewById(R.id.dialogName);
        CheckBox dialogCheck1 = dialogView.findViewById(R.id.dialogCheck1);
        CheckBox dialogCheck2 = dialogView.findViewById(R.id.dialogCheck2);

        dialogName.setText(textName.getText().toString());
        dialogCheck1.setChecked(check1.isChecked());
        dialogCheck2.setChecked(check2.isChecked());

        dialogBuilder.setTitle("Dialog Window");


        dialogBuilder.setPositiveButton(R.string.yes, (dialog, which) -> {
            textName.setText(dialogName.getText().toString());
            check1.setChecked(dialogCheck1.isChecked());
            check2.setChecked(dialogCheck2.isChecked());
        });

        dialogBuilder.setNegativeButton(R.string.no, (dialog, whichButton) ->
                Toast.makeText(MainActivity.this,R.string.no,Toast.LENGTH_LONG).show());


        AlertDialog customDialog = dialogBuilder.create();
        customDialog.show();
    }

    public void exitApp(View v) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.custom_alert_dialog, null);

        new AlertDialog.Builder(this)
                .setView(view)
                .setMessage("Are you sure you wanna exit?")

                .setPositiveButton(R.string.yes, (dialogInterface, i) -> {
                    setResult(RESULT_OK);
                    finish();
                })

                .setNegativeButton(R.string.no, (dialogInterface, i) -> {
                    // Nothing
                })

                .show();
    }
}