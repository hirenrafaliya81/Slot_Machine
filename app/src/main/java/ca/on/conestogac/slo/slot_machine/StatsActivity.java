package ca.on.conestogac.slo.slot_machine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StatsActivity extends AppCompatActivity {
    public SQLiteOpenHelper helper;
    private TextView totalCash;
    private  TextView currentCash;
    private TextView reset;
    SharedPref pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SlotMachine slotMachine = (SlotMachine) getApplication();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats2);
        getSupportActionBar().setTitle("Statistics");

        pref = new SharedPref(this);

        //write to statistics
        totalCash = findViewById(R.id.totalMoney);
        currentCash = findViewById(R.id.currentCash);
        reset = findViewById(R.id.btn_reset);
        totalCash.setText("  " + pref.getInt("cash_won"));
        int cash = GameActivity.getCurrent_status();
        currentCash.setText( String.valueOf(cash));


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pref.setInt("cash_won",0);
                currentCash.setText("0");
                totalCash.setText("0");
                GameActivity.cash_won = 0;

                Toast.makeText(StatsActivity.this, "Reset", Toast.LENGTH_SHORT).show();
                System.out.println("Database Reset");
            }
        });
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean ret = true;
        switch(item.getItemId())
        {
            case android.R.id.home:
                //back button
                super.onBackPressed();
                break;
            default:
                ret = super.onOptionsItemSelected(item);
                break;
        }
        return ret;
    }
}