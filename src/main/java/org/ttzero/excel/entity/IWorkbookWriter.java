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

package org.ttzero.excel.entity;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;

/**
 * Create by guanquan.wang at 2019-04-22 16:00
 */
public interface IWorkbookWriter extends Storageable {

    /**
     * Setting workbook
     *
     * @param workbook the global workbook context
     */
    void setWorkbook(Workbook workbook);

    /**
     * The Workbook suffix
     *
     * @return xlsx if excel07, xls if excel03
     */
    String getSuffix();

    /**
     * Write to OutputStream ${os}
     *
     * @param os the out put stream
     * @throws IOException         if io error occur
     */
    void writeTo(OutputStream os) throws IOException;

    /**
     * Write to file ${file}
     *
     * @param file the storage file
     * @throws IOException         if io error occur
     */
    void writeTo(File file) throws IOException;

    /**
     * Write with template
     *
     * @return the template path
     * @throws IOException if io error occur
     */
    Path template() throws IOException;
}
