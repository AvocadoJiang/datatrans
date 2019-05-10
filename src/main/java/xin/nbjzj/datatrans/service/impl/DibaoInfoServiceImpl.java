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
import xin.nbjzj.datatrans.dao.DibaoInfoDao;
import xin.nbjzj.datatrans.dao.PatientInfoDao;
import xin.nbjzj.datatrans.dao.ProgressLogDao;
import xin.nbjzj.datatrans.entity.DibaoInfoEntity;
import xin.nbjzj.datatrans.entity.PatientInforEntity;
import xin.nbjzj.datatrans.entity.ProgressLogEntity;
import xin.nbjzj.datatrans.entity.response.ApiRespEntity;
import xin.nbjzj.datatrans.service.DibaoInfoService;
import xin.nbjzj.datatrans.utils.IdCardUtils;
import xin.nbjzj.datatrans.utils.OkHttpUtils;


@Service("DibaoInfoService")
public class DibaoInfoServiceImpl implements DibaoInfoService {

	protected static final Logger logger = LoggerFactory.getLogger(DibaoInfoServiceImpl.class);
	@Autowired
	PatientInfoDao patientInfoDao;
	@Autowired
	DibaoInfoDao dibaoInfoDao;
	@Autowired
	ProgressLogDao progressLogDao;
	
	@Override
	public void getData(PatientInforEntity entity) throws IOException {
		boolean flag = false;
		String IdCard = entity.getZjhm();
		Response response = OkHttpUtils.DibaoInfoApi(IdCard);
    	String json = response.body().string();
    	logger.info("_______________dibaoInfojson________"+json);
    	System.out.println("dibaoInfojson"+json);
    	ApiRespEntity apiEntity = JSON.parseObject(json,ApiRespEntity.class);
    	if(apiEntity!=null && apiEntity.getDatas()!=null&&!apiEntity.getDatas().equals("[]")&&apiEntity.getCode().equals("00")) {
    		List<DibaoInfoEntity> dibaoInfolist = JSONArray.parseArray(apiEntity.getDatas(), DibaoInfoEntity.class);
    		//3.将每个人的数据存储到TB_ST_GAGRXX表中
    		for (DibaoInfoEntity dibaoInfoEntity : dibaoInfolist) {
    			logger.info("_______________dibaoInfoEntity________"+dibaoInfoEntity.toString());
				dibaoInfoDao.insert(dibaoInfoEntity);
			}
    		flag = true;
    	}
		//检验response并将response转换成entity
		ProgressLogEntity logEntity = new ProgressLogEntity();
		logEntity.setBm(DibaoInfoDao.TABLE_NAME);
		logEntity.setZjhm(IdCard);
		logEntity.setGxsj(new Date());
		if(flag) {
			//成功日志
			logEntity.setCgbs("1");
			logger.info(IdCard+"低保信息录入成功!!");
			System.out.println(IdCard+"低保信息录入成功!!");
		}else {
			//失败日志
			logEntity.setCgbs("2");
			logger.info(IdCard+"低保信息录入失败,共失败!");
			System.out.println(IdCard+"低保信息录入失败,共失败!");
		}
		//4.向TB_ST_ZJB增加一条日志,更新TB_YL_PATIENT_INFORMATION表中的TB_ST_GAGRXX字段
		progressLogDao.insert(logEntity);
	}

}
