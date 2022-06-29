package com.example.rastreio_how6.loja;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.rastreio_how6.R;

public class MenuFragment extends Fragment {

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_loja, container, false);



        Button btnEditarDados = view.findViewById(R.id.buttonEditarLoja);
        btnEditarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarDados();
            }
        });

        return view;
    }

    private void editarDados() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new EditarFragment()).commit();
    }
}