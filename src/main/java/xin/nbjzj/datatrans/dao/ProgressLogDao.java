package xin.nbjzj.datatrans.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import xin.nbjzj.datatrans.entity.PatientInforEntity;
import xin.nbjzj.datatrans.entity.ProgressLogEntity;

@Mapper
public interface ProgressLogDao {
	
	@Select("select count(1) from TB_ST_ZJB")
    public int findAll();
	@Insert("insert into TB_ST_ZJB (ZJHM,GXSJ,CGBS,BM) values (#{zjhm},#{gxsj},#{cgbs},#{bm})")
	public int insert(ProgressLogEntity entity);
	
	@Select("select count(1) from TB_ST_ZJB where BM = #{bm}")
    public int countByBM(String bm);
}
