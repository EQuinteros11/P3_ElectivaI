package utec.edu.sv.parcial_p3.clases;

import android.database.Cursor;
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

public class DeleteCV extends Fragment {

    private Button btn_delete, btn_Ver;

    private EditText edt_ID,edt_id_v,edt_Matri, edt_kilo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragmento_delete_cv,container,false);
        edt_ID = root.findViewById(R.id.ID);
        edt_id_v = root.findViewById(R.id.edt_ID_V);
        edt_Matri = root.findViewById(R.id.edt_Matricula);
        edt_kilo = root.findViewById(R.id.edt_Kilo);
        btn_delete = root.findViewById(R.id.btn_Borrar);
        btn_Ver = root.findViewById(R.id.btn_Buscar);

        btn_Ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper( getContext(), "Parcial", null, 1 );
                SQLiteDatabase db =admin.getWritableDatabase();
                String ID_Cliente = edt_ID.getText().toString();

                Cursor fila = db.rawQuery( "select ID_Cliente,ID_Vehiculo, sMatricula, iKilometros from MD_ClienteVehiculo WHERE ID_Cliente = " + "'"+ID_Cliente+"'",
                        null );

                if ( fila.moveToFirst() ){
                    edt_ID.setText( fila.getString( 0 ) );
                    edt_id_v.setText( fila.getString( 1 ) );
                    edt_Matri.setText( fila.getString( 2 ) );
                    edt_kilo.setText( fila.getString( 3 ) );
                    Toast.makeText( getContext(), "El registro fue encontrado", Toast.LENGTH_LONG ).show();

                }else {
                    Toast.makeText( getContext(), "Registro no encontrado", Toast.LENGTH_LONG ).show();
                }
                db.close();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper( getContext(), "Parcial", null, 1 );
                SQLiteDatabase db =admin.getWritableDatabase();
                String ID_Cliente =edt_ID.getText().toString();

                int cat = db.delete( "MD_ClienteVehiculo", "ID_Cliente = " + "'"+ID_Cliente+"'", null );

                if ( cat == 1 ){
                    Toast.makeText( getContext(), "Se elimino el registro", Toast.LENGTH_LONG ).show();
                    edt_ID.setText("");
                    edt_id_v.setText("");
                    edt_Matri.setText("");
                    edt_kilo.setText("");
                }else{
                    Toast.makeText( getContext(), "No se pudo realizar la eliminación del registro", Toast.LENGTH_LONG ).show();
                }

                db.close();
            }
        });



        return root;
    }
}
