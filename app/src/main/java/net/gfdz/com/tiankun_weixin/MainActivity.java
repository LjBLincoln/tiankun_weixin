package net.gfdz.com.tiankun_weixin;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jauker.widget.BadgeView;

import net.gfdz.com.tiankun_weixin.fragment.ChatMainTabFragment;
import net.gfdz.com.tiankun_weixin.fragment.ContactMainTabFragment;
import net.gfdz.com.tiankun_weixin.fragment.FriendMainTabFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mDatas;
    private TextView mChatTextView, mFindTextView, mContactTextView;
    private BadgeView mBadgeView;
    private LinearLayout mChatLinearLayout, mFindLinearLayout, mContactLinearLayout;
    private int mScreen1_3;
    private ImageView mTabline;
    private int mCurrentPageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabline= (ImageView) findViewById(R.id.id_iv_tabline);
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrice = new DisplayMetrics();
        display.getMetrics(outMetrice);
        mScreen1_3 = outMetrice.widthPixels / 3;
        mTabline.getLayoutParams().width=mScreen1_3;
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewPager);
        mDatas = new ArrayList<>();

        mChatTextView = (TextView) findViewById(R.id.id_tv_chat);
        mFindTextView = (TextView) findViewById(R.id.id_tv_find);
        mContactTextView = (TextView) findViewById(R.id.id_tv_contact);

        mChatLinearLayout = (LinearLayout) findViewById(R.id.id_ll_chat);
        mFindLinearLayout = (LinearLayout) findViewById(R.id.id_ll_find);
        mContactLinearLayout = (LinearLayout) findViewById(R.id.id_ll_content);

        ChatMainTabFragment TAB01 = new ChatMainTabFragment();
        FriendMainTabFragment TAB02 = new FriendMainTabFragment();
        ContactMainTabFragment TAB03 = new ContactMainTabFragment();

        mDatas.add(TAB01);
        mDatas.add(TAB02);
        mDatas.add(TAB03);
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mDatas.get(position);
            }

            @Override
            public int getCount() {
                return mDatas.size();
            }
        };
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
             LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) mTabline.getLayoutParams();
                if (mCurrentPageIndex==0&&position==0){//0->1
                    layoutParams.leftMargin = (int) (positionOffset*mScreen1_3+mCurrentPageIndex*mScreen1_3);
                }else if(mCurrentPageIndex==1&&position==0){//1->0
                    layoutParams.leftMargin = (int)(mCurrentPageIndex*mScreen1_3+(positionOffset-1)*mScreen1_3);
                }else if(mCurrentPageIndex==1&&position==1){//1->2
                    layoutParams.leftMargin = (int) (positionOffset*mScreen1_3+mCurrentPageIndex*mScreen1_3);
                }else if(mCurrentPageIndex==2&&position==1){//2->1
                    layoutParams.leftMargin = (int)(mCurrentPageIndex*mScreen1_3+(positionOffset-1)*mScreen1_3);
                 }
                mTabline.setLayoutParams(layoutParams);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        if (mBadgeView != null) {
                            mChatLinearLayout.removeView(mBadgeView);
                        }
                        mBadgeView = new BadgeView(MainActivity.this);
                        mBadgeView.setBadgeCount(7);
                        mChatLinearLayout.addView(mBadgeView);
                        mChatTextView.setTextColor(Color.parseColor("#008000"));
                        break;
                    case 1:
                        mFindTextView.setTextColor(Color.parseColor("#008000"));
                        break;
                    case 2:
                        mContactTextView.setTextColor(Color.parseColor("#008000"));
                        break;
                }
                mCurrentPageIndex=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void resetTextView() {
        mChatTextView.setTextColor(Color.BLACK);
        mFindTextView.setTextColor(Color.BLACK);
        mContactTextView.setTextColor(Color.BLACK);
    }
}
