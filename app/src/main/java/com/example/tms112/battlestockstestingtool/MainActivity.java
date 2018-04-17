package com.example.tms112.battlestockstestingtool;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.provider.ContactsContract.Intents.Insert.ACTION;

public class MainActivity extends AppCompatActivity {

    private String ACTION;
    private final String DESTINATION_PACKAGE = "com.example.der62.battlestocks";
    private Button trigger1, trigger2, trigger3, trigger4;
    private EditText company, price, priceChange, crash;

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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setCancelable(true);
        final View box = inflater.inflate(R.layout.dialog_add_company, null);
        builder.setView(box)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        ACTION = "edu.pitt.cs1699.team9.NEW_STOCK";
                        Intent intent = getPackageManager().getLaunchIntentForPackage(DESTINATION_PACKAGE);
                        intent.setAction(ACTION);

                        company = box.findViewById(R.id.company);
                        price = box.findViewById(R.id.price);

                        intent.putExtra("company", company.getText().toString());
                        intent.putExtra("price", price.getText().toString());
                        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

                        startActivity(intent);
                    }
                });
        builder.create();
        builder.show();
    }

    public void stockPriceChange(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setCancelable(true);
        final View box = inflater.inflate(R.layout.dialog_change_stock, null);
        builder.setView(box)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        ACTION = "edu.pitt.cs1699.team9.PRICE_CHANGE";
                        Intent intent = getPackageManager().getLaunchIntentForPackage(DESTINATION_PACKAGE);
                        intent.setAction(ACTION);

                        company = box.findViewById(R.id.company);
                        price = box.findViewById(R.id.price);

                        intent.putExtra("company", company.getText().toString());
                        intent.putExtra("price", price.getText().toString());
                        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

                        startActivity(intent);
                    }
                });
        builder.create();
        builder.show();
    }

    public void companyGoesOffMarket(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setCancelable(true);
        final View box = inflater.inflate(R.layout.dialog_off_market, null);
        builder.setView(box)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        ACTION = "edu.pitt.cs1699.team9.OFF_MARKET";
                        Intent intent = new Intent("edu.pitt.cs1699.team9.OFF_MARKET");
                        intent.setPackage("com.example.der62.battlestocks");

                        company = box.findViewById(R.id.company);
                        intent.putExtra("company", company.getText().toString());
                        startForegroundService(intent);
                    }
                });
        builder.create();
        builder.show();
    }

    public void marketCrash(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setCancelable(true);
        final View box = inflater.inflate(R.layout.dialog_market_crash, null);
        builder.setView(box)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        ACTION = "edu.pitt.cs1699.team9.CRASH";
                        Intent intent = new Intent("edu.pitt.cs1699.team9.CRASH");
                        intent.setAction(ACTION);

                        price = box.findViewById(R.id.price);

                        intent.putExtra("pct_decrease", price.getText().toString());
                        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

                        sendBroadcast(intent);
                    }
                });
        builder.create();
        builder.show();
    }

/*
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
//        Toast.makeText(MainActivity.this, "Trigger 2", Toast.LENGTH_SHORT).show();
        ACTION = "edu.pitt.cs1699.team9.PRICE_CHANGE";
        Intent intent = getPackageManager().getLaunchIntentForPackage(DESTINATION_PACKAGE);
        intent.setAction(ACTION);
        intent.putExtra("company", "TestCompany");
        intent.putExtra("price_change", "-3");
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
//        Toast.makeText(MainActivity.this, intent.getAction()+"", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void companyGoesOffMarket(){
//        Toast.makeText(MainActivity.this, "Trigger 3", Toast.LENGTH_SHORT).show();
        ACTION = "edu.pitt.cs1699.team9.OFF_MARKET";
        Intent intent = getPackageManager().getLaunchIntentForPackage(DESTINATION_PACKAGE);
        intent.setAction(ACTION);
        intent.putExtra("company", "TestCompany");
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
//        Toast.makeText(MainActivity.this, intent.getAction()+"", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void marketCrash(){
//        Toast.makeText(MainActivity.this, "Trigger 4", Toast.LENGTH_SHORT).show();
        ACTION = "edu.pitt.cs1699.team9.CRASH";
        Intent intent = getPackageManager().getLaunchIntentForPackage(DESTINATION_PACKAGE);
        intent.setAction(ACTION);
        intent.putExtra("pct_decrease", "50");
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
//        Toast.makeText(MainActivity.this, intent.getAction()+"", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
*/
}
