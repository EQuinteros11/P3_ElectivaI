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

public class Delete extends Fragment {
    private Button btn_delete, btn_Ver;
    private EditText edt_Nombre, edt_Apellido, edt_ID,edt_Dir,edt_Ciu;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragmento_delete,container,false);
        edt_ID = root.findViewById(R.id.ID);
        edt_Nombre = root.findViewById(R.id.edt_Marca);
        edt_Apellido = root.findViewById(R.id.edt_Modelo);
        edt_Dir = root.findViewById(R.id.edt_Dir);
        edt_Ciu = root.findViewById(R.id.edt_Ciudad);
        btn_delete = root.findViewById(R.id.btn_Borrar);
        btn_Ver = root.findViewById(R.id.btn_Buscar);

        btn_Ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper( getContext(), "Parcial", null, 1 );
                SQLiteDatabase db =admin.getWritableDatabase();
                String ID_Cliente = edt_ID.getText().toString();

                Cursor fila = db.rawQuery( "select ID_Cliente, sNombreCliente, sApellidoCliente, sDireccionCliente, sCiudadCliente from MD_Clientes WHERE ID_Cliente = " + "'"+ID_Cliente+"'",
                        null );

                if ( fila.moveToFirst() ){
                    edt_ID.setText( fila.getString( 0 ) );
                    edt_Nombre.setText( fila.getString( 1 ) );
                    edt_Apellido.setText( fila.getString( 2 ) );
                    edt_Dir.setText( fila.getString( 3 ) );
                    edt_Ciu.setText( fila.getString( 4 ) );
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

                int cat = db.delete( "MD_Clientes", "ID_Cliente = " + "'"+ID_Cliente+"'", null );

                if ( cat == 1 ){
                    Toast.makeText( getContext(), "Se elimino el registro", Toast.LENGTH_LONG ).show();
                    edt_Nombre.setText("");
                    edt_Apellido.setText("");
                    edt_ID.setText("");
                    edt_Dir.setText("");
                    edt_Ciu.setText("");
                }else{
                    Toast.makeText( getContext(), "No se pudo realizar la eliminación del registro", Toast.LENGTH_LONG ).show();
                }

                db.close();
            }
        });



        return root;
    }
}
