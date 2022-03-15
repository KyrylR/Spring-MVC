package ua.site.controllers.crud;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.site.dao.crud.LocationDAO;
import ua.site.services.ModelService;

@Controller
@RequestMapping("/locations")
public class LocationController {
    private final LocationDAO locationDAO;
    private final ModelService modelService;

    private static final String MAIN_MODEL_PAGE = "crud/location/index";
    private static final String SHOW_MODEL_PAGE = "crud/location/show";

    @Autowired
    public LocationController(LocationDAO locationDAO, ModelService modelService) {
        this.locationDAO = locationDAO;
        this.modelService = modelService;
    }

    // ------------ General block --------------

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
        return "redirect:/locations/areas";
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

    // ------------ Sample block --------------
    @GetMapping("/samples")
    public String indexSample(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                              @RequestParam(value = "size", required = false, defaultValue = "10") int size, Model model) {
        model.addAttribute("objects", modelService.getSamples(pageNumber, size, locationDAO));

        return MAIN_MODEL_PAGE;
    }

    @GetMapping("/sample/{id}")
    public String showTest(@PathVariable("id") int id, Model model) {
        model.addAttribute("object", locationDAO.showTest(id));
        return SHOW_MODEL_PAGE;
    }

    @DeleteMapping("/sample/{id}")
    public String deleteSample(@PathVariable("id") int id) {
        locationDAO.deleteSample(id);
        return "redirect:/locations/samples";
    }

//    @GetMapping("/newField")
//    public String newField(@ModelAttribute("person") Field field) {
//        return "crud/people/newField";
//    }
//
//    @PostMapping()
//    public String createField(@ModelAttribute("person") @Valid Field field,
//                              BindingResult bindingResult) {
//        if (bindingResult.hasErrors())
//            return "crud/people/newField";
//
//        locationDAO.saveField(field);
//        return "redirect:/people";
//    }
//
//    @GetMapping("/newArea")
//    public String newArea(@ModelAttribute("person") Area area) {
//        return "crud/people/newArea";
//    }
//
//    @PostMapping()
//    public String createArea(@ModelAttribute("person") @Valid Area area,
//                             BindingResult bindingResult) {
//        if (bindingResult.hasErrors())
//            return "crud/people/newArea";
//
//        locationDAO.saveArea(area);
//        return "redirect:/people";
//    }
//
//    @GetMapping("/newTest")
//    public String newTest(@ModelAttribute("person") Sample sample) {
//        return "crud/people/newTest";
//    }
//
//    @PostMapping()
//    public String createTest(@ModelAttribute("person") @Valid Sample sample,
//                             BindingResult bindingResult) {
//        if (bindingResult.hasErrors())
//            return "crud/people/newTest";
//
//        locationDAO.saveTest(sample);
//        return "redirect:/people";
//    }

//    @GetMapping("/{id}/edit")
//    public String edit(Model model,
//                       @PathVariable("id") int id) {
//        model.addAttribute("person", locationDAO.show(id));
//        return "crud/people/edit";
//    }
//
//    @PatchMapping("/{id}")
//    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
//                         @PathVariable("id") int id) {
//        if (bindingResult.hasErrors())
//            return "crud/people/edit";
//
//        locationDAO.update(id, person);
//        return "redirect:/people";
//    }
//

}
