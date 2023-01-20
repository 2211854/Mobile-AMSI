package pt.ipleiria.estg.dei.mjrammobile.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class VooBDHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="mjram";
    private static final int DB_VERSION=1;
    private static final String TABLE_VOO="voo";
    private static final String DESIGNACAO="designacao", ESTADO="estado",
            ID_AVIAO = "id_aviao", AVIAO="aviao", PISTA="pista", TOTALBILHETES="totalbilhetes", ID="id";
    private final SQLiteDatabase db;
    public VooBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreateTableVoo ="CREATE TABLE " + TABLE_VOO+ "(" +
                ID + " BIGINT  PRIMARY KEY, " +
                DESIGNACAO  + " VARCHAR NOT NULL, " +
                ESTADO  + "TEXT CHECK(ESTADO in ('atrasado', 'cancelado', 'concluido', 'planeado')) NOT NULL " +
                "NOT NULL, " +
                ID_AVIAO  + " BIGINT NOT NULL, " +
                AVIAO  + " VARCHAR NOT NULL, " +
                PISTA  + " VARCHAR NOT NULL, " +
                TOTALBILHETES + " INT NOT NULL " +
                ")";
        sqLiteDatabase.execSQL(sqlCreateTableVoo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sqlDeleteTableVoo ="DROP TABLE IF EXISTS " + TABLE_VOO;
        sqLiteDatabase.execSQL(sqlDeleteTableVoo);
        onCreate(sqLiteDatabase);
    }

    public Voo adicionarVooBD(Voo voo)
    {
        //adicionar um voo a bd
        ContentValues values = new ContentValues();
        values.put(ID, voo.getId());
        values.put(DESIGNACAO, voo.getDesignacao());
        values.put(ESTADO, voo.getEstado());
        values.put(ID_AVIAO, voo.getId_aviao());
        values.put(AVIAO, voo.getAviao());
        values.put(PISTA, voo.getPista());
        values.put(TOTALBILHETES, voo.getTotalbilhetes());
        // db.insert retorna -1 em caso de erro ou o id que foi criado
        int id = (int)db.insert(TABLE_VOO, null, values);
        if(id>-1)
        {
            voo.setId(id);
            return voo;
        }
        return null;
    }



    public Boolean editarVooBD(Voo voo)
    {
        //editar um voo especifico da bd
        ContentValues values = new ContentValues();
        values.put(DESIGNACAO, voo.getDesignacao());
        values.put(ESTADO, voo.getEstado());
        values.put(ID_AVIAO, voo.getId_aviao());
        values.put(AVIAO, voo.getAviao());
        values.put(PISTA, voo.getPista());
        values.put(TOTALBILHETES, voo.getTotalbilhetes());
        // db.update retorna o numero de linhas atualizadas
        return db.update(TABLE_VOO, values, ID+"=?", new String[]{voo.getId()+""})==1;

    }

    public Boolean removerVooBD(int id)
    {
        //remover um voo especifico da bd
        return db.delete(TABLE_VOO,ID+"=?", new String[]{id+""})==1;
    }

    public void removerAllVoo()
    {
        //remover todos os voos da bd
        db.delete(TABLE_VOO, null, null);
    }

    public ArrayList<Voo> getAllVooBD(){
        ArrayList<Voo> voos = new ArrayList<>();
        Cursor cursor = db.query(TABLE_VOO, new String[]{ID,DESIGNACAO, ESTADO, ID_AVIAO, AVIAO, PISTA, TOTALBILHETES}, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do {
                Voo auxVoo = new Voo(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(6), cursor.getString(5),cursor.getString(4));

                voos.add(auxVoo);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return voos;
    }
}

