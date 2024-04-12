package ru.apzakharov.abstract_crud;

import com.querydsl.core.types.Predicate;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.QuerydslBindingsFactory;
import org.springframework.data.querydsl.binding.QuerydslPredicateBuilder;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.apzakharov.abstract_crud.dto.ListResult;
import ru.apzakharov.abstract_crud.mapper.ExtendedEntityMapper;
import ru.apzakharov.abstract_crud.repository.BaseJpaRepository;
import ru.apzakharov.abstract_crud.util.ReflectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User: ialekseev
 * Date: 21.12.2021
 * Time: 16:55
 */
public abstract class AbstractCrudController<T extends Persistable<ID>, ID extends Serializable, D> {
    protected BaseJpaRepository<T, ID> repository;
    protected ExtendedEntityMapper<T, ID, D> mapper;

    private ConversionService conversionService;
    private QuerydslBindingsFactory bindingsFactory;
    private QuerydslPredicateBuilder predicateBuilder;

    private final Class<?> domainType;
    private final ClassTypeInformation<?> typeInformation;

    @Autowired
    public void setRepository(BaseJpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Autowired
    public void setMapper(ExtendedEntityMapper<T, ID, D> mapper) {
        this.mapper = mapper;
    }

    @Autowired
    @Qualifier("predicateConversionService")
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Autowired
    public void setBindingsFactory(QuerydslBindingsFactory bindingsFactory) {
        this.bindingsFactory = bindingsFactory;
    }

    protected AbstractCrudController() {
        domainType = ReflectionUtils.getGenericParameterClass(this.getClass(),AbstractCrudController.class,0 );
        typeInformation = ClassTypeInformation.from(domainType);
    }

    @PostConstruct
    private void afterPropertiesSet() {
        predicateBuilder = new QuerydslPredicateBuilder(conversionService, bindingsFactory.getEntityPathResolver());
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<ID> add(@RequestBody D dto) {
        T entity = mapper.dtoToEntity(dto);
        repository.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity.getId());
    }

    @GetMapping(value = "/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<D> get(@PathVariable ID id) {
        T t = repository.findById(id).orElseThrow(RuntimeException::new);
        D d = mapper.entityToDto(t);
        return ResponseEntity.ok(d);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Void> update(@RequestBody D dto, @PathVariable(name = "id") ID id) {
        T t = repository.findById(id).orElseThrow(RuntimeException::new);

        T newT = mapper.dtoToEntity(dto);
        BeanUtils.copyProperties(newT, t);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public ResponseEntity<ListResult<D>> getList(@RequestParam MultiValueMap<String, String> params,
                                                 @RequestParam(name = "skip", required = false, defaultValue = "0")
                                                         Integer skip,
                                                 @RequestParam(name = "take", required = false, defaultValue = "20")
                                                         Integer take,
                                                 @SortDefault(sort = "id", direction = Sort.Direction.DESC) Sort sort) {

        QuerydslBindings bindings = bindingsFactory.createBindingsFor(typeInformation);

        Predicate predicate = predicateBuilder.getPredicate(typeInformation, params, bindings);

        sort = sort != null && sort.isSorted() ? sort : OffsetLimitPageRequest.DEFAULT_SORT;

        Page<T> page = repository.findAll(predicate, new OffsetLimitPageRequest(skip, take, sort));

        List<D> collect = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return ResponseEntity.ok(new ListResult<>(page.getTotalElements(), collect));
    }

    public String getObjectType() {
        return domainType.getSimpleName();
    }

}
