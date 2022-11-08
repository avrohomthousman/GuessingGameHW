package com.example.guessing_game;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.guessing_game.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private GuessingGame mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupBinding();

        setSupportActionBar(binding.toolbar.toolbar);

        setupFab();

        setupModel();

        setupButton(binding.contentMain.buttonsSection.button1, 1);
        setupButton(binding.contentMain.buttonsSection.button2, 2);
        setupButton(binding.contentMain.buttonsSection.button3, 3);
    }


    private void setupBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void setupFab(){
        binding.fab.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mModel.startNewGame();
            }
        });
    }

    private void setupModel(){
        mModel = new GuessingGame(3);
        mModel.startNewGame();
    }

    private void setupButton(Button btn, int btnNumber){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitGuess(btnNumber, v);
            }
        });
    }

    private void submitGuess(int guess, View v){
        String output;

        if(mModel.guessNumber(guess)){
            output = String.format("%d is correct! Well done!", guess);
        }
        else{
            output = String.format("%d is incorrect. Try again.", guess);
        }

        Snackbar.make(v, output, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_new_game){
            mModel.startNewGame();
            return true;
        }
        else if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}