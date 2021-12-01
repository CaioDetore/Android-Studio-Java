package com.example.atmconsultoria.ui.principal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.atmconsultoria.databinding.FragmentPrincipalBinding;

public class PrincipalFragment extends Fragment {

    private PrincipalViewModel principalViewModel;
    private FragmentPrincipalBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        principalViewModel =
                new ViewModelProvider(this).get(PrincipalViewModel.class);

        binding = FragmentPrincipalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}