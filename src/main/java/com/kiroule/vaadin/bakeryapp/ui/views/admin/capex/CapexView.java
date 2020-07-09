package com.kiroule.vaadin.bakeryapp.ui.views.admin.capex;

import com.kiroule.vaadin.bakeryapp.app.security.CurrentUser;
import com.kiroule.vaadin.bakeryapp.backend.data.Role;
import com.kiroule.vaadin.bakeryapp.backend.data.entity.Capex;
import com.kiroule.vaadin.bakeryapp.backend.service.CapexService;
import com.kiroule.vaadin.bakeryapp.ui.MainView;
import com.kiroule.vaadin.bakeryapp.ui.crud.AbstractBakeryCrudView;
import com.kiroule.vaadin.bakeryapp.ui.utils.BakeryConst;
import com.kiroule.vaadin.bakeryapp.ui.utils.converters.CurrencyFormatter;
import com.vaadin.flow.component.crud.BinderCrudEditor;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import java.util.Currency;

import static com.kiroule.vaadin.bakeryapp.ui.utils.BakeryConst.PAGE_CAPEX;

@Route(value = PAGE_CAPEX, layout = MainView.class)
@PageTitle(BakeryConst.TITLE_CAPEX)
@Secured(Role.ADMIN)
public class CapexView extends AbstractBakeryCrudView<Capex> {

	private CurrencyFormatter currencyFormatter = new CurrencyFormatter();

	@Autowired
	public CapexView(CapexService service, CurrentUser currentUser) {
		super(Capex.class, service, new Grid<>(), createForm(), currentUser);
	}

	@Override
	protected void setupGrid(Grid<Capex> grid) {
		grid.addColumn(Capex::getName).setHeader("Capex Name").setFlexGrow(10);
	}

	@Override
	protected String getBasePage() {
		return PAGE_CAPEX;
	}

	private static BinderCrudEditor<Capex> createForm() {
		TextField name = new TextField("Capex name");
		name.getElement().setAttribute("colspan", "2");

		FormLayout form = new FormLayout(name);

		BeanValidationBinder<Capex> binder = new BeanValidationBinder<>(Capex.class);

		binder.bind(name, "name");

		return new BinderCrudEditor<Capex>(binder, form);
	}

}
