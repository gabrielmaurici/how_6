package com.example.rastreio_how6.produto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.rastreio_how6.R;
import com.example.rastreio_how6.database.DatabaseHelper;
import com.example.rastreio_how6.database.RepositorioIdLoja;
import com.example.rastreio_how6.loja.MenuFragment;

public class ListarFragment extends Fragment {

    public ListarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listar_produtos, container, false);

        ListView listView = view.findViewById(R.id.listViewProdutos);

        DatabaseHelper repositorio = new DatabaseHelper(getActivity());
        repositorio.buscarTodosProdutos(getActivity(), listView, RepositorioIdLoja.idLoja);

        Button btnVoltar = view.findViewById(R.id.buttonVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });

        return view;
    }

    private void voltar() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new MenuFragment()).commit();
    }
}