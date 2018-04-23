package com.km.stickers.model.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TrieNode {

    private Map<String, TrieNode> nodes = new HashMap<>();
    private Set<Sticker> stickers = new HashSet<>();

    public Map<String, TrieNode> getNodes() {
        return nodes;
    }

    public void setNodes(Map<String, TrieNode> nodes) {
        this.nodes = nodes;
    }

    public Set<Sticker> getStickers() {
        return stickers;
    }

    public void setStickers(Set<Sticker> stickers) {
        this.stickers = stickers;
    }

    public void addSticker(Sticker sticker) {
        stickers.add(sticker);
    }
}
