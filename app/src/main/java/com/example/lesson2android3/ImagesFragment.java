package com.example.lesson2android3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.lesson2android3.databinding.FragmentImagesBinding;
import com.example.lesson2android3.model.Hit;
import com.example.lesson2android3.model.ImageResponse;
import com.example.lesson2android3.viewmodel.PixaBayViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class ImagesFragment extends BaseFragment<FragmentImagesBinding> {
    ImageAdapter adapter;
    PixaBayViewModel viewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(PixaBayViewModel.class);
        initListener();
        initAdapter();
    }

    private void initListener() {
        binding.imageEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.progressBar.setVisibility(View.VISIBLE);
                viewModel.getImages(binding.imageEd.getText().toString()).observe(getViewLifecycleOwner(), hits -> {
                    if (hits != null) {
                        binding.progressBar.setVisibility(View.GONE);
                        binding.recycler.setAdapter(adapter);
                        adapter.setData((ArrayList<Hit>) hits);
                    }
                });
            }
        });
    }

    private void initAdapter() {
        adapter = new ImageAdapter();
    }


    @Override
    FragmentImagesBinding bind() {
        return FragmentImagesBinding.inflate(getLayoutInflater());
    }
}