package cn.kfroute.platform.payment.paymentEntity.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import net.sf.json.JSONObject;
import cn.kfroute.platform.payment.merchantPayment.service.inf.MerchantPaymentService;
import cn.kfroute.platform.payment.paymentEntity.service.inf.PaymentEntityService;
import cn.seocoo.platform.payment.MerchantPayment;
import cn.seocoo.platform.payment.PaymentEntity;

import com.odchina.micro.base.BaseAction;
import com.odchina.micro.shiro.ShiroUser;
import com.odchina.micro.util.SystemConfigUtil;

public class PaymentEntityAction extends BaseAction {
	private PaymentEntityService paymentEntityService;
	private List<PaymentEntity> paymentEntityList;
	private MerchantPayment merchantPayment;
	private MerchantPaymentService merchantPaymentService;
	private File file;
	private String filename_atr;
	
	
	public String findAll(){
		paymentEntityList=paymentEntityService.findAll();
		int id=Integer.parseInt(this.request.getParameter("id"));
		MerchantPayment mp=new MerchantPayment();
		mp.setId(id);
		merchantPayment=merchantPaymentService.find(mp);
		return "findAll";
	}
	
	public String findTheAll(){
		paymentEntityList=paymentEntityService.findAll();
		return "findTheAll";
	}
	
	public void find() throws IOException{
		int id=Integer.parseInt(this.request.getParameter("id"));
		PaymentEntity p=new PaymentEntity();
		p.setId(id);
		PaymentEntity payment=paymentEntityService.find(p);
		JSONObject json=new JSONObject();
		json.put("result", "1");
		json.put("paymentEntity", payment);
		this.sendMessages(json.toString());
		
	}
	
	
	public void add() throws IOException{
		JSONObject json=new JSONObject();
		String type=this.request.getParameter("type");
		int id=Integer.parseInt(this.request.getParameter("id"));
		String paymentCode=this.request.getParameter("paymentCode");
		String paymentName=this.request.getParameter("paymentName");
		String paymentUrl=this.request.getParameter("paymentUrl");
		String paymentDesc=this.request.getParameter("paymentDesc");
		String terminalType=this.request.getParameter("terminalType");
		PaymentEntity p=new PaymentEntity();
		p.setPaymentCode(paymentCode);
		if(type.equalsIgnoreCase("add")){
			PaymentEntity p1=paymentEntityService.find(p);
			if(p1!=null){
				json.put("result", "0");
			}else{
				p.setPaymentName(paymentName);
				p.setPaymentDesc(paymentDesc);
				p.setPaymentUrl(paymentUrl);
				p.setTerminalType(terminalType);
				paymentEntityService.add(p);
				json.put("result", "1");
			}
		}else if(type.equalsIgnoreCase("update")){
			p.setId(id);
			p.setPaymentName(paymentName);
			p.setPaymentDesc(paymentDesc);
			p.setPaymentUrl(paymentUrl);
			p.setTerminalType(terminalType);
			paymentEntityService.update(p);
			json.put("result", "1");
		}
		this.sendMessages(json.toString());
		
	}
	
	public void delete() throws IOException{
		int id=Integer.parseInt(this.request.getParameter("id"));
		PaymentEntity p=new PaymentEntity();
		p.setId(id);
		paymentEntityService.delete(p);
		JSONObject json=new JSONObject();
		json.put("result", "1");
		this.sendMessages(json.toString());
		
	}
	
	public void uploadImg() throws IOException{
		String newFileName="";
		String paymentCode=this.request.getParameter("paymentCode");
		String fileNameAtr=this.request.getParameter("filename_atr");
		String filePath=this.request.getParameter("filePath");
		StringBuffer currentPath1=this.request.getRequestURL();
		System.out.println("filePath:"+filePath+"\t"+"currentPath1:"+currentPath1.toString());
		FileOutputStream fos = null;
		FileInputStream fis= null;
		String realDir=request.getSession().getServletContext().getRealPath("/");
		String webUrl=SystemConfigUtil.getSingleProperty("img_dir").getPropertyValue();
		webUrl=webUrl+System.getProperty("file.separator")+"uploadImg";
		System.out.println("realDir:"+realDir+"\t"+"webUrl:"+webUrl );
		File file1=new File(webUrl);
		if(!file1.isDirectory()){
			file1.mkdirs();
		}
		ShiroUser su=this.queryCurrentShiroUser();
		String path=webUrl+System.getProperty("file.separator")+paymentCode+"."+fileNameAtr;
		
		String reqPath;
		//临时处理目录
		reqPath="";
		String suc;
		
		try {
			fis=new FileInputStream(getFile());
			fos = new FileOutputStream(new File(path));
			byte[] strByte  =new byte[(int) getFile().length()];
			//生成文件
			while(fis.read(strByte)!=-1){
				String str1=new String(strByte);
				fos.write(strByte);
			}
			
			fos.flush();
			suc="1";
			
		} catch (Exception e) {
			suc="0";
			e.printStackTrace();
		} finally {
			if (fos != null) {
	            try { fos.close(); } catch (IOException e) {}
	        }
		}
	 
          JSONObject json=new JSONObject();
          json.put("result", suc);
          String visit_path=SystemConfigUtil.getSingleProperty("visit_path").getPropertyValue();
          json.put("url", path.substring(path.lastIndexOf("upload")));
          try {
			this.sendMessages(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(fos!=null){
				fos.close();				
			}
			if(fis!=null){
				fis.close();				
			}
		}
	}
	

	public PaymentEntityService getPaymentEntityService() {
		return paymentEntityService;
	}

	public void setPaymentEntityService(PaymentEntityService paymentEntityService) {
		this.paymentEntityService = paymentEntityService;
	}

	public List<PaymentEntity> getPaymentEntityList() {
		return paymentEntityList;
	}

	public void setPaymentEntityList(List<PaymentEntity> paymentEntityList) {
		this.paymentEntityList = paymentEntityList;
	}

	public MerchantPayment getMerchantPayment() {
		return merchantPayment;
	}

	public void setMerchantPayment(MerchantPayment merchantPayment) {
		this.merchantPayment = merchantPayment;
	}

	public MerchantPaymentService getMerchantPaymentService() {
		return merchantPaymentService;
	}

	public void setMerchantPaymentService(
			MerchantPaymentService merchantPaymentService) {
		this.merchantPaymentService = merchantPaymentService;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFilename_atr() {
		return filename_atr;
	}

	public void setFilename_atr(String filename_atr) {
		this.filename_atr = filename_atr;
	}
	
	
	
}
