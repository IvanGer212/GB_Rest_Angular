package com.geekbrains.ru.gb_rest_angular.core.dto;

import java.util.List;

public class PageDto<E> {
    private List<E> items;
    private int page;
    private int totalPages;

    public PageDto() {
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<E> getItems() {
        return items;
    }

    public void setItems(List<E> items) {
        this.items = items;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "PageDto{" +
                "items=" + items +
                ", page=" + page +
                ", totalPages=" + totalPages +
                '}';
    }
}
