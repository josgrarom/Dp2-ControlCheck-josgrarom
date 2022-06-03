package acme.features.inventor.domp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.domps.Domp;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorDompController extends AbstractController<Inventor, Domp>{
 
	
	@Autowired
	protected InventorDompListService listService;

	@Autowired
	protected InventorDompShowService showService;
	
	@Autowired
	protected InventorDompCreateService createService;
	
	@Autowired
	protected InventorDompUpdateService updateService;

	@Autowired
	protected InventorDompDeleteService deleteService;

	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list", this.listService);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
	}

}