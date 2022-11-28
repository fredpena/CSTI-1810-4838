package com.mdcgroup.myapplication.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.mdcgroup.myapplication.database.CategoryAndProduct;
import com.mdcgroup.myapplication.database.Product;
import com.mdcgroup.myapplication.databinding.FragmentSecondBinding;
import com.mdcgroup.myapplication.models.WordViewModel;
import com.mdcgroup.myapplication.utils.CommonUtil;
import com.mdcgroup.myapplication.utils.FirebaseNetwork;
import com.mdcgroup.myapplication.utils.NetResponse;
import com.mdcgroup.myapplication.utils.PhotoOptions;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.function.Consumer;
import java.util.function.Function;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private Product product;
    private Uri uri;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        product = (Product) bundle.getSerializable("WORD");

        WordViewModel mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);

        if (product == null) {
            binding.btnDelete.setVisibility(View.GONE);
        } else {


            //Log.e("product: ", String.valueOf(product.category()));
            mWordViewModel.find(product.category(), categoryAndProduct -> {
                Log.e("Category: ", String.valueOf(categoryAndProduct.category().uid()));
                Log.e("Products: ", "");
                categoryAndProduct.products().forEach(System.out::println);
            });


//            binding.layout.name.setText(product.getName());
//            binding.layout.brand.setText(product.getBrand());
//            binding.layout.price.setText(String.valueOf(product.getPrice()));
//
//            FirebaseNetwork.obtain().download(String.format("images/%s.jpg", product.getUid()), new NetResponse<Bitmap>() {
//                @Override
//                public void onResponse(Bitmap response) {
//                    binding.layout.image.setImageBitmap(response);
//                }
//
//                @Override
//                public void onFailure(Throwable t) {
//                    Toast.makeText(getContext(), "Error download image", Toast.LENGTH_LONG).show();
//                }
//            });
        }


//        binding.layout.btnCart.setVisibility(View.GONE);
//        binding.layout.btnEdit.setVisibility(View.GONE);
//        binding.btnSave.setOnClickListener(view1 -> {
//            if (TextUtils.isEmpty(binding.layout.name.getText())
//                    || TextUtils.isEmpty(binding.layout.brand.getText())
//                    || TextUtils.isEmpty(binding.layout.price.getText())) {
//                Toast.makeText(getContext(), "Hay valores nulos", Toast.LENGTH_LONG).show();
//            } else {
//
//                String name = binding.layout.name.getText().toString();
//                String brand = binding.layout.brand.getText().toString();
//                String price = binding.layout.price.getText().toString();
//
//                if (product == null) {
//                    mWordViewModel.insert(new Product(name, brand, Double.valueOf(price), 1));
//                } else {
//                    product.setBrand(brand).setName(name).setPrice(Double.valueOf(price));
//                    mWordViewModel.update(product);
//                }
//
//                if(product != null && product.getUid() != 0 && uri != null){
//                  FirebaseNetwork.obtain().upload(uri, String.format("images/%s.jpg", product.getUid()), new NetResponse<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            Toast.makeText(getContext(), "Successfully upload image", Toast.LENGTH_LONG).show();
//                        }
//
//                        @Override
//                        public void onFailure(Throwable t) {
//                            Toast.makeText(getContext(), "Error upload image", Toast.LENGTH_LONG).show();
//                        }
//                    });
//
//                }
//
//
//                Toast.makeText(getContext(), "Guardado!!!", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        binding.btnDelete.setOnClickListener(view12 -> new AlertDialog.Builder(getContext()).setTitle("INFO")
//                .setMessage("Desea elimnar?")
//                .setPositiveButton("Yes", (dialog, which) -> {
//                    mWordViewModel.delete(product);
//                    clear();
//                })
//                .setNegativeButton("No", (dialog, which) -> dialog.cancel()).show());
//
//        binding.btnClear.setOnClickListener(v -> clear());
//
//        binding.layout.image.setOnClickListener(v -> photoOptions());

    }



    private void clear() {
        binding.layout.name.setText(null);
        binding.layout.brand.setText(null);
        binding.layout.price.setText(null);
        binding.layout.image.setImageBitmap(null);
        binding.btnDelete.setVisibility(View.GONE);
        product = null;

    }

    private void photoOptions() {
        final Function<PhotoOptions, Consumer<Intent>> function = photoOptions -> intent -> {
            switch (photoOptions) {
                case CHOOSE_FOLDER:
                case CHOOSE_GALLERY:
                    pickAndChoosePictureResultLauncher.launch(intent);
                    break;
                case TAKE_PHOTO:
                    takePictureResultLauncher.launch(intent);
                    break;

            }
        };
        CommonUtil.photoOptions(getContext(), function);
    }

    private ActivityResultLauncher<Intent> pickAndChoosePictureResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        try {
                            uri = result.getData().getData();
                            InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                            binding.layout.image.setImageBitmap(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

    private ActivityResultLauncher<Intent> takePictureResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                        binding.layout.image.setImageBitmap(bitmap);
                        uri = CommonUtil.getImageUri(getContext(), bitmap);
                    }
                }
            });


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}