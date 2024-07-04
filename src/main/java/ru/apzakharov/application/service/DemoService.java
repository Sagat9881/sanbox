package ru.apzakharov.application.service;

import org.springframework.stereotype.Service;
import ru.apzakharov.abstract_crud.controller.dto.DemoDto;
import ru.apzakharov.abstract_crud.service.crud.AbstractCrudService;
import ru.apzakharov.application.repository.DemoRepository;
import ru.apzakharov.application.repository.entity.DemoEntity;
import ru.apzakharov.application.service.mapper.DemoServiceMapper;

@Service
public class DemoService extends AbstractCrudService<DemoDto,Integer, DemoEntity> {

    protected DemoService(DemoRepository repository,
                          DemoServiceMapper mapper) {
        super(repository,  mapper);
    }
}
