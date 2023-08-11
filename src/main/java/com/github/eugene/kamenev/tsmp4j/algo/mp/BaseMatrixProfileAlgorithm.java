/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.eugene.kamenev.tsmp4j.algo.mp;

import com.github.eugene.kamenev.tsmp4j.stats.RollingWindowStatistics;

public abstract class BaseMatrixProfileAlgorithm implements MatrixProfileAlgorithm {

    protected final int minInstances;

    protected final int windowSize;

    protected final RollingWindowStatistics rollingStatistics;

    public BaseMatrixProfileAlgorithm(int minInstances, int windowSize) {
        this(minInstances, windowSize,
            new RollingWindowStatistics(windowSize, minInstances, false));
    }

    public BaseMatrixProfileAlgorithm(int minInstances, int windowSize,
        RollingWindowStatistics rollingWindowStatistics) {
        this.minInstances = minInstances;
        this.windowSize = windowSize;
        this.rollingStatistics = rollingWindowStatistics;
    }

    @Override
    public void update(double value) {
        this.rollingStatistics.apply(value);
    }

    public boolean isReady() {
        return this.rollingStatistics.getStatsBuffer().isFull();
    }
}
