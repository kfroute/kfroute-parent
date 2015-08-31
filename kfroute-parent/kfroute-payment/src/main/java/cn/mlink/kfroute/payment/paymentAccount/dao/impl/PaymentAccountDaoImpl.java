package cn.kfroute.platform.payment.paymentAccount.dao.impl;

import java.util.List;

import cn.kfroute.platform.payment.paymentAccount.dao.inf.PaymentAccountDao;
import cn.seocoo.platform.payment.PaymentAccount;

import com.tydic.framework.base.dao.EntityManagerImpl;

public class PaymentAccountDaoImpl extends EntityManagerImpl<PaymentAccount,Integer> implements PaymentAccountDao {

	@Override
	public List<PaymentAccount> findAll() {
		return entityDao.findAll("paymentAccount.queryAll");
	}

	@Override
	public PaymentAccount find(PaymentAccount pa) {
		return entityDao.findObj("paymentAccount.query", pa);
	}

	@Override
	public void add(PaymentAccount pa) {
		entityDao.save("paymentAccount.insert", pa);

	}

	@Override
	public void update(PaymentAccount pa) {
		entityDao.update("paymentAccount.update", pa);

	}

	@Override
	public void delete(PaymentAccount pa) {
		entityDao.remove("paymentAccount.delete", pa);

	}

	@Override
	public PaymentAccount findOne(PaymentAccount pa) {
		//List<PaymentAccount> psList = entityDao.find("paymentAccount.query", pa);
		return (PaymentAccount) entityDao.find("paymentAccount.query", pa).get(0);
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.paymentAccount.dao.inf.PaymentAccountDao#findByObject(cn.seocoo.platform.payment.PaymentAccount) 
	 */
	@Override
	public List<PaymentAccount> findByObject(PaymentAccount pa) {
		// TODO Auto-generated method stub
		return entityDao.find("paymentAccount.query", pa);
	}

}
