package au.com.vincentbai.springcloud.alibaba.myHandler;

import au.com.vincentbai.springcloud.entities.CommonResult;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author vincentbai
 * @date 24/01/2024 10:56 am
 * @description Life is a journey, learn to enjoy the ride. ❤️
 */

public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(4444, "按客戶自定义,global handlerException----1");
    }


    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(4444, "按客戶自定义,global handlerException----2");
    }
}
