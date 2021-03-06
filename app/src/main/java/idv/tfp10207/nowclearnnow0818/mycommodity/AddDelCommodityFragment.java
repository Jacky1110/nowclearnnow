package idv.tfp10207.nowclearnnow0818.mycommodity;

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

import org.jetbrains.annotations.NotNull;

import idv.tfp10207.nowclearnnow0818.R;


public class AddDelCommodityFragment extends Fragment {
    private Button bt_add_commodity07, bt_my_commobity07;
    private ImageView iv_commodity_return,ms_home09;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_del_commodity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handlebutton();
        handleimage(view);
    }

    private void findViews(View view) {
        bt_add_commodity07 = view.findViewById(R.id.bt_add_commodity07);
        bt_my_commobity07 = view.findViewById(R.id.bt_my_commobity07);
        iv_commodity_return = view.findViewById(R.id.iv_commodity_return);
        ms_home09 = view.findViewById(R.id.ms_home09);
    }
    private void handleimage(View view) {
        iv_commodity_return.setOnClickListener(v -> {
//            Navigation.findNavController(v)
//                    .navigate(R.id.chang_to_homeuserFragment);
            Navigation.findNavController(view).popBackStack(R.id.addDelCommodityFragment,true);
        });

        ms_home09.setOnClickListener(v -> {

            Navigation.findNavController(v)
                   .navigate(R.id.homePageFragment072);
        });
    }

    private void handlebutton() {
        bt_add_commodity07.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.produceFragment);
        });
        bt_my_commobity07.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.myProduceFragment);
        });


    }
}

