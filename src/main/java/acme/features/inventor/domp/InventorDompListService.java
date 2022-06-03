package acme.features.inventor.domp;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.currencyExchangeFunction.ExchangeMoneyFunctionService;
import acme.entities.domps.Domp;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorDompListService implements AbstractListService<Inventor, Domp> {

	@Autowired
	protected InventorDompRepository repository;
	
	@Autowired
	protected ExchangeMoneyFunctionService exchangeService;


	@Override
	public boolean authorise(final Request<Domp> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Domp> findMany(final Request<Domp> request) {
		assert request != null;
		
		final Collection<Domp> result;
		
		
		result = this.repository.findDompsByInventorId(request.getPrincipal().getActiveRoleId());

		return result;

	}

	@Override
	public void unbind(final Request<Domp> request, final Domp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "subject","helping", "startDate","endDate");

	}

}