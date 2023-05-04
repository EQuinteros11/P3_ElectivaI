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


public class CreateVehiculo extends Fragment {

    private Button btn_Insert;

    private EditText edt_ID,edt_Marca, edt_Modelo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragmento_create_vehiculo,container,false);
        edt_ID = root.findViewById(R.id.ID);
        edt_Marca = root.findViewById(R.id.edt_Marca);
        edt_Modelo = root.findViewById(R.id.edt_Modelo);
        btn_Insert = root.findViewById(R.id.btn_Guardar);



        btn_Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper( getContext(), "Parcial", null, 1 );
                SQLiteDatabase db =admin.getWritableDatabase();
                String  ID_Vehiculo =edt_ID.getText().toString(),
                        sMarca = edt_Marca.getText().toString(),
                        sModelo = edt_Modelo.getText().toString();


                ContentValues detalle = new ContentValues();
                detalle.put( "ID_Vehiculo", ID_Vehiculo );
                detalle.put( "sMarca", sMarca );
                detalle.put( "sModelo", sModelo );

                try {
                    db.insert( "MD_Vehiculo", null, detalle );
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
