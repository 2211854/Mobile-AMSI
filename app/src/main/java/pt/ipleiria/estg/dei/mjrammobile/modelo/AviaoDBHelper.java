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
    private static final String OCUPACAO="ocupacao", CLASSE="classe", COMBUSTIVELATUAL = "combustivelatual", ID="id";
    private final SQLiteDatabase db;
    public AviaoDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreateTableAviao ="CREATE TABLE " + TABLE_AVIAO+ "(" +
                ID + " BIGINT  PRIMARY KEY, " +
                COMBUSTIVELATUAL + " INT NOT NULL, " +
                OCUPACAO  + " INT NOT NULL, " +
                CLASSE  + " VARCHAR NOT NULL " +
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
        //adicionar um voo a bd
        ContentValues values = new ContentValues();
        values.put(ID, aviao.getId());
        values.put(COMBUSTIVELATUAL, aviao.getCombustivelatual());
        values.put(CLASSE, aviao.getClasse());
        values.put(OCUPACAO, aviao.getOcupacao());
        // db.insert retorna -1 em caso de erro ou o id que foi criado
        int id = (int)db.insert(TABLE_AVIAO, null, values);
        if(id>-1)
        {
            aviao.setId(id);
            return aviao;
        }
        return null;
    }

    public Boolean editarAviaoBD(Aviao aviao)
    {
        //editar um voo especifico da bd
        ContentValues values = new ContentValues();
        values.put(COMBUSTIVELATUAL, aviao.getCombustivelatual());
        values.put(CLASSE, aviao.getClasse());
        values.put(OCUPACAO, aviao.getOcupacao());
        // db.update retorna o numero de linhas atualizadas
        return db.update(TABLE_AVIAO, values, ID+"=?", new String[]{aviao.getId()+""})==1;

    }

    public Boolean removerAviaoBD(int id)
    {
        //remover um voo especifico da bd
        return db.delete(TABLE_AVIAO,ID+"=?", new String[]{id+""})==1;
    }

    public void removerAllAviao()
    {
        //remover todos os voos da bd
        db.delete(TABLE_AVIAO, null, null);
    }

    public ArrayList<Aviao> getAllAviaoBD(){
        ArrayList<Aviao> aviaos = new ArrayList<>();
        Cursor cursor = db.query(TABLE_AVIAO, new String[]{ID,COMBUSTIVELATUAL, CLASSE,OCUPACAO}, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do {
                Aviao auxVoo = new Aviao(cursor.getInt(5), cursor.getInt(0), cursor.getString(1),cursor.getInt(5));

                aviaos.add(auxVoo);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return aviaos;
    }
}
