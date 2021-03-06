package idv.tfp10207.nowclearnnow0818.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import idv.tfp10207.nowclearnnow0818.R;


public class PlacardFragment07 extends Fragment {
    private ImageButton ib_placard_return;
    private TextView tv_disinfect_step07;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_placard07, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        handleReturn();
        handleDisinfectStep();
    }

    private void findViews(View view) {
        ib_placard_return = view.findViewById(R.id.ib_placard_return);
        tv_disinfect_step07 = view.findViewById(R.id.tv_disinfect_step07);
    }

    private void handleReturn() {
        ib_placard_return.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.homePageFragment072);
        });
    }

    private void handleDisinfectStep() {
        tv_disinfect_step07.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.disinfectStepFragment07);
        });
    }
}