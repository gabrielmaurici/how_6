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
import com.example.rastreio_how6.database.DatabaseHelper;
import com.example.rastreio_how6.database.RepositorioIdLoja;
import com.example.rastreio_how6.loja.CadastroFragment;
import com.example.rastreio_how6.loja.Loja;
import com.example.rastreio_how6.loja.MenuFragment;
import com.example.rastreio_how6.viewRastreio.RastrearEncomenda;

public class LoginFragment extends Fragment {

    private EditText editTextCnpj, editTextSenha;

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

        // Linkando variáveis com os campos da tela
        editTextCnpj = view.findViewById(R.id.editTextCnpj);
        editTextSenha = view.findViewById(R.id.editTextSenha);

        // Linkando botões com os botões da tela
        // e chamando os métodos que contém as funcionalidades de cada botão
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

    // Chama Fragment para rastreio de encomendas pro código
    private void rastrear() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new RastrearEncomenda()).commit();
    }

    // Método que verifica se os campos foram preenchidos
    // Realiza consulta no db e trata mensagens e redirect
    // Armazena id da loja na propriedade IdLoja da classe static RepositorioIdLoja para funcionalidades futuras
    private void realizarLogin() {
        if(editTextCnpj.getText().toString().equals("") || editTextSenha.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "CNPJ ou Senha inválidos", Toast.LENGTH_SHORT).show();
        } else {
            // realizar login
            DatabaseHelper repositorio = new DatabaseHelper(getActivity());
            int verificaUsuario = repositorio.loginLoja(editTextCnpj.getText().toString(), editTextSenha.getText().toString());

            Toast.makeText(getActivity(), verificaUsuario >= 1 ? "Realizando login..." : "CNPJ ou Senha inválidos", Toast.LENGTH_LONG).show();

            if(verificaUsuario >= 1) {
                RepositorioIdLoja.idLoja = verificaUsuario;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new MenuFragment()).commit();
            }
        }
    }

    // Chama Fragment para cadastro de uma loja
    private void cadastrarLoja() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new CadastroFragment()).commit();
    }
}