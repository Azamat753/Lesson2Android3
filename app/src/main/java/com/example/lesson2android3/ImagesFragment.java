package com.example.lesson2android3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.lesson2android3.databinding.FragmentImagesBinding;
import com.example.lesson2android3.model.Hit;
import com.example.lesson2android3.model.ImageResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ImagesFragment extends BaseFragment<FragmentImagesBinding> {

    ImageAdapter adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
        initAdapter();
    }

    private void initAdapter() {
        adapter = new ImageAdapter();
    }

    private void getRemoteData(String word) {
        App.api.getImageBySearch("25007027-7418deb977c638792f4bfb99f", word).enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if (response.isSuccessful()) {
                    adapter.setData((ArrayList<Hit>) response.body().getHits());
                    binding.recycler.setAdapter(adapter);
                    binding.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
            public void afterTextChanged(Editable it) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        binding.progressBar.setVisibility(View.VISIBLE);
                        getRemoteData(it.toString());
                    }
                }, 2000);
            }
        });
    }

    @Override
    FragmentImagesBinding bind() {
        return FragmentImagesBinding.inflate(getLayoutInflater());
    }
}