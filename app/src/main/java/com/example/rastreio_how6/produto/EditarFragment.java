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
import com.example.rastreio_how6.loja.Loja;
import com.example.rastreio_how6.loja.MenuFragment;


public class EditarFragment extends Fragment {

    private EditText EditTextNome, EditTextValor, EditTextDescricao;
    private int id_produto, id_loja;

    public EditarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editar_produto, container, false);

        // Linkando variáveis com os campos da tela
        EditTextNome = view.findViewById(R.id.editTextNomeProdutoEdicao);
        EditTextValor = view.findViewById(R.id.editTextValorProdutoEdicao);
        EditTextDescricao = view.findViewById(R.id.editTextDescricaoProdutoEdicao);

        // Recebe id do produto via bundle
        Bundle b = getArguments();
        id_produto = b.getInt("id");

        // Busca produto por id
        buscarProduto();

        // Linkando botões com os botões da tela
        // e chamando os métodos que contém as funcionalidades de cada botão
        Button btnEditar = view.findViewById(R.id.buttonSalvarAlteracoesProduto);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar();
            }
        });

        Button btnDeletar = view.findViewById(R.id.buttonDeletarProduto);
        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletar();
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

    // Busca produto por id no db
    private void buscarProduto() {
        DatabaseHelper repositorio = new DatabaseHelper(getActivity());
        Produto produto = repositorio.buscaProdutoPorId(id_produto);
        id_loja = produto.getId_loja();
        EditTextNome.setText(produto.getNome());
        EditTextValor.setText(produto.getValor());
        EditTextDescricao.setText(produto.getDescricao());
    }

    // Método que verifica se os campos foram preenchidos
    // Realiza update no db e trata mensagens e redirect
    private void editar() {
        if(EditTextNome.getText().toString().equals("") ||
                EditTextValor.getText().toString().equals("") ||
                EditTextDescricao.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "É preciso preencher todos os campos do formulário", Toast.LENGTH_SHORT).show();
        } else {
            DatabaseHelper repositorio = new DatabaseHelper(getActivity());
            Produto produto = new Produto(
                    id_produto,
                    id_loja,
                    EditTextNome.getText().toString(),
                    EditTextValor.getText().toString(),
                    EditTextDescricao.getText().toString()
            );

            repositorio.atualizaProduto(produto);

            Toast.makeText(getActivity(), "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new ListarFragment()).commit();
        }
    }

    // Realiza delete no db e trata mensagens e redirect
    private void deletar() {
        DatabaseHelper repositorio = new DatabaseHelper(getActivity());
        repositorio.deletaProduto(id_produto);

        Toast.makeText(getActivity(), "Produto deletado com sucesso!", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new ListarFragment()).commit();
    }

    // Chama Fragment para voltar para o menu
    private void voltar() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new MenuFragment()).commit();
    }
}