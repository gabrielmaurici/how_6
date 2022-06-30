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
import com.example.rastreio_how6.loja.MenuFragment;

public class CadastroFragment extends Fragment {

    EditText editTextIdLoja, editTextNomeProduto, editTextValorProduto, editTextDescricaoProduto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastro_produto, container, false);

        editTextIdLoja = view.findViewById(R.id.editTextIdLoja);
        editTextNomeProduto = view.findViewById(R.id.editTextNomeProduto);
        editTextValorProduto = view.findViewById(R.id.editTextValorProduto);
        editTextDescricaoProduto = view.findViewById(R.id.editTextDescricaoProduto);

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

    private void cadastrarProduto() {
        if(editTextIdLoja.getText().toString().equals("") || editTextNomeProduto.getText().toString().equals("") ||
        editTextValorProduto.getText().toString().equals("") || editTextDescricaoProduto.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "É preciso preencher todos os campos do formulário", Toast.LENGTH_SHORT).show();
        } else {
            // cadastrar produto
        }
    }

    private void voltar() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new MenuFragment()).commit();
    }
}