package com.example.gogreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HelpAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private List<HelpItem> originalList; // Stores the full list
    private List<HelpItem> filteredList; // Stores the filtered results
    private LayoutInflater inflater;

    public HelpAdapter(Context context, List<HelpItem> helpList) {
        this.context = context;
        this.originalList = new ArrayList<>(helpList); // Copy original list
        this.filteredList = new ArrayList<>(helpList); // Copy for filtering
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return filteredList.size(); // Return filtered list size
    }

    @Override
    public Object getItem(int position) {
        return filteredList.get(position); // Return filtered item
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.help_list_item, parent, false);
        }

        ImageView helpIcon = convertView.findViewById(R.id.helpIcon);
        TextView helpTitle = convertView.findViewById(R.id.helpTitle);
        TextView helpAnswers = convertView.findViewById(R.id.helpAnswers);

        HelpItem item = filteredList.get(position);
        helpIcon.setImageResource(item.getIcon());
        helpTitle.setText(item.getTitle());
        helpAnswers.setText(item.getDescription());

        return convertView;
    }

    // ✅ Implement Search Filtering
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<HelpItem> filteredResults = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredResults.addAll(originalList); // Show all if search is empty
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (HelpItem item : originalList) {
                        if (item.getTitle().toLowerCase().contains(filterPattern)) {
                            filteredResults.add(item); // Match items
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredResults;
                results.count = filteredResults.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList.clear();
                filteredList.addAll((List<HelpItem>) results.values);
                notifyDataSetChanged();
            }
        };
    }
}
