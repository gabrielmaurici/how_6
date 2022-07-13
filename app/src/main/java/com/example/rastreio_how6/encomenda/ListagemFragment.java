package com.example.rastreio_how6.encomenda;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rastreio_how6.R;
import com.example.rastreio_how6.database.DatabaseHelper;
import com.example.rastreio_how6.database.RepositorioIdLoja;
import com.example.rastreio_how6.loja.MenuFragment;

public class ListagemFragment extends Fragment {

    public ListagemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listagem_encomenda, container, false);

        // Linka listview da tela com variável
        ListView listView = view.findViewById(R.id.listViewEncomendas);

        // Realiza consulta da listagem de encomendas por id da loja
        DatabaseHelper repositorio = new DatabaseHelper(getActivity());
        repositorio.buscarTodasEncomendas(getActivity(), listView, RepositorioIdLoja.idLoja);

        // Cria onClick na listagem das encomendas
        // Passa id da encomenda via bundle para o fragment da tela de edição
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvId = view.findViewById(R.id.textViewValorIdEncomenda);
                Bundle b = new Bundle();
                b.putInt("id", Integer.parseInt(tvId.getText().toString()));

                EditarFragment editar = new EditarFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                editar.setArguments(b);
                ft.replace(R.id.login_main, editar).commit();
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

    // Chama Fragment para voltar para o menu
    private void voltar() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new MenuFragment()).commit();
    }
}