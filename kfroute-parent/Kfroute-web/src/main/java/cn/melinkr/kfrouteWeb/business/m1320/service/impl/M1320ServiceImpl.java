package cn.melinkr.kfrouteWeb.business.m1320.service.impl;

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

import cn.melinkr.kfrouteWeb.business.m1320.dao.M1320Dao;
import cn.melinkr.kfrouteWeb.business.m1320.service.M1320Service;
import cn.melinkr.platform.kfroute.ServerBelongGroup;
import cn.melinkr.platform.kfroute.SourceServerMsg;
import cn.melinkr.platform.kfroute.SourceServerMsgHis;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.melinkr.micro.exception.ServiceException;
import com.melinkr.micro.geoip.entity.GeoIpLocation;
import com.melinkr.micro.util.GeoIpUtil;

/**
 * 
 * @author zhangyl at 2015-08-05 13:43 设备导入模块文件入库操作
 * 
 */

public class M1320ServiceImpl implements M1320Service {

	private M1320Dao m1320Dao;

	@Override
	public boolean doServerFileImp(String fileName,String opNote) throws ServiceException {
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
				throw new ServiceException("132001~导入文件不存在");
			}
			try {
				input = new FileInputStream(checkFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ServiceException("132001~导入文件不存在");
			}
			//HSSFWorkbook book = new HSSFWorkbook(input);
			XSSFWorkbook book=null;
			try {
				book = new XSSFWorkbook(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ServiceException("132007~导入文件解析错误");
			}

			XSSFSheet sheet = book.getSheetAt(0);
			XSSFRow titleRow = sheet.getRow(0);
			int cellNum = titleRow.getLastCellNum();
			System.out.println(cellNum);
			
			imp_flag = impServerFileToDb(sheet,opNote);

		} finally {
			try {
				input.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return imp_flag;
	}
	public boolean impServerFileToDb(XSSFSheet sheet,String opNote) throws ServiceException{
		int rowsnum = sheet.getLastRowNum();
		List<SourceServerMsg> impRecords = new ArrayList<SourceServerMsg>();// 记录返回的结果记录

		Random random = new Random();
		String createAccept = new SimpleDateFormat("yyyyMMddHHmmssSSS").format((new Date()))+random.nextInt(4);
		Map serverBelongGroupMap  = new HashMap();
		List<ServerBelongGroup> belongGroupList = null;
		try {
			belongGroupList = m1320Dao.queryBelongGroup();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(ServerBelongGroup belongGroup : belongGroupList){
			serverBelongGroupMap.put(belongGroup.getGroupName(), belongGroup.getGroupId());
		}
		
		for (int i = 1; i <= rowsnum; i++) {
			XSSFRow row = sheet.getRow(i);
			if (row == null) {
				continue;// 遇到空行继续解析,impRecords中不增加记录
			}
			String flag = row.getCell((short) 14) == null ? "" : row.getCell(
					(short) 14).getStringCellValue();//导入状态
			if(flag==null||!flag.trim().equals("Y")){
				continue;
			}
			String serverName = row.getCell(0) == null ? "" : row
					.getCell(0).getStringCellValue();//服务器名称
			String ipAddress = row.getCell((short) 1) == null ? "" : row
					.getCell((short) 1).getStringCellValue();//IP地址
			String operSystem = row.getCell((short) 2) == null ? "" : row
					.getCell((short) 2).getStringCellValue();//操作系统
			String sysKernel = row.getCell((short) 3) == null ? "" : row
					.getCell((short) 3).getStringCellValue();//内核版本
			String fileHandles = row.getCell((short) 4) == null ? "" : row
					.getCell((short) 4).getStringCellValue();//文件系统
			String cpuModel = row.getCell((short) 5) == null ? "" : row
					.getCell((short) 5).getStringCellValue();//CPU型号
			String cpuFreq = row.getCell((short) 6) == null ? "" : row
					.getCell((short) 6).getStringCellValue();//主频
			String centerName = row.getCell((short) 7) == null ? "" : row
					.getCell((short) 7).getStringCellValue();//机房
			String operator = row.getCell((short) 8) == null ? "" : row.getCell(
					(short) 8).getStringCellValue();//运营商
			String location = row.getCell((short) 9) == null ? "" : row
					.getCell((short) 9).getStringCellValue();//位置
			String bandWidth = row.getCell((short) 10) == null ? "" : row
					.getCell((short) 10).getStringCellValue();//流量限值
			String rate = row.getCell((short) 11) == null ? "" : row
					.getCell((short) 11).getStringCellValue();//费用
			String groupName = row.getCell((short) 12) == null ? "" : row
					.getCell((short) 12).getStringCellValue();//费用
			String note = row.getCell((short) 13) == null ? "" : row.getCell(
					(short) 13).getStringCellValue();//备注
			SourceServerMsg sourceServerMsg = new SourceServerMsg();
			sourceServerMsg.setSysStatus(0);
			sourceServerMsg.setBelongGroup(serverBelongGroupMap.get(groupName.trim())==null?0:Integer.parseInt((String)serverBelongGroupMap.get(groupName.trim())));
			sourceServerMsg.setServerName(serverName);
			sourceServerMsg.setIpAddress(ipAddress);
			sourceServerMsg.setOperSystem(operSystem);
			sourceServerMsg.setSysKernel(sysKernel);
			sourceServerMsg.setFileHandles(fileHandles);
			sourceServerMsg.setCpuModel(cpuModel);
			sourceServerMsg.setCpuFreq(cpuFreq);
			sourceServerMsg.setCenterName(centerName);
			sourceServerMsg.setOperator(operator);
			sourceServerMsg.setLocation(location);
			sourceServerMsg.setBandWidth(bandWidth);
			sourceServerMsg.setRate(rate);
			sourceServerMsg.setNote(note);
			sourceServerMsg.setOpNote(opNote);
			sourceServerMsg.setCreateTimestamp(new Timestamp(System.currentTimeMillis()));
			sourceServerMsg.setCreateAccept(createAccept);
			GeoIpLocation geoIpLocation=null;
			try {
				geoIpLocation = GeoIpUtil.getLocationBean(ipAddress);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GeoIp2Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sourceServerMsg.setLatitude(geoIpLocation.getLocation().getLatitude());
			sourceServerMsg.setLongitude(geoIpLocation.getLocation().getLongitude());
			sourceServerMsg.setCityName(geoIpLocation.getCity().getNames().get("zh-CN")==null?geoIpLocation.getCity().getName():geoIpLocation.getCity().getNames().get("zh-CN"));
			
			impRecords.add(sourceServerMsg);
		}
		
		try {
			return m1320Dao.impServerFileToDb(impRecords);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("132008~服务器信息批量入库失败！"+e.getMessage());
		}
	}
	public M1320Dao getm1320Dao() {
		return m1320Dao;
	}
	public void setm1320Dao(M1320Dao m1320Dao) {
		this.m1320Dao = m1320Dao;
	}
	@Override
	public Map queryServer(Map queryMap, Integer page,
			Integer pageSize) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return m1320Dao.queryServer(queryMap, page, pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("132009~服务器信息查询失败！"+e.getMessage());
		}
	}
	@Override
	public Map queryServerPortDetail(Map queryMap, Integer page,
			Integer pageSize) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return m1320Dao.queryServerPortDetail(queryMap, page, pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("132010~服务器端口信息查询失败！"+e.getMessage());
		}
	}
	@Override
	public boolean addServer(SourceServerMsg sourceServerMsg) throws ServiceException{
		// TODO Auto-generated method stub
		Random random = new Random();
		String createAccept = new SimpleDateFormat("yyyyMMddHHmmssSSS").format((new Date()))+random.nextInt(4);
		
		sourceServerMsg.setCreateAccept(createAccept);
		GeoIpLocation geoIpLocation=null;
		try {
			geoIpLocation = GeoIpUtil.getLocationBean(sourceServerMsg.getIpAddress());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeoIp2Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sourceServerMsg.setLatitude(geoIpLocation.getLocation().getLatitude());
		sourceServerMsg.setLongitude(geoIpLocation.getLocation().getLongitude());
		try {
			return m1320Dao.addServer(sourceServerMsg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("132009~服务器信息数据入库失败"+e.getMessage());
		}
	}
	
	@Override
	public SourceServerMsg queryServer(SourceServerMsg sourceServerMsg) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return m1320Dao.queryServer(sourceServerMsg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("132008~服务器数据查询失败"+e.getMessage());
		}
	}
	@Override
	public boolean updateServer(SourceServerMsg sourceServerMsg) throws ServiceException {
		// TODO Auto-generated method stub

		
		try {
			return m1320Dao.updateServer(sourceServerMsg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("131009~服务器信息数据入库失败"+e.getMessage());
		}
	}
	@Override
	public boolean addServerHis(SourceServerMsgHis serverMsgHis)
			throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return m1320Dao.addServerHis(serverMsgHis);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("132009~服务器历史信息数据入库失败"+e.getMessage());
		}
	}
	@Override
	public boolean addServerHisByMap(Map addParamMap) throws ServiceException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try {
			return m1320Dao.addServerHisByMap(addParamMap);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("132009~服务器历史信息数据入库失败"+e.getMessage());
		}
	}
	@Override
	public boolean delServer(SourceServerMsg sourceServerMsg) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return m1320Dao.delServer(sourceServerMsg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("132010~服务器数据删除失败"+e.getMessage());
		}
	}
}
