package com.github.yoojia.inputs;

/**
 * @author 陈小锅 (yoojia.chen@gmail.com)
 */
final class VerifierMeta {

    public final Input input;
    public final Scheme[] schemes;

    public VerifierMeta(Input input, Scheme[] schemes) {
        this.input = input;
        this.schemes = schemes;
    }
}
