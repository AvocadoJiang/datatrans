package xin.nbjzj.datatrans.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import xin.nbjzj.datatrans.entity.DibaoInfoEntity;
@Mapper
public interface DibaoInfoDao {
	public static String TABLE_NAME = "TB_ST_DBJZXX";
	@Insert("insert into TB_ST_DBJZXX (DB_HZSFZ,DB_ID,DB_XB,DB_SJJZD,DB_JTHK,DB_HKSZD,DB_ZPYY,DB_HZXM) "
			+ "values (#{dbHzsfz},#{dbId},#{dbXb},#{dbSjjzd},#{dbJthk},#{dbHkszd},#{dbZpyy},#{dbHzxm})")
	public int insert(DibaoInfoEntity entity);
}
