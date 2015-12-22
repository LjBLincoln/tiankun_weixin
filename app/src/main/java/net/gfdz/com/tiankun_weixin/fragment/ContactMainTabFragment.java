package net.gfdz.com.tiankun_weixin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.gfdz.com.tiankun_weixin.R;

/**
 * Created by Administrator on 2015/12/17.
 */
public class ContactMainTabFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab03,container,false);
    }
}
