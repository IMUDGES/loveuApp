package com.example.loveuApp.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.loveuApp.R;
import org.w3c.dom.Text;

/**
 * Created by caolu on 2016/9/19.
 */
public class ShowClassFragment extends Fragment{

    private TextView knumber1,knumber2,state1,state2;
    private Button refeshButton;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.showclass_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        knumber1 = (TextView) getActivity().findViewById(R.id.showclass_knumber1);
        knumber2 = (TextView) getActivity().findViewById(R.id.showclass_knumber2);
        state1 = (TextView) getActivity().findViewById(R.id.showclass_state1);
        state2 = (TextView) getActivity().findViewById(R.id.showclass_state2);

        refeshButton = (Button) getActivity().findViewById(R.id.showclass_refreshstate);
        refeshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 刷新状态逻辑
                 */
            }
        });
    }
}
