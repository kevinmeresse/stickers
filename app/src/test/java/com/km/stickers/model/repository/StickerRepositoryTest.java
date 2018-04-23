package com.km.stickers.model.repository;

import com.km.stickers.io.StickerProviderTest;
import com.km.stickers.model.entity.TrieNode;

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class StickerRepositoryTest {

    @Test
    public void checkParser() {
        // Check when payload is null
        assertNull(StickerRepository.parseStickers(null));

        // Get JSON payload example
        JSONObject json = StickerProviderTest.getStickersAsJson();
        assertNotNull(json);

        // Parse payload and check result
        TrieNode dictionary = StickerRepository.parseStickers(json);
        assertNotNull(dictionary);
        assertEquals(8, dictionary.getNodes().size());

        // Check created nodes
        assertNull(dictionary.getNodes().get("yoo"));
        assertNotNull(dictionary.getNodes().get("omw!"));

        // Check nested items
        assertEquals(1, dictionary.getNodes().get("omw!").getStickers().size());
        assertEquals(2, dictionary.getNodes().get("ready").getStickers().size());
        assertEquals(0, dictionary.getNodes().get("want").getStickers().size());
        assertEquals(1, dictionary.getNodes().get("want").getNodes().get("coffee").getStickers().size());
        assertEquals(1, dictionary.getNodes().get("want").getNodes().get("pizza").getStickers().size());
        assertEquals(0, dictionary.getNodes().get("ready").getNodes().size());
        assertEquals(2, dictionary.getNodes().get("want").getNodes().size());
    }
}