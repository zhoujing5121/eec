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

package cn.ttzero.excel.entity;

import cn.ttzero.excel.service.StudentService;

/**
 * Create by guanquan.wang at 2019-04-30 15:12
 */
public class CustomizeDataSourceSheet extends ListObjectSheet<ListObjectSheetTest.Student> {

    private StudentService service;

    private int pageNo, limit = 64;

    public CustomizeDataSourceSheet() {
        this.service = new StudentService();
    }

    public RowBlock nextBlock() {
        // Get block data
        super.setData(this.service.getPageData(pageNo++, limit));

        return super.nextBlock();
    }

}