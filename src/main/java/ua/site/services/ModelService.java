package ua.site.services;

import org.springframework.stereotype.Service;
import ua.site.dao.crud.LocationDAO;
import ua.site.models.crud.Area;
import ua.site.models.crud.Paging.Page;
import ua.site.models.crud.Paging.Paged;
import ua.site.models.crud.Paging.Paging;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelService {
    public ModelService() {
    }

    // ------------ Area block --------------
    public Paged<Area> getAreas(int pageNumber, int size, LocationDAO locationDAO) {
        List<Area> areas = locationDAO.indexAreas();

        List<Area> paged = areas.stream()
                .skip(pageNumber)
                .limit(size)
                .collect(Collectors.toList());

        int totalPages = areas.size() / size;
        return new Paged<>(new Page<>(paged, totalPages), Paging.of(totalPages, pageNumber, size));
    }
}
