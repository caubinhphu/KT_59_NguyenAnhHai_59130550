package vn.edu.ntu.nguyenanhhai.kt_59_nguyenanhhai_59130550;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Calendar;

import vn.edu.ntu.nguyenanhhai.controllers.INTController;

public class InputFragment extends Fragment implements View.OnClickListener {
  NavController navController;
  EditText edtDate, edtMua, edtBan;
  ImageView imvDate;
  Button btnAdd, btnSee;
  RadioGroup rdgNgoaiTe;
  INTController ntController;
  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);

    navController = NavHostFragment.findNavController(this);
    ((MainActivity)getActivity()).navController = navController;

    ntController = (INTController) ((MainActivity)getActivity()).getApplication();
  }

  @Override
  public void onStart() {
    super.onStart();
    ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_input, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    edtDate = view.findViewById(R.id.edtDate);
    edtMua = view.findViewById(R.id.edtMua);
    edtBan = view.findViewById(R.id.edtBan);
    imvDate = view.findViewById(R.id.imvDate);
    btnAdd = view.findViewById(R.id.btnAdd);
    btnSee = view.findViewById(R.id.btnSee);
    rdgNgoaiTe = view.findViewById(R.id.rdgNgoaiTe);

    btnAdd.setOnClickListener(this);
    btnSee.setOnClickListener(this);
    imvDate.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    int id = v.getId();
    switch (id) {
      case R.id.imvDate: chonNgay(); break;
      case R.id.btnAdd: add(); break;
      case R.id.btnSee: see(); break;
    }
  }

  private void chonNgay() {
    final Calendar calendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
      @Override
      public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dayOfMonth)
                .append("/")
                .append(month + 1)
                .append("/")
                .append(year);
        edtDate.setText(stringBuilder.toString());
      }
    };

    DatePickerDialog datePickerDialog = new DatePickerDialog(
            getActivity(),
            listener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
    );

    datePickerDialog.show();
  }

  private void add() {
    String date = edtDate.getText().toString();
    String mua = edtMua.getText().toString();
    String ban = edtBan.getText().toString();
    String ngoaiTe = "";
    switch (rdgNgoaiTe.getCheckedRadioButtonId()) {
      case R.id.rdbEUR: ngoaiTe =  "EUR"; break;
      case R.id.rdbUSD: ngoaiTe =  "USD"; break;
      case R.id.rdbCNY: ngoaiTe =  "CNY"; break;
    }
    if (date.length() > 0 && mua.length() > 0 && ban.length() > 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(edtDate.getText().toString())
              .append("\n")
              .append(ngoaiTe)
              .append("\n")
              .append("Mua vào: ")
              .append(edtMua.getText().toString())
              .append("  Bán ra: ")
              .append(edtBan.getText().toString());
      ntController.addNT(stringBuilder.toString());
      Toast.makeText(getActivity(), "Thêm ngoại tệ thành công", Toast.LENGTH_SHORT).show();
    } else {
      Toast.makeText(getActivity(), "Chưa nhập đủ thông tin đăng ký", Toast.LENGTH_SHORT).show();
    }
  }

  private void see() {
    navController.navigate(R.id.action_inputFragment_to_outputFragment);
  }
}
