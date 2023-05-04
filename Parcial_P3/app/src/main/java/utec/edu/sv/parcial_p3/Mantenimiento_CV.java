package utec.edu.sv.parcial_p3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import utec.edu.sv.parcial_p3.clases.CreateCV;
import utec.edu.sv.parcial_p3.clases.CreateVehiculo;
import utec.edu.sv.parcial_p3.clases.DeleteCV;
import utec.edu.sv.parcial_p3.clases.DeleteVehiculo;
import utec.edu.sv.parcial_p3.clases.UpdateCV;
import utec.edu.sv.parcial_p3.clases.UpdateVehiculo;

public class Mantenimiento_CV extends AppCompatActivity {
    BottomNavigationView btnNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenimiento_cv);
        btnNav = findViewById(R.id.btnNav);
        btnNav.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) navaListener);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navaListener= new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment seleccionFrag= null;

            switch (item.getItemId()){
                case R.id.nav_create:
                    seleccionFrag = new CreateCV();
                    break;
                case R.id.delete:
                    seleccionFrag = new DeleteCV();
                    break;

                case R.id.update:
                    seleccionFrag = new UpdateCV();
                    break;


            }



            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentCont, seleccionFrag).commit();
            return true;

        }



    };

}