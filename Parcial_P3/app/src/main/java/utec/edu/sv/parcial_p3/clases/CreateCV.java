package utec.edu.sv.parcial_p3.clases;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import utec.edu.sv.parcial_p3.helper.AdminSQLiteOpenHelper;
import utec.edu.sv.parcial_p3.R;


public class CreateCV extends Fragment {

    private Button btn_Insert;

    private EditText edt_ID,edt_id_v,edt_Matri, edt_kilo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragmento_create_cv,container,false);
        edt_ID = root.findViewById(R.id.ID);
        edt_id_v = root.findViewById(R.id.edt_ID_V);
        edt_Matri = root.findViewById(R.id.edt_Matricula);
        edt_kilo = root.findViewById(R.id.edt_Kilo);
        btn_Insert = root.findViewById(R.id.btn_Guardar);



        btn_Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper( getContext(), "Parcial", null, 1 );
                SQLiteDatabase db =admin.getWritableDatabase();
                String  ID_Cliente =edt_ID.getText().toString(),
                        ID_Vehiculo =edt_id_v.getText().toString(),
                        sMatricula = edt_Matri.getText().toString(),
                        iKilometros = edt_kilo.getText().toString();


                ContentValues detalle = new ContentValues();
                detalle.put( "ID_Cliente", ID_Cliente );
                detalle.put( "ID_Vehiculo", ID_Vehiculo );
                detalle.put( "sMatricula", sMatricula );
                detalle.put( "iKilometros", iKilometros );

                try {
                    db.insert( "MD_ClienteVehiculo", null, detalle );
                    db.close();
                    Toast.makeText( getContext(), "Se inserto el contacto", Toast.LENGTH_LONG ).show();
                }catch ( Exception e ){
                    Toast.makeText( getContext(), e.getMessage().toString(), Toast.LENGTH_LONG ).show();
                }
            }
        });

        return root;
    }

}
