package xin.nbjzj.datatrans.entity.response;

import lombok.Data;

@Data
public class ApiRespEntity {
	private String code;
	private String msg;
	private int dataCount;
	private String datas;
}
