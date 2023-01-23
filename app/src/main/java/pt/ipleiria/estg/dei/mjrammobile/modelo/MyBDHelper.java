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
    private static final String ID_VOO="id_voo",ID_HANGAR="id_hangar",ID_RECURSO="id_recurso", QUANTIDADE = "quantidade";

    private static final String TABLE_AVIAO="aviao";
    private static final String IDA = "ida",COMBUSTIVELATUAL = "combustivelatual",COMBUSTIVELMAXIMO = "combustivelmaximo",OCUPACAOECONOMICA = "ocupacaoeconomica",OCUPACAOPRIMEIRA = "ocupacaoprimeira",OCUPACAOBUSINESS = "ocupacaobusiness";

    private static final String TABLE_HANGAR="hangar";

    private static final String TABLE_RECURSO="recurso";
    private static final String NOME = "nome",UNIDADEMEDIDA = "unidadeMedida";





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
                ID + " BIGINT  PRIMARY KEY, " +
                NOMES + " VARCHAR  NOT NULL, " +
                TELEMOVEL + " VARCHAR NOT NULL, " +
                NIB + " VARCHAR NOT NULL, " +
                EMAIL + " VARCHAR NOT NULL," +
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

        String sqlCreateTableAviao ="CREATE TABLE " + TABLE_AVIAO+ "(" +
                ID + " BIGINT  PRIMARY KEY, " +
                COMBUSTIVELATUAL + " INT NOT NULL, " +
                COMBUSTIVELMAXIMO + " INT NOT NULL, " +
                OCUPACAOECONOMICA + " INT NOT NULL, " +
                OCUPACAOPRIMEIRA + " INT NOT NULL, " +
                OCUPACAOBUSINESS + " INT NOT NULL " +
                ")";
        sqLiteDatabase.execSQL(sqlCreateTableAviao);

        String sqlCreateTableHangar ="CREATE TABLE " + TABLE_HANGAR+ "(" +
                ID + " BIGINT  PRIMARY KEY, " +
                DESIGNACAO + " VARCHAR NOT NULL "+
                ")";
        sqLiteDatabase.execSQL(sqlCreateTableHangar);

        String sqlCreateTableRecurso ="CREATE TABLE " + TABLE_RECURSO+ "(" +
                ID + " BIGINT  PRIMARY KEY, " +
                NOME + " VARCHAR NOT NULL, "+
                UNIDADEMEDIDA + " VARCHAR NOT NULL "+
                ")";
        sqLiteDatabase.execSQL(sqlCreateTableRecurso);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sqlDeleteTableVoo ="DROP TABLE IF EXISTS " + TABLE_VOO;
        sqLiteDatabase.execSQL(sqlDeleteTableVoo);

        String sqlDeleteTablePerfil ="DROP TABLE IF EXISTS " + TABLE_PERFIL;
        sqLiteDatabase.execSQL(sqlDeleteTablePerfil);

        String sqlDeleteTableTarefa ="DROP TABLE IF EXISTS " + TABLE_TAREFA;
        sqLiteDatabase.execSQL(sqlDeleteTableTarefa);

        String sqlDeleteTableAviao ="DROP TABLE IF EXISTS " + TABLE_AVIAO;
        sqLiteDatabase.execSQL(sqlDeleteTableAviao);

        String sqlDeleteTableHangar ="DROP TABLE IF EXISTS " + TABLE_HANGAR;
        sqLiteDatabase.execSQL(sqlDeleteTableHangar);

        String sqlDeleteTableRecurso ="DROP TABLE IF EXISTS " + TABLE_RECURSO;
        sqLiteDatabase.execSQL(sqlDeleteTableRecurso);

        onCreate(sqLiteDatabase);
    }

    //---------------------------------------------------------------VOO------------------------------------------------------------
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

    //---------------------------------------------------------------PERFIL------------------------------------------------------------
    public Perfil adicionarPerfilBD(Perfil perfil)
    {

        //adicionar o perfil a bd
        ContentValues values = new ContentValues();
        values.put(ID, 1);
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

//
//    public Boolean editarPerfilBD(Perfil perfil)
//    {
//        //editar um perfil especifico da bd
//        ContentValues values = new ContentValues();
//        values.put(TELEMOVEL, perfil.getTelemovel());
//        values.put(EMAIL, perfil.getEmail());
//        values.put(NIB, perfil.getNib());
//        values.put(DATAREGISTO, perfil.getDataregisto());
//        // db.update retorna o numero de linhas atualizadas
//        return db.update(TABLE_PERFIL, values, EMAIL +"=?", new String[]{perfil.getEmail()+""})==1;
//
//    }

    public Boolean removerPerfilBD()
    {
        //remover os perfis da bd
        return db.delete(TABLE_PERFIL, null, null)==1;
    }



    public Perfil getPerfilBD(){
        Cursor cursor = db.query(TABLE_PERFIL, new String[]{NIB, EMAIL, TELEMOVEL, NOMES,DATAREGISTO,ID}, null, null, null, null, null);
        if(cursor.moveToFirst()){

            Perfil perfil = new Perfil(cursor.getString(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),1);
            cursor.close();
            return perfil;
        }
        else{
            return null;
        }
    }

    //---------------------------------------------------------------TAREFA------------------------------------------------------------
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
        Cursor cursor = db.query(TABLE_TAREFA, new String[]{ID, ID_VOO,ID_HANGAR, ID_RECURSO, ESTADO,DESIGNACAO, QUANTIDADE}, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do {
                Tarefa auxTarefa = new Tarefa(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3) , cursor.getString(4), cursor.getString(5), cursor.getInt(6));

                tarefas.add(auxTarefa);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return tarefas;
    }

    public Aviao adicionarAviaoBD(Aviao aviao)
    {
        //adicionar um aviao a bd
        ContentValues values = new ContentValues();
        values.put(ID, aviao.getId());
        values.put(COMBUSTIVELATUAL, aviao.getCombustivelatual());
        values.put(COMBUSTIVELMAXIMO, aviao.getCombustivelatual());
        values.put(OCUPACAOECONOMICA, aviao.getCombustivelatual());
        values.put(OCUPACAOBUSINESS, aviao.getCombustivelatual());
        values.put(OCUPACAOPRIMEIRA, aviao.getCombustivelatual());
        // db.insert retorna -1 em caso de erro ou o id que foi criado
        int id = (int)db.insert(TABLE_AVIAO, null, values);
        if(id>-1)
        {
            aviao.setId(aviao.getId());
            return aviao;
        }
        return null;
    }

    public void removerAllAviao()
    {
        //remover todos os aviao da bd
        db.delete(TABLE_AVIAO, null, null);
    }

    public Aviao getAllAviaoBD(){
        Cursor cursor = db.query(TABLE_AVIAO, new String[]{ID,COMBUSTIVELATUAL,COMBUSTIVELMAXIMO,OCUPACAOECONOMICA,OCUPACAOPRIMEIRA,OCUPACAOBUSINESS}, null, null, null, null, null);
        if(cursor.moveToFirst()){
                Aviao auxAviao = new Aviao(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2),cursor.getInt(3),cursor.getInt(4),cursor.getInt(5)) ;
            cursor.close();
            return auxAviao;

        }else{
            return null;
        }
    }


    public void removerAllAviao()
    {
        //remover todos os aviao da bd
        db.delete(TABLE_AVIAO, null, null);
    }

    public Hangar adicionarHangarBD(Hangar hangar)
    {
        //adicionar um hangar a bd
        ContentValues values = new ContentValues();
        values.put(ID, hangar.getId());
        values.put(DESIGNACAO, hangar.getDesignacao());
        // db.insert retorna -1 em caso de erro ou o id que foi criado
        int id = (int)db.insert(TABLE_HANGAR, null, values);
        if(id>-1)
        {
            return hangar;
        }
        return null;
    }

    public void removerAllHangar()
    {
        //remover todos os hangar da bd
        db.delete(TABLE_HANGAR, null, null);
    }

    public ArrayList<Hangar> getAllHangarBD(){
        ArrayList<Hangar> hangars = new ArrayList<>();
        Cursor cursor = db.query(TABLE_HANGAR, new String[]{ID,DESIGNACAO}, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do {
                Hangar auxHangar = new Hangar(cursor.getInt(0), cursor.getString(1));
                hangars.add(auxHangar);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return hangars;
    }

    public Recurso adicionarRecursoBD(Recurso recurso)
    {
        //adicionar um hangar a bd
        ContentValues values = new ContentValues();
        values.put(ID, recurso.getId());
        values.put(NOME, recurso.getNome());
        values.put(UNIDADEMEDIDA, recurso.getUnidadeMedida());
        // db.insert retorna -1 em caso de erro ou o id que foi criado
        int id = (int)db.insert(TABLE_RECURSO, null, values);
        if(id>-1)
        {
            return recurso;
        }
        return null;
    }

    public void removerAllRecurso()
    {
        //remover todos os hangar da bd
        db.delete(TABLE_RECURSO, null, null);
    }

    public ArrayList<Recurso> getAllRecursoBD(){
        ArrayList<Recurso> recursos = new ArrayList<>();
        Cursor cursor = db.query(TABLE_RECURSO, new String[]{ID,NOME,UNIDADEMEDIDA}, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do {
                Recurso auxRecurso = new Recurso(cursor.getInt(0), cursor.getString(1),cursor.getString(2));
                recursos.add(auxRecurso);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return recursos;
    }
}

