package com.anu.gp24s1.ui.following;

public class FollowingModel {
    String groupName;
    int groupIcon;

    public FollowingModel(String groupName, int groupIcon) {
        this.groupName = groupName;
        this.groupIcon = groupIcon;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getGroupIcon() {
        return groupIcon;
    }
}
