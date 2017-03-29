package com.example.projetoempsoft.activities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projetoempsoft.R;
import com.example.projetoempsoft.helper.DatabaseHelper;
import com.example.projetoempsoft.helper.ItemDatabaseHelper;
import com.example.projetoempsoft.models.Item;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FoodDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FoodDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodDetailsFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    public FoodDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoodDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodDetailsFragment newInstance(String param1, String param2) {
        FoodDetailsFragment fragment = new FoodDetailsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // TODO porque aqui o item é constante?
        final Item item = ItemDatabaseHelper.getPorId(db, 0);

        View myView = inflater.inflate(R.layout.fragment_food_details, container, false);
        TextView itemTitle = (TextView) myView.findViewById(R.id.itemTitle);
        TextView itemDesc = (TextView) myView.findViewById(R.id.itemDesc);
        final TextView itemPrice = (TextView) myView.findViewById(R.id.itemValue);

        final EditText itemQtd = (EditText) myView.findViewById(R.id.itemQtd);
        itemQtd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!itemQtd.getText().toString().equals("")){
                    itemPrice.setText("R$ " + item.getPreco() * Integer.parseInt(itemQtd.getText().toString()) );
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        itemDesc.setText(item.getDescricao());
        itemTitle.setText(item.getTitulo());
        itemPrice.setText("R$ " + item.getPreco().toString());

        return myView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
