package com.app.service.type;

import org.eclipse.collections.impl.collector.BigDecimalSummaryStatistics;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;

public record Statistics(BigDecimal min, BigDecimal avg, BigDecimal max) {
    public static Statistics toStatistics(DoubleSummaryStatistics doubleSummaryStatistics) {
        return new Statistics(
                BigDecimal.valueOf(doubleSummaryStatistics.getMin()),
                BigDecimal.valueOf(doubleSummaryStatistics.getAverage()),
                BigDecimal.valueOf(doubleSummaryStatistics.getMax())
        );
    }

    public static Statistics toStatistics(BigDecimalSummaryStatistics bigDecimalSummaryStatistics) {
        return new Statistics(
                bigDecimalSummaryStatistics.getMin(),
                bigDecimalSummaryStatistics.getAverage(),
                bigDecimalSummaryStatistics.getMax()
        );
    }
}
