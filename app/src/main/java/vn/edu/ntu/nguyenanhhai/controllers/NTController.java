package vn.edu.ntu.nguyenanhhai.controllers;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class NTController extends Application implements INTController {
  List<String> ngoaiTes = new ArrayList<>();
  String[] x;
  @Override
  public void addNT(String nt) {
    ngoaiTes.add(nt);
  }

  @Override
  public List<String> getAllNT() {
    return ngoaiTes;
  }
}
