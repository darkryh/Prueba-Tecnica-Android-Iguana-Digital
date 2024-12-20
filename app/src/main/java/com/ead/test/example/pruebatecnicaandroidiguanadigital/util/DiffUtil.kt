package com.ead.test.example.pruebatecnicaandroidiguanadigital.util

import androidx.recyclerview.widget.DiffUtil

class DiffUtil <T>(
    private val oldList: List<T>,
    private val newList: List<T>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return try {
            return if (oldList[oldItemPosition] is ComparisonDiffUtil) {
                (oldList[oldItemPosition] as ComparisonDiffUtil)
                    .equalsHeader((newList[oldItemPosition] as ComparisonDiffUtil))
            } else true
        } catch (e : Exception) { false }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return try {
            return if (oldList[oldItemPosition] is ComparisonDiffUtil) {
                (oldList[oldItemPosition] as ComparisonDiffUtil)
                    .equalsContent((newList[oldItemPosition] as ComparisonDiffUtil))
            } else true
        } catch (e : Exception) { false }
    }
}