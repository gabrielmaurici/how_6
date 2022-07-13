package com.example.rastreio_how6.encomenda;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rastreio_how6.R;
import com.example.rastreio_how6.database.DatabaseHelper;
import com.example.rastreio_how6.database.RepositorioIdLoja;
import com.example.rastreio_how6.loja.MenuFragment;
import com.example.rastreio_how6.produto.ListarFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CadastroFragment extends Fragment {

    TextView textViewStatusEncomenda;
    EditText editTextIdProduto;

    public CadastroFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastrar_encomenda, container, false);


        editTextIdProduto = view.findViewById(R.id.editTextIdProduto);
        textViewStatusEncomenda = view.findViewById(R.id.textViewStatusEncomendaValor);

        Button btnCadastrarEncomenda = view.findViewById(R.id.buttonCadastrarEncomendaFinal);
        btnCadastrarEncomenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarEncomenda();
            }
        });

        Button btnVoltar = view.findViewById(R.id.buttonVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });

        return view;
    }

    private void cadastrarEncomenda() {
        if(editTextIdProduto.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "É preciso preencher todos os campos do formulário", Toast.LENGTH_SHORT).show();
        } else {
            // cadastrar encomenda
            Encomenda encomenda = new Encomenda(
                    RepositorioIdLoja.idLoja,
                    Integer.parseInt(editTextIdProduto.getText().toString()),
                    UUID.randomUUID().toString(),
                    textViewStatusEncomenda.getText().toString(),
                    formatarData(new Date())
            );

            DatabaseHelper repositorio = new DatabaseHelper(getActivity());
            repositorio.cadastrarEncomenda(encomenda);

            Toast.makeText(getActivity(), "Encomenda cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new ListagemFragment()).commit();
        }
    }

    // Formata data para padrão brasileiro
    private String formatarData(Date data) {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        return formatador.format(data);
    }

    private void voltar() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new MenuFragment()).commit();
    }
}