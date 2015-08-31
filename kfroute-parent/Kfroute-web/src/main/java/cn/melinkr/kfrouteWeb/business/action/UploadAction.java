package cn.melinkr.kfrouteWeb.business.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.melinkr.kfrouteWeb.business.service.UploadService;
import cn.melinkr.platform.kfroute.SystemConfig;

import com.melinkr.micro.base.BaseAction;
import com.melinkr.micro.util.SystemConfigUtil;

public class UploadAction extends BaseAction {
	private final Logger logger = LoggerFactory.getLogger(UploadAction.class);
	private File[] attach_name;// 对应文件域的file，封装文件内容
	private static final long serialVersionUID = 1L;
	private String[] attach_nameContentType;// 封装文件类型
	private String[] attach_nameFileName;// 封装文件名
	private String savePath;// 保存路径
	private String title;// 文件标题
	private String retCode;
	private String retMsg;
	
	private UploadService uploadService;

	public String main() {
		System.out.println("2222");
		return "success";
	}

	/**
	 * 下载模板文件
	 */
	public String  m1310DownloadModel() {
		response.setContentType("text/html;charset=utf-8");

		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;

		// String filePath = request.getParameter("filePath");
		SystemConfig systemConfig = SystemConfigUtil
				.getSingleProperty("device_upload_file_model_path");
		String fileName = systemConfig == null ? "/" : systemConfig
				.getPropertyValue() + request.getParameter("filename");
		// System.out.println(fileName);
		File _file = new File(fileName);
		try {
			request.setCharacterEncoding("UTF-8");
			long fileLength = _file.length();

			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(_file.getName().getBytes("GBK"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));

			bis = new BufferedInputStream(new FileInputStream(fileName));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			return "fail";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "success";
	}
	/**
	 * 下载模板文件
	 */
	public String  m1320DownloadModel() {
		response.setContentType("text/html;charset=utf-8");

		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;

		// String filePath = request.getParameter("filePath");
		SystemConfig systemConfig = SystemConfigUtil
				.getSingleProperty("service_upload_file_model_path");
		String fileName = systemConfig == null ? "/" : systemConfig
				.getPropertyValue() + request.getParameter("filename");
		// System.out.println(fileName);
		File _file = new File(fileName);
		try {
			request.setCharacterEncoding("UTF-8");
			long fileLength = _file.length();

			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(_file.getName().getBytes("GBK"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));

			bis = new BufferedInputStream(new FileInputStream(fileName));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			return "fail";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "success";
	}
	/**
	 * 下载导入详情文件
	 */
	public String  m1310DownloadUploadDetail() {
		response.setContentType("text/html;charset=utf-8");

		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;


		String uploadFileName = (String)request.getSession().getAttribute("m1310UploadFileName");
		if(uploadFileName==null){
			return "fail";
		}else{
			String fileExt = uploadFileName
				.substring(uploadFileName.lastIndexOf("."));
			String checkFileName = uploadFileName.substring(0, uploadFileName.lastIndexOf(fileExt));
			checkFileName = checkFileName + "_Check"+fileExt;
			// System.out.println(fileName);
			File _file = new File(checkFileName);
			try {
				request.setCharacterEncoding("UTF-8");
				long fileLength = _file.length();
	
				response.setContentType("application/x-msdownload;");
				response.setHeader("Content-disposition", "attachment; filename="
						+ new String(_file.getName().getBytes("GBK"), "ISO8859-1"));
				response.setHeader("Content-Length", String.valueOf(fileLength));
	
				bis = new BufferedInputStream(new FileInputStream(checkFileName));
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
				return "fail";
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (bis != null)
						bis.close();
					if (bos != null)
						bos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "success";
	}
	/**
	 * 下载导入详情文件
	 */
	public String  m1320DownloadUploadDetail() {
		response.setContentType("text/html;charset=utf-8");

		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;


		String uploadFileName = (String)request.getSession().getAttribute("m1320UploadFileName");
		if(uploadFileName==null){
			return "fail";
		}else{
			String fileExt = uploadFileName
				.substring(uploadFileName.lastIndexOf("."));
			String checkFileName = uploadFileName.substring(0, uploadFileName.lastIndexOf(fileExt));
			checkFileName = checkFileName + "_Check"+fileExt;
			// System.out.println(fileName);
			File _file = new File(checkFileName);
			try {
				request.setCharacterEncoding("UTF-8");
				long fileLength = _file.length();
	
				response.setContentType("application/x-msdownload;");
				response.setHeader("Content-disposition", "attachment; filename="
						+ new String(_file.getName().getBytes("GBK"), "ISO8859-1"));
				response.setHeader("Content-Length", String.valueOf(fileLength));
	
				bis = new BufferedInputStream(new FileInputStream(checkFileName));
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
				return "fail";
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (bis != null)
						bis.close();
					if (bos != null)
						bos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "success";
	}

	/**
	 * 上传设备信息文件，并进行数据校验
	 */
	public String m1310UploadAndCheck() {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (attach_name != null) {

				for (int i=0;i<attach_name.length;i++) {
					File file  = attach_name[i];
					InputStream is = null;
					OutputStream os = null;

					String fileName = getAttach_nameFileName()[i];
					String fileExt = fileName
							.substring(fileName.lastIndexOf(".") + 1);
					long lbegin = System.currentTimeMillis();
					String date = new SimpleDateFormat("yyyyMMddHHmmss")
							.format((new Date(lbegin)));
					try {
						String _newFileName = getSavePath()+System.getProperty("file.separator")+ fileName.substring(0,fileName.lastIndexOf(".")) + "@"
								+ date + "." + fileExt;
						logger.debug(_newFileName);
						File _newFile = new File(_newFileName);
						if(!_newFile.exists()){
							if(!_newFile.getParentFile().exists())
								_newFile.getParentFile().mkdirs();
							_newFile.createNewFile();
						}
							
						is = new FileInputStream(file);
						// 创建输出流，生成新文件
						os = new FileOutputStream(new File(_newFileName));
						// 将InputStream里的byte拷贝到OutputStream
						IOUtils.copy(is, os);
						os.flush();
						
						//文件保存成功以后，进行文件内容校验
						//result:校验返回结果：result[0] 成功结果，result[1]失败结果
						int[] results = uploadService.checkM1310UploadFile(_newFileName);
						out.println("{\"retCode\":\"0000\",\"retMsg\":{\"success\":\""+results[0]+"\",\"fail\":\""+results[1]+"\"}}");
						request.getSession().setAttribute("m1310UploadFileName",_newFileName);
					} catch (Exception e) {
						e.printStackTrace();
						out.println("{\"retCode\":\"0003\",\"retMsg\":\"系统执行异常【"+e.getMessage()+"】\"}");
						if(out!=null){
							out.flush();
							out.close();
						}
						return "fail";
					} finally {
						IOUtils.closeQuietly(is);
						IOUtils.closeQuietly(os);
					}
					
				}
				
			}else{
				out.println("{\"retCode\":\"0001\",\"retMsg\":\"上传文件为空\"}");
				if(out!=null){
					out.flush();
					out.close();
				}
				return "fail";
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			out.println("{\"retCode\":\"0002\",\"retMsg\":\"系统执行异常【"+e1.getMessage()+"】\"}");
			if(out!=null){
				out.flush();
				out.close();
			}
			return "fail";
		}finally{
			if(out!=null){
				out.flush();
				out.close();
			}
		}
		return "success";
		
	}
	/**
	 * 上传设备信息文件，并进行数据校验
	 */
	public String m1320UploadAndCheck() {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (attach_name != null) {

				for (int i=0;i<attach_name.length;i++) {
					File file  = attach_name[i];
					InputStream is = null;
					OutputStream os = null;

					String fileName = getAttach_nameFileName()[i];
					String fileExt = fileName
							.substring(fileName.lastIndexOf(".") + 1);
					long lbegin = System.currentTimeMillis();
					String date = new SimpleDateFormat("yyyyMMddHHmmss")
							.format((new Date(lbegin)));
					try {
						String _newFileName = getSavePath()+System.getProperty("file.separator")+ fileName.substring(0,fileName.lastIndexOf(".")) + "@"
								+ date + "." + fileExt;
						logger.debug(_newFileName);
						File _newFile = new File(_newFileName);
						if(!_newFile.exists()){
							if(!_newFile.getParentFile().exists())
								_newFile.getParentFile().mkdirs();
							_newFile.createNewFile();
						}
							
						is = new FileInputStream(file);
						// 创建输出流，生成新文件
						os = new FileOutputStream(new File(_newFileName));
						// 将InputStream里的byte拷贝到OutputStream
						IOUtils.copy(is, os);
						os.flush();
						
						//文件保存成功以后，进行文件内容校验
						//result:校验返回结果：result[0] 成功结果，result[1]失败结果
						int[] results = uploadService.checkM1320UploadFile(_newFileName);
						out.println("{\"retCode\":\"0000\",\"retMsg\":{\"success\":\""+results[0]+"\",\"fail\":\""+results[1]+"\"}}");
						request.getSession().setAttribute("m1320UploadFileName",_newFileName);
					} catch (Exception e) {
						e.printStackTrace();
						out.println("{\"retCode\":\"0003\",\"retMsg\":\"系统执行异常【"+e.getMessage()+"】\"}");
						if(out!=null){
							out.flush();
							out.close();
						}
						return "fail";
					} finally {
						IOUtils.closeQuietly(is);
						IOUtils.closeQuietly(os);
					}
					
				}
				
			}else{
				out.println("{\"retCode\":\"0001\",\"retMsg\":\"上传文件为空\"}");
				if(out!=null){
					out.flush();
					out.close();
				}
				return "fail";
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			out.println("{\"retCode\":\"0002\",\"retMsg\":\"系统执行异常【"+e1.getMessage()+"】\"}");
			if(out!=null){
				out.flush();
				out.close();
			}
			return "fail";
		}finally{
			if(out!=null){
				out.flush();
				out.close();
			}
		}
		return "success";
		
	}
	
	/**
	 * 重新导入删除文件信息
	 * @return
	 */
	public String m1310DelUploadFile(){
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		try{
			out = response.getWriter();
			String uploadFileName = (String)request.getSession().getAttribute("m1310UploadFileName");
			if(uploadFileName!=null){
				File file = new File(uploadFileName);
				if(file.exists())
					file.delete();
			}
			String fileExt = uploadFileName
				.substring(uploadFileName.lastIndexOf("."));
			String checkFileName = uploadFileName.substring(0, uploadFileName.lastIndexOf(fileExt));
			checkFileName = checkFileName + "_Check"+fileExt;
			// System.out.println(checkFileName);
			File checkFile = new File(checkFileName);
			if(checkFile.exists())
				checkFile.delete();
			out.println("1");
		}catch(Exception e){
			e.printStackTrace();
			out.println("0");
			return "fail";
		}finally{
			if(out!=null){
				out.flush();
				out.close();
			}
		}
		return "success";
	}
	/**
	 * 重新导入删除文件信息
	 * @return
	 */
	public String m1320DelUploadFile(){
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		try{
			out = response.getWriter();
			String uploadFileName = (String)request.getSession().getAttribute("m1320UploadFileName");
			if(uploadFileName!=null){
				File file = new File(uploadFileName);
				if(file.exists())
					file.delete();
			}
			String fileExt = uploadFileName
				.substring(uploadFileName.lastIndexOf("."));
			String checkFileName = uploadFileName.substring(0, uploadFileName.lastIndexOf(fileExt));
			checkFileName = checkFileName + "_Check"+fileExt;
			// System.out.println(checkFileName);
			File checkFile = new File(checkFileName);
			if(checkFile.exists())
				checkFile.delete();
			out.println("1");
		}catch(Exception e){
			e.printStackTrace();
			out.println("0");
			return "fail";
		}finally{
			if(out!=null){
				out.flush();
				out.close();
			}
		}
		return "success";
	}
	
	public File[] getAttach_name() {
		return attach_name;
	}

	public void setAttach_name(File[] attach_name) {
		this.attach_name = attach_name;
	}

	public String[] getAttach_nameContentType() {
		return attach_nameContentType;
	}

	public void setAttach_nameContentType(String[] attach_nameContentType) {
		this.attach_nameContentType = attach_nameContentType;
	}

	public String[] getAttach_nameFileName() {
		return attach_nameFileName;
	}

	public void setAttach_nameFileName(String[] attach_nameFileName) {
		this.attach_nameFileName = attach_nameFileName;
	}

	public String getSavePath() {
		// 将相对路径转换成绝对路径
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public UploadService getUploadService() {
		return uploadService;
	}

	public void setUploadService(UploadService uploadService) {
		this.uploadService = uploadService;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	
	
}
