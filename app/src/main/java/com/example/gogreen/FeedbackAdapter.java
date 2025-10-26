package com.example.gogreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder> {

    private List<Feedback> feedbackList;

    public FeedbackAdapter(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    @NonNull
    @Override
    public FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_item, parent, false);
        return new FeedbackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackViewHolder holder, int position) {
        Feedback feedback = feedbackList.get(position);
        holder.tvFeedbackTitle.setText(feedback.getTitle());
        holder.tvFeedbackMessage.setText(feedback.getMessage());
        holder.tvUserName.setText(feedback.getUserName());
        holder.ivUserImage.setImageResource(feedback.getUserImage()); // Set user image
    }

    @Override
    public int getItemCount() {
        return feedbackList.size();
    }

    static class FeedbackViewHolder extends RecyclerView.ViewHolder {
        TextView tvFeedbackTitle, tvFeedbackMessage, tvUserName;
        ImageView ivUserImage;

        public FeedbackViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFeedbackTitle = itemView.findViewById(R.id.tvFeedbackTitle);
            tvFeedbackMessage = itemView.findViewById(R.id.tvFeedbackMessage);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            ivUserImage = itemView.findViewById(R.id.ivUserImage);
        }
    }
}
