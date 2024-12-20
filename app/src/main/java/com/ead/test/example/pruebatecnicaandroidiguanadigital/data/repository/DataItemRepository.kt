package com.ead.test.example.pruebatecnicaandroidiguanadigital.data.repository

import com.ead.test.example.pruebatecnicaandroidiguanadigital.data.model.DataItem

class DataItemRepository {

    private val source: MutableList<DataItem> = mutableListOf()
    private var currentId = 1

    fun add(dataItem: DataItem) {
        val itemWithId = if (dataItem.id <= 0) {
            val newId = currentId++
            dataItem.copy(id = newId)
        } else {
            dataItem
        }
        source.add(itemWithId)
    }

    fun update(dataItem: DataItem) {
        val index = source.indexOfFirst { it.id == dataItem.id }
        if (index != -1) {
            source[index] = dataItem
        }
    }

    fun getAll(): List<DataItem> {
        return source
    }
}
