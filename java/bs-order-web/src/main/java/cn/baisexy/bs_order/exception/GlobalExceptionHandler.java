package cn.baisexy.bs_order.exception;


import cn.baisexy.bs_order.api.pojo.send.ReturnCode;
import cn.baisexy.bs_order.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

// 全局异常处理器
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理所有RuntimeException异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler
    public ResultUtil allException (RuntimeException e) {
       logger.error(e.getMessage());
        System.out.println(e.getMessage());
        return new ResultUtil(ReturnCode.DEFAULT_EXCEPTION,"错误原因:"+e.getMessage());
    }

    /**
     * 处理所有Exception异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler({Exception.class})
    public ResultUtil allException(Exception e) {
          e.printStackTrace();
         logger.error(e.getMessage());
        return new ResultUtil(ReturnCode.DEFAULT_EXCEPTION,"错误原因:"+e.getMessage());
    }
}
