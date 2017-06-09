package ajil.com.expensemanager;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import ajil.com.expensemanager.fragments.OverviewFragment;

public class MainActivity extends AppCompatActivity {

    FrameLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (FrameLayout) findViewById(R.id.container);

        OverviewFragment overviewFragment = new OverviewFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, overviewFragment).commit();
    }
}
