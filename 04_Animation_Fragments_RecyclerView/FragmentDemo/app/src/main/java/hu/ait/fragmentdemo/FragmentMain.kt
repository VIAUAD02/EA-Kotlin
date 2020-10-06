package hu.ait.fragmentdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.view.*

class FragmentMain : Fragment() {

    companion object {
        const val TAG = "TAG_FRAGMENT_MAIN"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        rootView.btnDemo1.setOnClickListener {
            Toast.makeText(activity, "Demo1", Toast.LENGTH_LONG).show()
        }

        rootView.btnDemo2.setOnClickListener {
            (activity as MainActivity).showFragmentByTag(FragmentDetails.TAG)
        }


        return rootView
    }

}