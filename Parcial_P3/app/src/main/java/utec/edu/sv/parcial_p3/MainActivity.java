package utec.edu.sv.parcial_p3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_Cliente,btn_Vehiculos, btn_ClienteVehiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_Cliente = findViewById(R.id.btn_MDClientes);
        btn_Vehiculos = findViewById(R.id.btn_MDVehiculos);
        btn_ClienteVehiculo = findViewById(R.id.btn_CV);

        btn_Cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Mantenimiento_Cliente.class);
                startActivity(i);
            }
        });
        btn_Vehiculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent o = new Intent(getApplicationContext(),Mantenimiento_vehiculos.class);
                startActivity(o);
            }
        });
        btn_ClienteVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Mantenimiento_CV.class);
                startActivity(i);
            }
        });


    }
}