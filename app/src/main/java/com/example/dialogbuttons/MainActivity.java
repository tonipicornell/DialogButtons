package com.example.dialogbuttons;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button firstButton = findViewById(R.id.first_dialog);
        Button secondButton = findViewById(R.id.second_dialog);
        Button thirstButton = findViewById(R.id.thirst_dialog);

        TextView textChange = findViewById(R.id.textChange);

        firstButton.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("First Dialog").setMessage("This is the first dialog").setPositiveButton("OK", (dialog, which) -> {
                textChange.setText("This is the fisrt dialog");
            }).setNegativeButton("Cancel", (dialog, which) -> {
                textChange.setText("");
            });

            // Mostrar el dialogo:
            builder.show();
        });

        secondButton.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Choose a number");

            // Opcions:
            String[] opcions = {"Number one", "Number two", "Number three"};

            // Variable para almacenar la opción elegida.
            final int[] selectItem = {-1};

            builder.setSingleChoiceItems(opcions, -1, (dialog, which) -> {
                selectItem[0] = which;
            });

            builder.setPositiveButton("OK", (dialog, which) -> {
               if (selectItem[0] != -1) {
                   String selectedOpcion = opcions[selectItem[0]];
                   textChange.setText("Your selection is: " + selectedOpcion);
               } else {
                   textChange.setText("You don't selected anything");
               }
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> {
                dialog.dismiss();
            });

            builder.show();
        });

        thirstButton.setOnClickListener(view -> {
            android.view.LayoutInflater inflater = getLayoutInflater();
            android.view.View dialogSign = inflater.inflate(R.layout.sign_user, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(dialogSign).setPositiveButton("Sign in", (dialog, which) -> {
                textChange.setText("User signed");
            }).setNegativeButton("Cancel", (dialog, which) -> {
                dialog.dismiss();
            });

            builder.create().show();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}