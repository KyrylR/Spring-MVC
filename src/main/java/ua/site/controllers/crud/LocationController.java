package ua.site.controllers.crud;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ua.site.dao.crud.LocationDAO;
import ua.site.logic.SampleToFieldMatcher;
import ua.site.models.crud.Area;
import ua.site.models.crud.Display;
import ua.site.models.crud.Field;
import ua.site.models.crud.Sample;
import ua.site.services.ModelService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/locations")
public class LocationController {
    private static final String MAIN_MODEL_PAGE = "crud/location/index";
    private static final String SHOW_MODEL_PAGE = "crud/location/show";
    private static final String NEW_MODEL_PAGE = "crud/location/new";
    private static final String EDIT_MODEL_PAGE = "crud/location/edit";
    private final LocationDAO locationDAO;
    private final ModelService modelService;

    @Autowired
    public LocationController(LocationDAO locationDAO, ModelService modelService) {
        this.locationDAO = locationDAO;
        this.modelService = modelService;
    }

    private static String afterActionRedirection(Display object) {
        return String.format("redirect:/locations/%ss", object.getObject());
    }


    // ------------ Area block --------------
    @GetMapping("/areas")
    public String indexArea(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                            @RequestParam(value = "size", required = false, defaultValue = "10") int size, Model model) {
        model.addAttribute("objects", modelService.getAreas(pageNumber, size, locationDAO));

        return MAIN_MODEL_PAGE;
    }

    @GetMapping("/area/{id}")
    public String showArea(@PathVariable("id") int id, Model model) {
        model.addAttribute("object", locationDAO.showArea(id));
        return SHOW_MODEL_PAGE;
    }

    @DeleteMapping("/area/{id}")
    public String deleteArea(@PathVariable("id") int id) {
        locationDAO.deleteArea(id);
        return afterActionRedirection(new Area());
    }

    @GetMapping("/area/new")
    public String newArea(@ModelAttribute("model_obj") Area area) {
        return NEW_MODEL_PAGE;
    }

    @PostMapping("/area")
    public String createArea(@ModelAttribute("model_obj") @Valid Area area,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return NEW_MODEL_PAGE;


        locationDAO.saveArea(area);
        return afterActionRedirection(area);
    }

    @GetMapping("/area/{id}/edit")
    public String editArea(Model model,
                           @PathVariable("id") int id) {
        model.addAttribute("model_obj", locationDAO.showArea(id));
        return EDIT_MODEL_PAGE;
    }

    @PatchMapping("/area/{id}")
    public String updateArea(@ModelAttribute("model_obj") @Valid Area area, BindingResult bindingResult,
                             @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return EDIT_MODEL_PAGE;

        locationDAO.updateArea(id, area);
        return afterActionRedirection(area);
    }


    // ------------ Field block --------------
    @GetMapping("/fields")
    public String indexField(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                             @RequestParam(value = "size", required = false, defaultValue = "10") int size, Model model) {
        model.addAttribute("objects", modelService.getFields(pageNumber, size, locationDAO));

        return MAIN_MODEL_PAGE;
    }

    @GetMapping("/field/{id}")
    public String showField(@PathVariable("id") int id, Model model) {
        model.addAttribute("object", locationDAO.showField(id));
        return SHOW_MODEL_PAGE;
    }

    @DeleteMapping("/field/{id}")
    public String deleteField(@PathVariable("id") int id) {
        locationDAO.deleteField(id);
        return "redirect:/locations/fields";
    }

    @GetMapping("/field/new")
    public String newField(@ModelAttribute("model_obj") Field field, Model model) {
        model.addAttribute("options", locationDAO.indexArea());
        return NEW_MODEL_PAGE;
    }

    @PostMapping("/field")
    public String createField(@ModelAttribute("model_obj") @Valid Field field,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("options", locationDAO.indexArea());
            return NEW_MODEL_PAGE;
        }

        field.setArea(locationDAO.showArea(field.getAreaId()));
        locationDAO.saveField(field);
        return afterActionRedirection(field);
    }

    @GetMapping("/field/{id}/edit")
    public String editField(Model model,
                            @PathVariable("id") int id) {
        model.addAttribute("model_obj", locationDAO.showField(id));
        model.addAttribute("options", locationDAO.indexArea());
        return EDIT_MODEL_PAGE;
    }

    @PatchMapping("/field/{id}")
    public String updateField(@ModelAttribute("model_obj") @Valid Field field, BindingResult bindingResult,
                              @PathVariable("id") int id, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("options", locationDAO.indexArea());
            return EDIT_MODEL_PAGE;
        }

        field.setArea(locationDAO.showArea(field.getAreaId()));
        locationDAO.updateField(id, field);
        return afterActionRedirection(field);
    }

    // ------------ Sample block --------------
    @GetMapping("/samples")
    public String indexSample(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                              @RequestParam(value = "size", required = false, defaultValue = "10") int size, Model model) {
        model.addAttribute("objects", modelService.getSamples(pageNumber, size, locationDAO));

        return MAIN_MODEL_PAGE;
    }

    @GetMapping("/sample/{id}")
    public String showTest(@PathVariable("id") int id, Model model) {
        model.addAttribute("object", locationDAO.showSample(id));
        return SHOW_MODEL_PAGE;
    }

    @DeleteMapping("/sample/{id}")
    public String deleteSample(@PathVariable("id") int id) {
        locationDAO.deleteSample(id);
        return "redirect:/locations/samples";
    }

    @GetMapping("/sample/new")
    public String newSample(@ModelAttribute("model_obj") Sample sample, Model model) {
        model.addAttribute("options", locationDAO.indexArea());
        return NEW_MODEL_PAGE;
    }

    @PostMapping("/sample")
    public String createSample(@ModelAttribute("model_obj") @Valid Sample sample,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("options", locationDAO.indexArea());
            return NEW_MODEL_PAGE;
        }
        List<Field> fields = locationDAO.findFieldByAreaId(sample.getAreaId());
        Field field = SampleToFieldMatcher.match(fields, sample);
        if (field == null) {
            bindingResult.addError(new ObjectError("", "Sample coordinates do not match any field!"));
            model.addAttribute("options", locationDAO.indexArea());
            return NEW_MODEL_PAGE;
        }

        sample.setField(field);
        locationDAO.saveSample(sample);
        return afterActionRedirection(sample);
    }

    @GetMapping("/sample/{id}/edit")
    public String editSample(Model model,
                             @PathVariable("id") int id) {
        model.addAttribute("model_obj", locationDAO.showSample(id));
        model.addAttribute("options", locationDAO.indexArea());
        return EDIT_MODEL_PAGE;
    }

    @PatchMapping("/sample/{id}")
    public String updateSample(@ModelAttribute("model_obj") @Valid Sample sample, BindingResult bindingResult,
                               @PathVariable("id") int id, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("options", locationDAO.indexArea());
            return EDIT_MODEL_PAGE;
        }

        List<Field> fields = locationDAO.findFieldByAreaId(sample.getAreaId());
        Field field = SampleToFieldMatcher.match(fields, sample);
        if (field == null) {
            bindingResult.addError(new ObjectError("", "Sample coordinates do not match any field!"));
            model.addAttribute("options", locationDAO.indexArea());
            return EDIT_MODEL_PAGE;
        }

        sample.setField(field);
        locationDAO.updateSample(id, sample);
        return afterActionRedirection(sample);
    }
}
