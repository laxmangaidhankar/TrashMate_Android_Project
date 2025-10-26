package com.example.gogreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PickupAdapter extends RecyclerView.Adapter<PickupAdapter.PickupViewHolder> {

    private List<Pickup> pickupList;

    public PickupAdapter(List<Pickup> pickupList) {
        this.pickupList = pickupList;
    }

    @NonNull
    @Override
    public PickupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pickup_card, parent, false);
        return new PickupViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PickupViewHolder holder, int position) {
        Pickup pickup = pickupList.get(position);

        holder.tvAddress.setText(pickup.getPickupAddress());
        holder.tvDateTime.setText(pickup.getDate() + " at " + pickup.getTime());
        holder.tvWasteType.setText("Waste: " + pickup.getWasteType());
        holder.tvQty.setText("General: " + pickup.getGeneralQty() + " | Organic: " + pickup.getOrganicQty());
        holder.tvAmount.setText("Amount: ₹" + pickup.getTotalAmount());
        holder.tvStatus.setText("Status: " + pickup.getScheduleStatus() + " (" + pickup.getPaymentStatus() + ")");
    }

    @Override
    public int getItemCount() {
        return pickupList.size();
    }

    public static class PickupViewHolder extends RecyclerView.ViewHolder {
        TextView tvAddress, tvDateTime, tvWasteType, tvQty, tvAmount, tvStatus;

        public PickupViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvDateTime = itemView.findViewById(R.id.tvDateTime);
            tvWasteType = itemView.findViewById(R.id.tvWasteType);
            tvQty = itemView.findViewById(R.id.tvQty);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}
