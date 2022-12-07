package ca.on.conestogac.slo.slot_machine;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {

    SharedPref pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Settings");
        setContentView(R.layout.settings_activity);

        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pref = new SharedPref(this);

        SwitchCompat switchGame = findViewById(R.id.switchSaveGame);
        SwitchCompat switchNight = findViewById(R.id.switchDarkTheme);

        switchGame.setChecked(pref.getBoolean("save_game"));

        switchGame.setOnCheckedChangeListener((compoundButton, b) -> pref.setBoolean("save_game", b));
        switchNight.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
    }
}