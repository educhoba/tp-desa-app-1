package com.example.myapplication.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.models.Imagenes;
import com.example.myapplication.models.Reclamos;
import com.example.myapplication.models.SitiosManuales;

import java.util.ArrayList;
import java.util.List;

public class ReclamosLocalHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "reclamosLocal.db";

    public ReclamosLocalHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crea la tabla de clubes si no existe
        final String CREATE_RECLAMOS = "CREATE TABLE " + DataContract.ReclamosEntry.TABLE_NAME + "("
            + DataContract.ReclamosEntry._ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + DataContract.ReclamosEntry.ID + " TEXT,  "
            + DataContract.ReclamosEntry.DOCUMENTO + " TEXT, "
            + DataContract.ReclamosEntry.LEGAJO + " TEXT, "
            + DataContract.ReclamosEntry.IDSITIO + " TEXT NOT NULL, "
            + DataContract.ReclamosEntry.IDDESPERFECTO + " TEXT, "
            + DataContract.ReclamosEntry.DESCRIPCION + " TEXT, "
            + DataContract.ReclamosEntry.ESTADO + " TEXT, "
            + DataContract.ReclamosEntry.IDRECLAMOUNIFICADO + " TEXT, "
            + DataContract.ReclamosEntry.SITIOMANUAL + " TEXT, "
            + DataContract.ReclamosEntry.IMAGEN_1 + " TEXT, "
            + DataContract.ReclamosEntry.IMAGEN_2 + " TEXT, "
            + DataContract.ReclamosEntry.IMAGEN_3 + " TEXT, "
            + DataContract.ReclamosEntry.IMAGEN_4 + " TEXT, "
            + DataContract.ReclamosEntry.IMAGEN_5 + " TEXT, "
            + DataContract.ReclamosEntry.IMAGEN_6 + " TEXT, "
            + DataContract.ReclamosEntry.IMAGEN_7 + " TEXT)";
        db.execSQL(CREATE_RECLAMOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long saveReclamo(Reclamos reclamo){
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(DataContract.ReclamosEntry.TABLE_NAME, null, reclamo.toContentValues());
    }

    public List<Reclamos> getReclamos(){
        List<Reclamos> reclamos = new ArrayList<Reclamos>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =  db.query(DataContract.ReclamosEntry.TABLE_NAME,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                String saux = null;
                Reclamos aux = new Reclamos();
                aux.setIdReclamo(cursor.getInt(1));
                aux.setDocumento(cursor.getString(2));
                aux.setLegajo(cursor.getInt(3));
                aux.setIdSitio(cursor.getInt(4));
                aux.setIdDesperfecto(cursor.getInt(5));
                aux.setDescripcion(cursor.getString(6));
                aux.setEstado(cursor.getString(7));
                aux.setIdReclamoUnificado(cursor.getInt(8));
                saux=cursor.getString(9);
                if(saux != null){ //sitio
                    SitiosManuales sm = new SitiosManuales();
                    sm.setDescripcion(saux);
                    List<SitiosManuales> lsm = new ArrayList<>();
                    lsm.add(sm);
                    aux.setSitiosManuales(lsm);
                }
                List<Imagenes> nuevaListaAux = new ArrayList<>();
                Imagenes img1 = crearImagenoNull(cursor.getString(10));
                Imagenes img2 = crearImagenoNull(cursor.getString(11));
                Imagenes img3 = crearImagenoNull(cursor.getString(12));
                Imagenes img4 = crearImagenoNull(cursor.getString(13));
                Imagenes img5 = crearImagenoNull(cursor.getString(14));
                Imagenes img6 = crearImagenoNull(cursor.getString(15));
                Imagenes img7 = crearImagenoNull(cursor.getString(16));
                if(img1 != null)
                    nuevaListaAux.add(img1);
                if(img2 != null)
                    nuevaListaAux.add(img2);
                if(img3 != null)
                    nuevaListaAux.add(img3);
                if(img4 != null)
                    nuevaListaAux.add(img4);
                if(img5 != null)
                    nuevaListaAux.add(img5);
                if(img6 != null)
                    nuevaListaAux.add(img6);
                if(img7 != null)
                    nuevaListaAux.add(img7);
                aux.setImagenes(nuevaListaAux);
                reclamos.add(aux);
            }while (cursor.moveToNext());
        }
        return reclamos;
    }

    private Imagenes crearImagenoNull(String data){
        if(data != null){
            Imagenes nuevaImagen = new Imagenes();
            nuevaImagen.setData(data);
            return nuevaImagen;
        }
        return null;
    }
    public int deleteAllReclamos(){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(DataContract.ReclamosEntry.TABLE_NAME,null,null);
    }
    
}

