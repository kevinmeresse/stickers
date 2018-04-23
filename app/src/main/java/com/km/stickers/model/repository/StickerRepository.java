package com.km.stickers.model.repository;

import android.content.Context;

import com.km.stickers.io.StickerProvider;
import com.km.stickers.model.entity.Sticker;
import com.km.stickers.model.entity.TrieNode;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class StickerRepository {

    private static StickerRepository instance;

    private Executor executor;
    private TrieNode dictionary;

    private StickerRepository() {}

    public static StickerRepository getInstance(Context context) {
        if (instance == null) {
            instance = new StickerRepository();
            instance.executor = Executors.newSingleThreadExecutor();
            instance.generateStickers(context);
        }
        return instance;
    }

    private void generateStickers(final Context context) {
        executor.execute(() -> {
            if (dictionary == null) {
                dictionary = parseStickers(StickerProvider.getStickersAsJson(context));
            }
        });
    }

    public static TrieNode parseStickers(JSONObject json) {
        if (json == null) return null;

        TrieNode rootNode = new TrieNode();
        Iterator<String> it = json.keys();
        if (it == null) return null;

        while (it.hasNext()) {
            String key = it.next();
            try {
                JSONObject jsonSticker = json.getJSONObject(key);
                Sticker sticker = new Sticker(key);

                // Assets
                JSONArray jsonAssets = jsonSticker.getJSONArray("assets");
                for (int i = 0; i < jsonAssets.length(); i++) {
                    JSONObject jsonAsset = jsonAssets.getJSONObject(i);
                    if ("thumb".equals(jsonAsset.getString("size"))) {
                        sticker.setThumbnailUrl(jsonAsset.getString("url"));
                    } else if ("full".equals(jsonAsset.getString("size"))) {
                        sticker.setFullSizeUrl(jsonAsset.getString("url"));
                    }
                }

                // Make sure we have a thumbnail and a full size image
                if (StringUtils.isEmpty(sticker.getThumbnailUrl())
                        || StringUtils.isEmpty(sticker.getFullSizeUrl())) {
                    continue;
                }

                // Tags
                JSONArray jsonTags = jsonSticker.getJSONArray("tags");
                for (int i = 0; i < jsonTags.length(); i++) {
                    String tag = jsonTags.getString(i);
                    TrieNode currentNode = rootNode;
                    String[] words = tag.trim().toLowerCase().split(" ");
                    for (int j = 0; j < words.length; j++) {
                        String word = words[j];
                        boolean isLastWord = (j == words.length - 1);
                        if (!StringUtils.isEmpty(word)) {
                            if (!currentNode.getNodes().containsKey(word)) {
                                currentNode.getNodes().put(word, new TrieNode());
                            }
                            currentNode = currentNode.getNodes().get(word);
                            if (isLastWord) {
                                currentNode.addSticker(sticker);
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return rootNode;
    }

    public Set<Sticker> search(String input) {
        // Check if input is empty
        if (input == null || input.length() == 0) return null;

        // Check if we have data in dictionary
        if (dictionary == null
                || dictionary.getNodes() == null
                || dictionary.getNodes().size() == 0) {
            return null;
        }

        // Initialize result set
        Set<Sticker> result = new HashSet<>();

        // Split input into words
        String[] words = input.trim().toLowerCase().split(" ");

        TrieNode previousNode = null;
        for (String word : words) {
            TrieNode matchingNode = null;

            // First try to find a match in the previous found node (in case of composite tag)
            if (previousNode != null) {
                matchingNode = previousNode.getNodes().get(word);

                // In case nothing matched, try the same word trimmed from punctuation ('?', '!')
                if (matchingNode == null) {
                    String trimmedWord = StringUtils.strip(word, "?!");
                    matchingNode = previousNode.getNodes().get(trimmedWord);
                }
            }

            // If not found, search at the root of dictionary
            if (matchingNode == null) {
                matchingNode = dictionary.getNodes().get(word);

                // In case nothing matched, try the same word trimmed from punctuation ('?', '!')
                if (matchingNode == null) {
                    String trimmedWord = StringUtils.strip(word, "?!");
                    matchingNode = dictionary.getNodes().get(trimmedWord);
                }
            }

            // If found a matching node, add related stickers to result
            if (matchingNode != null) {
                result.addAll(matchingNode.getStickers());
                previousNode = matchingNode;
            }
        }

        return result;
    }
}
