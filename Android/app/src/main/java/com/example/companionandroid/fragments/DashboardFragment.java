package com.example.companionandroid.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentTransaction;
import androidx.gridlayout.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


import com.example.companionandroid.R;

public class DashboardFragment extends Fragment {
    private GridLayout gridLayout;
    private CardView milesToKm;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_fragment,container,false);

        gridLayout = view.findViewById(R.id.converter_grid);
        setClickEvent(gridLayout);
        return view;
    }

    private void setClickEvent(GridLayout gridLayout){
        for (int i = 0; i< gridLayout.getChildCount(); i++){
            /*
            * Recall in grid layout, items are mapped to index
            * For example, the item at column 0 and row 0 is at index 0, row 0 column 1 at index 1
            * etc
            * Since all of our items in grid layout are cardview, we use cardview to grab them
             */
            final CardView cardView = (CardView) gridLayout.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*
                    * https://stackoverflow.com/questions/45543306/how-to-get-text-from-textview-inside-a-cardview
                    * My assumption is that since the imageView and textview are inside linearlayout which is a view group, we
                    * need to do this weird stuff, where the first item in the linear layout at index 0 and second item at index 1 etc
                    * since text view is the second item, we use index 1 to retrieve it
                     */
//                    ViewGroup viewGroup = (ViewGroup) cardView.getChildAt(0);
//                    String text = ((TextView) viewGroup.getChildAt(1)).getText().toString();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    /*
                    * https://www.youtube.com/watch?v=ONR94DlQFcA&t=9s
                    * Watch from 9 minutes mark
                     */
                    Fragment thisFragment = new TempConversion();
                    if (cardView.getId() == R.id.miles_km){
                        thisFragment = new MilesToKm();
                    }
                    else if(cardView.getId() == R.id.kg_lbs){
                        thisFragment = new KgToLbs();
                    }
                    else if(cardView.getId() == R.id.usd_cad_card){
                        thisFragment = new UsdToCad();
                    }
                    fragmentTransaction.replace(R.id.fragment_layout,thisFragment);
                    fragmentTransaction.addToBackStack(null).commit();

                }
            });

        }

    }
}

