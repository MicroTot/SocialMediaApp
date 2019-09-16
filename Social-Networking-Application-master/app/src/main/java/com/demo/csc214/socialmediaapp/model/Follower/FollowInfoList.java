package com.demo.csc214.socialmediaapp.model.Follower;


import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sailesh on 4/17/18.
 */

public class FollowInfoList {

    List<FollowInfo> followInfoList;

    public FollowInfoList() {
        followInfoList = new LinkedList<>();
    }

    public void addFollowInfo(FollowInfo followInfo) {
        followInfoList.add(followInfo);
    }

    public void addFollowInfo(int user_id, int id_followed) {
        FollowInfo followInfo = new FollowInfo(user_id, id_followed);
        followInfoList.add(followInfo);
    }

    public void deleteFollowInfo(FollowInfo followInfo) {
        for (FollowInfo f : followInfoList) {
            if (f.equals(followInfo)) {
                followInfoList.remove(f);
            }
        }
    }

    public void deletePost(int user_id, int id_followed) {
        FollowInfo followInfo = new FollowInfo(user_id, id_followed);
        for (FollowInfo f : followInfoList) {
            if (f.equals(followInfo)) {
                followInfoList.remove(f);
            }
        }
    }
}
