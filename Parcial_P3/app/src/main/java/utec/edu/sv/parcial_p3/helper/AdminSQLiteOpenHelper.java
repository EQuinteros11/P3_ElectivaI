package utec.edu.sv.parcial_p3.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE MD_Clientes ( " +
                "ID_Cliente text PRIMARY KEY, " +
                "sNombreCliente text, " +
                "sApellidoCliente text, " +
                "sDireccionCliente text, " +
                "sCiudadCliente text )");

        db.execSQL("CREATE TABLE MD_Vehiculo ( " +
                "ID_Vehiculo text PRIMARY KEY, " +
                "sMarca text, " +
                "sModelo text )");

        db.execSQL("CREATE TABLE MD_ClienteVehiculo ( " +
                "ID_Cliente text,"+
                "ID_Vehiculo text, " +
                "sMatricula text, " +
                "iKilometros text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
