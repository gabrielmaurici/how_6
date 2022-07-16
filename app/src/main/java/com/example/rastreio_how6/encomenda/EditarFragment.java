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
import com.example.rastreio_how6.database.DatabaseHelper;
import com.example.rastreio_how6.produto.ListarFragment;
import com.example.rastreio_how6.produto.Produto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditarFragment extends Fragment {

    private EditText editTextIdProduto, editTextStatusEncomenda;
    private int id_encomenda, id_loja;
    private String guid, data_envio, data_alteracao;

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
        View view = inflater.inflate(R.layout.fragment_editar_encomenda, container, false);

        // Linkando variáveis com os campos da tela
        editTextIdProduto = view.findViewById(R.id.editTextIdProdutoEdicao);
        editTextStatusEncomenda = view.findViewById(R.id.editTextStatusEncomendaEdicao);

        // Recebe id do produto via bundle
        Bundle b = getArguments();
        id_encomenda = b.getInt("id");

        buscarEncomenda();

        //Linkando botões
        Button btnEditar = view.findViewById(R.id.buttonEditarrEncomendaFinal);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar();
            }
        });

        Button btnDeletar = view.findViewById(R.id.buttonDeletarEncomendaFinal);
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

    // Busca encomenda por id no db
    private void buscarEncomenda() {
        DatabaseHelper repositorio = new DatabaseHelper(getActivity());
        Encomenda encomenda = repositorio.buscarEncomendaPorId(id_encomenda);
        id_loja = encomenda.getId_loja();
        editTextIdProduto.setText(Integer.toString(encomenda.getId_produto()));
        editTextStatusEncomenda.setText(encomenda.getStatus());
        guid = encomenda.getGuid();
        data_envio = encomenda.getData_envio();
    }


    // Método que verifica se os campos foram preenchidos
    // Realiza update no db e trata mensagens e redirect
    private void editar() {
        if(editTextIdProduto.getText().toString().equals("") ||
                editTextStatusEncomenda.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "É preciso preencher todos os campos do formulário", Toast.LENGTH_SHORT).show();
        } else {
            DatabaseHelper repositorio = new DatabaseHelper(getActivity());
            Encomenda encomenda = new Encomenda(
                    id_encomenda,
                    id_loja,
                    Integer.parseInt(editTextIdProduto.getText().toString()),
                    guid,
                    editTextStatusEncomenda.getText().toString(),
                    data_envio,
                    formatarData(new Date())
            );

            repositorio.atualizarEncomenda(encomenda);

            Toast.makeText(getActivity(), "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new ListagemFragment()).commit();
        }
    }

    // Realiza delete no db e trata mensagens e redirect
    private void deletar() {
        DatabaseHelper repositorio = new DatabaseHelper(getActivity());
        repositorio.deletaEncomenda(id_encomenda);

        Toast.makeText(getActivity(), "Encomenda deletada com sucesso!", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new ListagemFragment()).commit();
    }

    // Formata data para padrão brasileiro
    private String formatarData(Date data) {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        return formatador.format(data);
    }

    // Chama Fragment para voltar para o menu
    private void voltar() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new ListagemFragment()).commit();
    }
}