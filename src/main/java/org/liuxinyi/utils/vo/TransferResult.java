package org.liuxinyi.utils.vo;

import lombok.*;

/**
 * Created by Eric.Liu on 2016/11/10.
 */
@Getter
@Setter
@ToString
public class TransferResult {
    private boolean result;
    private FileVo fileVo;
    private String message;

    public TransferResult(FileVo fileVo) {
        this.result = true;
        this.fileVo = fileVo;
    }

    public TransferResult(FileVo fileVo, String message) {
        this.result = false;
        this.fileVo = fileVo;
        this.message = message;
    }

}
