package com.anl.card.vo;

import com.anl.card.persistence.po.SupplierInterfaceItem;

import java.util.Date;

public class SupplierInterfaceItemExt extends SupplierInterfaceItem{

	private String supplierName;

	private String interfaceName;

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
}

