package cn.kfroute.platform.payment.server.service.impl;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import cn.kfroute.platform.payment.server.service.inf.Service;
import cn.seocoo.platform.unite.Reslut;
import cn.seocoo.platform.wifi.FileReq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.odchina.micro.util.StringUtil;

public class WriteBackFileServiceImpl implements Service{
	private static final Logger logger = Logger.getLogger(WriteBackFileServiceImpl.class);
	@Override
	public Object sevice(String param) {
		// TODO Auto-generated method stub
		logger.info("param:"+param);
		Reslut reslut=new Reslut();
		List<FileReq> fileList=JSONObject.parseArray(param, FileReq.class);
		if(fileList==null||fileList.size()==0){
			reslut.setResultCode("FAIL");
			reslut.setResultMsg("param error!");
		}
		//判断文件是否存在
		File file=new File(fileList.get(0).getFilePath());
		if(!file.exists()){
			reslut.setResultCode("FAIL");
			reslut.setResultMsg("file is not exsist!");
		}
		if(StringUtil.isEmpty(reslut.getResultCode())){
			return file;
		}
		
		
		String msg=JSON.toJSONString(reslut);
		return msg;
	}

}
