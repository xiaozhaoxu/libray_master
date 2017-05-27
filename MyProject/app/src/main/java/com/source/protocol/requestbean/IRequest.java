package com.source.protocol.requestbean;

/**
 * author: Shawn
 * time  : 2016/10/6 17:54
 */

public interface IRequest {

    /**
     * 在 设置缓存 Key值 时，将 ext 字段设为空串，否则时间戳一直变化，无法缓存
     *
     * @param ext
     */
    void setExt(String ext);
}
