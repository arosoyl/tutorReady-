package com.example.tutorready;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tutorready.home.FeeFragment;
import com.example.tutorready.student.DangkyHocFragment;
import com.example.tutorready.student.DanhGiaFragment;
import com.example.tutorready.student.HomeFragment;
import com.example.tutorready.student.LichHocFragment;
import com.example.tutorready.student.StudentInfoFragment;
import com.example.tutorready.tutor.DangKyDayFragment;
import com.example.tutorready.tutor.DoiMatKhauFragment;
import com.example.tutorready.tutor.Home1Fragment;
import com.example.tutorready.tutor.LichDayFragment;
import com.example.tutorready.tutor.StudentFragment;
import com.example.tutorready.tutor.TutorInfoFragment;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity6 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // tutor
    private DrawerLayout drawerLayout;

    String id,token,role,email,fullname,birthday,address,phone,user,picture,CV;
    Boolean gender;

    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        Toolbar toolbar = findViewById(R.id.toolbar_tutor);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout_tutor);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view_tutor);
        navigationView.setNavigationItemSelectedListener(this);

        getData();

        textView = navigationView.getHeaderView(0).findViewById(R.id.tv_username);
        imageView = navigationView.getHeaderView(0).findViewById(R.id.imageViewAvatar);
        textView.setText(user);
        Glide.with(this).load(picture).error(R.drawable.avatardefaulticon).into(imageView);

        replaceFragment(new Home1Fragment());
        navigationView.getMenu().findItem(R.id.nav_home).setCheckable(true);






    }

    private void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();


        id = bundle.getString("id_user");
        token = bundle.getString("token_user");

        user = bundle.getString("username");
        email = bundle.getString("email_user");
        fullname = bundle.getString("fullname");
        birthday = bundle.getString("birthday");
        phone = bundle.getString("phone");
        address = bundle.getString("address");

        role = bundle.getString("role_user");
        gender = bundle.getBoolean("gender");

        picture = bundle.getString("picture");
        CV = bundle.getString("CV");

    }

    private void replaceFragment(Home1Fragment homeFragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame_tutor,homeFragment);
        fragmentTransaction.commit();
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame_tutor,new Home1Fragment()).commit();
                break;
            case R.id.nav_student:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame_tutor,new StudentFragment()).commit();
                break;
            case R.id.nav_fee:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame_tutor,new FeeFragment()).commit();
                break;
            case R.id.nav_doimatkhau:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame_tutor,new DoiMatKhauFragment()).commit();
                break;
            case R.id.nav_info_tutor:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame_tutor,new TutorInfoFragment()).commit();
                break;
            case R.id.nav_lichday:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame_tutor,new LichDayFragment()).commit();
                break;
            case R.id.nav_dangkyday:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame_tutor,new DangKyDayFragment()).commit();
                break;
            case R.id.nav_logout:
                Toast.makeText(getApplication(), "Bạn đã thoát khỏi ứng dụng",Toast.LENGTH_LONG).show();
                onBackPressed();
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
        super.onBackPressed();
    }

    public String getUser() {
        return user;
    }

    public String getEmail() {
        return email;
    }

    public String getFullname() {
        return fullname;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public boolean getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getAva() {
        return picture;
    }

    public String getCV() {
        return CV;
    }
}