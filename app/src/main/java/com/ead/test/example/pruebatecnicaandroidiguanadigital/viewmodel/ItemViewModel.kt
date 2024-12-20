package com.ead.test.example.pruebatecnicaandroidiguanadigital.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ead.test.example.pruebatecnicaandroidiguanadigital.TestHelper
import com.ead.test.example.pruebatecnicaandroidiguanadigital.data.model.DataItem
import com.ead.test.example.pruebatecnicaandroidiguanadigital.data.repository.DataItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val dataItemRepository: DataItemRepository
) : ViewModel() {

    private val _items = MutableLiveData<List<DataItem>>()
    val items: LiveData<List<DataItem>> = _items

    init {
        viewModelScope.launch(Dispatchers.IO) {
            if (TestHelper.IS_TESTING_ITEMS) dataItemRepository.generateTestItems()

            withContext(Dispatchers.Main) {
                _items.value = dataItemRepository.getAll()
            }
        }
    }

    fun addItem(dataItem: DataItem) {
        viewModelScope.launch(Dispatchers.IO) {
            val existingItemIndex = dataItemRepository.getAll().indexOfFirst { it.id == dataItem.id }

            if (existingItemIndex != -1) {
                dataItemRepository.update(dataItem)
            } else {
                dataItemRepository.add(dataItem)
            }

            withContext(Dispatchers.Main) {
                _items.value = dataItemRepository.getAll()
            }
        }
    }
}
