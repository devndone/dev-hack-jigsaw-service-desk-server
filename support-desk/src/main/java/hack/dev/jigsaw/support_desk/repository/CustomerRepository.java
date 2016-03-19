package hack.dev.jigsaw.support_desk.repository;

import hack.dev.jigsaw.support_desk.entity.Customer;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "customer")
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

	List<Customer> findByCustomerArchived(@Param("archivedfalse") int customerArchivedFalse);
	
}

