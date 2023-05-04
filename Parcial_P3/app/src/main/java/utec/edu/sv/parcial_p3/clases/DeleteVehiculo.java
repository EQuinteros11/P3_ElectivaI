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

public class DeleteVehiculo extends Fragment {

    private Button btn_delete, btn_Ver;

    private EditText edt_ID,edt_Marca, edt_Modelo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragmento_delete_vehiculo,container,false);
        edt_ID = root.findViewById(R.id.ID);
        edt_Marca = root.findViewById(R.id.edt_Marca);
        edt_Modelo = root.findViewById(R.id.edt_Modelo);
        btn_delete = root.findViewById(R.id.btn_Borrar);
        btn_Ver = root.findViewById(R.id.btn_Buscar);

        btn_Ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper( getContext(), "Parcial", null, 1 );
                SQLiteDatabase db =admin.getWritableDatabase();
                String ID_Vehiculo = edt_ID.getText().toString();

                Cursor fila = db.rawQuery( "select ID_Vehiculo, sMarca, sModelo from MD_Vehiculo WHERE ID_Vehiculo = " + "'"+ID_Vehiculo+"'",
                        null );

                if ( fila.moveToFirst() ){
                    edt_ID.setText( fila.getString( 0 ) );
                    edt_Marca.setText( fila.getString( 1 ) );
                    edt_Modelo.setText( fila.getString( 2 ) );
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
                String ID_Vehiculo =edt_ID.getText().toString();

                int cat = db.delete( "MD_Vehiculo", "ID_Vehiculo = " + "'"+ID_Vehiculo+"'", null );

                if ( cat == 1 ){
                    Toast.makeText( getContext(), "Se elimino el registro", Toast.LENGTH_LONG ).show();
                    edt_ID.setText("");
                    edt_Modelo.setText("");
                    edt_Marca.setText("");
                }else{
                    Toast.makeText( getContext(), "No se pudo realizar la eliminación del registro", Toast.LENGTH_LONG ).show();
                }

                db.close();
            }
        });



        return root;
    }
}
