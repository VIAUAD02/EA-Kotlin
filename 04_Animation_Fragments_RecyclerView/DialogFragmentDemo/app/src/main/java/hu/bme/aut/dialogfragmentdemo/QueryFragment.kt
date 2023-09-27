package hu.bme.aut.dialogfragmentdemo

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class QueryFragment : DialogFragment() {

    interface OnQueryFragmentAnswer {
        fun onPositiveSelected(text: String)
        fun onNegativeSelected()
    }

    private var onQueryFragmentAnswer: OnQueryFragmentAnswer? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnQueryFragmentAnswer) {
            onQueryFragmentAnswer = context
        } else {
            throw RuntimeException(
                "This Activity is not implementing the " +
                        "OnQueryFragmentAnswer interface")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val message = arguments?.getString(MainActivity.KEY_MSG)

        val alertDialogBuilder = AlertDialog.Builder(requireContext())

        val inflater = LayoutInflater.from(getContext())
        val dialogLayout = inflater.inflate(R.layout.layout_dialog, null)
        val etName = dialogLayout.findViewById<EditText>(R.id.etName)
        alertDialogBuilder.setView(dialogLayout)


        alertDialogBuilder.setTitle("Please read this message")
        alertDialogBuilder.setMessage(message)

        alertDialogBuilder.setPositiveButton("Okay", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.dismiss()
            onQueryFragmentAnswer!!.onPositiveSelected(etName.text.toString())
        })
        alertDialogBuilder.setNegativeButton("Nope", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.dismiss()
            onQueryFragmentAnswer!!.onNegativeSelected()
        })


        return alertDialogBuilder.create()
    }
}
