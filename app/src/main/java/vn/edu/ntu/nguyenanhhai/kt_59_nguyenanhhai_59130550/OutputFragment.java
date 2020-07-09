package vn.edu.ntu.nguyenanhhai.kt_59_nguyenanhhai_59130550;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import vn.edu.ntu.nguyenanhhai.controllers.INTController;

public class OutputFragment extends Fragment {
  ListView lvNT;
  INTController ntController;

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    ntController = (INTController) ((MainActivity)getActivity()).getApplication();
  }

  @Override
  public void onStart() {
    super.onStart();
    ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_output, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    lvNT = view.findViewById(R.id.lvNT);
    String[] dsNT = ntController.getAllNT().toArray(new String[0]);
    lvNT.setAdapter(new ArrayAdapter<>(((MainActivity)getActivity()), android.R.layout.simple_list_item_1, dsNT));
  }
}
