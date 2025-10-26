package com.example.gogreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BagFragment extends Fragment {

    private static final String ARG_NAME = "name";
    private static final String ARG_DESC = "desc";
    private static final String ARG_PRICE = "price";

    public static BagFragment newInstance(String name, String desc, String price) {
        BagFragment fragment = new BagFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putString(ARG_DESC, desc);
        args.putString(ARG_PRICE, price);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bag, container, false);

        TextView tvName = view.findViewById(R.id.tvBagName);
        TextView tvDesc = view.findViewById(R.id.tvBagDesc);
        TextView tvPrice = view.findViewById(R.id.tvBagPrice);
        TextView tvQuantity = view.findViewById(R.id.tvQuantity);
        Button btnMinus = view.findViewById(R.id.btnMinus);
        Button btnPlus = view.findViewById(R.id.btnPlus);

        if (getArguments() != null) {
            tvName.setText(getArguments().getString(ARG_NAME));
            tvDesc.setText(getArguments().getString(ARG_DESC));
            tvPrice.setText(getArguments().getString(ARG_PRICE));
        }

        // Quantity logic
        btnMinus.setOnClickListener(v -> {
            int qty = Integer.parseInt(tvQuantity.getText().toString());
            if (qty > 1) tvQuantity.setText(String.valueOf(qty - 1));
        });

        btnPlus.setOnClickListener(v -> {
            int qty = Integer.parseInt(tvQuantity.getText().toString());
            tvQuantity.setText(String.valueOf(qty + 1));
        });

        return view;
    }
}
