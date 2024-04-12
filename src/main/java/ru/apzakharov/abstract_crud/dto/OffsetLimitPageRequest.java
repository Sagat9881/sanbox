package ru.apzakharov.abstract_crud.dto;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public class OffsetLimitPageRequest implements Pageable {
    public static final Sort DEFAULT_SORT = Sort.by(Sort.Direction.DESC, "id");

    private int offset;
    private int limit;

    private Sort sort;

    public OffsetLimitPageRequest(int offset, int limit) {
        this(offset, limit, DEFAULT_SORT);
    }

    public OffsetLimitPageRequest(int offset, int limit, Sort sort) {
        if (limit < 1) {
            throw new IllegalArgumentException("Limit must not be less than one!");
        }
        if (offset < 0) {
            throw new IllegalArgumentException("Offset index must not be less than zero!");
        }
        this.offset = offset;
        this.limit = limit;

        this.sort = sort;
    }

    @Override
    public int getPageNumber() {
        return offset / limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new OffsetLimitPageRequest((int) (getOffset() + getPageSize()), getPageSize(), sort);
    }

    public Pageable previous() {
        return hasPrevious() ?
                new OffsetLimitPageRequest((int) (getOffset() - getPageSize()), getPageSize(), sort) : this;
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Override
    public Pageable first() {
        return new OffsetLimitPageRequest(0, getPageSize(), sort);
    }

    @Override
    public boolean hasPrevious() {
        return offset >= limit;
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return null;
    }
}
