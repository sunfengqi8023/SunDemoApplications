package demos.sun.com.myapplication;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import demos.sun.com.myapplication.adapter.SunPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ViewPager vp_myViewPager;
    private View pager1;
    private View pager2;
    private View pager3;
    private View pager4;
    private List<View> mlist = new ArrayList<View>();

    private SunPagerAdapter sunPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

        sunPagerAdapter = new SunPagerAdapter(mlist);
        vp_myViewPager.setAdapter(sunPagerAdapter);
    }

    public void initView(){
        vp_myViewPager = (ViewPager)findViewById(R.id.vp_myViewPager);
        LayoutInflater inflater = getLayoutInflater();
        pager1 = inflater.inflate(R.layout.pager_1,null);
        pager2 = inflater.inflate(R.layout.pager_2,null);
        pager3 = inflater.inflate(R.layout.pager_3,null);
        pager4 = inflater.inflate(R.layout.pager_4,null);

    }

    public void initData(){
        mlist.add(pager1);
        mlist.add(pager2);
        mlist.add(pager3);
        mlist.add(pager4);
    }
}
