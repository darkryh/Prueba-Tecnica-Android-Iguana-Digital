package com.ead.test.example.pruebatecnicaandroidiguanadigital.data.model

import com.ead.test.example.pruebatecnicaandroidiguanadigital.util.ComparisonDiffUtil

data class DataItem(
    val id: Int,
    val name: String
) : ComparisonDiffUtil {
    override fun equalsHeader(other: Any?): Boolean {
        return other is DataItem && this.id == other.id
    }

    override fun equalsContent(other: Any?): Boolean {
        return other is DataItem && this.name == other.name
    }
}