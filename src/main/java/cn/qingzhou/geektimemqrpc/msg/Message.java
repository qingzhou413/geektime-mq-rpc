package cn.qingzhou.geektimemqrpc.msg;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 消息
 * @author qingzhou
 * 2019-08-24
 */
@Data
@AllArgsConstructor
public class Message implements Serializable {

    private static final long serialVersionUID = 325123123738581347L;


    private String msg;
}
