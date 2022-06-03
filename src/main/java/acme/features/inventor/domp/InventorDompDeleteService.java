package acme.features.inventor.domp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.domps.Domp;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorDompDeleteService  implements AbstractDeleteService<Inventor,Domp>  {

	@Autowired
	protected InventorDompRepository repository;
	
	@Override
	public boolean authorise(final Request<Domp> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Domp> request, final Domp entity, final Errors errors) {
		request.bind(entity, errors, "code", "subject","summary", "helping", "creationMoment", "startDate", "endDate", "furtherInfo");

		
	}

	@Override
	public void unbind(final Request<Domp> request, final Domp entity, final Model model) {
		request.unbind(entity, model,"code", "subject","summary", "helping", "creationMoment", "startDate", "endDate", "furtherInfo");

		
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
	public void validate(final Request<Domp> request, final Domp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<Domp> request, final Domp entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
		
	}

}
