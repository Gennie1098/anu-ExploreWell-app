package com.anu.gp24s1;

import android.view.View;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    private ProgressBar mProgressBar;

    public void setProgressBar(int resId) {
        mProgressBar = getView().findViewById(resId);
    }

    public void showProgressBar() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgressBar() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }
}
