package com.example.loveuApp.right;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.userModel;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.model.FriendModel;
import com.example.loveuApp.right.Adapter.FriendAdapter;
import com.example.loveuApp.service.Service;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.RequestParams;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caolu on 2016/8/11.
 */
public class BaiGuoQingFragment extends Fragment{

    private FriendAdapter adapter;
    private PullToRefreshListView listView;
    private FriendModel model;
    private List<userModel> userModels;

    private Goback goback;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rightfragment2, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        userModels=new ArrayList<>();
        listView= (PullToRefreshListView) getView().findViewById(R.id.friend_listView);
        goback=new Goback() {
            @Override
            public void back() {
                if(userModels.size()!=0){
                    listView.onRefreshComplete();
                    adapter=new FriendAdapter(getActivity(),userModels);
                    listView.setAdapter(adapter);
                }
            }
        };
        getData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("friend",i+"");
                RongIM.getInstance().startConversation(getActivity(),
                        Conversation.ConversationType.PRIVATE, userModels.get(i).getUserId()+"", userModels.get(i).getNickName());
            }
        });

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if(!isNetworkConnected(getActivity())){
                    Toast.makeText(getActivity(),"网络错误",Toast.LENGTH_SHORT).show();
                    listView.onRefreshComplete();
                }
                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

        });
    }

    public void getData() {

        SharedPreferences sf=getActivity().getSharedPreferences("user",getActivity().MODE_PRIVATE);
        RequestParams params=new RequestParams();
        Service service=new Service();
        String url="getfriends";
        params.put("UserPhone",sf.getString("UserPhone",""));
        params.put("SecretKey",sf.getString("SecretKey",""));

        service.post(getActivity(), url, params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                Log.i("userFriend",new String((byte[]) object));
                model=new Gson().fromJson(new String((byte[]) object),FriendModel.class);
                if(model.getState()==0)
                    return;
                userModels=model.getData();
                goback.back();
            }

            @Override
            public void onFailure(String msg) {
                Log.i("userFriend","*****************************");
            }
        });


    }

    private interface Goback{
        public void back();
    }

    /**
     * 联网判断
     * @param context
     * @return
     */
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
