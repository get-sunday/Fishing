package com.jude.fishing.module.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.data.BeamDataFragment;
import com.jude.fishing.R;
import com.jude.fishing.model.ImageModel;
import com.jude.fishing.model.entities.Account;
import com.jude.fishing.module.blog.UserBlogActivity;
import com.jude.fishing.module.place.CollectionPlaceActivity;
import com.jude.fishing.module.place.UserEvaluateActivity;
import com.jude.fishing.module.place.UserPlaceActivity;
import com.jude.utils.JUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr.Jude on 2015/9/11.
 */
@RequiresPresenter(UserPresenter.class)
public class UserFragment extends BeamDataFragment<UserPresenter, Account> {


    @InjectView(R.id.avatar)
    SimpleDraweeView avatar;
    @InjectView(R.id.name)
    TextView name;
    @InjectView(R.id.sign)
    TextView sign;
    @InjectView(R.id.head_arrows)
    ImageView headArrows;
    @InjectView(R.id.container_user)
    RelativeLayout containerUser;
    @InjectView(R.id.divider)
    View divider;
    @InjectView(R.id.blog)
    TextView blog;
    @InjectView(R.id.container_blog)
    LinearLayout containerBlog;
    @InjectView(R.id.attention)
    TextView attention;
    @InjectView(R.id.container_attention)
    LinearLayout containerAttention;
    @InjectView(R.id.fans)
    TextView fans;
    @InjectView(R.id.container_fans)
    LinearLayout containerFans;
    @InjectView(R.id.notify)
    LinearLayout notify;
    @InjectView(R.id.collect)
    LinearLayout collect;
    @InjectView(R.id.evaluate)
    LinearLayout evaluate;
    @InjectView(R.id.place)
    LinearLayout place;

    @InjectView(R.id.notification_count)
    TextView notificationCount;

    int uid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.user_fragment_main2, container, false);
        ButterKnife.inject(this, root);
        containerUser.setOnClickListener(v -> startActivity(new Intent(getActivity(), UserDetailActivity.class)));
        containerBlog.setOnClickListener(v -> goToActivity(UserBlogActivity.class));
        containerFans.setOnClickListener(v -> goToActivity(FansActivity.class));
        containerAttention.setOnClickListener(v -> goToActivity(AttentionActivity.class));
        collect.setOnClickListener(v -> startActivity(new Intent(getActivity(), CollectionPlaceActivity.class)));
        evaluate.setOnClickListener(v -> startActivity(new Intent(getActivity(), UserEvaluateActivity.class)));
        place.setOnClickListener(v -> startActivity(new Intent(getActivity(), UserPlaceActivity.class)));
        notify.setOnClickListener(v -> startActivity(new Intent(getActivity(), NotificationActivity.class)));
        return root;
    }

    public void setNotificationCount(int i) {
        JUtils.Log("UI NotificationCount:"+i);
        notificationCount.setVisibility(i == 0 ? View.GONE : View.VISIBLE);
        notificationCount.setText(i + "");
    }

    private void goToActivity(Class clz) {
        Intent intent = new Intent(getActivity(), clz);
        intent.putExtra("id", uid);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void setData(Account data) {
        uid = data.getUID();
        avatar.setImageURI(ImageModel.getInstance().getSmallImage(data.getAvatar()));
        name.setText(data.getName());
        sign.setText(data.getSign());
        blog.setText(data.getBlogCount() + "");
        attention.setText(data.getCared());
        fans.setText(data.getFans());
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null)
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
    }
}
