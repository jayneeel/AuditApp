package com.example.auditappfinal.onboarding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.auditappfinal.R;

import java.util.List;

public class OnBoardAdapter extends PagerAdapter {

    Context context;
    List<OnBoardItems> onBoardItems;

    public OnBoardAdapter(Context context, List<OnBoardItems> onBoardItems) {
        this.context = context;
        this.onBoardItems = onBoardItems;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.layout_screen,null);

        ImageView onBoardImg = layoutScreen.findViewById(R.id.onBoardImg);
        TextView header = layoutScreen.findViewById(R.id.onBoardHeader);
        TextView desc = layoutScreen.findViewById(R.id.onBoardText);

        header.setText(onBoardItems.get(position).getHeader());
        desc.setText(onBoardItems.get(position).getDescription());
        onBoardImg.setImageResource(onBoardItems.get(position).getImage());

        container.addView(layoutScreen);

        return layoutScreen;
    }

    @Override
    public int getCount() {
        return onBoardItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
