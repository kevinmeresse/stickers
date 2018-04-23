package com.km.stickers.ui.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.km.stickers.model.entity.Sticker;
import com.km.stickers.model.repository.StickerRepository;

import java.util.Set;

public class MainViewModel extends ViewModel {

    private StickerRepository repository;
    private MutableLiveData<Sticker[]> stickers;

    public void init(Context context) {
        this.repository = StickerRepository.getInstance(context);
        this.stickers = new MutableLiveData<>();
    }

    public LiveData<Sticker[]> getStickers() {
        return stickers;
    }

    public void search(String input) {
        Set<Sticker> stickerSet = repository.search(input);
        if (stickerSet == null) {
            stickers.setValue(null);
        } else {
            stickers.setValue(stickerSet.toArray(new Sticker[stickerSet.size()]));
        }
    }
}
