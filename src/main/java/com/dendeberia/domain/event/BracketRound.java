package com.dendeberia.domain.event;

import com.dendeberia.domain.event.BracketNode;

import java.util.List;

public class BracketRound {

    private String name;
    private List<BracketNode> nodes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BracketNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<BracketNode> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return "BracketRound{" +
                "name='" + name + '\'' +
                ", nodes=" + nodes +
                '}';
    }
}
