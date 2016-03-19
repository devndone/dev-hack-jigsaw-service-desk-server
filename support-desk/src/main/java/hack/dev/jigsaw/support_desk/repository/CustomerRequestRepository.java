package hack.dev.jigsaw.support_desk.repository;

import hack.dev.jigsaw.support_desk.entity.CustomerRequest;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "ticket")
public interface CustomerRequestRepository extends CrudRepository<CustomerRequest, Integer> {

	List<CustomerRequest> findByCustomerRequestArchived(@Param("archivedfalse") int customerRequestArchivedFalse);
	
}
