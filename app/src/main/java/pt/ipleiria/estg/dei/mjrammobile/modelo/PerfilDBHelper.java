package pt.ipleiria.estg.dei.mjrammobile.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class PerfilDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="mjram";
    private static final int DB_VERSION=1;
    private static final String TABLE_PERFIL="perfil";
    private static final String NIB ="nib", EMAIL ="email", TELEMOVEL = "telemovel", NOMES ="nomes", DATAREGISTO="dataregisto";
    private final SQLiteDatabase db;
    public PerfilDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreateTablePerfil ="CREATE TABLE " + TABLE_PERFIL+ "(" +
                NOMES + " TEXT  NOT NULL, " +
                TELEMOVEL + " VARCHAR NOT NULL, " +
                NIB + " VARCHAR NOT NULL, " +
                EMAIL + " VARCHAR PRIMARY KEY," +
                DATAREGISTO + "VARCHAR NOT NULL"+
                ")";
        sqLiteDatabase.execSQL(sqlCreateTablePerfil);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sqlDeleteTablePerfil ="DROP TABLE IF EXISTS " + TABLE_PERFIL;
        sqLiteDatabase.execSQL(sqlDeleteTablePerfil);
        onCreate(sqLiteDatabase);
    }

    public Perfil adicionarPerfilBD(Perfil perfil)
    {
        //adicionar o perfil a bd
        ContentValues values = new ContentValues();
        values.put(NOMES, perfil.getNomes());
        values.put(TELEMOVEL, perfil.getTelemovel());
        values.put(EMAIL, perfil.getEmail());
        values.put(NIB, perfil.getNib());
        values.put(DATAREGISTO, perfil.getDataregisto());
        // db.insert retorna -1 em caso de erro ou o id que foi criado
        int id = (int)db.insert(TABLE_PERFIL, null, values);
        if(id>-1)
        {
            return perfil;
        }
        return null;
    }

    public Boolean editarPerfilBD(Perfil perfil)
    {
        //editar um perfil especifico da bd
        ContentValues values = new ContentValues();
        values.put(TELEMOVEL, perfil.getTelemovel());
        values.put(EMAIL, perfil.getEmail());
        values.put(NIB, perfil.getNib());
        values.put(DATAREGISTO, perfil.getDataregisto());
        // db.update retorna o numero de linhas atualizadas
        return db.update(TABLE_PERFIL, values, EMAIL +"=?", new String[]{perfil.getEmail()+""})==1;

    }

    public Boolean removerPerfilBD(String email)
    {
        //remover um perfil especifico da bd
        return db.delete(TABLE_PERFIL, EMAIL +"=?", new String[]{email+""})==1;
    }



    public Perfil getAllPerfilBD(){
        Cursor cursor = db.query(TABLE_PERFIL, new String[]{NOMES, TELEMOVEL, EMAIL, NIB,DATAREGISTO}, null, null, null, null, null);
        if(cursor.moveToFirst()){
            Perfil perfil = new Perfil(cursor.getString(3), cursor.getString(2), cursor.getString(1),cursor.getString(0),cursor.getString(4));

            cursor.close();
            return perfil;
        }
        else{
            return null;
        }
    }
}
