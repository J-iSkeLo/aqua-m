package l.chernenkiy.aqua.Delivery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import l.chernenkiy.aqua.R;

public class Wholesale extends Fragment {
    private static final String TAG = "Оптовым покупателям";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.delivery_wholesale, container, false);

        return view;
    }
}
