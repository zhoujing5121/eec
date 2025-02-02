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

import org.ttzero.excel.util.StringUtil;
import org.dom4j.Element;

import java.awt.Color;
import java.lang.reflect.Field;

/**
 * Border line styles and colors
 *
 * The border contains orientation, style and color. The Excel
 * border has 6 orientations, which are left, right, top, bottom,
 * diagonalDown, diagonalUp. For the style, please refer to {@link BorderStyle}.
 * The default border color is #333333 in EEC
 *
 * @see BorderStyle
 * <p>
 * Created by guanquan.wang on 2018-02-06 08:55
 */
public class Border {

    private static final Color defaultColor = new Color(51, 51, 51); // #333333

    private SubBorder[] borders;

    public Border() {
        borders = new SubBorder[6]; // left-right-top-bottom-diagonalDown-diagonalUp
    }

    /**
     * Top line style
     *
     * @param style the {@link BorderStyle}
     * @return the {@link Border}
     */
    public Border setBorderTop(BorderStyle style) {
        borders[2] = new SubBorder(style, defaultColor);
        return this;
    }

    /**
     * Right line style
     *
     * @param style the {@link BorderStyle}
     * @return the {@link Border}
     */
    public Border setBorderRight(BorderStyle style) {
        borders[1] = new SubBorder(style, defaultColor);
        return this;
    }

    /**
     * Bottom line style
     *
     * @param style the {@link BorderStyle}
     * @return the {@link Border}
     */
    public Border setBorderBottom(BorderStyle style) {
        borders[3] = new SubBorder(style, defaultColor);
        return this;
    }

    /**
     * Left line style
     *
     * @param style the {@link BorderStyle}
     * @return the {@link Border}
     */
    public Border setBorderLeft(BorderStyle style) {
        borders[0] = new SubBorder(style, defaultColor);
        return this;
    }

    /**
     * Top-left to bottom-right line style [\]
     *
     * @param style the {@link BorderStyle}
     * @return the {@link Border}
     */
    public Border setDiagonalDown(BorderStyle style) {
        borders[4] = new SubBorder(style, defaultColor);
        return this;
    }

    /**
     * Bottom-left to top-right line style [/]
     *
     * @param style the {@link BorderStyle}
     * @return the {@link Border}
     */
    public Border setDiagonalUp(BorderStyle style) {
        borders[5] = new SubBorder(style, defaultColor);
        return this;
    }

    /**
     * Setting top-right-bottom-left line style
     *
     * @param style the {@link BorderStyle}
     * @return the {@link Border}
     */
    public Border setBorder(BorderStyle style) {
        borders[0] = new SubBorder(style, defaultColor);
        borders[1] = borders[2] = borders[3] = borders[0];
        return this;
    }


    /**
     * Setting top-left to bottom-right and bottom-left to top-right line style [X]
     *
     * @param style the {@link BorderStyle}
     * @return the {@link Border}
     */
    public Border setDiagonal(BorderStyle style) {
        borders[4] = new SubBorder(style, defaultColor);
        borders[5] = borders[4];
        return this;
    }

    /**
     * Top line style
     *
     * @param style the {@link BorderStyle}
     * @param color the border {@link Color}
     * @return the {@link Border}
     */
    public Border setBorderTop(BorderStyle style, Color color) {
        borders[2] = new SubBorder(style, color);
        return this;
    }

    /**
     * Right line style
     *
     * @param style the {@link BorderStyle}
     * @param color the border {@link Color}
     * @return the {@link Border}
     */
    public Border setBorderRight(BorderStyle style, Color color) {
        borders[1] = new SubBorder(style, color);
        return this;
    }

    /**
     * Bottom line style
     *
     * @param style the {@link BorderStyle}
     * @param color the border {@link Color}
     * @return the {@link Border}
     */
    public Border setBorderBottom(BorderStyle style, Color color) {
        borders[3] = new SubBorder(style, color);
        return this;
    }

    /**
     * Left line style
     *
     * @param style the {@link BorderStyle}
     * @param color the border {@link Color}
     * @return the {@link Border}
     */
    public Border setBorderLeft(BorderStyle style, Color color) {
        borders[0] = new SubBorder(style, color);
        return this;
    }

    /**
     * Top-left to bottom-right line style [\]
     *
     * @param style the {@link BorderStyle}
     * @param color the border {@link Color}
     * @return the {@link Border}
     */
    public Border setDiagonalDown(BorderStyle style, Color color) {
        borders[4] = new SubBorder(style, color);
        return this;
    }

    /**
     * Bottom-left to top-right line style [/]
     *
     * @param style the {@link BorderStyle}
     * @param color the border {@link Color}
     * @return the {@link Border}
     */
    public Border setDiagonalUp(BorderStyle style, Color color) {
        borders[5] = new SubBorder(style, color);
        return this;
    }

    /**
     * Setting top-left to bottom-right and bottom-left to top-right line style [X]
     *
     * @param style the {@link BorderStyle}
     * @param color the border {@link Color}
     * @return the {@link Border}
     */
    public Border setDiagonal(BorderStyle style, Color color) {
        borders[4] = new SubBorder(style, color);
        borders[5] = borders[4];
        return this;
    }

    /**
     * Setting top-right-bottom-left line style
     *
     * @param style the {@link BorderStyle}
     * @param color the border {@link Color}
     * @return the {@link Border}
     */
    public Border setBorder(BorderStyle style, Color color) {
        borders[0] = new SubBorder(style, color);
        borders[1] = borders[0];
        borders[2] = borders[0];
        borders[3] = borders[0];
        return this;
    }

    Border setBorder(int index, BorderStyle style) {
        borders[index] = new SubBorder(style, defaultColor);
        return this;
    }

    Border setBorder(int index, BorderStyle style, Color color) {
        borders[index] = new SubBorder(style, color);
        return this;
    }

    Border delBorder(int index) {
        borders[index] = null;
        return this;
    }

    public int hashCode() {
        int down = borders[4] != null ? 1 : 0
            , up = borders[5] != null ? 2 : 0;
        int hash = down | up;
        for (SubBorder sub : borders) {
            hash += sub.hashCode();
        }
        return hash;
    }

    public boolean equals(Object o) {
        if (o instanceof Border) {
            Border other = (Border) o;
            for (int i = 0; i < borders.length; i++) {
                if (other.borders[i] != null) {
                    if (!other.borders[i].equals(borders[i]))
                        return false;
                } else if (borders[i] != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * The setting order is top -&gt; right -&gt; bottom -&gt; left, the
     * attribute order is style-name + color, if the orientation setting
     * is not complete, the unset orientation has no border. If only the
     * orientation is not set, the last color will be complemented.
     * <p>
     * eq:
     * thin red
     * thin red thin dashed dashed
     * medium black thick #cccccc double black hair green
     * none none thin thin
     * </p>
     * @param text the border value
     * @return the parse value of {@link Border}
     */
    public static Border parse(String text) {
        Border border = new Border();
        if (StringUtil.isEmpty(text)) return border;
        String[] values = text.split(" ");
        int index = 0;
        Color color = null;
        for (int i = 0; i < values.length; i++) {
            BorderStyle style = BorderStyle.getByName(values[i]);
            if (style == null) {
                throw new BorderParseException("Border style error.");
            }
            int n = i + 1;
            if (values.length <= n) break;
            String v = values[n];
            BorderStyle style1 = BorderStyle.getByName(v);
            if (style1 == null) {
                if (v.charAt(0) == '#') {
                    color = Color.decode(v);
                } else {
                    try {
                        Field field = Color.class.getDeclaredField(v);
                        color = (Color) field.get(null);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        throw new ColorParseException("Color \"" + v + "\" not support.");
                    }
                }
                border.setBorder(index++, style, color);
                i++;
            } else if (color != null) {
                border.setBorder(index++, style, color);
            } else {
                border.setBorder(index++, style);
            }
        }
        if (index == 1) {
            border.borders[1] = border.borders[0];
            border.borders[2] = border.borders[0];
            border.borders[3] = border.borders[0];
        }
        return border;
    }

    private static class SubBorder {
        private BorderStyle style;
        private Color color;

        public SubBorder(BorderStyle style, Color color) {
            this.style = style;
            this.color = color;
        }

        public int hashCode() {
            int hash = color.hashCode();
            return (style.ordinal() << 24) | (hash << 8 >>> 8);
        }

        public boolean equals(Object o) {
            return (o instanceof SubBorder) && o.hashCode() == hashCode();
        }
    }

    static final String[] direction = {"left", "right", "top", "bottom", "diagonal", "diagonal"};

    public Element toDom4j(Element root) {
        Element element = root.addElement(StringUtil.lowFirstKey(getClass().getSimpleName()));
        for (int i = 0; i < direction.length; i++) {
            Element sub = element.element(direction[i]);
            if (sub == null) sub = element.addElement(direction[i]);
            writeProperties(sub, borders[i]);
        }

        boolean down = borders[4] != null, up = borders[5] != null;
        if (down) {
            element.addAttribute("diagonalDown", "1");
        }
        if (up) {
            element.addAttribute("diagonalUp", "1");
        }
        return element;
    }

    protected void writeProperties(Element element, SubBorder subBorder) {
        if (subBorder != null && subBorder.style != BorderStyle.NONE) {
            element.addAttribute("style", subBorder.style.getName());
            Element colorEle = element.element("color");
            if (colorEle == null) colorEle = element.addElement("color");
            int colorIndex;
            if ((colorIndex = ColorIndex.indexOf(subBorder.color)) > -1) {
                colorEle.addAttribute("indexed", String.valueOf(colorIndex));
            } else {
                colorEle.addAttribute("rgb", ColorIndex.toARGB(subBorder.color));
            }
        }
    }

}
