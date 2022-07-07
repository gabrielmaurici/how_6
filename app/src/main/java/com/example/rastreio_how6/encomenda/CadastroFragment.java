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
import com.example.rastreio_how6.database.RepositorioIdLoja;
import com.example.rastreio_how6.loja.MenuFragment;

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
                    UUID.randomUUID(),
                    textViewStatusEncomenda.getText().toString(),
                    new Date()
            );
            SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");
            String teste = sdf1.format(encomenda.getData_envio());
            String a = "1";
        }
    }

    private void voltar() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new MenuFragment()).commit();
    }
}