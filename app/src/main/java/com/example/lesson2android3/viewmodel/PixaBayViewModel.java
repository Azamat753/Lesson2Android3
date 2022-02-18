package com.example.lesson2android3.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lesson2android3.model.Hit;
import com.example.lesson2android3.repository.PixaBayRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PixaBayViewModel extends ViewModel {

    public MutableLiveData<List<Hit>> hitMutableLiveData = new MutableLiveData<>();
    PixaBayRepository repository;

    @Inject
    public PixaBayViewModel(PixaBayRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<List<Hit>> getImages(String word) {
        hitMutableLiveData = repository.getImages(word);
        return hitMutableLiveData;
    }
}
