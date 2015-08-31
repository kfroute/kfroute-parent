package cn.melinkr.platform.kfroute;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.melinkr.platform.util.TimestampFormatUtil;

/**
 * @author: zhangyl
 * @time: 2015-08-17 11:20
 * @version: 1.0
 * 资源服务器端口实时状态对象，对应表：dserverportusemsg
 */
public class ServerPortUseMsgHis {
	private ServerPortUseMsg serverPortUseMsg;
	private Timestamp hisTime;
	private String hisNote;
	private int hisType;
	private String yearMonth = new SimpleDateFormat("yyyyMM").format(new Date());
	public ServerPortUseMsg getServerPortUseMsg() {
		return serverPortUseMsg;
	}
	public void setServerPortUseMsg(ServerPortUseMsg serverPortUseMsg) {
		this.serverPortUseMsg = serverPortUseMsg;
	}
	public Timestamp getHisTime() {
		return hisTime;
	}
	public void setHisTime(Timestamp hisTime) {
		this.hisTime = hisTime;
	}
	public String getHisNote() {
		return hisNote;
	}
	public void setHisNote(String hisNote) {
		this.hisNote = hisNote;
	}
	public int getHisType() {
		return hisType;
	}
	public void setHisType(int hisType) {
		this.hisType = hisType;
	}
	public String getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	@Override
	public String toString() {
		return "ServerPortUseMsgHis [serverPortUseMsg=" + serverPortUseMsg
				+ ", hisTime=" + hisTime + ", hisNote=" + hisNote
				+ ", hisType=" + hisType + ", yearMonth=" + yearMonth + "]";
	}
	
	
}
