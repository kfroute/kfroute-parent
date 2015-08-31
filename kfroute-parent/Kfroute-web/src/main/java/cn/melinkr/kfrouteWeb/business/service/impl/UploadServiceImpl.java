package cn.melinkr.kfrouteWeb.business.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.melinkr.kfrouteWeb.business.dao.UploadDao;
import cn.melinkr.kfrouteWeb.business.service.UploadService;
import cn.melinkr.platform.kfroute.RouterMsg;

import com.melinkr.micro.auth.entity.ChnGroupMsg;
import com.tydic.framework.base.exception.ActionException;

/**
 * 
 * @author zhangyl at 2015-08-04 22:13 文件上传组件校验服务
 * 
 */

public class UploadServiceImpl implements UploadService {

	private UploadDao uploadDao;

	@Override
	public int[] checkM1310UploadFile(String fileName) throws Exception {
		// TODO Auto-generated method stub
		FileInputStream input = null;
		int[] checkResults;
		try {
			input = new FileInputStream(fileName);
			// HSSFWorkbook book = new HSSFWorkbook(input);
			XSSFWorkbook book = new XSSFWorkbook(input);

			XSSFSheet sheet = book.getSheetAt(0);
			XSSFRow titleRow = sheet.getRow(0);
			int cellNum = titleRow.getLastCellNum();
			System.out.println(cellNum);
			if (cellNum != 11) {
				throw new ActionException("导入模板错误，请重新下载模板");

			}
			checkResults = checkDeviceImpSheet(fileName, sheet);

		} catch (ActionException e) {
			e.printStackTrace();
			throw new ActionException(e.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ActionException(ex.getMessage());
		} finally {
			try {
				input.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return checkResults;
	}

	public int[] checkDeviceImpSheet(String filePath, XSSFSheet sheet)
			throws ActionException, SQLException {
		int rowsnum = sheet.getLastRowNum();
		List<String[]> impRecords = new ArrayList<String[]>();// 记录返回的结果记录
		String updateNotes = "";
		String updateFlag = "Y";
		List macList = new ArrayList();// 记录批量导入的mac，用户重复导入项统计
		int imp_success = 0;
		int imp_fail = 0;
		int credit_clear = 0;

		for (int i = 1; i <= rowsnum; i++) {
			XSSFRow row = sheet.getRow(i);
			if (row == null) {
				continue;// 遇到空行继续解析,impRecords中不增加记录
			}
			String mac = row.getCell(0) == null ? "" : row.getCell(0)
					.getStringCellValue();
			String modelType = row.getCell((short) 1) == null ? "" : row
					.getCell((short) 1).getStringCellValue();// 设备型号
			String version = row.getCell((short) 2) == null ? "" : row.getCell(
					(short) 2).getStringCellValue();// 系统版本
			String chipModel = row.getCell((short) 3) == null ? "" : row
					.getCell((short) 3).getStringCellValue();// 芯片型号
			String basicFreq = row.getCell((short) 4) == null ? "" : row
					.getCell((short) 4).getStringCellValue();// 主频
			String ram = row.getCell((short) 5) == null ? "" : row.getCell(
					(short) 5).getStringCellValue();// 内存
			String flash = row.getCell((short) 6) == null ? "" : row.getCell(
					(short) 6).getStringCellValue();// falsh
			String brandName = row.getCell((short) 7) == null ? "" : row
					.getCell((short) 7).getStringCellValue();// 品牌名称
			String belongType = row.getCell((short) 8) == null ? "" : row
					.getCell((short) 8).getStringCellValue();// 归属类型
			String groupName = row.getCell((short) 9) == null ? "" : row
					.getCell((short) 9).getStringCellValue();// 代理商名称
			String note = row.getCell((short) 10) == null ? "" : row.getCell(
					(short) 10).getStringCellValue();// 备注
			if (mac.equals("")) {
				updateNotes = "mac地址为空";
				updateFlag = "N";
				impRecords.add(new String[] { mac, modelType, version,
						chipModel, basicFreq, ram, flash, brandName,
						belongType, groupName, note, updateFlag, updateNotes });
				imp_fail++;
				continue;//
			}
			if (macList.contains(mac)) {
				updateNotes = "该MAC本次导入已有操作，请勿重复执行";
				updateFlag = "N";
				impRecords.add(new String[] { mac, modelType, version,
						chipModel, basicFreq, ram, flash, brandName,
						belongType, groupName, note, updateFlag, updateNotes });
				imp_fail++;
				continue;//
			}
			String reg_exp = "^[0-9 A-Z]{12}$";
			Pattern pattern = Pattern.compile(reg_exp);
			Matcher m = pattern.matcher(mac);
			if (!m.find()) {
				updateNotes = "MAC地址格式错误";
				updateFlag = "N";
				impRecords.add(new String[] { mac, modelType, version,
						chipModel, basicFreq, ram, flash, brandName,
						belongType, groupName, note, updateFlag, updateNotes });
				imp_fail++;
				continue;//
			}
			try {
				Object obj = uploadDao.findRouterMsgByMac(mac);
				if (obj != null) {
					updateNotes = "MAC地址在系统中已经存在";
					updateFlag = "N";
					impRecords.add(new String[] { mac, modelType, version,
							chipModel, basicFreq, ram, flash, brandName,
							belongType, groupName, note, updateFlag,
							updateNotes });
					imp_fail++;
					continue;//
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (modelType.equals("")) {
				updateNotes = "设备型号为空";
				updateFlag = "N";
				impRecords.add(new String[] { mac, modelType, version,
						chipModel, basicFreq, ram, flash, brandName,
						belongType, groupName, note, updateFlag, updateNotes });
				imp_fail++;
				continue;//
			}
			if (version.equals("")) {
				updateNotes = "系统初始版本为空";
				updateFlag = "N";
				impRecords.add(new String[] { mac, modelType, version,
						chipModel, basicFreq, ram, flash, brandName,
						belongType, groupName, note, updateFlag, updateNotes });
				imp_fail++;
				continue;//
			}
			if (chipModel.equals("")) {
				updateNotes = "芯片型号为空";
				updateFlag = "N";
				impRecords.add(new String[] { mac, modelType, version,
						chipModel, basicFreq, ram, flash, brandName,
						belongType, groupName, note, updateFlag, updateNotes });
				imp_fail++;
				continue;//
			}
			if (basicFreq.equals("")) {
				updateNotes = "主频为空";
				updateFlag = "N";
				impRecords.add(new String[] { mac, modelType, version,
						chipModel, basicFreq, ram, flash, brandName,
						belongType, groupName, note, updateFlag, updateNotes });
				imp_fail++;
				continue;//
			}
			if (ram.equals("")) {
				updateNotes = "ram为空";
				updateFlag = "N";
				impRecords.add(new String[] { mac, modelType, version,
						chipModel, basicFreq, ram, flash, brandName,
						belongType, groupName, note, updateFlag, updateNotes });
				imp_fail++;
				continue;//
			}
			if (flash.equals("")) {
				updateNotes = "flash为空";
				updateFlag = "N";
				impRecords.add(new String[] { mac, modelType, version,
						chipModel, basicFreq, ram, flash, brandName,
						belongType, groupName, note, updateFlag, updateNotes });
				imp_fail++;
				continue;//
			}
			if (brandName.equals("")) {
				updateNotes = "品牌名称为空";
				updateFlag = "N";
				impRecords.add(new String[] { mac, modelType, version,
						chipModel, basicFreq, ram, flash, brandName,
						belongType, groupName, note, updateFlag, updateNotes });
				imp_fail++;
				continue;//
			}
			if (belongType.equals("")) {
				updateNotes = "归属类型为空";
				updateFlag = "N";
				impRecords.add(new String[] { mac, modelType, version,
						chipModel, basicFreq, ram, flash, brandName,
						belongType, groupName, note, updateFlag, updateNotes });
				imp_fail++;
				continue;//
			} else {
				if (belongType.equals("代理商渠道")) {// 代理商渠道进行代理商校验
					if (groupName.equals("")) {
						updateNotes = "代理商名称为空";
						updateFlag = "N";
						impRecords.add(new String[] { mac, modelType, version,
								chipModel, basicFreq, ram, flash, brandName,
								belongType, groupName, note, updateFlag,
								updateNotes });
						imp_fail++;
						continue;//
					} else {
						try {
							ChnGroupMsg chnGroupMsg = uploadDao.findGroupIdByName(groupName);
							if (chnGroupMsg == null) {
								updateNotes = "代理商名称不存在";
								updateFlag = "N";
								impRecords.add(new String[] { mac, modelType,
										version, chipModel, basicFreq, ram,
										flash, brandName, belongType,
										groupName, note, updateFlag,
										updateNotes });
								imp_fail++;
								continue;//
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			}
			macList.add(mac);

			updateNotes = "导入成功";
			updateFlag = "Y";
			impRecords.add(new String[] { mac, modelType, version, chipModel,
					basicFreq, ram, flash, brandName, belongType, groupName,
					note, updateFlag, updateNotes });
			imp_success++;

		}

		String fileExt = filePath.substring(filePath.lastIndexOf("."));
		String checkFileName = filePath.substring(0,
				filePath.lastIndexOf(fileExt));
		checkFileName = checkFileName + "_Check" + fileExt;
		// System.out.println(checkFileName);
		File file = new File(checkFileName);

		boolean load_flag = false;

		try {
			load_flag = loadDeviceCheckDataToFile(impRecords, file);
		} catch (Exception e) {
			throw new ActionException(e.getMessage());
		}

		if (!load_flag) {// 文件导入正常以后数据文件入库

			throw new ActionException("导入记录文件生成异常，请重新导入");
		}
		return new int[] { imp_success, imp_fail };
	}

	public int[] checkServiceImpSheet(String filePath, XSSFSheet sheet)
			throws ActionException, SQLException {
		int rowsnum = sheet.getLastRowNum();
		List<String[]> impRecords = new ArrayList<String[]>();// 记录返回的结果记录
		String updateNotes = "";
		String updateFlag = "Y";
		List ipList = new ArrayList();// 记录批量导入的ip，用户重复导入项统计
		int imp_success = 0;
		int imp_fail = 0;
		int credit_clear = 0;

		for (int i = 1; i <= rowsnum; i++) {
			XSSFRow row = sheet.getRow(i);
			if (row == null) {
				continue;// 遇到空行继续解析,impRecords中不增加记录
			}

			String serverName = row.getCell(0) == null ? "" : row.getCell(0)
					.getStringCellValue();// 服务器名称
			String ipAddress = row.getCell((short) 1) == null ? "" : row
					.getCell((short) 1).getStringCellValue();// IP地址
			String operSystem = row.getCell((short) 2) == null ? "" : row
					.getCell((short) 2).getStringCellValue();// 操作系统
			String sysKernel = row.getCell((short) 3) == null ? "" : row
					.getCell((short) 3).getStringCellValue();// 内核版本
			String fileHandles = row.getCell((short) 4) == null ? "" : row
					.getCell((short) 4).getStringCellValue();// 文件系统
			String cpuModel = row.getCell((short) 5) == null ? "" : row
					.getCell((short) 5).getStringCellValue();// CPU型号
			String cpuFreq = row.getCell((short) 6) == null ? "" : row.getCell(
					(short) 6).getStringCellValue();// 主频
			String centerName = row.getCell((short) 7) == null ? "" : row
					.getCell((short) 7).getStringCellValue();// 机房
			String operator = row.getCell((short) 8) == null ? "" : row
					.getCell((short) 8).getStringCellValue();// 运营商
			String location = row.getCell((short) 9) == null ? "" : row
					.getCell((short) 9).getStringCellValue();// 位置
			String bandWidth = row.getCell((short) 10) == null ? "" : row
					.getCell((short) 10).getStringCellValue();// 流量限值
			XSSFCell cell11 = row.getCell((short) 11);
			String rate = "";
			if (cell11 == null) {
				rate = "";
			} else {
				int cellType = cell11.getCellType();
				if (cellType == XSSFCell.CELL_TYPE_NUMERIC) {
					rate = row.getCell((short) 11) == null ? "" : row.getCell(
							(short) 11).getNumericCellValue()
							+ "";// 费用
				} else {
					rate = row.getCell((short) 11) == null ? "" : row.getCell(
							(short) 11).getStringCellValue();// 费用
				}
			}
			String groupName = row.getCell((short) 12) == null ? "" : row.getCell(
					(short) 12).getStringCellValue();// 备注
			String note = row.getCell((short) 13) == null ? "" : row.getCell(
					(short) 13).getStringCellValue();// 备注
			if (serverName.equals("")) {
				updateNotes = "服务器名称为空";
				updateFlag = "N";
				impRecords.add(new String[] { serverName, ipAddress,
						operSystem, sysKernel, fileHandles, cpuModel, cpuFreq,
						centerName, operator, location, bandWidth, rate, groupName,note,
						updateFlag, updateNotes });
				imp_fail++;
				continue;//
			}
			if (ipAddress.equals("")) {
				updateNotes = "ip地址为空";
				updateFlag = "N";
				impRecords.add(new String[] { serverName, ipAddress,
						operSystem, sysKernel, fileHandles, cpuModel, cpuFreq,
						centerName, operator, location, bandWidth, rate,groupName, note,
						updateFlag, updateNotes });
				imp_fail++;
				continue;//
			}
			if (ipList.contains(ipAddress)) {
				updateNotes = "该IP导入已有操作，请勿重复执行";
				updateFlag = "N";
				impRecords.add(new String[] { serverName, ipAddress,
						operSystem, sysKernel, fileHandles, cpuModel, cpuFreq,
						centerName, operator, location, bandWidth, rate,groupName, note,
						updateFlag, updateNotes });
				imp_fail++;
				continue;//
			}
			String reg_exp = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";
			Pattern pattern = Pattern.compile(reg_exp);
			Matcher m = pattern.matcher(ipAddress);
			if (!m.find()) {
				updateNotes = "IP地址格式错误";
				updateFlag = "N";
				impRecords.add(new String[] { serverName, ipAddress,
						operSystem, sysKernel, fileHandles, cpuModel, cpuFreq,
						centerName, operator, location, bandWidth, rate,groupName, note,
						updateFlag, updateNotes });
				imp_fail++;
				continue;//
			}
			try {
				Object obj = uploadDao.findSourceServerMsgByIp(ipAddress);
				if (obj != null) {
					updateNotes = "IP地址在系统中已经存在";
					updateFlag = "N";
					impRecords.add(new String[] { serverName, ipAddress,
							operSystem, sysKernel, fileHandles, cpuModel,
							cpuFreq, centerName, operator, location, bandWidth,
							rate,groupName, note, updateFlag, updateNotes });
					imp_fail++;
					continue;//
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (operSystem.equals("")) {
				updateNotes = "操作系统为空";
				updateFlag = "N";
				impRecords.add(new String[] { serverName, ipAddress,
						operSystem, sysKernel, fileHandles, cpuModel, cpuFreq,
						centerName, operator, location, bandWidth, rate,groupName, note,
						updateFlag, updateNotes });
				imp_fail++;
				continue;//
			}
			if (sysKernel.equals("")) {
				updateNotes = "系统内核版本为空";
				updateFlag = "N";
				impRecords.add(new String[] {

				ipAddress, operSystem, sysKernel, fileHandles, cpuModel,
						cpuFreq, centerName, operator, location, bandWidth,
						rate,groupName, note, updateFlag, updateNotes });
				imp_fail++;
				continue;//
			}
			if (fileHandles.equals("")) {
				updateNotes = "文件系统为空";
				updateFlag = "N";
				impRecords.add(new String[] { serverName, ipAddress,
						serverName, operSystem, sysKernel, fileHandles,
						cpuModel, cpuFreq, centerName, operator, location,
						bandWidth, rate,groupName, note, updateFlag, updateNotes });
				imp_fail++;
				continue;//
			}
			if (cpuModel.equals("")) {
				updateNotes = "CPU型号为空";
				updateFlag = "N";
				impRecords.add(new String[] { serverName, ipAddress,
						operSystem, sysKernel, fileHandles, cpuModel, cpuFreq,
						centerName, operator, location, bandWidth, rate,groupName, note,
						updateFlag, updateNotes });
				imp_fail++;
				continue;//
			}
			if (cpuFreq.equals("")) {
				updateNotes = "主频为空";
				updateFlag = "N";
				impRecords.add(new String[] { serverName, ipAddress,
						operSystem, sysKernel, fileHandles, cpuModel, cpuFreq,
						centerName, operator, location, bandWidth, rate,groupName, note,
						updateFlag, updateNotes });
				imp_fail++;
				continue;//
			}
			if (centerName.equals("")) {
				updateNotes = "机房地址为空";
				updateFlag = "N";
				impRecords.add(new String[] { serverName, ipAddress,
						operSystem, sysKernel, fileHandles, cpuModel, cpuFreq,
						centerName, operator, location, bandWidth, rate,groupName, note,
						updateFlag, updateNotes });
				imp_fail++;
				continue;//
			}
			if (operator.equals("")) {
				updateNotes = "运营商名称为空";
				updateFlag = "N";
				impRecords.add(new String[] { serverName, ipAddress,
						operSystem, sysKernel, fileHandles, cpuModel, cpuFreq,
						centerName, operator, location, bandWidth, rate,groupName, note,
						updateFlag, updateNotes });
				imp_fail++;
				continue;//
			}
			if (location.equals("")) {
				updateNotes = "位置为空";
				updateFlag = "N";
				impRecords.add(new String[] { serverName, ipAddress,
						operSystem, sysKernel, fileHandles, cpuModel, cpuFreq,
						centerName, operator, location, bandWidth, rate,groupName, note,
						updateFlag, updateNotes });
				imp_fail++;
				continue;//
			}
			if (bandWidth.equals("")) {
				updateNotes = "流量限值为空";
				updateFlag = "N";
				impRecords.add(new String[] { serverName, ipAddress,
						operSystem, sysKernel, fileHandles, cpuModel, cpuFreq,
						centerName, operator, location, bandWidth, rate,groupName, note,
						updateFlag, updateNotes });
				imp_fail++;
				continue;//
			}
			if (rate.equals("")) {
				updateNotes = "费用为空";
				updateFlag = "N";
				impRecords.add(new String[] { serverName, ipAddress,
						operSystem, sysKernel, fileHandles, cpuModel, cpuFreq,
						centerName, operator, location, bandWidth, rate,groupName, note,
						updateFlag, updateNotes });
				imp_fail++;
				continue;//
			}
			ipList.add(ipAddress);

			updateNotes = "导入成功";
			updateFlag = "Y";
			impRecords.add(new String[] { serverName, ipAddress, operSystem,
					sysKernel, fileHandles, cpuModel, cpuFreq, centerName,
					operator, location, bandWidth, rate,groupName, note, updateFlag,
					updateNotes });
			imp_success++;

		}

		String fileExt = filePath.substring(filePath.lastIndexOf("."));
		String checkFileName = filePath.substring(0,
				filePath.lastIndexOf(fileExt));
		checkFileName = checkFileName + "_Check" + fileExt;
		// System.out.println(checkFileName);
		File file = new File(checkFileName);

		boolean load_flag = false;

		try {
			load_flag = loadServcieCheckDataToFile(impRecords, file);
		} catch (Exception e) {
			throw new ActionException(e.getMessage());
		}

		if (!load_flag) {// 文件导入正常以后数据文件入库

			throw new ActionException("导入记录文件生成异常，请重新导入");
		}
		return new int[] { imp_success, imp_fail };
	}

	/**
	 * 导出核查文件后的数据
	 * 
	 * @param list
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public boolean loadDeviceCheckDataToFile(List list, File file)
			throws IOException {
		FileOutputStream out = new FileOutputStream(file);
		XSSFWorkbook book = new XSSFWorkbook();
		XSSFSheet sheet = book.createSheet("import_record");
		XSSFRow titleRow = sheet.createRow(0);

		XSSFCell cell1 = titleRow.createCell((short) 0);
		cell1.setCellValue("MAC地址");

		XSSFCell cell_add = titleRow.createCell((short) 1);
		cell_add.setCellValue("设备型号");

		XSSFCell cell2 = titleRow.createCell((short) 2);
		cell2.setCellValue("系统初始版本");

		XSSFCell cell3 = titleRow.createCell((short) 3);
		cell3.setCellValue("芯片型号");

		XSSFCell cell4 = titleRow.createCell((short) 4);
		cell4.setCellValue("主频");

		XSSFCell cell5 = titleRow.createCell((short) 5);
		cell5.setCellValue("内存");

		XSSFCell cell6 = titleRow.createCell((short) 6);
		cell6.setCellValue("falsh");

		XSSFCell cell7 = titleRow.createCell((short) 7);
		cell7.setCellValue("品牌名称");

		XSSFCell cell8 = titleRow.createCell((short) 8);
		cell8.setCellValue("归属类型");

		XSSFCell cell9 = titleRow.createCell((short) 9);
		cell9.setCellValue("代理商名称");
		XSSFCell cell10 = titleRow.createCell((short) 10);
		cell10.setCellValue("备注");
		XSSFCell cell11 = titleRow.createCell((short) 11);
		cell11.setCellValue("导入状态");
		XSSFCell cell12 = titleRow.createCell((short) 12);
		cell12.setCellValue("导入情况描述");

		for (int j = 0; j < list.size(); j++) {
			XSSFRow row = sheet.createRow(j + 1);// 表头已经生成，从下一行开始入数据
			int i = 0;// 从每行的第一列开始入数据
			String[] strs = (String[]) list.get(j);
			for (; i < 13; i++) {
				XSSFCell cell = row.createCell((short) i);
				cell.setCellValue(strs[i]);
			}
		}
		book.write(out);
		out.flush();
		out.close();
		System.out.println("批量导入文件" + file.getAbsolutePath() + "生成成功!");
		return true;
	}

	/**
	 * 导出核查文件后的数据
	 * 
	 * @param list
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public boolean loadServcieCheckDataToFile(List list, File file)
			throws IOException {
		FileOutputStream out = new FileOutputStream(file);
		XSSFWorkbook book = new XSSFWorkbook();
		XSSFSheet sheet = book.createSheet("import_record");
		XSSFRow titleRow = sheet.createRow(0);

		XSSFCell cell0 = titleRow.createCell((short) 0);
		cell0.setCellValue("服务器名称");

		XSSFCell cell1 = titleRow.createCell((short) 1);
		cell1.setCellValue("IP地址");

		XSSFCell cell2 = titleRow.createCell((short) 2);
		cell2.setCellValue("操作系统");

		XSSFCell cell3 = titleRow.createCell((short) 3);
		cell3.setCellValue("内核版本");

		XSSFCell cell4 = titleRow.createCell((short) 4);
		cell4.setCellValue("文件系统");

		XSSFCell cell5 = titleRow.createCell((short) 5);
		cell5.setCellValue("CPU型号");

		XSSFCell cell6 = titleRow.createCell((short) 6);
		cell6.setCellValue("主频");

		XSSFCell cell7 = titleRow.createCell((short) 7);
		cell7.setCellValue("机房");

		XSSFCell cell8 = titleRow.createCell((short) 8);
		cell8.setCellValue("运营商");

		XSSFCell cell9 = titleRow.createCell((short) 9);
		cell9.setCellValue("位置");

		XSSFCell cell10 = titleRow.createCell((short) 10);
		cell10.setCellValue("流量限值");

		XSSFCell cell11 = titleRow.createCell((short) 11);
		cell11.setCellValue("费用");
		XSSFCell cell12 = titleRow.createCell((short) 12);
		cell12.setCellValue("分组");
		XSSFCell cell13 = titleRow.createCell((short) 13);
		cell13.setCellValue("备注");
		XSSFCell cell14 = titleRow.createCell((short) 14);
		cell14.setCellValue("导入状态");
		XSSFCell cell15 = titleRow.createCell((short) 15);
		cell15.setCellValue("导入情况描述");

		for (int j = 0; j < list.size(); j++) {
			XSSFRow row = sheet.createRow(j + 1);// 表头已经生成，从下一行开始入数据
			int i = 0;// 从每行的第一列开始入数据
			String[] strs = (String[]) list.get(j);
			for (; i < 16; i++) {
				XSSFCell cell = row.createCell((short) i);
				cell.setCellValue(strs[i]);
			}
		}
		book.write(out);
		out.flush();
		out.close();
		System.out.println("批量导入文件" + file.getAbsolutePath() + "生成成功!");
		return true;
	}

	public UploadDao getUploadDao() {
		return uploadDao;
	}

	public void setUploadDao(UploadDao uploadDao) {
		this.uploadDao = uploadDao;
	}

	@Override
	public RouterMsg findRouterMsgByMac(String mac) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] checkM1320UploadFile(String fileName) throws Exception {
		// TODO Auto-generated method stub
		FileInputStream input = null;
		int[] checkResults;
		try {
			input = new FileInputStream(fileName);
			// HSSFWorkbook book = new HSSFWorkbook(input);
			XSSFWorkbook book = new XSSFWorkbook(input);

			XSSFSheet sheet = book.getSheetAt(0);
			XSSFRow titleRow = sheet.getRow(0);
			int cellNum = titleRow.getLastCellNum();
			System.out.println(cellNum);
			if (cellNum != 14) {
				throw new ActionException("导入模板错误，请重新下载模板");

			}
			checkResults = checkServiceImpSheet(fileName, sheet);

		} catch (ActionException e) {
			e.printStackTrace();
			throw new ActionException(e.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ActionException(ex.getMessage());
		} finally {
			try {
				input.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return checkResults;
	}

}
