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
import com.example.rastreio_how6.login.LoginFragment;

public class CadastroFragment extends Fragment {

    EditText editTextNomeLojista, editTextCnpjCadastro, editTextSenhaCadastro;

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
        View view = inflater.inflate(R.layout.loja_fragment_cadastro, container, false);

        editTextNomeLojista = view.findViewById(R.id.editTextNomeLojista);
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
        if(editTextNomeLojista.getText().toString().equals("") ||
            editTextCnpjCadastro.getText().toString().equals("") ||
            editTextSenhaCadastro.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "É preciso preencher todos os campos do formulário", Toast.LENGTH_SHORT).show();
        } else {
            // realizar cadastro
        }
    }

    private void voltar() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new LoginFragment()).commit();;
    }
}