package com.github.yoojia.inputs;

import com.github.yoojia.inputs.impl.*;

/**
 * @author 陈小锅 (yoojia.chen@gmail.com)
 * @since 1.4
 */
public class ValueScheme {

    public static final int PRIORITY_REQUIRED = StaticScheme.PRIORITY_REQUIRED;
    public static final int PRIORITY_GENERAL = StaticScheme.PRIORITY_GENERAL;

    /**
     * 必要项，输入内容不能为空
     * @return Scheme
     */
    public static Scheme Required(){
        return StaticScheme.Required();
    }

    /**
     * 输入内容不能小于最小长度
     * @param min 最小长度
     * @return Scheme
     */
    public static Scheme MinLength(final int min) {
        return new Scheme(new MinLengthVerifier(min)).msg("输入内容至少" + min + "个字符");
    }

    /**
     * 输入内容不能大于最大长度
     * @param max 最大长度
     * @return Scheme
     */
    public static Scheme MaxLength(final int max) {
        return new Scheme(new MaxLengthVerifier(max)).msg("输入内容最多" + max + "个字符");
    }

    /**
     * 输入内容在长度范围内
     * @param min 最小长度
     * @param max 最大长度
     * @return Scheme
     */
    public static Scheme RangeLength(final int min, final int max) {
        return new Scheme(new RangeLengthVerifier(min, max)).msg("输入内容字符数量必须在[" + min + "," + max + "]之间");
    }

    /**
     * 输入数值不能小于最小值
     * @param min 最小值
     * @return Scheme
     */
    public static Scheme MinValue(final int min) {
        return ABTest(new MinValueBridge(min)).msg("输入数值最小为：" + min);
    }

    /**
     * 输入数值不能小于最小值
     * @param min 最小值
     * @return Scheme
     */
    public static Scheme MinValue(final long min) {
        return ABTest(new MinValueBridge(min)).msg("输入数值最小为：" + min);
    }

    /**
     * 输入数值不能小于最小值
     * @param min 最小值
     * @return Scheme
     */
    public static Scheme MinValue(final float min) {
        return ABTest(new MinValueBridge(min)).msg("输入数值最小为：" + min);
    }

    /**
     * 输入数值不能小于最小值
     * @param min 最小值
     * @return Scheme
     */
    public static Scheme MinValue(final double min) {
        return ABTest(new MinValueBridge(min)).msg("输入数值最小为：" + min);
    }

    /**
     * 输入数值不能大于最大值
     * @param max 最大值
     * @return Scheme
     */
    public static Scheme MaxValue(final int max) {
        return ABTest(new MaxValueBridge(max)).msg("输入数值最大为：" + max);
    }

    /**
     * 输入数值不能大于最大值
     * @param max 最大值
     * @return Scheme
     */
    public static Scheme MaxValue(final long max) {
        return ABTest(new MaxValueBridge(max)).msg("输入数值最大为：" + max);
    }

    /**
     * 输入数值不能大于最大值
     * @param max 最大值
     * @return Scheme
     */
    public static Scheme MaxValue(final float max) {
        return ABTest(new MaxValueBridge(max)).msg("输入数值最大为：" + max);
    }

    /**
     * 输入数值不能大于最大值
     * @param max 最大值
     * @return Scheme
     */
    public static Scheme MaxValue(final double max) {
        return ABTest(new MaxValueBridge(max)).msg("输入数值最大为：" + max);
    }

    /**
     * 输入数值必须在最值区间
     * @param min 最小值
     * @param max 最大值
     * @return Scheme
     */
    public static Scheme RangeValue(final int min, final int max) {
        return ABTest(new RangeValueBridge(min, max)).msg("输入数值大小必须在[" + min + "," + max + "]之间");
    }

    /**
     * 输入数值必须在最值区间
     * @param min 最小值
     * @param max 最大值
     * @return Scheme
     */
    public static Scheme RangeValue(final long min, final long max) {
        return ABTest(new RangeValueBridge(min, max)).msg("输入数值大小必须在[" + min + "," + max + "]之间");
    }

    /**
     * 输入数值必须在最值区间
     * @param min 最小值
     * @param max 最大值
     * @return Scheme
     */
    public static Scheme RangeValue(final float min, final float max) {
        return ABTest(new RangeValueBridge(min, max)).msg("输入数值大小必须在[" + min + "," + max + "]之间");
    }

    /**
     * 输入数值必须在最值区间
     * @param min 最小值
     * @param max 最大值
     * @return Scheme
     */
    public static Scheme RangeValue(final double min, final double max) {
        return ABTest(new RangeValueBridge(min, max)).msg("输入数值大小必须在[" + min + "," + max + "]之间");
    }

    /**
     * 输入内容与加载器的内容相同
     * @param lazyLoader 加载器
     * @return Scheme
     */
    public static Scheme EqualsTo(final LazyLoader<String> lazyLoader){
        return ABTest(new EqualsBridge(lazyLoader)).msg("输入内容与要求不一致");
    }

    /**
     * 输入内容必须与指定内容相同
     * @param fixedValue 指定内容
     * @return Scheme
     */
    public static Scheme EqualsTo(final String fixedValue) {
        return EqualsTo(new LazyLoader<String>() {
            @Override
            public String getValue() {
                return fixedValue;
            }
        });
    }

    /**
     * 输入内容必须与加载器的内容不相同
     * @param lazyLoader 加载器
     * @return Scheme
     */
    public static Scheme NotEquals(final LazyLoader<String> lazyLoader){
        return ABTest(new NotEqualsBridge(lazyLoader)).msg("输入内容不能与要求的相同");
    }

    /**
     * 输入内容必须与指定内容不相同
     * @param fixedValue 指定内容
     * @return Scheme
     */
    public static Scheme NotEquals(final String fixedValue) {
        return NotEquals(new LazyLoader<String>() {
            @Override
            public String getValue() {
                return fixedValue;
            }
        });
    }

    public static <T> Scheme ABTest(final ABBridge<T> bridge) {
        return new Scheme(new BridgeVerifier<T>(bridge));
    }

}
