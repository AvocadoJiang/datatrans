package xin.nbjzj.datatrans.entity;


import java.util.Date;

import lombok.Data;
/*
 * Welcome to use the TableGo Tools.
 * 
 * http://vipbooks.iteye.com
 * http://blog.csdn.net/vipbooks
 * http://www.cnblogs.com/vipbooks
 * 
 * Author:bianj
 * Email:edinsker@163.com
 * Version:5.8.0
 */

/**
 * 数据获取进度日志
 * 
 * @author bianj
 * @version 1.0.0 2019-02-27
 */
@Data
public class ProgressLogEntity implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 5033330835098137560L;

    /** 证件号码 */
    private String zjhm;

    /** 时间戳 */
    private Date gxsj;

    /** 是否成功 */
    private String cgbs;

    /** 表名 */
    private String bm;
    
}