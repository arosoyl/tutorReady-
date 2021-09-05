package com.example.tutorready;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tutorready.tutor.TutorInfoFragment;
import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class ActivityTutor extends AppCompatActivity {


    SNavigationDrawer sNavigationDrawer3;
    Class fragmentClass1;
    public static Fragment fragment1;

    String id,token,role,email,fullname,birthday,address,phone,user;
    Boolean gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);

        initControl1();


            // Nhận chuỗi token và id user

            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            id = bundle.getString("id_user");
            token = bundle.getString("token_user");
            user = bundle.getString("username");

            role = bundle.getString("role_user");

            email = bundle.getString("email_user");
            fullname = bundle.getString("fullname");
            birthday = bundle.getString("birthday");
            phone = bundle.getString("phone");
            address = bundle.getString("address");
            gender = bundle.getBoolean("gender");



            Toast.makeText(ActivityTutor.this, "Mời bạn nhập đầy đủ thông tin" +id +user , Toast.LENGTH_LONG).show();

        }


        private void initControl1() {sNavigationDrawer3 = findViewById(R.id.navigationDrawer3);

            //Creating a list of menu Items

            List<MenuItem> menuItems1 = new ArrayList<>();

            //Use the MenuItem given by this library and not the default one.
            //First parameter is the title of the menu item and then the second parameter is the image which will be the background of the menu item.

            menuItems1.add(new MenuItem("Trang chủ",R.drawable.news_bg));
            menuItems1.add(new MenuItem("Đăng xuất",R.drawable.feed_bg));
            //menuItems1.add(new MenuItem("Tìm lớp học",R.drawable.message_bg));
            //menuItems1.add(new MenuItem("Tìm gia sư",R.drawable.music_bg));
            //menuItems1.add(new MenuItem("Học phí tham khảo",R.drawable.music_bg));
            //menuItems1.add(new MenuItem("Thông tin cá nhân",R.drawable.music_bg));

            //then add them to navigation drawer

            sNavigationDrawer3.setMenuItemList(menuItems1);
            fragmentClass1 =  TutorInfoFragment.class;
            try {
                fragment1 = (Fragment) fragmentClass1.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (fragment1 != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment1).commit();
            }

            //Listener to handle the menu item click. It returns the position of the menu item clicked. Based on that you can switch between the fragments.

            sNavigationDrawer3.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
                @Override
                public void onMenuItemClicked(int position) {
                    System.out.println("Position "+position);

                    switch (position){
                        case 0:{
                            fragmentClass1 = TutorInfoFragment.class;
                            break;
                        }
                        case 1:{
                            Intent intent = new Intent(ActivityTutor.this, MainActivity.class);
                            token ="";
                            id = "";
                            role = "";
                            user = "";
                            email = "";
                            fullname = "";
                            birthday = "";
                            phone = "";
                            startActivity(intent);
                            onBackPressed();
                            finish();
                            break;
                        }
                    /*
                    case 2:{
                        fragmentClass1 = TimLopHocFragment.class;
                        break;
                    }
                    case 3:{
                        fragmentClass1 = TimGiaSuFragment.class;
                        break;
                    }
                    case 4: {
                        fragmentClass1 = FeeFragment.class;
                        break;
                    }
                    case 5: {
                        fragmentClass1 = InfoFragment.class;
                        break;
                    }

                    */

                    }

                    //Listener for drawer events such as opening and closing.
                    sNavigationDrawer3.setDrawerListener(new SNavigationDrawer.DrawerListener() {

                        @Override
                        public void onDrawerOpened() {

                        }

                        @Override
                        public void onDrawerOpening(){

                        }

                        @Override
                        public void onDrawerClosing(){
                            System.out.println("Drawer closed");

                            try {
                                fragment1 = (Fragment) fragmentClass1.newInstance();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            if (fragment1 != null) {
                                FragmentManager fragmentManager = getSupportFragmentManager();
                                fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment1).commit();

                            }
                        }

                        @Override
                        public void onDrawerClosed() {

                        }

                        @Override
                        public void onDrawerStateChanged(int newState) {
                            System.out.println("State "+newState);
                        }
                    });
                }
            });
        }

    public String getUser() {
        return user;
    }
}