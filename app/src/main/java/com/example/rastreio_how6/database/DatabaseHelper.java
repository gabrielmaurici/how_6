package com.example.rastreio_how6.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;

import com.example.rastreio_how6.R;
import com.example.rastreio_how6.encomenda.Encomenda;
import com.example.rastreio_how6.loja.Loja;
import com.example.rastreio_how6.produto.Produto;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "rastreio";

    private static final String TABLE_LOJA = "loja";
    private static final String TABLE_PRODUTO = "produto";
    private static final String TABLE_ENCOMENDA = "encomenda";

    private static final String CREATE_TABLE_LOJA = "CREATE TABLE " + TABLE_LOJA + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(100), " +
            "cnpj VARCHAR(100), " +
            "senha VARCHAR(50))";

    private static final String CREATE_TABLE_PRODUTO = "CREATE TABLE " + TABLE_PRODUTO + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "id_loja INTEGER, " +
            "nome VARCHAR(100), " +
            "valor VARCHAR(15), " +
            "descricao VARCHAR(150), " +
            "CONSTRAINT fk_produto_loja FOREIGN KEY (id_loja) REFERENCES loja (id))";

    private static final String CREATE_TABLE_ENCOMENDA = "CREATE TABLE " + TABLE_ENCOMENDA + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "id_loja INTEGER, " +
            "id_produto INTEGER, " +
            "status VARCHAR(100), " +
            "guid VARCHAR(35), " +
            "data_envio DATE, " +
            "data_alteracao DATE, " +
            "CONSTRAINT fk_encomenda_loja FOREIGN KEY (id_loja) REFERENCES loja (id), " +
            "CONSTRAINT fk_encomenda_produto FOREIGN KEY (id_produto) REFERENCES produto (id))";

    private static final String DROP_TABLE_LOJA = "DROP TABLE IF EXISTS " + TABLE_LOJA;
    private static final String DROP_TABLE_PRODUTO = "DROP TABLE IF EXISTS " + TABLE_PRODUTO;
    private static final String DROP_TABLE_ENCOMENDA = "DROP TABLE IF EXISTS " + TABLE_ENCOMENDA;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_LOJA);
        sqLiteDatabase.execSQL(CREATE_TABLE_PRODUTO);
        sqLiteDatabase.execSQL(CREATE_TABLE_ENCOMENDA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE_LOJA);
        sqLiteDatabase.execSQL(DROP_TABLE_PRODUTO);
        sqLiteDatabase.execSQL(DROP_TABLE_ENCOMENDA);
        onCreate(sqLiteDatabase);
    }

    // CRUD Loja
    public long cadastrarLoja (Loja loja) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", loja.getNome());
        cv.put("cnpj", loja.getCnpj());
        cv.put("senha", loja.getSenha());
        long id = db.insert(TABLE_LOJA, null, cv);
        db.close();
        return id;
    }

    public long atualizaLoja (Loja loja) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", loja.getNome());
        cv.put("cnpj", loja.getCnpj());
        cv.put("senha", loja.getSenha());
        long id = db.update(TABLE_LOJA, cv,
                "_id = ?", new String[]{String.valueOf(loja.getId())});
        db.close();
        return id;
    }

    public long deletaLoja (int idLoja) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_LOJA, "_id = ?",
                new String[]{String.valueOf(idLoja)});
        db.close();
        return id;
    }

    public Loja buscaLojaPorId (int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "cnpj", "senha"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_LOJA, columns, "_id = ?", args,
                null, null, null);
        data.moveToFirst();
        Loja loja = new Loja();
        loja.setId(data.getInt(0));
        loja.setNome(data.getString(1));
        loja.setCnpj(data.getString(2));
        loja.setSenha(data.getString(3));
        data.close();
        db.close();
        return loja;
    }

    public int loginLoja (String cnpj, String senha) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id"};
        String[] args = {String.valueOf(cnpj), String.valueOf(senha)};
        Cursor data = db.query(TABLE_LOJA, columns, "cnpj = ? and senha = ? ", args,
                null, null, null);
        data.moveToFirst();

        if(data.getCount() == 0)
            return 0;

        return data.getInt(0);
    }
    // Fim CRUD Loja

    // CRUD Produto
    public long cadastrarProduto (Produto produto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id_loja", produto.getId_loja());
        cv.put("nome", produto.getNome());
        cv.put("valor", produto.getValor());
        cv.put("descricao", produto.getDescricao());
        long id = db.insert(TABLE_PRODUTO, null, cv);
        db.close();
        return id;
    }

    public long atualizaProduto (Produto produto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", produto.getNome());
        cv.put("valor", produto.getValor());
        cv.put("descricao", produto.getDescricao());
        long id = db.update(TABLE_PRODUTO, cv,
                "_id = ?", new String[]{String.valueOf(produto.getId())});
        db.close();
        return id;
    }

    public long deletaProduto (int idProduto) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_PRODUTO, "_id = ?",
                new String[]{String.valueOf(idProduto)});
        db.close();
        return id;
    }


    public Produto buscaProdutoPorId (int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "id_loja", "nome", "valor", "descricao"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_PRODUTO, columns, "_id = ?", args,
                null, null, null);
        data.moveToFirst();
        Produto produto = new Produto();
        produto.setId(data.getInt(0));
        produto.setId_loja(data.getInt(1));
        produto.setNome(data.getString(2));
        produto.setValor(data.getString(3));
        produto.setDescricao(data.getString(4));
        data.close();
        db.close();
        return produto;
    }

    public void buscarTodosProdutos (Context context, ListView lv, int idLoja) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "valor", "descricao"};
        String[] args = {String.valueOf(idLoja)};
        Cursor data = db.query(TABLE_PRODUTO, columns, "id_loja = ?", args,
                null, null, "nome");
        int[] to = {R.id.textViewIdListarProduto, R.id.textViewNomeListarProduto,
                R.id.textViewValorListarProduto, R.id.textViewDescricaoListarProduto};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.produto_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }
    // Fim CRUD Produto

    // CRUD Encomenda
    public long cadastrarEncomenda (Encomenda encomenda) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id_loja", encomenda.getId_loja());
        cv.put("id_produto", encomenda.getId_produto());
        cv.put("guid", encomenda.getGuid());
        cv.put("status", encomenda.getStatus());
        cv.put("data_envio", encomenda.getData_envio());
        cv.put("data_alteracao", encomenda.getData_alteracao());
        long id = db.insert(TABLE_ENCOMENDA, null, cv);
        db.close();
        return id;
    }

    public void buscarTodasEncomendas (Context context, ListView lv, int idLoja) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "guid", "status", "data_envio", "data_alteracao"};
        String[] args = {String.valueOf(idLoja)};
        Cursor data = db.query(TABLE_ENCOMENDA, columns, "id_loja = ?", args,
                null, null, "_id");
        int[] to = {R.id.textViewValorIdEncomenda, R.id.textViewValorCodigoEncomenda,
                R.id.textViewValorStatusEncomenda, R.id.textViewValorDataEnvioEncomenda,
                R.id.textViewValorDataAlteracao
        };
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.encomenda_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }

    public Encomenda buscarEncomendaPorId (int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "id_loja", "id_produto", "guid", "status", "data_envio", "data_alteracao"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_ENCOMENDA, columns, "_id = ?", args,
                null, null, null);
        data.moveToFirst();
        Encomenda encomenda = new Encomenda();
        encomenda.setId(data.getInt(0));
        encomenda.setId_loja(data.getInt(1));
        encomenda.setId_produto(data.getInt(2));
        encomenda.setGuid(data.getString(3));
        encomenda.setStatus(data.getString(4));
        encomenda.setData_envio(data.getString(5));
        encomenda.setData_alteracao(data.getString(6));
        data.close();
        db.close();
        return encomenda;
    }

    public Encomenda buscarEncomendaPorGuid (String guid) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "id_loja", "id_produto", "guid", "status", "data_envio", "data_alteracao"};
        String[] args = {String.valueOf(guid)};
        Cursor data = db.query(TABLE_ENCOMENDA, columns, "guid = ?", args,
                null, null, null);
        data.moveToFirst();

        if(data.getCount() == 0)
            return new Encomenda();

        Encomenda encomenda = new Encomenda();
        encomenda.setId(data.getInt(0));
        encomenda.setId_loja(data.getInt(1));
        encomenda.setId_produto(data.getInt(2));
        encomenda.setGuid(data.getString(3));
        encomenda.setStatus(data.getString(4));
        encomenda.setData_envio(data.getString(5));
        encomenda.setData_alteracao(data.getString(6));
        data.close();
        db.close();
        return encomenda;
    }

    public long atualizarEncomenda (Encomenda encomenda) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id_produto", encomenda.getId_produto());
        cv.put("status", encomenda.getStatus());
        cv.put("data_alteracao", encomenda.getData_alteracao());
        long id = db.update(TABLE_ENCOMENDA, cv,
                "_id = ?", new String[]{String.valueOf(encomenda.getId())});
        db.close();
        return id;
    }

    public long deletaEncomenda (int idEncomenda) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_ENCOMENDA, "_id = ?",
                new String[]{String.valueOf(idEncomenda)});
        db.close();
        return id;
    }
    // Fim CRUD Encomenda
}
