package xin.nbjzj.datatrans.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import xin.nbjzj.datatrans.entity.PatientInforEntity;


@Mapper
public interface PatientInfoDao {
	
	
	@Select("select * from TB_YL_PATIENT_INFORMATION where rownum <= #{start}+#{num} minus select * from TB_YL_PATIENT_INFORMATION where rownum <= #{start}")
    public List<PatientInforEntity> findAll(@Param("start")int start,@Param("num")int num);
	
	
}
