package utec.edu.sv.parcial_p3.clases;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import utec.edu.sv.parcial_p3.helper.AdminSQLiteOpenHelper;
import utec.edu.sv.parcial_p3.R;


public class Create extends Fragment {

    private Button btn_Insert, btn_Ver;
    private TextView ver;

    private EditText edt_ID,edt_Nombre, edt_Apellido,edt_Dir,edt_Ciu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragmento_create,container,false);
        edt_ID = root.findViewById(R.id.ID);
        edt_Nombre = root.findViewById(R.id.edt_Marca);
        edt_Apellido = root.findViewById(R.id.edt_Modelo);
        edt_Dir = root.findViewById(R.id.edt_Dir);
        edt_Ciu = root.findViewById(R.id.edt_Ciudad);
        btn_Insert = root.findViewById(R.id.btn_Guardar);



        btn_Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper( getContext(), "Parcial", null, 1 );
                SQLiteDatabase db =admin.getWritableDatabase();
                String  ID_Cliente =edt_ID.getText().toString(),
                        sNombreCliente = edt_Nombre.getText().toString(),
                        sApellidoCliente = edt_Apellido.getText().toString(),
                        sDireccionCliente = edt_Dir.getText().toString(),
                        sCiudadCliente = edt_Ciu.getText().toString();

                ContentValues detalle = new ContentValues();
                detalle.put( "ID_Cliente", ID_Cliente );
                detalle.put( "sNombreCliente", sNombreCliente );
                detalle.put( "sApellidoCliente", sApellidoCliente );
                detalle.put( "sDireccionCliente", sDireccionCliente );
                detalle.put( "sCiudadCliente", sCiudadCliente );
                try {
                    db.insert( "MD_Clientes", null, detalle );
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
