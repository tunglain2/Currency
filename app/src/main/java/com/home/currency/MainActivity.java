package com.home.currency;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText ntd;
    private TextView us;
    private TextView jp;
    private static final double US_Rate = 30.9;
    private static final double JP_Rate = 0.2723;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }

    private void findViews() {
        ntd = findViewById(R.id.ntd);
        us = findViewById(R.id.us);
        jp = findViewById(R.id.jp);
    }

    public void currency(View view){
        String ntdValue = ntd.getText().toString();
        if(ntdValue.isEmpty()){
            new AlertDialog.Builder(this)
                    .setTitle(R.string.problem)
                    .setMessage(R.string.please_enter_ntd)
                    .setPositiveButton(R.string.ok,null)
                    .show();
        }else{
            float nt = Float.parseFloat(ntdValue);
            double jp_Amount = nt/ JP_Rate;
            double us_Amount = nt / US_Rate;
            DecimalFormat jp_df = new DecimalFormat("#,###.####");
            DecimalFormat us_df = new DecimalFormat("#,###.####");
            String jp_Amt = jp_df.format(jp_Amount);
            String us_Amt = us_df.format(us_Amount);
            jp.setText("" + jp_Amt);
            us.setText("" + us_Amt);
            new AlertDialog.Builder(this)
                    .setTitle(R.string.result)
                    .setMessage(getString(R.string.jp_is) + jp_Amt + "\n" + getString(R.string.usd_is) + us_Amt  )
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ntd.setText("");
                            jp.setText("");
                            us.setText("");
                        }
                    })
                    .show();

        }


    }
}
