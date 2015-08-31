package com.melinkr.micro.auth.filter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.melinkr.micro.util.SystemConfigUtil;

public class FileUploadFilter implements Filter {
	 /** 
     * 表单域的信息 
     */  
    private Map parameters = null;  
	 /** 
     * 允许上传的文件大小 
     */  
    private long MAX_SIZE = 10*1024*1024;  
    /*
    * 允许上传的文件类型 
    */  
   private String[] allowedExt = new String[] {".xls",".xlsx"};  
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String _action = request.getParameter("action");
		if(_action.equals("upload")){
			uploadAndCheck((HttpServletRequest)request,(HttpServletResponse)response);
		}else{
			downloadModel((HttpServletRequest)request,(HttpServletResponse)response);
		}
		return;
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 下载模板文件
	 */
	public void downloadModel(HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;

		//String filePath = request.getParameter("filePath");
		String fileName = SystemConfigUtil.getSingleProperty("device_upload_file_model_path").getPropertyValue()+request.getParameter("filename");
		System.out.println(fileName);
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	/**
	 * 上传设备信息文件，并进行数据校验
	 */
	public void uploadAndCheck(HttpServletRequest request,HttpServletResponse response) {
		 /** 
         * 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload 
         */  
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();  
        diskFileItemFactory.setSizeThreshold(4096);// 设置上传文件时用于临时存放文件的内存大小,这里是4K.多于的部分将临时存在硬盘  
        /** 
         * 采用系统临时文件目录作为上传的临时目录 
         */  
        File tempfile = new File(System.getProperty("java.io.tmpdir"));    
        diskFileItemFactory.setRepository(tempfile);  
        List fileInfoList = null; 
        /** 
         * 用以上工厂实例化上传组件 
         * 设置最大上传尺寸 
         */  
        ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);  
        fileUpload.setSizeMax(MAX_SIZE);  
          
        /** 
         * 调用FileUpload.settingHeaderEncoding(”UTF-8″)，这项设置可以解决路径或者文件名为乱码的问题。 
         * 设置输出字符集 
         */  
        fileUpload.setHeaderEncoding("UTF-8");  
        response.setContentType("text/html;charset=utf-8");  
          
        PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
        /** 
         * 从request得到 所有 上传域的列表 
         */  
        List fileList = null;  
        try {  
            fileList = fileUpload.parseRequest(request);  
        } catch (FileUploadException e) {  
            if (e instanceof SizeLimitExceededException) {  
                /** 
                 * 文件大小超出限制 
                 */  
                out.println("{\"retCode\":\"0001\",\"retMsg\":\"文件尺寸超过规定大小:" + MAX_SIZE + "字节\"");  
                return;  
            }  
            e.printStackTrace();  
        }  
        /** 
         * 没有上传文件 
         */  
        if (fileList == null || fileList.size() == 0) {  
        	out.println("{\"retCode\":\"0002\",\"retMsg\":\"请选择上传文件\"");  
           
            return;  
        }  
        /** 
         * 得到所有上传的文件 
         * 对文件域操作 
         * 并保存每个文件的详细信息 
         */  
        Iterator fileItr = fileList.iterator();  
        Map fileInfo = null;  
        while (fileItr.hasNext()) {  
            FileItem fileItem = null;  
            long size = 0;  
            String userPath = null;  
            String serverPath = null;  
            String fileName = null;  
            String fileExt = null;  
            fileItem = (FileItem) fileItr.next();  
            /** 
             * 忽略简单form字段而不是上传域的文件域(<input type="text" />等) 
             */  
            if (!fileItem.isFormField()) {  
              
                /** 
                 * 得到文件的详细信息 
                 * 客户端完整路径：userPath 
                 * 服务器端完整路径：serverPath 
                 * 大小：size 
                 * 文件名：fileName 
                 * 扩展名：fileExt 
                 *  
                 */  
                userPath = fileItem.getName();  
                size = fileItem.getSize();  
                if ("".equals(userPath) || size == 0) {  
                	out.println("{\"retCode\":\"0002\",\"retMsg\":\"请选择上传文件\"");  
                    
                    return; 
                }  
                fileName = userPath.substring(userPath.lastIndexOf("\\") + 1);  
                fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);  
  
                /** 
                 * 文件类型是否合法 
                 */  
                int allowFlag = 0;  
                int allowedExtCount = allowedExt.length;  
                for (; allowFlag < allowedExtCount; allowFlag++) {  
                    if (allowedExt[allowFlag].toLowerCase().equals(fileExt.toLowerCase()))  
                        break;  
                }  
                if (allowFlag == allowedExtCount) {  
                	out.println("{\"retCode\":\"0003\",\"retMsg\":\"请选择excel文件\"");  
                    return;  
                }  
                /** 
                 * 根据系统时间生成上传后保存的文件名 
                 */  
                serverPath = userPath + fileName+"-"+System.currentTimeMillis() + "." + fileExt;  
                  
                try {  
                    /** 
                     * 保存文件 
                     */  
                    File diskPath = new File(userPath);  
                    if(!diskPath.exists()) {  
                        diskPath.mkdirs();  
                    }  
                    File diskFile = new File(serverPath);  
                    if(!diskFile.exists()) {  
                        diskFile.createNewFile();  
                    }  
                    fileItem.write(diskFile);  
                    out.println("{\"retCode\":\"0000\",\"retMsg\":\"上传成功\"");
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
                  
                fileInfo = new HashMap();  
                fileInfo.put("size", String.valueOf(size));  
                fileInfo.put("userpath", userPath);  
                fileInfo.put("name",fileName);  
                fileInfo.put("ext", fileExt);  
                fileInfo.put("serverpath", serverPath);  
                fileInfoList.add(fileInfo);  
            } else {  
                String fieldName = fileItem.getFieldName();  
                /** 
                 * 在取字段值的时候，用FileItem.getString(”UTF-8″)，这项设置可以解决获取的表单字段为乱码的问题。 
                 */   
                String value="";
				try {
					value = fileItem.getString("UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
                parameters.put(fieldName, value);  
            }  
        }  
	}
	

}
