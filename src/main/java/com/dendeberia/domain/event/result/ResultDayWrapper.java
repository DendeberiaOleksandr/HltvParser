package com.dendeberia.domain.event.result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResultDayWrapper {
    private Date date;
    private List<ResultRowWrapper> results = new ArrayList<>();

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<ResultRowWrapper> getResults() {
        return results;
    }

    public void setResults(List<ResultRowWrapper> results) {
        this.results = results;
    }
}
