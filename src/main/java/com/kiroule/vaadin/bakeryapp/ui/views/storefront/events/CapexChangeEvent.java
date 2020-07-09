package com.kiroule.vaadin.bakeryapp.ui.views.storefront.events;

import com.kiroule.vaadin.bakeryapp.backend.data.entity.Capex;
import com.kiroule.vaadin.bakeryapp.backend.data.entity.Product;
import com.kiroule.vaadin.bakeryapp.ui.views.orderedit.OrderItemEditor;
import com.vaadin.flow.component.ComponentEvent;

public class CapexChangeEvent extends ComponentEvent<OrderItemEditor> {

	private final Capex capex;

	public CapexChangeEvent(OrderItemEditor component, Capex capex) {
		super(component, false);
		this.capex = capex;
	}

	public Capex getProduct() {
		return capex;
	}

}