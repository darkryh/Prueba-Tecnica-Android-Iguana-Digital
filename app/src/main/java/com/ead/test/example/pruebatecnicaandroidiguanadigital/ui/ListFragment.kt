package com.ead.test.example.pruebatecnicaandroidiguanadigital.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ead.test.example.pruebatecnicaandroidiguanadigital.R
import com.ead.test.example.pruebatecnicaandroidiguanadigital.databinding.FragmentListBinding
import com.ead.test.example.pruebatecnicaandroidiguanadigital.util.ItemAdapter
import com.ead.test.example.pruebatecnicaandroidiguanadigital.viewmodel.ItemViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null

    private val viewModel: ItemViewModel by lazy {
        try {
            activityViewModels<ItemViewModel>().value
        } catch (e: Exception) {
            Log.e("error", "Error injectando ViewModel: ${e.message}")
            throw IllegalStateException("ViewModel injeccion fallida")
        }
    }

    private lateinit var adapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.let { binding ->
            adapter = ItemAdapter()
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerView.adapter = adapter

            viewModel.items.observe(viewLifecycleOwner) { items ->
                adapter.submitList(items)
            }

            adapter.setOnItemClickListener { item -> navigateToEditFragment(item.id, item.name) }
            binding.addButton.setOnClickListener { navigateToEditFragment() }
        } ?: run {
            Log.e("ListFragment", "Binding is null in onViewCreated")
        }
    }

    private fun navigateToEditFragment(itemId: Int? = null, itemName: String? = null) {
        val editFragment = EditFragment.newInstance(itemId, itemName)
        parentFragmentManager.beginTransaction()
            .replace(R.id.main, editFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
