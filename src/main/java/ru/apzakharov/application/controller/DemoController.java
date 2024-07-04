package ru.apzakharov.application.controller;

import org.springframework.web.bind.annotation.RestController;
import ru.apzakharov.abstract_crud.controller.crud.AbstractCrudController;
import ru.apzakharov.abstract_crud.controller.dto.DemoDto;
import ru.apzakharov.application.service.DemoService;
@RestController
public class DemoController extends AbstractCrudController<DemoDto,Integer> {
    public DemoController(DemoService service) {
        super(service);
    }
}
