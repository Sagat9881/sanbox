package ru.apzakharov.abstract_crud.service;

import com.querydsl.core.types.Predicate;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
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
import ru.apzakharov.abstract_crud.domain.base.EntityWithId;
import ru.apzakharov.abstract_crud.dto.ListResult;
import ru.apzakharov.abstract_crud.dto.OffsetLimitPageRequest;
import ru.apzakharov.abstract_crud.mapper.ExtendedEntityMapper;
import ru.apzakharov.abstract_crud.repository.BaseJpaRepository;
import ru.apzakharov.abstract_crud.util.ReflectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


public abstract class AbstractCrudService<DTO, ID extends Serializable, ENTITY extends EntityWithId<ID>> {
    protected BaseJpaRepository<ENTITY, ID> repository;
    protected ExtendedEntityMapper<ENTITY, ID, DTO> mapper;

    private ConversionService conversionService;
    private QuerydslBindingsFactory bindingsFactory;
    private QuerydslPredicateBuilder predicateBuilder;

    private final Class<?> domainType;
    private final ClassTypeInformation<?> typeInformation;

    @Autowired
    public void setRepository(BaseJpaRepository<ENTITY, ID> repository) {
        this.repository = repository;
    }

    @Autowired
    public void setMapper(ExtendedEntityMapper<ENTITY, ID, DTO> mapper) {
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

    protected AbstractCrudService() {
        domainType = ReflectionUtils.getGenericParameterClass(this.getClass(), AbstractCrudService.class, 0);
        typeInformation = ClassTypeInformation.from(domainType);
    }

    @PostConstruct
    private void afterPropertiesSet() {
        predicateBuilder = new QuerydslPredicateBuilder(conversionService, bindingsFactory.getEntityPathResolver());
    }


    @Transactional
    public ID add(@RequestBody DTO dto) {
        ENTITY entity = mapper.dtoToEntity(dto);
        return repository.save(entity).getId();
    }


    @Transactional(readOnly = true)
    public DTO get(@PathVariable ID id) {
        return repository.findById(id)
                .map(mapper::entityToDto)
                .orElseThrow(RuntimeException::new);
    }

    @Transactional
    public void update(@RequestBody DTO dto, @PathVariable(name = "id") ID id) {
        ENTITY entity = repository.findById(id).orElseThrow(RuntimeException::new);

        ENTITY newEntity = mapper.dtoToEntity(dto);
        BeanUtils.copyProperties(newEntity, entity);
    }

    @Transactional
    public void delete(@PathVariable ID id) {
        repository.deleteById(id);
    }


    @Transactional(readOnly = true)
    public ListResult<DTO> getList( MultiValueMap<String, String> params, Integer skip, Integer take, Sort sort) {

        QuerydslBindings bindings = bindingsFactory.createBindingsFor(typeInformation);
        // {url}/resource?path.to.field>0 && path.to.another.field=5
        Predicate predicate = predicateBuilder.getPredicate(typeInformation, params, bindings);

        sort = sort != null && sort.isSorted() ? sort : OffsetLimitPageRequest.DEFAULT_SORT;

        Page<ENTITY> page = repository.findAll(predicate, new OffsetLimitPageRequest(skip, take, sort));

        List<DTO> collect = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return new ListResult<>(page.getTotalElements(), collect);
    }

    public String getObjectType() {
        return domainType.getSimpleName();
    }

}
