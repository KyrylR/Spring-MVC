package ua.site.controllers.crud;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.site.dao.crud.LocationDAO;
import ua.site.services.ModelService;

@Controller
@RequestMapping("/locations")
public class LocationController {
    private final LocationDAO locationDAO;
    private final ModelService modelService;

    @Autowired
    public LocationController(LocationDAO locationDAO, ModelService modelService) {
        this.locationDAO = locationDAO;
        this.modelService = modelService;
    }

    // ------------ Area block --------------
    @GetMapping("/areas")
    public String indexArea(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                            @RequestParam(value = "size", required = false, defaultValue = "10") int size, Model model) {
        model.addAttribute("objects", modelService.getAreas(pageNumber, size, locationDAO));

        return "crud/location/index";
    }


    @GetMapping("/fields")
    public String indexFields(Model model) {
        model.addAttribute("objects", locationDAO.indexFields());
        return "crud/index";
    }
//
//    @GetMapping("/areas")
//    public String indexArea(Model model) {
//        model.addAttribute("objects", locationDAO.indexAreas());
//        return "crud/index";
//    }
//
//    @GetMapping("/tests")
//    public String indexTest(Model model) {
//        model.addAttribute("objects", locationDAO.indexTests());
//        return "crud/index";
//    }
//
//    @GetMapping("/field/{id}")
//    public String showField(@PathVariable("id") int id, Model model) {
//        model.addAttribute("object", locationDAO.showField(id));
//        return "crud/location/show";
//    }
//
//    @GetMapping("/area/{id}")
//    public String showArea(@PathVariable("id") int id, Model model) {
//        model.addAttribute("object", locationDAO.showArea(id));
//        return "crud/location/show";
//    }
//
//    @GetMapping("/sample/{id}")
//    public String showTest(@PathVariable("id") int id, Model model) {
//        model.addAttribute("object", locationDAO.showTest(id));
//        return "crud/location/show";
//    }

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
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") int id) {
//        locationDAO.delete(id);
//        return "redirect:/people";
//    }
}
