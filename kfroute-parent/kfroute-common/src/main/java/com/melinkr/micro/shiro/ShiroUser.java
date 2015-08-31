package com.melinkr.micro.shiro;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;
		private String loginNo;
		private String name;
		private Long id;
		private Long areaId;
		private  String manages;//数据权限
		public String getName() {
			return name;
		}

		public Long getId() {
			return id;
		}
		
		public String getloginNo() {
			return loginNo;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getAreaId() {
			return areaId;
		}

		public void setAreaId(Long areaId) {
			this.areaId = areaId;
		}

		public void setloginNo(String loginNo) {
			this.loginNo = loginNo;
		}

		public void setName(String name) {
			this.name = name;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return loginNo;
		}

		/**
		 * 重载equals,只计算loginNo;
		 */
		@Override
		public int hashCode() {
			return this.loginNo.hashCode();
		}

		/**
		 * 重载equals,只比较loginNo
		 */
		@Override
		public boolean equals(Object obj) {
			if (obj == null || !(obj instanceof ShiroUser))
				return false;
			ShiroUser o = (ShiroUser) obj;
			return StringUtils.equals(loginNo, o.loginNo);
		}

		public String getManages() {
			return manages;
		}

		public void setManages(String manages) {
			this.manages = manages;
		}
		
		
	}