package com.ead.test.example.pruebatecnicaandroidiguanadigital.data.repository

import com.ead.test.example.pruebatecnicaandroidiguanadigital.data.model.DataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataItemRepository {

    private val source: MutableList<DataItem> = mutableListOf()
    private var currentId = 1

    suspend fun generateTestItems() {
        withContext(Dispatchers.IO) {
            val largeList = List(10000) { "Item $it" }
            largeList.forEachIndexed { index, it ->
                source.add(DataItem(id = index, name = it))
            }
        }
    }

    suspend fun add(dataItem: DataItem) {
        withContext(Dispatchers.IO) {
            val id = if (dataItem.id <= 0) {
                currentId++
            } else {
                dataItem.id
            }
            source[id] = dataItem.copy(id = id)
        }
    }

    suspend fun update(dataItem: DataItem) {
        withContext(Dispatchers.IO) {
            source[dataItem.id] = dataItem
        }
    }

    suspend fun getAll(): List<DataItem> {
        return withContext(Dispatchers.IO) {
            source.toList()
        }
    }
}
