package demos.sun.com.myapplication.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Copyright (c) 2016. 东华博育云有限公司 Inc. All rights reserved.
 * demos.sun.com.myapplication.adapter
 * 作者：Created by sfq on 2016/11/8 0008.
 * 联系方式：sunfengqi@dhcc.com.cn
 * 功能描述：
 * 修改：无
 */

public class SunPagerAdapter extends PagerAdapter {
    private static final String TAG = "SunPagerAdapter";
    private List<View> pagerList;

    public SunPagerAdapter(List<View> pageList) {
        this.pagerList = pageList;
    }

    @Override
    public int getCount() {
        return pagerList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object; // key
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(pagerList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        System.out.println("pos:" + position);
        View view = pagerList.get(position);
        container.addView(view);
        return pagerList.get(position); // 返回该view对象，作为key
    }
}
