package com.example.rastreio_how6.encomenda;

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

    EditText editTextIdLoja, editTextIdProduto;

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

        editTextIdLoja = view.findViewById(R.id.editTextIdLoja);
        editTextIdProduto = view.findViewById(R.id.editTextIdProduto);

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
        if(editTextIdLoja.getText().toString().equals("") || editTextIdProduto.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "É preciso preencher todos os campos do formulário", Toast.LENGTH_SHORT).show();
        } else {
            // cadastrar encomenda
        }
    }

    private void voltar() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new MenuFragment()).commit();
    }
}