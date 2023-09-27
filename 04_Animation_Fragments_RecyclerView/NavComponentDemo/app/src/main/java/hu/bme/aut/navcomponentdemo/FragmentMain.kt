package hu.bme.aut.navcomponentdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import hu.bme.aut.navcomponentdemo.databinding.FragmentMainBinding

class FragmentMain : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnGo.setOnClickListener {

            binding.root.findNavController().navigate(
                FragmentMainDirections.actionFragmentMainToFragmentDetail(
                    Person(
                    binding.etName.text.toString(),
                    binding.etAddress.text.toString()
                ))
            )

        }
    }

}