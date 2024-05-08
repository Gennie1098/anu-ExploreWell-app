package com.anu.gp24s1.ui.following;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anu.gp24s1.pojo.vo.PostVo;

import java.util.List;

public class FollowingViewModel extends ViewModel {

    private MutableLiveData<List<PostVo>> postVoListData;

    public FollowingViewModel() {
        postVoListData = new MutableLiveData<>();
    }

    public LiveData<List<PostVo>> getPostVoListData() {
        return postVoListData;
    }

    public void setPostVoListData(List<PostVo> postVoListData) {
        this.postVoListData.setValue(postVoListData);
    }
}