package meol.app.myapp

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Debug
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import meol.app.myapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        if(haveNetwork()){
            Snackbar.make(binding.root, "Internet Found", Snackbar.LENGTH_INDEFINITE).show()
        }
        else {
            Snackbar.make(binding.root, "Internet Not Found", Snackbar.LENGTH_INDEFINITE).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun haveNetwork(): Boolean {
        var haveWifi = false
        var haveMobile = false
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo =                                                                                                                                                                                                           connectivityManager.allNetworkInfo
        for(info in networkInfo){
            if(info.typeName.equals("WIFI", ignoreCase = true)) if(info.isConnected) haveWifi = true
            if(info.typeName.equals("MOBILE", ignoreCase = true)) if(info.isConnected) haveMobile = true
        }
        return haveMobile || haveWifi
    }
}