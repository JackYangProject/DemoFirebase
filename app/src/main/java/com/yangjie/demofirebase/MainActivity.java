package com.yangjie.demofirebase;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText email,password,name_edit;
    private Button login,registered;
    private ProgressDialog progressBar;
    private DatabaseReference registeredref;
    private List<Member> members = new ArrayList<>();
    private Listadpter listadpter;
    private Member member = new Member();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = new ProgressDialog(this);
        name_edit = findViewById(R.id.name_edit);
        email = findViewById(R.id.email_edit);
        password = findViewById(R.id.email_password);

        login = findViewById(R.id.login);
        registered = findViewById(R.id.registered);
        login.setOnClickListener(this);
        registered.setOnClickListener(this);

        listadpter = new Listadpter();
        RecyclerView recyclerView = findViewById(R.id.listview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext()
                ,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listadpter);

        registeredref = FirebaseDatabase.getInstance().getReference("會員");

        registeredref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                members.clear();
                for (DataSnapshot data: dataSnapshot.getChildren()){
                   members.add(data.getValue(Member.class));
                }
                listadpter.setMembers(members);
                listadpter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Query https://www.youtube.com/watch?v=WeoryL3XyA4
    }

    @Override
    public void onClick(View view) {

        if (view == login){
            loginUser();
        }else if (view == registered){
            registerUser();
        }
    }
    private void loginUser() {

    }

    private void registerUser() {
        String emailvalue = email.getText().toString().trim();
        String passwrodvalue = password.getText().toString().trim();

        if (TextUtils.isEmpty(emailvalue)){
            Toast.makeText(this,"帳號不能為空 !",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(passwrodvalue)) {
            Toast.makeText(this, "密碼不能為空 !", Toast.LENGTH_SHORT).show();
            return;
        }
        member.setName(name_edit.getText().toString().trim());
        member.setEmail(emailvalue);
        member.setPassword(passwrodvalue);
//        progressBar.setMessage("註冊中請稍後...");
//        progressBar.show();
        registeredref.push().setValue(member);
    }
}
