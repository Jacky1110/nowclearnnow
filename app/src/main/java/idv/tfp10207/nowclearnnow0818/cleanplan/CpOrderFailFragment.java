package idv.tfp10207.nowclearnnow0818.cleanplan;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import idv.tfp10207.nowclearnnow0818.R;

public class CpOrderFailFragment extends Fragment {
    private static final String TAG = "TAG_CpOrderFailFragment";
    private Activity activity;

    private Button cp_orderfailback;

    //next button
    private Button bt_cp03_next;
    //Toolbar
    private ImageView righthomeicon;
    private ImageView leftarrowicon;
    private TextView tvprojectname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_cp_order_fail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findview(view);
        handleback(view);
        handletoolbar(view);
    }

    private void handleback(View view) {
        cp_orderfailback.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.f_CleanplanFragment);

        });
    }


    private void findview(View view) {
        cp_orderfailback = view.findViewById(R.id.cp_orderfailback);
    }

    //    客製Toolbar
    private void handletoolbar(View view) {
        //    抓按鍵
        leftarrowicon = view.findViewById(R.id.iv_arrow_back_11);
        righthomeicon = view.findViewById(R.id.iv_home_11);
        //    改標題
        tvprojectname = view.findViewById(R.id.tv_project_name_11);


        //    按鍵(需更改導覽路線的ID)

        leftarrowicon.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack(R.id.cpOrderFailFragment, true);


        });


        righthomeicon.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack(R.id.homePageFragment072, false);
        });

        //    標題
        tvprojectname.setText("清潔計畫");

    }


}