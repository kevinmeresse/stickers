package com.km.stickers.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.km.stickers.R;
import com.km.stickers.ui.adapter.StickerAdapter;
import com.km.stickers.ui.viewmodel.MainViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    // Layout views
    @BindView(R.id.recyclerview) RecyclerView recyclerView;
    @BindView(R.id.text_field) EditText textField;
    @BindView(R.id.empty_view) ImageView emptyView;

    // ViewModel
    private MainViewModel viewModel;

    // Adapter
    private StickerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind layout views and initialize things
        ButterKnife.bind(this);
        configureRecyclerView();
        configureViewModel();

        // Listen to text edition
        textField.addTextChangedListener(new MyTextWatcher(textField));
    }

    private void configureViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.init(this);
        viewModel.getStickers().observe(this, stickers -> {
            // Update adapter
            if (adapter != null) {
                adapter.setStickers(stickers);
            }

            // Show/hide empty view
            emptyView.setVisibility((stickers != null && stickers.length > 0) ? View.GONE : View.VISIBLE);
        });
    }

    private void configureRecyclerView() {
        // The layout size of RecyclerView will never change
        recyclerView.setHasFixedSize(true);

        // Set a grid layout manager
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        // Create new adapter
        adapter = new StickerAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_glide:
                if (adapter != null) adapter.setUseGlide(true);
                return true;
            case R.id.action_fresco:
                if (adapter != null) adapter.setUseGlide(false);
                return true;
            case R.id.action_clear_cache:
                if (viewModel != null) viewModel.clearCaches(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * When this object is attached to an Editable, its methods will
     * be called when the text is changed
     */
    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.text_field:
                    if (viewModel != null) viewModel.search(editable.toString());
                    break;
            }
        }
    }
}
