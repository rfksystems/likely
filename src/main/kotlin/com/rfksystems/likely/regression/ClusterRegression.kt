package com.rfksystems.likely.regression

import java.util.*

class ClusterRegression(private val interval: Long) {
    private val clusters: TreeMap<Long, Regression> = TreeMap()

    private var currentSlot = 0L

    fun addData(
            clusterPos: Long,
            x: Double,
            y: Double
    ) {
        if (clusterPos >= interval) {
            throw ClusterSizeException("Cluster key ($clusterPos) must lesser than cluster interval ($interval)")
        }

        if (!clusters.containsKey(clusterPos)) {
            clusters.put(clusterPos, Regression())
        }

        clusters.get(clusterPos)!!.addData(x, y)
    }

    fun next(): Double {
        if (!clusters.containsKey(currentSlot)) {
            throw MissingClusterException("There are no entries for cluster with position $currentSlot")
        }

        val get = clusters.get(currentSlot)!!

        currentSlot++

        if (currentSlot == interval) {
            currentSlot = 0L
        }

        return get.next().y
    }
}
