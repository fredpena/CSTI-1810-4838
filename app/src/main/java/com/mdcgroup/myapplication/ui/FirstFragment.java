package com.mdcgroup.myapplication.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mdcgroup.myapplication.R;
import com.mdcgroup.myapplication.databinding.FragmentFirstBinding;
import com.mdcgroup.myapplication.models.WordViewModel;
import com.mdcgroup.myapplication.repositories.WordRepository;

import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WordAdapter adapter = new WordAdapter();

        WordViewModel mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);

        RecyclerView recyclerView = binding.mainRecyclervie;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);


        mWordViewModel.findAll().observe(getViewLifecycleOwner(), words -> {
            adapter.setList(words);
        });


        binding.send.setOnClickListener(view1 -> NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment));


        //       SharedPreferences shared = getContext().getSharedPreferences("pucmm", Context.MODE_PRIVATE);

//        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = binding.name.getText().toString();
//                String lastName = binding.lastName.getText().toString();
//                String id = binding.id.getText().toString();
//
//                SharedPreferences.Editor editor = shared.edit();
//                editor.putString("name", name);
//                editor.putString("lastName", lastName);
//                editor.putString("id", id);
//                editor.apply();
//            }
//        });
//
//        binding.btnLoad.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = shared.getString("name", "");
//                String lastName = shared.getString("lastName", "");
//                String id = shared.getString("id", "");
//
//                binding.name.setText(name);
//                binding.lastName.setText(lastName);
//                binding.id.setText(id);
//            }
//        });
//
//        binding.btnClear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                binding.name.setText("");
//                binding.lastName.setText("");
//                binding.id.setText("");
//            }
//        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}