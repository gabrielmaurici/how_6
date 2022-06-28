package com.example.rastreio_how6.viewRastreio;

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
import com.example.rastreio_how6.login.LoginFragment;

public class RastrearEncomenda extends Fragment {

    EditText editTextCodigoEncomenda;
    TextView textValorStatus, textValorDataEnvio, textValorDataAlteracao;
    TextView textValorNomeProduto, textValorValorProduto, textValorDescricao;

    public RastrearEncomenda() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rastrear_encomenda, container, false);

        editTextCodigoEncomenda = view.findViewById(R.id.editTextCodigoEncomenda);

        Button btnRastrearEncomenda = view.findViewById(R.id.buttonRastrearEncomenda);
        btnRastrearEncomenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rastrear();
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

    private void rastrear() {
        if(editTextCodigoEncomenda.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, preencha o campo do código!", Toast.LENGTH_SHORT).show();
        } else {
            // Verificar se código existe e buscar dados caso exista
        }
    }

    private void voltar() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new LoginFragment()).commit();;
    }
}