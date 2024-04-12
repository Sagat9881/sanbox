package ru.apzakharov.abstract_crud.controller;

import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.apzakharov.abstract_crud.dto.ListResult;
import ru.apzakharov.abstract_crud.service.AbstractCrudService;

import java.io.Serializable;

public abstract class AbstractCrudController<DTO, ID extends Serializable> {
    private final AbstractCrudService<DTO, ID, ?> service;

    public AbstractCrudController(AbstractCrudService<DTO, ID, ?> service) {
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

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public ResponseEntity<ListResult<DTO>> getList(
            // {url}/resource?path.to.field>0 && path.to.another.field=5
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "skip", required = false, defaultValue = "0")
            Integer skip,
            @RequestParam(name = "take", required = false, defaultValue = "20")
            Integer take,
            @SortDefault(sort = "id", direction = Sort.Direction.DESC) Sort sort) {

        ListResult<DTO> resultList = service.getList(params, skip, take, sort);

        return ResponseEntity.ok(resultList);
    }
}
