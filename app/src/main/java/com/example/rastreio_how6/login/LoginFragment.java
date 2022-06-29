package com.example.rastreio_how6.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rastreio_how6.R;
import com.example.rastreio_how6.loja.CadastroFragment;
import com.example.rastreio_how6.loja.MenuFragment;
import com.example.rastreio_how6.viewRastreio.RastrearEncomenda;

public class LoginFragment extends Fragment {

    private EditText editTextCnpj;
    private EditText editTextSenha;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        editTextCnpj = view.findViewById(R.id.editTextCnpj);
        editTextSenha = view.findViewById(R.id.editTextSenha);


        Button btnRastrearEncomenda = view.findViewById(R.id.buttonRastrear);
        btnRastrearEncomenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rastrear();
            }
        });

        Button btnLogin = view.findViewById(R.id.buttonLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarLogin();
            }
        });

        Button btnCadastrar = view.findViewById(R.id.buttonCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarLoja();
            }
        });

        return view;
    }

    private void rastrear() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new RastrearEncomenda()).commit();
    }

    private void realizarLogin() {
        if(editTextCnpj.getText().toString().equals("") || editTextSenha.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "CNPJ ou Senha inválidos", Toast.LENGTH_SHORT).show();
        } else {
            // realizar login

            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new MenuFragment()).commit();
        }
    }

    private void cadastrarLoja() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new CadastroFragment()).commit();
    }
}