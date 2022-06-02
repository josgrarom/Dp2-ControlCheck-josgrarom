package acme.features.inventor.chimpum;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpamDetector.SpamDetector;
import acme.currencyExchangeFunction.ExchangeMoneyFunctionService;
import acme.entities.chimpums.Chimpum;
import acme.entities.initialConfiguration.InitialConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorChimpumUpdateService implements AbstractUpdateService<Inventor,Chimpum>{
	

	@Autowired
	protected InventorChimpumRepository repository;
	
	@Autowired
	protected ExchangeMoneyFunctionService exchangeService;

	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;

		boolean result;
		int chimpumId;
		Chimpum chimpum;

		chimpumId = request.getModel().getInteger("id");
		chimpum = this.repository.findOneChimpumById(chimpumId);
		result = chimpum.getItem().getInventor().getId() == request.getPrincipal().getActiveRoleId();
		
		return result;
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "title", "description","creationMoment", "budget", "startDate", "endDate", "moreInfo");
		
		
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code","title", "description","creationMoment", "budget", "startDate", "endDate", "moreInfo");
		
		model.setAttribute("pattern", entity.getCode().substring(0,3));
		
	}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		assert request != null;
		Chimpum result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneChimpumById(id);
		
		return result;
	}

	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

        final InitialConfiguration initialConfig = this.repository.getSystemCofig();
        final String Strong = initialConfig.getStrongSpamTerms();
        final String Weak = initialConfig.getWeakSpamTerms();

        final double StrongT = initialConfig.getStrongSpamTreshold();
        final double WeakT = initialConfig.getWeakSpamTreshold();
        
        String pattern;

        pattern = request.getModel().getString("pattern");

        errors.state(request, pattern.matches("[A-Z]{3}"), "pattern", "inventor.chimpum.error.pattern");
		
        if(!errors.hasErrors("title")) {
            final boolean res;

            res = SpamDetector.spamDetector(entity.getTitle(),Strong,Weak,StrongT,WeakT);

            errors.state(request, res, "title", "any.chirp.form.error.spam");

        }
        
        if(!errors.hasErrors("description")) {
            final boolean res;

            res = SpamDetector.spamDetector(entity.getDescription(),Strong,Weak,StrongT,WeakT);

            errors.state(request, res, "description", "any.chirp.form.error.spam");

        }
        
        if(!errors.hasErrors("moreInfo")) {
            final boolean res;

            res = SpamDetector.spamDetector(entity.getMoreInfo(),Strong,Weak,StrongT,WeakT);

            errors.state(request, res, "moreInfo", "any.chirp.form.error.spam");

        }
		
		
		if(!errors.hasErrors("budget")) {
			
			final List<String> currencies = new ArrayList<>();
			
			String currency;
			
			for(final String c: this.repository.acceptedCurrencies().split(",")) {
				currencies.add(c.trim());
			}
			
			currency = entity.getBudget().getCurrency();
			
			errors.state(request, currencies.contains(currency) , "budget","patron.patronage.form.error.currency");
			
		}
		if(entity.getStartDate()!=null) {
		if(!errors.hasErrors("startDate")) {
			
			Date startDate;
			
			startDate = entity.getStartDate();
			
			final long diff = startDate.getTime() - entity.getCreationMoment().getTime();

	        final TimeUnit time = TimeUnit.DAYS; 
	        final long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
	        
	        errors.state(request, diffrence>=30 , "startDate","inventor.chimpum.form.error.startDate");
			
			
		}
		
		if(!errors.hasErrors("endDate")) {
			
			Date endDate;
			
			endDate = entity.getEndDate();
			
			final long diff = endDate.getTime() - entity.getStartDate().getTime();

	        final TimeUnit time = TimeUnit.DAYS; 
	        final long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
	        
	        errors.state(request, diffrence>=7 , "endDate","inventor.chimpum.form.error.endDate");
			
			
		}
		}
		if(!errors.hasErrors("budget")) {
			
			Money budget;
			
			budget = entity.getBudget();
			
			
			errors.state(request, budget.getAmount()>0 , "budget","patron.patronage.form.error.amount");
			
		}
		
		
	}

	@Override
	public void update(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
		
		String pattern;
		pattern = request.getModel().getString("pattern");
		final LocalDate cm =  entity.getCreationMoment().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		entity.setCode(pattern + "-" + this.generateCode(cm));

		this.repository.save(entity);
		
	}

	public String generateCode(final LocalDate creationMoment) {
		String res = "";
		Integer day;
		Integer month;
		Integer year;
		
		day =creationMoment.getDayOfMonth();
		month =creationMoment.getMonthValue();
		year =creationMoment.getYear();
		String tYear= "";
		String tDay= "";
		String tMonth= "";
		
		tYear = year.toString().substring(2, 4);
		
		if(day.toString().length()==1) {
			
			tDay = "0" +day.toString();
			
			
		}
		
		else{

			tDay = day.toString();
			
		}
		
		if(month.toString().length()==1) {
			
			tMonth = "0" +month.toString();
			
			
		}
		
		else{
			
			tMonth = month.toString();
			
			
		}
		
		res= tYear + "-" + tMonth + "-" + tDay;
		
		return res;
		
		
		
	}
}
