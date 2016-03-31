package domain.webservices;

import domain.core.Report;
import domain.repository.ReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
public class CoreWebService {

    @Autowired ReportsRepository reportsRepository;

    @RequestMapping("/{reportId}")
    public List<String> getLocations(@PathVariable long reportId) {

        Report report = reportsRepository.findById(reportId);

        List<String> reportLocations = report.getLocations();

        return reportLocations;
    }

}
