package ru.apzakharov.abstract_crud.controller.crud;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.apzakharov.abstract_crud.service.crud.AbstractCrudService;
import ru.apzakharov.abstract_crud.service.crud.CrudService;

import java.io.Serializable;

public abstract class AbstractCrudController<DTO, ID extends Serializable> {
    private final CrudService<DTO, ID> service;

    public AbstractCrudController(CrudService<DTO, ID> service) {
        this.service = service;
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ID> add(@RequestBody DTO dto) {
        ID id = service.add(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DTO> get(@PathVariable ID id) {
        DTO dto = service.get(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@RequestBody DTO dto, @PathVariable(name = "id") ID id) {
        service.update(dto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }


}
