package com.rfksystems.likely.regression

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ClusterRegressionTest {
    @Test
    fun name() {
        val regression = ClusterRegression(3)

        addSet(0, regression)
        addSet(1, regression)
        addSet(2, regression)

        assertThat(regression.next()).isWithin(0.0).of(521.9827977082344)
        assertThat(regression.next()).isWithin(0.0).of(521.9827977082344)
        assertThat(regression.next()).isWithin(0.0).of(521.9827977082344)

        assertThat(regression.next()).isWithin(0.0).of(487.0143006822566)
        assertThat(regression.next()).isWithin(0.0).of(487.0143006822566)
        assertThat(regression.next()).isWithin(0.0).of(487.0143006822566)

        assertThat(regression.next()).isWithin(0.0).of(456.9188560711197)
        assertThat(regression.next()).isWithin(0.0).of(456.9188560711197)
        assertThat(regression.next()).isWithin(0.0).of(456.9188560711197)
    }

    private fun addSet(clusterId: Long, regression: ClusterRegression) {
        regression.addData(clusterId, 1.0, 4664.12);
        regression.addData(clusterId, 2.0, 1398.22);
        regression.addData(clusterId, 3.0, 1423.89);
        regression.addData(clusterId, 4.0, 1186.83);
        regression.addData(clusterId, 5.0, 928.83);
        regression.addData(clusterId, 6.0, 838.29);
        regression.addData(clusterId, 7.0, 765.76);
        regression.addData(clusterId, 8.0, 734.51);
        regression.addData(clusterId, 9.0, 587.15);
        regression.addData(clusterId, 10.0, 624.66);
    }
}
