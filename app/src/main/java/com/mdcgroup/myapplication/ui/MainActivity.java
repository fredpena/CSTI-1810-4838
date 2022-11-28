package com.mdcgroup.myapplication.ui;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.mdcgroup.myapplication.R;

import android.view.Menu;
import android.view.MenuItem;
import com.mdcgroup.myapplication.database.Category;
import com.mdcgroup.myapplication.database.Product;
import com.mdcgroup.myapplication.databinding.ActivityMainBinding;
import com.mdcgroup.myapplication.models.WordViewModel;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


        WordViewModel mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);

//        mWordViewModel.insertCategory(new Category("Tecnologia"));
//        mWordViewModel.insertCategory(new Category("Ropa"));
//
////        mWordViewModel.deleteAll(unused -> {
////        });
//        mWordViewModel.insert(new Product("T-Shirt, Style G2000, Multipack", "Gildan", 107.87, 1));
//        mWordViewModel.insert(new Product("New Freedom Flag T-Shirt", "Under Armour", 43.87, 2));
//        mWordViewModel.insert(new Product("Dri-Power Long Sleeve T-Shirt", "Jerzees", 9.99, 1));
//        mWordViewModel.insert(new Product("Crew Neck Soft Fitted Tees S - 4XL Fresh Classic Tshirts", "INTO THE AM", 21.95, 2));
//        mWordViewModel.insert(new Product("Casual Slim Fit Basic Tops Knitted Thermal Turtleneck Pullover Sweater", "Poriff", 20.99, 1));
//        mWordViewModel.insert(new Product("Classic Cotton Tee, Crewneck Tee, Men's Mid-Weight T-Shirt, Script Logo", "Champion", 10.00, 2));


//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}