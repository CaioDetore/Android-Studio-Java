package com.example.atmconsultoria.ui.clientes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.atmconsultoria.databinding.FragmentClientesBinding;

public class ClientesFragment extends Fragment {
    private ClientesViewModel clientesViewModel;
    private FragmentClientesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        clientesViewModel =
                new ViewModelProvider(this).get(ClientesViewModel.class);

        binding = FragmentClientesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}