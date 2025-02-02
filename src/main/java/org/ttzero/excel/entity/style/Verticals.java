/*
 * Copyright (c) 2019, guanquan.wang@yandex.com All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ttzero.excel.entity.style;

/**
 * Created by guanquan.wang at 2018-02-11 14:59
 */
public class Verticals {
    public static final int CENTER = 0 // Align Center
        , BOTTOM = 1 << Styles.INDEX_VERTICAL // Align Bottom
        , TOP    = 2 << Styles.INDEX_VERTICAL // Align Top
        , BOTH   = 3 << Styles.INDEX_VERTICAL // Vertical Justification
        ;

    private static final String[] _names = {"center", "bottom", "top", "both"};

    public static String of(int n) {
        return _names[n];
    }
}
