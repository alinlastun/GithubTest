package com.example.githubtraining

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var pref: SharedPreferences

    private var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = nav_host_fragment as NavHostFragment

        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)

        token = pref.getString(getString(R.string.sharedPrefToken), getString(R.string.sharedPrefNoToken))
        Log.d("asdfasf","i: " +token)
        if (token != getString(R.string.sharedPrefNoToken)) {
            Log.d("asdfasf","infoUserFragment")
            graph.startDestination = R.id.infoUserFragment
        } else {
            Log.d("asdfasf","loginFragment")
            graph.startDestination = R.id.loginFragment
        }
        navHostFragment.navController.graph = graph
    }
}
