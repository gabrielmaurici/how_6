package com.example.rastreio_how6.produto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rastreio_how6.R;
import com.example.rastreio_how6.database.DatabaseHelper;
import com.example.rastreio_how6.database.RepositorioIdLoja;
import com.example.rastreio_how6.loja.MenuFragment;

public class CadastroFragment extends Fragment {

    EditText editTextNomeProduto, editTextValorProduto, editTextDescricaoProduto;

    public CadastroFragment() {
        
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastro_produto, container, false);

        // Linkando variáveis com os campos da tela
        editTextNomeProduto = view.findViewById(R.id.editTextNomeProduto);
        editTextValorProduto = view.findViewById(R.id.editTextValorProduto);
        editTextDescricaoProduto = view.findViewById(R.id.editTextDescricaoProduto);

        // Linkando botões com os botões da tela
        // e chamando os métodos que contém as funcionalidades de cada botão
        Button btnCadastrarProduto = view.findViewById(R.id.buttonCadastrarProdutoFinal);
        btnCadastrarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarProduto();
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

    // Método que verifica se os campos foram preenchidos
    // Realiza insert no db e trata mensagens e redirect
    private void cadastrarProduto() {
        if(editTextNomeProduto.getText().toString().equals("") ||
            editTextValorProduto.getText().toString().equals("") ||
            editTextDescricaoProduto.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "É preciso preencher todos os campos do formulário", Toast.LENGTH_SHORT).show();
        } else {
            DatabaseHelper repositorio = new DatabaseHelper(getActivity());

            Produto produto = new Produto(
                RepositorioIdLoja.idLoja,
                editTextNomeProduto.getText().toString(),
                editTextValorProduto.getText().toString(),
                editTextDescricaoProduto.getText().toString()
            );

            repositorio.cadastrarProduto(produto);

            Toast.makeText(getActivity(), "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new ListarFragment()).commit();
        }
    }

    // Chama Fragment para voltar para o menu
    private void voltar() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new MenuFragment()).commit();
    }
}