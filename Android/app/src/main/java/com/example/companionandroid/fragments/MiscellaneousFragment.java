package com.example.companionandroid.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.gridlayout.widget.GridLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.companionandroid.R;

import java.util.ArrayList;
import java.util.List;

public class MiscellaneousFragment extends Fragment {
    private GridLayout gridLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_miscellaneous,container,false);
        this.gridLayout = view.findViewById(R.id.miscellaneous_grid);
        this.setClickEvent(gridLayout);
        return view;
    }



    private void setClickEvent(GridLayout gridLayout){
        int loopLength = gridLayout.getChildCount();
        for(int i = 0; i<loopLength; i++){
            /*
             * Recall in grid layout, items are mapped to index
             * For example, the item at column 0 and row 0 is at index 0, row 0 column 1 at index 1
             * etc
             * Since all of our items in grid layout are cardview, we use cardview to grab them
             */
            final CardView cardView = (CardView) gridLayout.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                /*
                 * https://stackoverflow.com/questions/45543306/how-to-get-text-from-textview-inside-a-cardview
                 * My assumption is that since the imageView and textview are inside linearlayout which is a view group, we
                 * need to do this weird stuff, where the first item in the linear layout at index 0 and second item at index 1 etc
                 * since text view is the second item, we use index 1 to retrieve it
                 */
//                    ViewGroup viewGroup = (ViewGroup) cardView.getChildAt(0);
//                    String text = ((TextView) viewGroup.getChildAt(1)).getText().toString();
                @Override
                public void onClick(View view) {
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    /*
                     * https://www.youtube.com/watch?v=ONR94DlQFcA&t=9s
                     * Watch from 9 minutes mark
                     */
                    Fragment fragment = new RiceToWater();
                    if (cardView.getId() == R.id.pc_temp_monitor){
                        fragment = new pcTempMonitor();
                    }
                    fragmentTransaction.replace(R.id.fragment_layout,fragment);
                    fragmentTransaction.commit();
                }
            });
        }
    }
}