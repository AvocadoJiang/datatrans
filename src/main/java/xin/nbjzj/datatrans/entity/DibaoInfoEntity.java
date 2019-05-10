package xin.nbjzj.datatrans.entity;

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
 * 低保救助信息
 * 
 * @author bianj
 * @version 1.0.0 2019-02-27
 */
@Data
public class DibaoInfoEntity implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -4199454820331515049L;

    /** dbHzsfz */
    private String dbHzsfz;

    /** dbId */
    private String dbId;

    /** dbXb */
    private String dbXb;

    /** dbSjjzd */
    private String dbSjjzd;

    /** dbJthk */
    private String dbJthk;

    /** dbHkszd */
    private String dbHkszd;

    /** dbZpyy */
    private String dbZpyy;

    /** dbHzxm */
    private String dbHzxm;

}