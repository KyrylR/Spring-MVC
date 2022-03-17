package ua.site.services;

import org.springframework.stereotype.Service;
import ua.site.Paging.Page;
import ua.site.Paging.Paged;
import ua.site.Paging.Paging;
import ua.site.dao.crud.LocationDAO;
import ua.site.models.crud.Area;
import ua.site.models.crud.Field;
import ua.site.models.crud.Sample;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelService {
    // ------------ Area block --------------
    public Paged<Area> getAreas(int pageNumber, int size, LocationDAO locationDAO) {
        List<Area> areas = locationDAO.indexArea();

        List<Area> paged = areas.stream()
                .limit(size)
                .collect(Collectors.toList());

        int totalPages = areas.size() / size;
        return new Paged<>(new Page<>(paged, totalPages), Paging.of(totalPages, pageNumber, size));
    }

    // ------------ Field block --------------
    public Paged<Field> getFields(int pageNumber, int size, LocationDAO locationDAO) {
        List<Field> fields = locationDAO.indexField();

        List<Field> paged = fields.stream()
                .limit(size)
                .collect(Collectors.toList());

        int totalPages = fields.size() / size;
        return new Paged<>(new Page<>(paged, totalPages), Paging.of(totalPages, pageNumber, size));
    }

    // ------------ Sample block --------------
    public Paged<Sample> getSamples(int pageNumber, int size, LocationDAO locationDAO) {
        List<Sample> samples = locationDAO.indexSample();

        List<Sample> paged = samples.stream()
                .limit(size)
                .collect(Collectors.toList());

        int totalPages = samples.size() / size;
        return new Paged<>(new Page<>(paged, totalPages), Paging.of(totalPages, pageNumber, size));
    }
}
