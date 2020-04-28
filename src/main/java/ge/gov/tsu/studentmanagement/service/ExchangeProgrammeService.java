package ge.gov.tsu.studentmanagement.service;

import ge.gov.tsu.studentmanagement.entity.ExchangeProgramme;
import ge.gov.tsu.studentmanagement.exception.TsuException;
import ge.gov.tsu.studentmanagement.pojo.ExchangeProgrammePojo;
import ge.gov.tsu.studentmanagement.repository.ExchangeProgrammeRepository;
import ge.gov.tsu.studentmanagement.rest.request.ActionPerformer;
import ge.gov.tsu.studentmanagement.rest.request.RequestObject;
import ge.gov.tsu.studentmanagement.specification.view.ExchangeProgrammeSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExchangeProgrammeService {

    private ExchangeProgrammeRepository exchangeProgrammeRepository;

    @Autowired
    public ExchangeProgrammeService(ExchangeProgrammeRepository exchangeProgrammeRepository) {
        this.exchangeProgrammeRepository = exchangeProgrammeRepository;
    }

    public Page<ExchangeProgramme> search(RequestObject<ExchangeProgrammePojo> ro) {
        ExchangeProgrammePojo exchangeProgrammePojo = ro.getData();

        Specifications<ExchangeProgramme> spec = Specifications.where(ExchangeProgrammeSpecification.hasRecord());
        if (exchangeProgrammePojo.getName() != null) {
            spec = spec.and(ExchangeProgrammeSpecification.nameLike(exchangeProgrammePojo.getName()));
        }

        return exchangeProgrammeRepository.findAll(spec, ro.getPaging());
    }


    public ExchangeProgramme add(RequestObject<ExchangeProgrammePojo> ro) {
        ExchangeProgrammePojo exchangeProgrammePojo = ro.getData();
        ExchangeProgramme exchangeProgramme = new ExchangeProgramme();
        exchangeProgramme.setName(exchangeProgrammePojo.getName());
        exchangeProgramme.setNote(exchangeProgrammePojo.getNote());
        exchangeProgramme.setCreatedAt(new Date());

        List<ExchangeProgramme> allProgrammes = findAll(new Sort(Sort.Direction.DESC, "orderNumber"));
        ExchangeProgramme ep = allProgrammes.get(0);
        if (ep != null) {
            exchangeProgramme.setOrderNumber(ep.getOrderNumber() + 1);
        }

        return exchangeProgrammeRepository.save(exchangeProgramme);
    }

    public ExchangeProgramme edit(RequestObject<ExchangeProgrammePojo> ro) throws TsuException {
        ExchangeProgrammePojo exchangeProgrammePojo = ro.getData();

        ExchangeProgramme edited = null;
        List<ExchangeProgramme> allProgrammes = findAll(new Sort(Sort.Direction.ASC, "orderNumber"));

        Integer correctOrderNumber = exchangeProgrammePojo.getOrderNumber();
        if (correctOrderNumber > allProgrammes.size()) correctOrderNumber = allProgrammes.size();
        if (correctOrderNumber < 1) correctOrderNumber = 1;
        for (int i = 0; i < allProgrammes.size(); i++) {
            ExchangeProgramme programme = allProgrammes.get(i);
            if (programme.getId().equals(exchangeProgrammePojo.getId())) {
                edited = programme;
                if (exchangeProgrammePojo.getName() != null) programme.setName(exchangeProgrammePojo.getName());
                if (exchangeProgrammePojo.getNote() != null) programme.setNote(exchangeProgrammePojo.getNote());
                programme.setOrderNumber(correctOrderNumber);
                allProgrammes.remove(i);
                allProgrammes.add(correctOrderNumber - 1, programme);
            }
        }
        for (int i = 0; i < allProgrammes.size(); i++) {
            allProgrammes.get(i).setOrderNumber(i + 1);
        }
        if (edited == null) throw new TsuException("ExchangeProgramme Not Found");
        exchangeProgrammeRepository.save(allProgrammes);
        return edited;

    }

    public List<ExchangeProgramme> findAll(Sort sort) {
        Specifications<ExchangeProgramme> spec = Specifications.where(ExchangeProgrammeSpecification.hasRecord());
        return exchangeProgrammeRepository.findAll(spec, sort);
    }

    public boolean delete(Long id) {
        exchangeProgrammeRepository.delete(id);
        return true;
    }

}
