package acme.features.inventor.domp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.currencyExchangeFunction.ExchangeMoneyFunctionService;
import acme.entities.domps.Domp;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorDompShowService implements AbstractShowService<Inventor, Domp> {

	@Autowired
	protected InventorDompRepository repository;
	
	@Autowired
	protected ExchangeMoneyFunctionService exchangeService;

	@Override
	public boolean authorise(final Request<Domp> request) {
		assert request != null;

		boolean result;
		int dompId;
		Domp domp;

		dompId = request.getModel().getInteger("id");
		domp = this.repository.findOneDompById(dompId);
		result = domp.getItem().getInventor().getId() == request.getPrincipal().getActiveRoleId();
		
		return result;
	}

	@Override
	public Domp findOne(final Request<Domp> request) {
		assert request != null;
		Domp result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneDompById(id);


		return result;
	}

	@Override
	public void unbind(final Request<Domp> request, final Domp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final String defaultCurrency;
		MoneyExchange exchange;
		boolean isExchange;
		
		defaultCurrency = this.repository.defaultCurrency();
		exchange = this.exchangeService.computeMoneyExchange(entity.getHelping(), defaultCurrency);
		isExchange = ! entity.getHelping().getCurrency().equals(exchange.target.getCurrency());

		request.unbind(entity, model, "code", "subject", "summary", "helping", "creationMoment", "startDate", "endDate", "furtherInfo");
		
		model.setAttribute("exchange", exchange.target);
		model.setAttribute("isExchange", isExchange);
		model.setAttribute("itemId", entity.getItem().getId());
		model.setAttribute("pattern", entity.getCode().substring(0,3));

	}

}