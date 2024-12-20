package com.ead.test.example.pruebatecnicaandroidiguanadigital.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ead.test.example.pruebatecnicaandroidiguanadigital.data.model.DataItem
import com.ead.test.example.pruebatecnicaandroidiguanadigital.data.repository.DataItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val dataItemRepository: DataItemRepository
) : ViewModel() {

    private val _items = MutableLiveData<List<DataItem>>()
    val items: LiveData<List<DataItem>> = _items

    init {
        _items.value = dataItemRepository.getAll()
    }

    fun addItem(dataItem: DataItem) {
        val existingItemIndex = dataItemRepository.getAll().indexOfFirst { it.id == dataItem.id }

        if (existingItemIndex != -1) {
            dataItemRepository.update(dataItem)
        } else {
            dataItemRepository.add(dataItem)
        }
        _items.value = dataItemRepository.getAll()
    }
}
