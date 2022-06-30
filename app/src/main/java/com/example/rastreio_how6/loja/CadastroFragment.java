package com.example.rastreio_how6.loja;

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
import com.example.rastreio_how6.login.LoginFragment;

public class CadastroFragment extends Fragment {

    EditText editTextNomeLoja, editTextCnpjCadastro, editTextSenhaCadastro;

    public CadastroFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastro_loja, container, false);

        editTextNomeLoja = view.findViewById(R.id.editTextNomeLoja);
        editTextCnpjCadastro = view.findViewById(R.id.editTextCnpjCadastro);
        editTextSenhaCadastro = view.findViewById(R.id.editTextSenhaCadastro);


        Button btnCadastrarLoja = view.findViewById(R.id.buttonCadastrarLoja);
        btnCadastrarLoja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();
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

    private void cadastrar() {
        if(editTextNomeLoja.getText().toString().equals("") ||
            editTextCnpjCadastro.getText().toString().equals("") ||
            editTextSenhaCadastro.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "É preciso preencher todos os campos do formulário", Toast.LENGTH_SHORT).show();
        } else {
            DatabaseHelper repositorio = new DatabaseHelper(getActivity());

            Loja loja = new Loja(editTextNomeLoja.getText().toString(),
                editTextCnpjCadastro.getText().toString(),
                editTextSenhaCadastro.getText().toString()
            );

            long id = repositorio.cadastrarLoja(loja);
            RepositorioIdLoja.idLoja = (int)id;

            Toast.makeText(getActivity(), "Loja Cadastrada com sucesso!", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new MenuFragment()).commit();
        }
    }

    private void voltar() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new LoginFragment()).commit();;
    }
}