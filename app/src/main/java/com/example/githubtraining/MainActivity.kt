package com.example.githubtraining

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var pref: SharedPreferences
    private var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appComponent.inject(this)
        val navHostFragment = nav_host_fragment as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)
        token = pref.getString(getString(R.string.sharedPrefToken), getString(R.string.sharedPrefNoToken))

        if (token != getString(R.string.sharedPrefNoToken)) {
            graph.startDestination = R.id.infoUserFragment
        } else {
            graph.startDestination = R.id.loginFragment
        }
        navHostFragment.navController.graph = graph
    }
}