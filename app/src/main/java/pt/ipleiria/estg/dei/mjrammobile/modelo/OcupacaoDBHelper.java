package pt.ipleiria.estg.dei.mjrammobile.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OcupacaoDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="mjram";
    private static final int DB_VERSION=1;
    private static final String TABLE_OCUPACAO ="ocupacao";
    private static final String ID_AVIAO ="id_aviao", CLASSE ="classe", OCUPACAO = "ocupacao";
    private final SQLiteDatabase db;
    public OcupacaoDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreateTablePerfil ="CREATE TABLE " + TABLE_OCUPACAO + "(" +
                OCUPACAO + " INT NOT NULL, " +
                ID_AVIAO + " BIGINT PRIMARY KEY, " +
                CLASSE + " VARCHAR NOT NULL" +
                ")";
        sqLiteDatabase.execSQL(sqlCreateTablePerfil);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sqlDeleteTableOcupacao ="DROP TABLE IF EXISTS " + TABLE_OCUPACAO;
        sqLiteDatabase.execSQL(sqlDeleteTableOcupacao);
        onCreate(sqLiteDatabase);
    }

    public Ocupacao adicionarOcupacaoBD(Ocupacao ocupacao)
    {
        //adicionar o perfil a bd
        ContentValues values = new ContentValues();
        values.put(OCUPACAO, ocupacao.getOcupacao());
        values.put(CLASSE, ocupacao.getClasse());
        values.put(ID_AVIAO, ocupacao.getId_aviao());
        // db.insert retorna -1 em caso de erro ou o id que foi criado
        int id = (int)db.insert(TABLE_OCUPACAO, null, values);
        if(id>-1)
        {
            return ocupacao;
        }
        return null;
    }



    public void removerAllOcupacao()
    {
        //remover todos os voos da bd
        db.delete(TABLE_OCUPACAO, null, null);
    }


    public Ocupacao getAllOcupacaoBD(){
        Cursor cursor = db.query(TABLE_OCUPACAO, new String[]{ OCUPACAO, CLASSE, ID_AVIAO}, null, null, null, null, null);
        if(cursor.moveToFirst()){
            Ocupacao ocupacao = new Ocupacao(cursor.getInt(2), cursor.getInt(0), cursor.getString(1));

            cursor.close();
            return ocupacao;
        }
        else{
            return null;
        }
    }

    public Ocupacao getOcupacaoDB(int id_aviao){
        Cursor cursor = db.query(TABLE_OCUPACAO, new String[]{ OCUPACAO, CLASSE, ID_AVIAO}, null, null, null, ID_AVIAO+"="+ id_aviao, CLASSE);
        if(cursor.moveToFirst()){
            Ocupacao ocupacao = new Ocupacao(cursor.getInt(2), cursor.getInt(0), cursor.getString(1));

            cursor.close();
            return ocupacao;
        }
        else{
            return null;
        }
    }
}
