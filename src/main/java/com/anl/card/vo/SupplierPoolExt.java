package com.anl.card.vo;

import com.anl.card.persistence.po.SupplierPool;

public class SupplierPoolExt extends SupplierPool{

	private String supplierName;
	private Integer poolTotal;

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getPoolTotal() {
		return poolTotal;
	}

	public void setPoolTotal(Integer poolTotal) {
		this.poolTotal = poolTotal;
	}
}

