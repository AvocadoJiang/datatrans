package xin.nbjzj.datatrans.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import okhttp3.Response;
import xin.nbjzj.datatrans.dao.DisabledInfoDao;
import xin.nbjzj.datatrans.dao.PatientInfoDao;
import xin.nbjzj.datatrans.dao.ProgressLogDao;
import xin.nbjzj.datatrans.entity.DisabledInfoEntity;
import xin.nbjzj.datatrans.entity.PatientInforEntity;
import xin.nbjzj.datatrans.entity.ProgressLogEntity;
import xin.nbjzj.datatrans.entity.response.ApiRespEntity;
import xin.nbjzj.datatrans.service.DisabledInfoService;
import xin.nbjzj.datatrans.utils.IdCardUtils;
import xin.nbjzj.datatrans.utils.OkHttpUtils;

@Service("DisabledInfoService")
public class DisabledInfoServiceImpl implements DisabledInfoService{

	protected static final Logger logger = LoggerFactory.getLogger(DisabledInfoServiceImpl.class);
	@Autowired
	PatientInfoDao patientInfoDao;
	@Autowired
	DisabledInfoDao disabledInfoDao;
	@Autowired
	ProgressLogDao progressLogDao;
	
	@Override
	public void getData(PatientInforEntity entity) throws IOException {
		boolean flag = false;
		String IdCard = entity.getZjhm();
		Response response = OkHttpUtils.DisabledInfoApi(IdCard);
    	String json = response.body().string();
    	logger.info("_______________disabledInfojson________"+json);
    	System.out.println("disabledInfojson"+json);
    	ApiRespEntity apiEntity = JSON.parseObject(json,ApiRespEntity.class);
    	if(apiEntity!=null && apiEntity.getDatas()!=null&&!apiEntity.getDatas().equals("[]")&&apiEntity.getCode().equals("00")) {
    		List<DisabledInfoEntity> disabledInfoList = JSONArray.parseArray(apiEntity.getDatas(), DisabledInfoEntity.class);
    		//3.将每个人的数据存储到TB_ST_CJRXX表中
    		for (DisabledInfoEntity disabledInfoEntity : disabledInfoList) {
    			logger.info("_______________disabledInfojson________"+disabledInfoEntity.toString());
    			disabledInfoDao.insert(disabledInfoEntity);
			}
    		flag = true;
    	}
		//检验response并将response转换成entity
		ProgressLogEntity logEntity = new ProgressLogEntity();
		logEntity.setBm(DisabledInfoDao.TABLE_NAME);
		logEntity.setZjhm(IdCard);
		logEntity.setGxsj(new Date());
		if(flag) {
			//成功日志
			logEntity.setCgbs("1");
			logger.info(IdCard+"残疾人信息录入成功!!");
			System.out.println(IdCard+"残疾人信息录入成功!!");
		}else {
			//失败日志
			logEntity.setCgbs("2");
			logger.info(IdCard+"残疾人信息录入失败,共失败!");
			System.out.println(IdCard+"残疾人信息录入失败,共失败!");
		}
		//4.向TB_ST_ZJB增加一条日志,更新TB_YL_PATIENT_INFORMATION表中的TB_ST_GAGRXX字段
		progressLogDao.insert(logEntity);
	}

}
