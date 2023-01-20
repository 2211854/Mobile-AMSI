package pt.ipleiria.estg.dei.mjrammobile.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class TarefaDBHelper extends SQLiteOpenHelper{
    private static final String DB_NAME="mjram";
    private static final int DB_VERSION=1;
    private static final String TABLE_TAREFA="tarefa";
    private static final String DESIGNACAO="designacao", ESTADO="estado", ID="id",ID_VOO="id_voo",ID_HANGAR="id_hangar",ID_RECURSO="id_recurso";
    private final SQLiteDatabase db;
    public TarefaDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreateTableTarefa ="CREATE TABLE " + TABLE_TAREFA+ "(" +
                ID + " BIGINT  PRIMARY KEY, " +
                DESIGNACAO  + " VARCHAR NOT NULL, " +
                ID_VOO  + " BIGINT NOT NULL, " +
                ID_HANGAR  + " BIGINT , " +
                ID_RECURSO  + " BIGINT, " +
                ESTADO  + "TEXT CHECK(ESTADO in ('atrasado', 'cancelado', 'concluido', 'planeado')) NOT NULL " +
                ")";
        sqLiteDatabase.execSQL(sqlCreateTableTarefa);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sqlDeleteTableTarefa ="DROP TABLE IF EXISTS " + TABLE_TAREFA;
        sqLiteDatabase.execSQL(sqlDeleteTableTarefa);
        onCreate(sqLiteDatabase);
    }

    public Tarefa adicionarTarefaBD(Tarefa tarefa)
    {
        //adicionar um tarefa a bd
        ContentValues values = new ContentValues();
        values.put(ID, tarefa.getId());
        values.put(DESIGNACAO, tarefa.getDesignacao());;
        values.put(ID_VOO, tarefa.getId_voo());;
        values.put(ID_HANGAR, tarefa.getId_hangar());;
        values.put(ID_RECURSO, tarefa.getId_recurso());
        values.put(ESTADO, tarefa.getEstado());
        // db.insert retorna -1 em caso de erro ou o id que foi criado
        int id = (int)db.insert(TABLE_TAREFA, null, values);
        if(id>-1)
        {
            tarefa.setId(id);
            return tarefa;
        }
        return null;
    }

    public Boolean editarTarefaBD(Tarefa tarefa)
    {
        //editar uma tarefa especifico da bd
        ContentValues values = new ContentValues();
        values.put(DESIGNACAO, tarefa.getDesignacao());;
        values.put(ID_VOO, tarefa.getId_voo());;
        values.put(ID_HANGAR, tarefa.getId_hangar());;
        values.put(ID_RECURSO, tarefa.getId_recurso());
        values.put(ESTADO, tarefa.getEstado());
        // db.update retorna o numero de linhas atualizadas
        return db.update(TABLE_TAREFA, values, ID+"=?", new String[]{tarefa.getId()+""})==1;

    }

    public Boolean removerTarefaBD(int id)
    {
        //remover um tarefa especifico da bd
        return db.delete(TABLE_TAREFA,ID+"=?", new String[]{id+""})==1;
    }

    public void removerAllTarefa()
    {
        //remover todos os tarefas da bd
        db.delete(TABLE_TAREFA, null, null);
    }

    public ArrayList<Tarefa> getAllTarefaBD(){
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        Cursor cursor = db.query(TABLE_TAREFA, new String[]{ID, ID_VOO,ID_HANGAR, ID_RECURSO, ESTADO,DESIGNACAO }, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do {
                Tarefa auxTarefa = new Tarefa(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3) , cursor.getString(4), cursor.getString(5));

                tarefas.add(auxTarefa);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return tarefas;
    }
}
