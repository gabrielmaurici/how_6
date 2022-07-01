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
import com.example.rastreio_how6.loja.Loja;
import com.example.rastreio_how6.produto.Produto;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "rastreio";

    private static final String TABLE_LOJA = "loja";
    private static final String TABLE_PRODUTO = "produto";

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

    private static final String DROP_TABLE_LOJA = "DROP TABLE IF EXISTS " + TABLE_LOJA;
    private static final String DROP_TABLE_PRODUTO = "DROP TABLE IF EXISTS " + TABLE_PRODUTO;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_LOJA);
        sqLiteDatabase.execSQL(CREATE_TABLE_PRODUTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE_LOJA);
        sqLiteDatabase.execSQL(DROP_TABLE_PRODUTO);
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
}
