package cn.melinkr.kfrouteWeb.business.m1310.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.melinkr.kfrouteWeb.business.m1310.dao.M1310Dao;
import cn.melinkr.kfrouteWeb.business.m1310.service.M1310Service;
import cn.melinkr.platform.kfroute.RouterMsg;
import cn.melinkr.platform.kfroute.RouterMsgHis;

import com.melinkr.micro.auth.entity.ChnGroupMsg;
import com.melinkr.micro.exception.ServiceException;

/**
 * 
 * @author zhangyl at 2015-08-05 13:43 设备导入模块文件入库操作
 * 
 */

public class M1310ServiceImpl implements M1310Service {

	private M1310Dao m1310Dao;

	@Override
	public boolean doDeviceFileImp(String fileName,String opNote) throws ServiceException{
		// TODO Auto-generated method stub
		FileInputStream input = null;
		boolean imp_flag = false;
		try {
			String fileExt = fileName
				.substring(fileName.lastIndexOf("."));
			String checkFileName = fileName.substring(0, fileName.lastIndexOf(fileExt));
			checkFileName = checkFileName + "_Check"+fileExt;
			// System.out.println(checkFileName);
			File checkFile = new File(checkFileName);
			if(!checkFile.exists()){
				throw new ServiceException("131003~导入文件不存在");
			}
			try {
				input = new FileInputStream(checkFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ServiceException("131003~导入文件不存在");
			}
			//HSSFWorkbook book = new HSSFWorkbook(input);
			XSSFWorkbook book;
			try {
				book = new XSSFWorkbook(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new ServiceException("131003~导入文件解析错误");
			}

			XSSFSheet sheet = book.getSheetAt(0);
			XSSFRow titleRow = sheet.getRow(0);
			int cellNum = titleRow.getLastCellNum();
			System.out.println(cellNum);
			
			imp_flag = impDeviceFileToDb(sheet,opNote);

		} finally {
			try {
				input.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return imp_flag;
	}
	public boolean impDeviceFileToDb(XSSFSheet sheet,String opNote) throws ServiceException{
		int rowsnum = sheet.getLastRowNum();
		List<RouterMsg> impRecords = new ArrayList<RouterMsg>();// 记录返回的结果记录

		Random random = new Random();
		String createAccept = new SimpleDateFormat("yyyyMMddHHmmssSSS").format((new Date()))+random.nextInt(4);
		for (int i = 1; i <= rowsnum; i++) {
			XSSFRow row = sheet.getRow(i);
			if (row == null) {
				continue;// 遇到空行继续解析,impRecords中不增加记录
			}
			String flag = row.getCell((short) 11) == null ? "" : row.getCell(
					(short) 11).getStringCellValue();//导入状态
			if(flag==null||!flag.trim().equals("Y")){
				continue;
			}
			String mac = row.getCell(0) == null ? "" : row
					.getCell(0).getStringCellValue();
			String modelType = row.getCell((short) 1) == null ? "" : row
					.getCell((short) 1).getStringCellValue();//设备型号
			String version = row.getCell((short) 2) == null ? "" : row
					.getCell((short) 2).getStringCellValue();//系统版本
			String chipModel = row.getCell((short) 3) == null ? "" : row
					.getCell((short) 3).getStringCellValue();//芯片型号
			String basicFreq = row.getCell((short) 4) == null ? "" : row
					.getCell((short) 4).getStringCellValue();//主频
			String ram = row.getCell((short) 5) == null ? "" : row
					.getCell((short) 5).getStringCellValue();//内存
			String flash = row.getCell((short) 6) == null ? "" : row
					.getCell((short) 6).getStringCellValue();//falsh
			String brandName = row.getCell((short) 7) == null ? "" : row
					.getCell((short) 7).getStringCellValue();//品牌名称
			String belongType = row.getCell((short) 8) == null ? "" : row
					.getCell((short) 8).getStringCellValue();// 归属类型
			String groupName = row.getCell((short) 9) == null ? "" : row
					.getCell((short) 9).getStringCellValue();// 代理商名称
			String note = row.getCell((short) 10) == null ? "" : row.getCell(
					(short) 10).getStringCellValue();//备注
			ChnGroupMsg chnGroupMsg=null;
			try {
				chnGroupMsg = m1310Dao.findGroupIdByName(groupName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ServiceException("渠道信息查询失败"+e.getMessage());
			}
			String groupId =null;
			if(chnGroupMsg==null){
				groupId = null;
			}else{
				groupId = chnGroupMsg.getGroupId();
			}
			 
			RouterMsg routerMsg = new RouterMsg();
			routerMsg.setMac(mac);
			routerMsg.setModelType(modelType);
			routerMsg.setVersion(version);
			//routerMsg.setSysStatus(0);
			routerMsg.setChipModel(chipModel);
			routerMsg.setBasicFreq(basicFreq);
			routerMsg.setRam(ram);
			routerMsg.setFlash(flash);
			routerMsg.setBrandName(brandName);
			routerMsg.setGroupId(groupId);
			routerMsg.setBelongType(belongType.equals("代理商渠道")?2:1);
			routerMsg.setCreateTimestamp(new Timestamp(System.currentTimeMillis()));
			routerMsg.setCreateAccept(createAccept);
			routerMsg.setNote(note);
			routerMsg.setOpNote(opNote);
			impRecords.add(routerMsg);
		}
		
		try {
			return m1310Dao.impDeviceFileToDb(impRecords);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("131007~批量数据入库失败"+e.getMessage());
		}
	}
	public M1310Dao getM1310Dao() {
		return m1310Dao;
	}
	public void setM1310Dao(M1310Dao m1310Dao) {
		this.m1310Dao = m1310Dao;
	}
	@Override
	public Map queryDevice(Map queryMap, Integer page,
			Integer pageSize) throws ServiceException{
		// TODO Auto-generated method stub
		try {
			return m1310Dao.queryDevice(queryMap, page, pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("131008~设备数据查询失败"+e.getMessage());
		}
	}
	@Override
	public boolean addDevice(RouterMsg routerMsg) throws ServiceException{
		// TODO Auto-generated method stub
		Random random = new Random();
		String createAccept = new SimpleDateFormat("yyyyMMddHHmmssSSS").format((new Date()))+random.nextInt(4);
		if(routerMsg.getBelongType()!=2){//非代理商渠道，代理商值设置为空
			routerMsg.setGroupId(null);
		}
		routerMsg.setCreateAccept(createAccept);
		try {
			return m1310Dao.addDevice(routerMsg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("131009~设备信息数据入库失败"+e.getMessage());
		}
	}
	@Override
	public RouterMsg queryDevice(RouterMsg routerMsg) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return m1310Dao.queryDevice(routerMsg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("131008~设备数据查询失败"+e.getMessage());
		}
	}
	@Override
	public boolean updateDevice(RouterMsg routerMsg) throws ServiceException {
		// TODO Auto-generated method stub

		if(routerMsg.getBelongType()!=2){//非代理商渠道，代理商值设置为空
			routerMsg.setGroupId("00");
		}
		try {
			return m1310Dao.updateDevice(routerMsg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("131009~设备信息数据入库失败"+e.getMessage());
		}
	}
	@Override
	public boolean addDeviceHis(RouterMsgHis routerMsgHis)
			throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return m1310Dao.addDeviceHis(routerMsgHis);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("131009~设备历史信息数据入库失败"+e.getMessage());
		}
	}
	@Override
	public boolean addDeviceBatchHis(List<RouterMsgHis> routerMsgHisList)
			throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return m1310Dao.addDeviceBatchHis(routerMsgHisList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("131009~设备历史信息数据入库失败"+e.getMessage());
		}
	}
	@Override
	public boolean addDeviceHisByMap(Map addParamMap) throws ServiceException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try {
			return m1310Dao.addDeviceHisByMap(addParamMap);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("131009~设备历史信息数据入库失败"+e.getMessage());
		}
	}
	@Override
	public boolean addDeviceHisByIdList(Map addParamMap) throws ServiceException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try {
			return m1310Dao.addDeviceHisByIdList(addParamMap);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("131009~设备历史信息数据入库失败"+e.getMessage());
		}
	}
	@Override
	public boolean addDeviceBatchHisByMap(List<Map> addParamsList)
			throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return m1310Dao.addDeviceBatchHisByMap(addParamsList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("131009~设备历史信息数据入库失败"+e.getMessage());
		}
	}
	@Override
	public boolean delDevice(RouterMsg routerMsg) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return m1310Dao.delDevice(routerMsg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("131010~设备数据删除失败"+e.getMessage());
		}
	}
	@Override
	public Map queryDeviceByIdList(Map queryMap)
			throws ServiceException {
		// TODO Auto-generated method stub
		try {
			List<RouterMsg> list = m1310Dao.queryDeviceByIdList(queryMap);
			String macList = "";
			for(RouterMsg routerMsg:list){
				macList = macList+routerMsg.getMac()+"<br/>";
			}
			Map returnMap = new HashMap();
			returnMap.put("macList", macList);
			returnMap.put("routerMsg", list.get(0));//取第一个对象用于填充界面
			return returnMap;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("131010~设备数据删除失败"+e.getMessage());
		}
	}
	@Override
	public boolean updateBatchDevice(Map updateParam)
			throws ServiceException {
		// TODO Auto-generated method stub
		
		try {
			return m1310Dao.updateBatchDevice(updateParam);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("131009~设备信息数据修改失败"+e.getMessage());
		}
	}
	
	
}
