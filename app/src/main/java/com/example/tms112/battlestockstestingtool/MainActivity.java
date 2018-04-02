package com.example.tms112.battlestockstestingtool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.provider.ContactsContract.Intents.Insert.ACTION;

public class MainActivity extends AppCompatActivity {

    private String ACTION;
    private final String DESTINATION_PACKAGE = "com.example.der62.battlestocks";
    private Button trigger1, trigger2, trigger3, trigger4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trigger1 = (Button)findViewById(R.id.trigger1);
        trigger1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newCompanyAdded();
            }
        });

        trigger2 = (Button)findViewById(R.id.trigger2);
        trigger2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stockPriceChange();
            }
        });

        trigger3 = (Button)findViewById(R.id.trigger3);
        trigger3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyGoesOffMarket();
            }
        });

        trigger4 = (Button)findViewById(R.id.trigger4);
        trigger4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marketCrash();
            }
        });

//        launchOtherApp();
    }

    public void launchOtherApp(){
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage(DESTINATION_PACKAGE);
        if (launchIntent != null) {
            startActivity(launchIntent);//null pointer check in case package name was not found
        }
    }

    public void newCompanyAdded(){
//        Toast.makeText(MainActivity.this, "Trigger 1", Toast.LENGTH_SHORT).show();
        ACTION = "edu.pitt.cs1699.team9.NEW_STOCK";
        Intent intent = getPackageManager().getLaunchIntentForPackage(DESTINATION_PACKAGE);
        intent.setAction(ACTION);
        intent.putExtra("company", "TestCompany");
        intent.putExtra("price", "3.50");
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
//        Toast.makeText(MainActivity.this, intent.getAction()+"", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void stockPriceChange(){
        Toast.makeText(MainActivity.this, "Trigger 2", Toast.LENGTH_SHORT).show();
    }

    public void companyGoesOffMarket(){
        Toast.makeText(MainActivity.this, "Trigger 3", Toast.LENGTH_SHORT).show();
    }

    public void marketCrash(){
        Toast.makeText(MainActivity.this, "Trigger 4", Toast.LENGTH_SHORT).show();
    }

}
