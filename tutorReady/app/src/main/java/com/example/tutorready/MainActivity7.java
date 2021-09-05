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
import com.example.tutorready.admin.DoiMatKhau3Fragment;
import com.example.tutorready.admin.DuyetYeuCauFragment;
import com.example.tutorready.admin.GiaSuFragment;
import com.example.tutorready.admin.HocSinhFragment;
import com.example.tutorready.admin.Home2Fragment;
import com.example.tutorready.home.FeeFragment;
import com.example.tutorready.student.HomeFragment;
import com.example.tutorready.tutor.DangKyDayFragment;
import com.example.tutorready.tutor.DoiMatKhauFragment;
import com.example.tutorready.tutor.Home1Fragment;
import com.example.tutorready.tutor.LichDayFragment;
import com.example.tutorready.tutor.TutorInfoFragment;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity7 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    String id,token,role,email,fullname,birthday,address,phone,user,picture;
    Boolean gender;

    private DrawerLayout drawerLayout;

    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        drawerLayout = findViewById(R.id.drawer_layout_admin);

        Toolbar toolbar = findViewById(R.id.toolbar_admin);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view_admin);
        navigationView.setNavigationItemSelectedListener(this);


        replaceFragment(new Home2Fragment());
        navigationView.getMenu().findItem(R.id.nav_home).setCheckable(true);

        getData();

        textView = navigationView.getHeaderView(0).findViewById(R.id.tv_username);
        imageView = navigationView.getHeaderView(0).findViewById(R.id.imageViewAvatar);
        textView.setText(user);
        Glide.with(this).load(picture).error(R.drawable.avatardefaulticon).into(imageView);

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
    }

    private void replaceFragment(Home2Fragment homeFragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame_admin,homeFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame_admin,new Home2Fragment()).commit();
                break;
            case R.id.nav_fee:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame_admin,new FeeFragment()).commit();
                break;
            case R.id.nav_doimatkhau:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame_admin,new DoiMatKhau3Fragment()).commit();
                break;
            case R.id.nav_giasu:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame_admin,new GiaSuFragment()).commit();
                break;
            case R.id.nav_hocsinh:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame_admin,new HocSinhFragment()).commit();
                break;
            case R.id.nav_duyetyeucau:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame_admin,new DuyetYeuCauFragment()).commit();
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

}