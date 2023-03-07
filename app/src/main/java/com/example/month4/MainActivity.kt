package com.example.month4

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.month4.databinding.ActivityMainBinding
import com.example.month4.ui.data.local.Pref
import com.example.month4.ui.home.HomeFragmentDirections
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pref: Pref
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = Pref(this)
        auth = FirebaseAuth.getInstance()
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        if (!pref.isUserSeen())
            navController.navigate(HomeFragmentDirections.actionNavigationHomeToOnBoardingFragment2())
        if (auth.currentUser?.uid ==  null){
            navController.navigate(R.id.authFragment )
        }
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.taskFragment,
                R.id.navigation_img,
                R.id.authFragment
            )
        )
        val bottomNavFragment = arrayListOf(
            R.id.navigation_home,
            R.id.navigation_dashboard,
            R.id.navigation_notifications,
            R.id.navigation_img,
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            navView.isVisible = bottomNavFragment.contains(destination.id)//Start
            if (destination.id == R.id.onBoardingFragment) {
                supportActionBar?.hide()
            } else supportActionBar?.show()
//            navView.isVisible = destination.id != R.id.taskFragment
        }
        navView.setupWithNavController(navController)
    }
}