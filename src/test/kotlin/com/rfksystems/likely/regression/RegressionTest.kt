package com.rfksystems.likely.regression

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegressionTest {
    @Test
    fun it_correctly_predicts_values_in_sequence() {
        val regression = Regression()

        regression.addData(1.0, 4664.12);
        regression.addData(2.0, 1398.22);
        regression.addData(3.0, 1423.89);
        regression.addData(4.0, 1186.83);
        regression.addData(5.0, 928.83);
        regression.addData(6.0, 838.29);
        regression.addData(7.0, 765.76);
        regression.addData(8.0, 734.51);
        regression.addData(9.0, 587.15);
        regression.addData(10.0, 624.66);

        val nextPair = regression.next()

        assertThat(nextPair.x).isWithin(0.0).of(11.0)
        assertThat(nextPair.y).isWithin(0.0).of(521.9827977082344)

        val nextPairs = regression.next(3L)

        assertThat(nextPairs).hasSize(3)

        val (x0, y0) = nextPairs.get(0);
        assertThat(x0).isWithin(0.0).of(12.0)
        assertThat(y0).isWithin(0.0).of(487.0143006822566)

        val (x1, y1) = nextPairs.get(1);
        assertThat(x1).isWithin(0.0).of(13.0)
        assertThat(y1).isWithin(0.0).of(456.9188560711197)

        val (x2, y2) = nextPairs.get(2);
        assertThat(x2).isWithin(0.0).of(14.0)
        assertThat(y2).isWithin(0.0).of(430.715448238576)
    }
}
