package ru.arlen.androidadapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerColorsAdapter extends RecyclerView.Adapter<RecyclerColorsAdapter.Holder> {
    private static final int LIMIT = 256;
    private static final int STEP = 16;
    private List<String> colorsList = new ArrayList<>(256);

    public RecyclerColorsAdapter() {
        initColors();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView colorName;
        View preview;

        public Holder(@NonNull View itemView) {
            super(itemView);
            colorName = itemView.findViewById(R.id.color_name);
            preview = itemView.findViewById(R.id.preview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setBackgroundColor(Color.DKGRAY);
                }
            });
        }

        public void bind(String color) {
            colorName.setText(color);
            preview.setBackgroundColor(Color.parseColor(color));
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return new Holder(inflater.inflate(R.layout.item_color, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(colorsList.get(position));
    }

    @Override
    public int getItemCount() {
        return colorsList.size();
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
