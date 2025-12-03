package com.example.android_native_features_showcase.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_native_features_showcase.R;
import com.example.android_native_features_showcase.data.database.RecordingEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RecordingAdapter extends RecyclerView.Adapter<RecordingAdapter.RecordingViewHolder> {

    private List<RecordingEntity> recordings = new ArrayList<>();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    public void setRecordings(List<RecordingEntity> recordings) {
        this.recordings = recordings;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecordingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recording, parent, false);
        return new RecordingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordingViewHolder holder, int position) {
        RecordingEntity recording = recordings.get(position);
        holder.fileNameTextView.setText(recording.getFileName());

        // Format the date/time
        Date date = new Date(recording.getTimestamp());
        String formattedDate = dateFormat.format(date);
        
        // Show upload status
        String status = recording.isUploaded() ? " (Uploaded)" : " (Pending)";
        holder.dateTimeTextView.setText(formattedDate + status);
    }

    @Override
    public int getItemCount() {
        return recordings == null ? 0 : recordings.size();
    }

    static class RecordingViewHolder extends RecyclerView.ViewHolder {
        TextView fileNameTextView;
        TextView dateTimeTextView;

        public RecordingViewHolder(@NonNull View itemView) {
            super(itemView);
            fileNameTextView = itemView.findViewById(R.id.fileNameTextView);
            dateTimeTextView = itemView.findViewById(R.id.dateTimeTextView);
        }
    }
}
