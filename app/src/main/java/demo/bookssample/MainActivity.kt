package demo.bookssample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import demo.bookssample.databinding.ActivityMainBinding
import demo.bookssample.di.Injectable

class MainActivity : AppCompatActivity(), Injectable {


    //https://api.itbook.store/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navController = Navigation.findNavController(this, R.id.nav_fragment)
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
    }
}
