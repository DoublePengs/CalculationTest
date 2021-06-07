package com.example.calculationtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.SavedStateVMFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    NavController controller;
    MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //myViewModel = new ViewModelProvider(this,new SavedStateVMFactory(getApplication(),this)).get(MyViewModel.class);
        // myViewModel = ViewModelProviders.of(this,new SavedStateViewModelFactory(r(),this)).get(MyViewModel.class);
        controller = Navigation.findNavController(this,R.id.fragment);
        NavigationUI.setupActionBarWithNavController(this,controller);
        //

//        final Intent intent  = new Intent(MainActivity.this,MyService.class);
//        ImageButton imageButton = findViewById(R.id.play);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//           public void onClick(View v) {
//                if(MyService.isPlay == false){//若音乐没有播放，则启动服务，修改图标
//                    startService(intent);//启动服务
//                    ((ImageButton)v).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_volume_up_black_24dp));
//                }else{//否则停止服务，修改图标
//                    stopService(intent);
//                    ((ImageButton)v).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_volume_off_black_24dp));
//                }
//            }
//        });
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_title, container, false);*/
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (controller.getCurrentDestination().getId() == R.id.questionFragment) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.quit_dialog_title));
            builder.setPositiveButton(R.string.dialog_positive_message, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    myViewModel.getCurrentScore().setValue(0);
                    controller.navigateUp();
                }
            });
            builder.setNegativeButton(R.string.dialog_negative_message, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (controller.getCurrentDestination().getId() == R.id.titleFragment) {
            finish();
        } else {
            controller.navigate(R.id.titleFragment);
        }

        return super.onSupportNavigateUp();
    }

    //
    @Override
    protected void onStart() {
        super.onStart();
        startService(new Intent(MainActivity.this, MyService.class));
    }

    @Override
    public void onBackPressed() {
        onSupportNavigateUp();
    }
}
