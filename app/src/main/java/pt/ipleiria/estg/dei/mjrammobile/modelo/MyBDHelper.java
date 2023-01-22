package pt.ipleiria.estg.dei.mjrammobile.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyBDHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="mjram";
    private static final int DB_VERSION=1;
    private static final String TABLE_VOO="voo";
    private static final String DESIGNACAO="designacao", ESTADO="estado", ID_AVIAO = "id_aviao", AVIAO="aviao", PISTA="pista", TOTALBILHETES="totalbilhetes", COMPANHIA="companhia" ,ID="id";

    private static final String TABLE_PERFIL="perfil";
    private static final String NIB ="nib", EMAIL ="email", TELEMOVEL = "telemovel", NOMES ="nomes", DATAREGISTO="dataregisto";

    private static final String TABLE_TAREFA="tarefa";
    private static final String ID_VOO="id_voo",ID_HANGAR="id_hangar",ID_RECURSO="id_recurso";

    private static final String TABLE_OCUPACAO ="ocupacao";
    private static final String CLASSE ="classe", OCUPACAO = "ocupacao";

    private static final String TABLE_AVIAO="aviao";
    private static final String COMBUSTIVELATUAL = "combustivelatual",COMBUSTIVELMAXIMO = "combustivelmaximo";



    private final SQLiteDatabase db;
    public MyBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreateTableVoo ="CREATE TABLE " + TABLE_VOO+ "(" +
                ID + " BIGINT  PRIMARY KEY, " +
                DESIGNACAO  + " VARCHAR NOT NULL, " +
                ESTADO  + " TEXT CHECK(estado in ('atrasado', 'cancelado', 'concluido', 'planeado')) NOT NULL, " +
                ID_AVIAO  + " BIGINT NOT NULL, " +
                AVIAO  + " VARCHAR NOT NULL, " +
                PISTA  + " VARCHAR NOT NULL, " +
                COMPANHIA + " VARCHAR NOT NULL, " +
                TOTALBILHETES + " INT NOT NULL " +
                ")";
        sqLiteDatabase.execSQL(sqlCreateTableVoo);

        String sqlCreateTablePerfil ="CREATE TABLE " + TABLE_PERFIL+ "(" +
                NOMES + " TEXT  NOT NULL, " +
                TELEMOVEL + " VARCHAR NOT NULL, " +
                NIB + " VARCHAR NOT NULL, " +
                EMAIL + " VARCHAR PRIMARY KEY," +
                DATAREGISTO + " VARCHAR NOT NULL"+
                ")";
        sqLiteDatabase.execSQL(sqlCreateTablePerfil);

        String sqlCreateTableTarefa ="CREATE TABLE " + TABLE_TAREFA+ "(" +
                ID + " BIGINT  PRIMARY KEY, " +
                DESIGNACAO  + " VARCHAR NOT NULL, " +
                ID_VOO  + " BIGINT NOT NULL, " +
                ID_HANGAR  + " VARCHAR NOT NULL, " +
                ID_RECURSO  + " VARCHAR NOT NULL, " +
                ESTADO  + " TEXT CHECK(estado in ('cancelado', 'concluido', 'planeada')) NOT NULL " +
                ")";
        sqLiteDatabase.execSQL(sqlCreateTableTarefa);

        String sqlCreateTableOcupacao ="CREATE TABLE " + TABLE_OCUPACAO + "(" +
                OCUPACAO + " INT NOT NULL, " +
                ID_AVIAO + " BIGINT PRIMARY KEY, " +
                CLASSE + " VARCHAR NOT NULL" +
                ")";
        sqLiteDatabase.execSQL(sqlCreateTableOcupacao);

        String sqlCreateTableAviao ="CREATE TABLE " + TABLE_AVIAO+ "(" +
                ID + " BIGINT  PRIMARY KEY, " +
                COMBUSTIVELATUAL + " INT NOT NULL, " +
                COMBUSTIVELMAXIMO + " INT NOT NULL " +
                ")";
        sqLiteDatabase.execSQL(sqlCreateTableAviao);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sqlDeleteTableVoo ="DROP TABLE IF EXISTS " + TABLE_VOO;
        sqLiteDatabase.execSQL(sqlDeleteTableVoo);

        String sqlDeleteTablePerfil ="DROP TABLE IF EXISTS " + TABLE_PERFIL;
        sqLiteDatabase.execSQL(sqlDeleteTablePerfil);

        String sqlDeleteTableTarefa ="DROP TABLE IF EXISTS " + TABLE_TAREFA;
        sqLiteDatabase.execSQL(sqlDeleteTableTarefa);

        String sqlDeleteTableOcupacao ="DROP TABLE IF EXISTS " + TABLE_OCUPACAO;
        sqLiteDatabase.execSQL(sqlDeleteTableOcupacao);

        String sqlDeleteTableAviao ="DROP TABLE IF EXISTS " + TABLE_AVIAO;
        sqLiteDatabase.execSQL(sqlDeleteTableAviao);

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
        values.put(COMPANHIA, voo.getCompanhia());
        values.put(PISTA, voo.getPista());
        values.put(TOTALBILHETES, voo.getTotalbilhetes());
        // db.insert retorna -1 em caso de erro ou o id que foi criado
        int id = (int)db.insert(TABLE_VOO, null, values);
        if(id>-1)
        {
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
        values.put(COMPANHIA, voo.getCompanhia());
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
        Cursor cursor = db.query(TABLE_VOO, new String[]{ID,DESIGNACAO, ESTADO, ID_AVIAO, AVIAO, PISTA, TOTALBILHETES, COMPANHIA},
                null, null, null, null, null);
        if(cursor.moveToFirst()){
            do {
                Voo auxVoo = new Voo(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3),
                        cursor.getInt(6), cursor.getString(5),cursor.getString(4),cursor.getString(7));

                voos.add(auxVoo);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return voos;
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
                Tarefa auxTarefa = new Tarefa(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3) , cursor.getString(4), cursor.getString(5));

                tarefas.add(auxTarefa);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return tarefas;
    }

    public Ocupacao adicionarOcupacaoBD(Ocupacao ocupacao)
    {
        //adicionar o ocupacao a bd
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
        //remover todos os ocupacoes da bd
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

    public  ArrayList<Ocupacao> getOcupacaoDB(int id_aviao){
        ArrayList<Ocupacao> ocupacoes = new ArrayList<>();
        Cursor cursor = db.query(TABLE_OCUPACAO, new String[]{ OCUPACAO, CLASSE, ID_AVIAO}, null, null, null, ID_AVIAO+"="+ id_aviao, CLASSE);
        if(cursor.moveToFirst()){
            do {

                Ocupacao auxOcupacao = new Ocupacao(cursor.getInt(2), cursor.getInt(0), cursor.getString(1));


                ocupacoes.add(auxOcupacao);
            }while (cursor.moveToNext());

            cursor.close();
            return ocupacoes;
        }
        else{
            return null;
        }
    }

    public Aviao adicionarAviaoBD(Aviao aviao)
    {
        //adicionar um aviao a bd
        ContentValues values = new ContentValues();
        values.put(ID, aviao.getId());
        values.put(COMBUSTIVELATUAL, aviao.getCombustivelatual());
        values.put(COMBUSTIVELMAXIMO, aviao.getCombustivelatual());
        // db.insert retorna -1 em caso de erro ou o id que foi criado
        int id = (int)db.insert(TABLE_AVIAO, null, values);
        if(id>-1)
        {
            return aviao;
        }
        return null;
    }



    public void removerAllAviao()
    {
        //remover todos os aviao da bd
        db.delete(TABLE_AVIAO, null, null);
    }

    public ArrayList<Aviao> getAllAviaoBD(){
        ArrayList<Aviao> aviaos = new ArrayList<>();
        Cursor cursor = db.query(TABLE_AVIAO, new String[]{ID,COMBUSTIVELATUAL,COMBUSTIVELMAXIMO}, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do {

                Aviao auxAviao = new Aviao(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), new ArrayList<>()) ;

                aviaos.add(auxAviao);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return aviaos;
    }

}

