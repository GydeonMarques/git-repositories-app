package br.com.android.git.repositories.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import br.com.android.git.repositories.R
import br.com.android.git.repositories.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar.toolbar)
        setContentView(binding.root)
        setupNavController()
    }

    private fun setupNavController() {
        navController = findNavController(R.id.nav_host_fragment).apply {
            setupActionBarWithNavController(this)
            addOnDestinationChangedListener { _, destination, _ ->
                binding.toolbar.toolbar.title = destination.label
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}