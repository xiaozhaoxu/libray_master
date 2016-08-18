package com.github.yoojia.inputs;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * NextInputs
 *
 * @author 陈小锅 (yoojia.chen@gmail.com)
 */
public class NextInputs {

    private static final Comparator<Scheme> ORDERING = new Comparator<Scheme>() {
        @Override
        public int compare(Scheme lhs, Scheme rhs) {
            return lhs.orderPriority - rhs.orderPriority;
        }
    };

    private final ArrayList<VerifierMeta> mVerifiers = new ArrayList<VerifierMeta>();

    private MessageDisplay mMessageDisplay = new MessageDisplay() {
        @Override
        public void show(Input input, String message) {
            Log.d("message",message);
        }
    };

    /**
     * 默认情况下，校验测试失败即停止其它校验
     */
    private boolean mStopIfFail = true;

    /**
     * 执行校验测试，并返回测试结果。
     * @return 校验测试结果是否成功
     */
    public boolean test(){
        VerifierMeta current = null;
        try{
            for (VerifierMeta meta : mVerifiers) {
                current = meta;
                if ( ! performTest(meta) && mStopIfFail) {
                    return false;
                }
            }
            return true;
        }catch (Throwable thr) {
            mMessageDisplay.show(current.input, thr.getMessage());
            return false;
        }
    }

    /**
     * 添加输入条目及测试模式。
     * @param input 输入条目
     * @param schemes 测试模式
     * @return NextInputs
     */
    public NextInputs add(Input input, Scheme... schemes){
        if (schemes == null || schemes.length == 0){
            throw new IllegalArgumentException("Test schemes is required !");
        }
        Arrays.sort(schemes, ORDERING);
        mVerifiers.add(new VerifierMeta(input, schemes));
        return this;
    }

    /**
     * 移除指定Input的校验条目
     * @param input Input对象
     * @return NextInputs
     */
    public NextInputs remove(Input input) {
        final List<VerifierMeta> toRemove = new ArrayList<VerifierMeta>(1);
        for(VerifierMeta meta: mVerifiers) {
            if(meta.input == input) {
                toRemove.add(meta);
            }
        }
        mVerifiers.removeAll(toRemove);
        return this;
    }

    /**
     * 在校验测试遇到失败时，是否停止校验
     * @param stopOnFail 是否停止
     * @return NextInputs
     */
    public NextInputs setStopIfFail(boolean stopOnFail){
        mStopIfFail = stopOnFail;
        return this;
    }

    /**
     * 设置校验测试结果消息显示接口
     * @param display 消息显示接口。
     * @throws NullPointerException 当参数为Null时，抛出异常。
     * @return NextInputs
     */
    public NextInputs setMessageDisplay(MessageDisplay display){
        if (display == null) {
            throw new NullPointerException("MessageDisplay is null !");
        }
        mMessageDisplay = display;
        return this;
    }

    /**
     * 流式API
     * @param input Input对象
     * @return 流式API接口
     */
    @Deprecated
    public Fluent on(Input input) {
        return add(input);
    }

    public Fluent add(Input input) {
        return new Fluent(input, this);
    }

    private boolean performTest(VerifierMeta meta) throws Exception {
        final String value = meta.input.getValue();
        for (Scheme scheme : meta.schemes) {
            if ( ! scheme.verifier.perform(value)) {
                mMessageDisplay.show(meta.input, scheme.message);
                return false;
            }
        }
        return true;
    }

}
