package ru.arlen.androidadapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ColorsAdapter extends BaseAdapter {
    private static final int LIMIT = 256;
    private static final int STEP = 16;
    private List<String> colorsList = new ArrayList<>(256);

    public ColorsAdapter() {
        initColors();
    }

    @Override
    public int getCount() {
        return colorsList.size();
    }

    @Override
    public String getItem(int position) {
        return colorsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.item_color, parent, false);
            View preview = view.findViewById(R.id.preview);
            TextView colorName = view.findViewById(R.id.color_name);
            view.setTag(new Holder(colorName, preview));
        }

        Holder holder = (Holder) view.getTag();
        holder.colorName.setText(getItem(position));
        holder.preview.setBackgroundColor(Color.parseColor(getItem(position)));
        return view;
    }

    static class Holder {
        TextView colorName;
        View preview;

        public Holder(TextView colorName, View preview) {
            this.colorName = colorName;
            this.preview = preview;
        }
    }

    private void initColors() {
        for (int red = 0; red < LIMIT; red += STEP) {
            for (int green = 0; green < LIMIT; green += STEP) {
                for (int blue = 0; blue < LIMIT; blue += STEP) {
                    String color = "#" + component(red) + component(green) + component(blue);
                    colorsList.add(color);
                }
            }
        }
    }

    private String component(int value) {
        String result = Integer.toHexString(value);
        return result.length() == 2 ? result : "0" + result;
    }
}
