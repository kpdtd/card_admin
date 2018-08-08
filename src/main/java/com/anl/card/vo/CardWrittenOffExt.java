package com.anl.card.vo;

import com.anl.card.persistence.po.Card;
import com.anl.card.persistence.po.CardWrittenOff;
import com.anl.card.persistence.po.SupplierPool;

public class CardWrittenOffExt extends CardWrittenOff{
	public CardWrittenOffExt(){
		
	}

	public CardWrittenOffExt(Card card) {
	    super(card);
    }

	private String ownerCompany;
	private String supplierName;

	public String getOwnerCompany() {
		return ownerCompany;
	}

	public void setOwnerCompany(String ownerCompany) {
		this.ownerCompany = ownerCompany;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
}

