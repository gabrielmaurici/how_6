package com.example.rastreio_how6.loja;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
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


public class EditarFragment extends Fragment {

    EditText editTextNomeLojistaEdicao, editTextCnpjEdicao, editTextSenhaEdicao;

    public EditarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_editar_loja, container, false);


        editTextNomeLojistaEdicao = view.findViewById(R.id.editTextNomeLojistaEdicao);
        editTextCnpjEdicao = view.findViewById(R.id.editTextCnpjEdicao);
        editTextSenhaEdicao = view.findViewById(R.id.editTextSenhaEdicao);

        buscarLoja();

        Button btnEditar = view.findViewById(R.id.buttonSalvarAlteracoesLoja);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar();
            }
        });

        Button btnDeletar = view.findViewById(R.id.buttonDeletarLoja);
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

    private void buscarLoja() {
        DatabaseHelper repositorio = new DatabaseHelper(getActivity());
        Loja loja = repositorio.buscaLojaPorId(RepositorioIdLoja.idLoja);
        editTextNomeLojistaEdicao.setText(loja.getNome());
        editTextCnpjEdicao.setText(loja.getCnpj());
        editTextSenhaEdicao.setText(loja.getSenha());
    }

    private void editar() {
        if(editTextNomeLojistaEdicao.getText().toString().equals("") ||
                editTextCnpjEdicao.getText().toString().equals("") ||
                editTextSenhaEdicao.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "É preciso preencher todos os campos do formulário", Toast.LENGTH_SHORT).show();
        } else {
            DatabaseHelper repositorio = new DatabaseHelper(getActivity());
            Loja loja = new Loja(
                RepositorioIdLoja.idLoja,
                editTextNomeLojistaEdicao.getText().toString(),
                editTextCnpjEdicao.getText().toString(),
                editTextSenhaEdicao.getText().toString()
            );

            repositorio.atualizaLoja(loja);

            Toast.makeText(getActivity(), "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new MenuFragment()).commit();
        }
    }

    private void deletar() {
        DatabaseHelper repositorio = new DatabaseHelper(getActivity());
        repositorio.deletaLoja(RepositorioIdLoja.idLoja);

        Toast.makeText(getActivity(), "Loja deletada com sucesso!", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new LoginFragment()).commit();
    }
    
    private void voltar() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new MenuFragment()).commit();
    }
}