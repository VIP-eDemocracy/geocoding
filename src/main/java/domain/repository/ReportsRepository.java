package domain.repository;

import domain.core.Report;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportsRepository extends MongoRepository<Report, String> {

    public Report findById(long id);

}
