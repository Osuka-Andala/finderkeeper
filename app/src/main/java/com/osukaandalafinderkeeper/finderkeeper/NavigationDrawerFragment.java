package com.osukaandalafinderkeeper.finderkeeper;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    public static final String PREF_FILE_NAME = "testpref";

    public static final String KEY_USER_LEARNED_DRAWER="user_learned_drawer";

    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerLayout mDrawerLayout;

    private boolean mUserLearnedDrawer;

    private boolean mFromSavedInstanceState;

    private View containerView;

    private ListView listView;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer=Boolean.valueOf(readFromPreferences(getActivity(),KEY_USER_LEARNED_DRAWER,"false"));

        if (savedInstanceState!=null){
            mFromSavedInstanceState=true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int[] images = {R.drawable.profilegreen,R.drawable.sridesgreen,R.drawable.newsroomgreen,R.drawable.howistrfcgreen,R.drawable.helpgreen,R.drawable.settingsgreen,R.drawable.logoutgreen };
        View v = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        listView = (ListView) v.findViewById(R.id.navdrawer);
        listView.setAdapter(new NewRecyclerViewAdapter(getActivity(), 0, getActivity().getResources().getStringArray(R.array.navDrawerItems), images));
        return v;
    }




    public void setUp(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {
        containerView=getActivity().findViewById(fragmentId);

        mDrawerLayout = drawerLayout;

        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
                }

                getActivity().invalidateOptionsMenu();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };

        if (!mUserLearnedDrawer && !mFromSavedInstanceState){
            mDrawerLayout.openDrawer(containerView);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static  String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }

}
