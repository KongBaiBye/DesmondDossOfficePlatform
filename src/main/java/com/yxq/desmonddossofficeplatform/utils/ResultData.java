package com.yxq.desmonddossofficeplatform.utils;

/**
 * LayUI 解析数据要求的格式
 *      {
 *      "code": 0,
 *      "msg": "",
 *      "count": 1000,
 *      "data": [{}, {}]
 *      }
 *
 * ResultData LayUI 数据模版对象 ==> JSON ==> LayUI
 *
 * @author Anonymous 2022/12/17 11:20
 */
public class ResultData {
    /**
     * code 是 LayUI 标记数据是否正确的形式
     * 0 表示数据成功，其他都是非法
     */
    private Integer code;
    /**
     * 数据信息，可以为空字符串，标记 "成功" "success"
     */
    private String msg;
    /**
     * count 是数据数量
     */
    private Integer count;
    /**
     * 提交的数据内容，Object 类型，支持任何类型模式
     * 包括 Bean BeanList Map MapList Array ArrayList
     * 【墙裂推荐】 Map MapList
     */
    private Object data;

    public ResultData() {
    }

    public static ResultData success() {
        return success(0, "成功", null, null);
    }

    public static ResultData success(Integer count, Object data) {
        return success(0, "成功", count, data);
    }

    public static ResultData success(Object data) {
        return success(0, "成功", null, data);
    }

    /**
     * 数据成功执行方法
     *
     * @param code  状态码
     * @param msg   信息字符串
     * @param count 数据数量
     * @param data  数据内容
     * @return ResultData LayUI 解析数据标准模型
     */
    public static ResultData success(Integer code, String msg, Integer count, Object data) {
        ResultData resultData = new ResultData();

        resultData.setCode(code);
        resultData.setMsg(msg);
        resultData.setCount(count);
        resultData.setData(data);

        return resultData;
    }

    /**
     * 失败
     * @return
     */
    public static ResultData fail() {
        ResultData resultData = new ResultData();

        resultData.setCode(-1);
        resultData.setMsg("失败");

        return resultData;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
