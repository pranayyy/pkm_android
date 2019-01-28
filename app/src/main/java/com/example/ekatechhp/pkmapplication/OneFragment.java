package com.example.ekatechhp.pkmapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressLint("ValidFragment")
public class OneFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    EditText editTextNickName;
    EditText editTextOrganization;
    EditText editTextMobile;
    EditText editTextDesignation;
    Spinner spinnerJob;
    TextView textViewShare;
    ListView listViewShare;
    Button buttonCancel;
    Button buttonOk;
    TextView textViewChangePassword;
    View view;
    SqliteHelper sqliteHelper;

    public OneFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_one, container, false);
        editTextNickName = (EditText) view.findViewById(R.id.editTextNickName);
        editTextOrganization = (EditText) view.findViewById(R.id.editTextOrganization);
        editTextMobile = (EditText) view.findViewById(R.id.editTextMobile);
        editTextDesignation = (EditText) view.findViewById(R.id.editTextDesignation);

        spinnerJob = (Spinner) view.findViewById(R.id.spinnerJob);
        spinnerJob.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinnerJob, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJob.setAdapter(adapter);

        listViewShare = (ListView) view.findViewById(R.id.listViewShare);

        buttonCancel = (Button) view.findViewById(R.id.buttonCancel);
        buttonOk = (Button) view.findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String NickName = editTextNickName.getText().toString();
                    String Organization = editTextOrganization.getText().toString();
                    String Mobile = editTextMobile.getText().toString();
                    String Designation = editTextDesignation.getText().toString();
                    String JobLevel = spinnerJob.getSelectedItem().toString();
                    String ShareWith = textViewShare.getText().toString();
                    //ListElementsArrayList.add(editTextEmail.getText().toString());
                    sqliteHelper.addSetupUser(new SetupUser(null, NickName, Organization, Mobile, Designation, JobLevel, ShareWith));

                }
            }


        });
        return view;
    }
    private boolean validate() {
        boolean valid = false;
        String NickName = editTextNickName.getText().toString();
        if (NickName.isEmpty()) {
            valid = false;
            editTextNickName.setError("Please enter valid username!");
        } else {
            if (NickName.length() > 5) {
                valid = true;
                editTextNickName.setError(null);
            } else {
                valid = false;
                editTextNickName.setError("Username is too short!");
            }
        }
        return valid;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected " + item, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
