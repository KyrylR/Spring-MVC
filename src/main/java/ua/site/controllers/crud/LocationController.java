package ua.site.controllers.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.site.dao.crud.LocationDAO;
import ua.site.models.crud.Area;
import ua.site.models.crud.Field;
import ua.site.models.crud.Test;

import javax.validation.Valid;

@Controller
@RequestMapping("/locations")
public class LocationController {
    private final LocationDAO locationDAO;

    @Autowired
    public LocationController(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

//    static public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql:///Agriculture");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("password");
//
//        return dataSource;
//    }
//
//    private static Double[] arrayOfEmps = {
//            123.2233,
//            13243.2243,
//            7653.2223
//    };
//
//
//    public static void main(String[] args) {
////        Location[] locations = readJSONArray("src/main/resources/locations.json");
////        Field field = new Field(0, "default", locations);
////        locationDAO.saveField(field);
//
//        try {
//            String url = "jdbc:postgresql:///Agriculture";
//            Connection conn = DriverManager.getConnection(url, "postgres", "password");
//            Statement stmt = conn.createStatement();
//            ResultSet rs;
//
//            rs = stmt.executeQuery("SELECT * FROM field");
//            while (rs.next()) {
//                String lastName = rs.getString("name");
//                var lat = rs.getArray("latitude").getArray();
//                var lon = rs.getArray("longitude").getArray();
//                System.out.println(lastName);
//            }
//            conn.close();
//        } catch (Exception e) {
//            System.err.println("Got an exception! ");
//            System.err.println(e.getMessage());
//        }
//    }

    @GetMapping("/fields")
    public String indexFields(Model model) {
        model.addAttribute("fields", locationDAO.indexFields());
        return "crud/people/index";
    }

    @GetMapping("/areas")
    public String indexArea(Model model) {
        model.addAttribute("areas", locationDAO.indexAreas());
        return "crud/people/index";
    }

    @GetMapping("/tests")
    public String indexTest(Model model) {
        model.addAttribute("tests", locationDAO.indexTests());
        return "crud/people/index";
    }

    @GetMapping("/field/{id}")
    public String showField(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", locationDAO.showField(id));
        return "crud/people/show";
    }

    @GetMapping("/area/{id}")
    public String showArea(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", locationDAO.showArea(id));
        return "crud/people/show";
    }

    @GetMapping("/test/{id}")
    public String showTest(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", locationDAO.showTest(id));
        return "crud/people/show";
    }

    @GetMapping("/newField")
    public String newField(@ModelAttribute("person") Field field) {
        return "crud/people/newField";
    }

    @PostMapping()
    public String createField(@ModelAttribute("person") @Valid Field field,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "crud/people/newField";

        locationDAO.saveField(field);
        return "redirect:/people";
    }

    @GetMapping("/newArea")
    public String newArea(@ModelAttribute("person") Area area) {
        return "crud/people/newArea";
    }

    @PostMapping()
    public String createArea(@ModelAttribute("person") @Valid Area area,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "crud/people/newArea";

        locationDAO.saveArea(area);
        return "redirect:/people";
    }

    @GetMapping("/newTest")
    public String newTest(@ModelAttribute("person") Test test) {
        return "crud/people/newTest";
    }

    @PostMapping()
    public String createTest(@ModelAttribute("person") @Valid Test test,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "crud/people/newTest";

        locationDAO.saveTest(test);
        return "redirect:/people";
    }

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
