package com.dianwoda.test.bassy.common.exception;


import com.dianwoda.test.bassy.common.exception.error.BassyError;

public class BassyException extends BaseRuntimeException {

    public BassyException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public BassyException(String errorCode, String errorMessage, Throwable cause) {
        super(errorCode, errorMessage, cause);
    }

    public BassyException(BassyError systemError) {
        super(systemError.getCode(), systemError.getMessage());
    }

    public BassyException(BassyError systemError, Throwable cause) {
        super(systemError.getCode(), systemError.getMessage(), cause);
    }

}
