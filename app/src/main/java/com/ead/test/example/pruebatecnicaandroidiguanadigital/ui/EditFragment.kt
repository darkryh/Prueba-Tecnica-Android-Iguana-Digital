package com.ead.test.example.pruebatecnicaandroidiguanadigital.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ead.test.example.pruebatecnicaandroidiguanadigital.data.model.DataItem
import com.ead.test.example.pruebatecnicaandroidiguanadigital.databinding.FragmentEditBinding
import com.ead.test.example.pruebatecnicaandroidiguanadigital.viewmodel.ItemViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditFragment : Fragment() {

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ItemViewModel by activityViewModels()

    private var itemId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            itemId = it.getInt(ITEM_ID, -1)
            val itemName = it.getString(ITEM_NAME, "")
            binding.editTextName.setText(itemName)
        }

        binding.editTextName.doAfterTextChanged {
            if (it.isNullOrBlank()) {
                binding.textInputLayout.error = "Nombre no puede estar vacio"
            } else {
                binding.textInputLayout.error = null
            }
        }

        binding.saveButton.setOnClickListener {
            val itemName = binding.editTextName.text.toString()

            if (itemName.isNotBlank()) {
                val newItem = DataItem(id = if (itemId == -1) 0 else itemId ?: 0, name = itemName)
                viewModel.addItem(newItem)

                parentFragmentManager.popBackStack()
            } else {
                binding.textInputLayout.error = "Name cannot be empty"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ITEM_ID = "item_id"
        private const val ITEM_NAME = "item_name"

        fun newInstance(itemId: Int? = null, itemName: String? = null): EditFragment {
            val fragment = EditFragment()
            fragment.arguments = Bundle().apply {
                itemId?.let { putInt(ITEM_ID, it) }
                itemName?.let { putString(ITEM_NAME, it) }
            }
            return fragment
        }
    }
}
