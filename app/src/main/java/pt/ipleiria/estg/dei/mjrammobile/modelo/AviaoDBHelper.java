package pt.ipleiria.estg.dei.mjrammobile.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class AviaoDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="mjram";
    private static final int DB_VERSION=1;
    private static final String TABLE_AVIAO="aviao";
    private static final String COMBUSTIVELATUAL = "combustivelatual", ID="id";
    private final SQLiteDatabase db;
    public AviaoDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreateTableAviao ="CREATE TABLE " + TABLE_AVIAO+ "(" +
                ID + " BIGINT  PRIMARY KEY, " +
                COMBUSTIVELATUAL + " INT NOT NULL " +
                ")";
        sqLiteDatabase.execSQL(sqlCreateTableAviao);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sqlDeleteTableAviao ="DROP TABLE IF EXISTS " + TABLE_AVIAO;
        sqLiteDatabase.execSQL(sqlDeleteTableAviao);
        onCreate(sqLiteDatabase);
    }

    public Aviao adicionarAviaoBD(Aviao aviao)
    {
        //adicionar um aviao a bd
        ContentValues values = new ContentValues();
        values.put(ID, aviao.getId());
        values.put(COMBUSTIVELATUAL, aviao.getCombustivelatual());
        // db.insert retorna -1 em caso de erro ou o id que foi criado
        int id = (int)db.insert(TABLE_AVIAO, null, values);
        if(id>-1)
        {
            aviao.setId(id);
            return aviao;
        }
        return null;
    }



    public void removerAllAviao()
    {
        //remover todos os voos da bd
        db.delete(TABLE_AVIAO, null, null);
    }

    public ArrayList<Aviao> getAllAviaoBD(){
        ArrayList<Aviao> aviaos = new ArrayList<>();
        Cursor cursor = db.query(TABLE_AVIAO, new String[]{ID,COMBUSTIVELATUAL}, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do {

                Aviao auxAviao = new Aviao(cursor.getInt(0), cursor.getInt(1), new ArrayList<>()) ;

                aviaos.add(auxAviao);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return aviaos;
    }
}
