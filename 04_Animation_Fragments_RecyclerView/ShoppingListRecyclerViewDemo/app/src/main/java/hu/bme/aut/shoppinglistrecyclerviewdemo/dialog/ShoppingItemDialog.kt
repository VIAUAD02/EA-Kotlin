package hu.bme.aut.shoppinglistrecyclerviewdemo.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import hu.bme.aut.shoppinglistrecyclerviewdemo.R
import hu.bme.aut.shoppinglistrecyclerviewdemo.data.ShoppingItem
import hu.bme.aut.shoppinglistrecyclerviewdemo.databinding.ShoppingDialogBinding

class ShoppingItemDialog : DialogFragment() {

    interface ShoppingItemDialogHandler {
        fun shoppingItemCreated(item: ShoppingItem)
        fun shoppingItemModified(item: ShoppingItem)
    }

    private lateinit var shoppingItemHandler: ShoppingItemDialogHandler

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is ShoppingItemDialogHandler) {
            shoppingItemHandler = context
        } else {
            throw RuntimeException("The Activity does not implement the ShoppingItemDialogHandler interface")
        }
    }

    private lateinit var shoppingDialogBinding: ShoppingDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("New Item")

        shoppingDialogBinding = ShoppingDialogBinding.inflate(
            requireActivity().layoutInflater
        )

        var categoryAdapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.category_array,
            android.R.layout.simple_spinner_item
        )
        categoryAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        shoppingDialogBinding.spinnerCategory.adapter = categoryAdapter

        builder.setView(shoppingDialogBinding.root)

        builder.setPositiveButton("OK") { dialog, which ->
            //... keep empty
        }
        return builder.create()
    }

    override fun onResume() {
        super.onResume()

        val dialog = dialog as AlertDialog
        val positiveButton = dialog.getButton(Dialog.BUTTON_POSITIVE)

        positiveButton.setOnClickListener {
            if (shoppingDialogBinding.etName.text.isNotEmpty()) {
                if (shoppingDialogBinding.etPrice.text.isNotEmpty()) {
                    handleItemCreate()

                    dialog.dismiss()
                } else {
                    shoppingDialogBinding.etPrice.error = "This field can not be empty"
                }
            } else {
                shoppingDialogBinding.etName.error = "This field can not be empty"
            }
        }
    }

    fun handleItemCreate() {
        shoppingItemHandler.shoppingItemCreated(
            ShoppingItem(
                shoppingDialogBinding.etName.text.toString(),
                shoppingDialogBinding.etPrice.text.toString().toInt(),
                "Demo",
                false,
                shoppingDialogBinding.spinnerCategory.selectedItemPosition
            )
        )

    }
}