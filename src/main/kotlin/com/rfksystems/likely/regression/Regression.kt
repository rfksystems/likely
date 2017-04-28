package com.rfksystems.likely.regression

import java.lang.Double.MIN_VALUE
import java.lang.Math.*

/**
 * Based on Apache commons math SimpleRegression
 */
class Regression {
    private var sumX: Double = .0

    private var sumXX: Double = .0

    private var sumY: Double = .0

    private var sumYY: Double = .0

    private var sumXY: Double = .0

    private var xBar: Double = .0

    private var yBar: Double = .0

    private var lastX: Double = .0

    private var n: Long = 0

    fun addData(x: Double, y: Double) {
        lastX = x

        val logOfX = log(x)
        val logOfY = log(y)

        if (0L == n) {
            xBar = logOfX
            yBar = logOfY
        } else {
            val fact1 = 1.0 + n
            val fact2 = n / (1.0 + n)
            val dx = logOfX - xBar
            val dy = logOfY - yBar
            sumXX += dx * dx * fact2
            sumYY += dy * dy * fact2
            sumXY += dx * dy * fact2
            xBar += dx / fact1
            yBar += dy / fact1
        }
        sumX += logOfX
        sumY += logOfY
        n++
    }

    fun getSlope(): Double {
        if (n < 2) {
            throw NotEnoughDataException()
        }

        if (Math.abs(sumXX) < 10 * MIN_VALUE) {
            throw NotEnoughVariationsException()
        }

        return sumXY / sumXX
    }

    fun getIntercept(): Double {
        return (sumY - getSlope() * sumX) / n
    }

    fun next(): SlotPair {
        val slope = getSlope()
        val intercept = exp(getIntercept())

        lastX++

        val y = intercept * pow(lastX, slope)

        return SlotPair(lastX, y)
    }

    fun next(numPredictions: Long): List<SlotPair> {
        val slots: ArrayList<SlotPair> = ArrayList()
        val slope = getSlope()
        val intercept = exp(getIntercept())

        for (i: Long in 1L..numPredictions) {
            lastX++
            val y = intercept * pow(lastX, slope)
            slots.add(SlotPair(lastX, y))
        }

        return slots
    }
}
