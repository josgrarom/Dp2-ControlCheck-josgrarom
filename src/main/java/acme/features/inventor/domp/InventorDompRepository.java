package acme.features.inventor.domp;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.domps.Domp;
import acme.entities.initialConfiguration.InitialConfiguration;
import acme.entities.items.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorDompRepository extends AbstractRepository {

	@Query("select i from Item i where i.id = :id")
	Item findOneItemById(int id);

	@Query("select c from Domp c where c.id = :id")
	Domp findOneDompById(int id);

	
	@Query("select c from Domp c where c.item.inventor.id =:id")
	Collection<Domp> findDompsByInventorId(int id);
	
	@Query("select c.defaultCurrency from InitialConfiguration c")
	String defaultCurrency();
	
	@Query("select initialConfiguration from InitialConfiguration initialConfiguration")
    InitialConfiguration getSystemCofig();
	
	@Query("select p from Domp p where p.code = :code")
	Domp findOneDompByCode(String code);
	
	@Query("SELECT ic.acceptedCurrencies FROM InitialConfiguration ic")
	String acceptedCurrencies();
	

}