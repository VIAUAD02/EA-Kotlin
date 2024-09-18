package hu.bme.aut.dynamicfragementdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            showHomeFragment()
        }
    }


    private fun showHomeFragment() {
        val homeFragment=HomeFragment()

        val ft=supportFragmentManager.beginTransaction()
        ft.add(R.id.fragmentContainer,homeFragment,HomeFragment.TAG)
        ft.commit()

    }

    fun showDetails(parameter: String) {
        val detailsFragment=DetailFragment.newInstance(parameter)

        val ft=supportFragmentManager.beginTransaction()

        ft.replace(R.id.fragmentContainer,detailsFragment,DetailFragment.TAG)

        ft.addToBackStack(DetailFragment.TAG)
        ft.commit()
    }

    /*
    public fun showFragmentByTag(tag: String) {
        var fragment = supportFragmentManager.findFragmentByTag(tag)

        if (fragment == null) {
            if (FragmentMain.TAG == tag) {
                fragment = FragmentMain()
            } else if (FragmentDetails.TAG == tag) {
                fragment = FragmentDetails()
            }
        }

        if (fragment != null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragmentContainer, fragment, tag)

            ft.addToBackStack(null) // add fragment transaction to the back stack

            ft.commit()
        }
    }
    */
}