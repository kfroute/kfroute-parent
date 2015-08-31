package cn.melinkr.platform.payment;

public enum OrderStatus {

	WAITPAY(1,"未支付"),WAITDELIVERED(2,"待发货"),DELIVERED(3,"待收货"),RECEIVED(4,"已收货"),WAITRETUNFEE(5,"待退款"),RETUNFEE(6,"已退款"),COMPLETED(7,"已完成"),CANCEL(8,"已撤单"),PAYFAIL(9,"支付失败");
	
	private String name;
	private int status;
	
	// 构造方法  
    private OrderStatus(int status,String name) {  
        this.name = name;  
        this.status = status;  
    }  
    // 普通方法  
    public static String getName(int status) {  
        for (OrderStatus orderStatus : OrderStatus.values()) {  
            if (orderStatus.getStatus() == status) {  
                return orderStatus.name;  
            }  
        }  
        return null;  
    } 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
