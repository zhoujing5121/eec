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

import cn.ttzero.excel.Print;
import cn.ttzero.excel.annotation.DisplayName;
import cn.ttzero.excel.annotation.NotExport;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Create by guanquan.wang at 2019-04-28 19:17
 */
public class ListObjectSheetTest extends WorkbookTest{

    @Test
    public void testWrite() throws IOException {
        new Workbook("test object", "guanquan.wang")
            .watch(Print::println)
            .addSheet(Item.randomTestData())
            .writeTo(defaultTestPath);
    }

    @Test public void testAllTypeWrite() throws IOException {
        new Workbook("all type object", "guanquan.wang")
            .watch(Print::println)
            .addSheet(AllType.randomTestData())
            .writeTo(defaultTestPath);
    }

    @Test public void testAnnotation() throws IOException {
        new Workbook("annotation object", "guanquan.wang")
            .watch(Print::println)
            .addSheet(Student.randomTestData())
            .writeTo(defaultTestPath);
    }


    public static class Item {
        private int id;
        private String name;

        Item(int id, String name) {
            this.id = id;
            this.name = name;
        }

        static List<Item> randomTestData() {
            int n = random.nextInt(100) + 1;
            List<Item> list = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                list.add(new Item(i, getRandomString()));
            }
            return list;
        }
    }

    public static class AllType {
        private char cv;
        private short sv;
        private int nv;
        private long lv;
        private float fv;
        private double dv;
        private String s;
        private BigDecimal mv;
        private Date av;
        private Timestamp iv;
        private Time tv;
        private LocalDate ldv;
        private LocalDateTime ldtv;
        private LocalTime ltv;

        static List<AllType> randomTestData() {
            int size = random.nextInt(100) + 1;
            List<AllType> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                AllType o = new AllType();
                o.cv = charArray[random.nextInt(charArray.length)];
                o.sv = (short) (random.nextInt() & 0xFFFF);
                o.nv = random.nextInt();
                o.lv = random.nextLong();
                o.fv = random.nextFloat();
                o.dv = random.nextDouble();
                o.s = getRandomString();
                o.mv = BigDecimal.valueOf(random.nextDouble());
                o.av = new Date();
                o.iv = new Timestamp(System.currentTimeMillis() - random.nextInt(9999999));
                o.tv = new Time(random.nextLong());
                o.ldv = LocalDate.now();
                o.ldtv = LocalDateTime.now();
                o.ltv = LocalTime.now();
                list.add(o);
            }
            return list;
        }
    }

    /**
     * Annotation Object
     */
    public static class Student {
        @DisplayName("学号")
        private int id;
        @DisplayName("姓名")
        private String name;
        @DisplayName("年龄")
        private int age;
        @NotExport("secret")
        private String password;

        Student(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        static List<Student> randomTestData() {
            int n = random.nextInt(100) + 1;
            List<Student> list = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                Student e = new Student(i, getRandomString(), random.nextInt(15) + 5);
                e.password = getRandomString();
                list.add(e);
            }
            return list;
        }
    }
}