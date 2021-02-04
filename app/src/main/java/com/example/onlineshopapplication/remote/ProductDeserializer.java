package com.example.onlineshopapplication.remote;

import android.text.Html;

import com.example.onlineshopapplication.model.Product;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductDeserializer implements JsonDeserializer<Product> {
    @Override
    public Product deserialize(JsonElement json, Type typeOfT,
                               JsonDeserializationContext context) throws JsonParseException {
        JsonObject productObject = json.getAsJsonObject();

        int id = productObject.get("id").getAsInt();
        String name = productObject.get("name").getAsString();
        String price = productObject.get("price").getAsString();
        int ratingCount = productObject.get("rating_count").getAsInt();
        String description = Html.fromHtml(
                productObject.get("description").getAsString()).toString();
        String stockStatus = productObject.get("stock_status").getAsString();

        JsonArray images = productObject.get("images").getAsJsonArray();
        List<String> imageUrls = new ArrayList<>();
        for (int j = 0; j < images.size(); j++) {
            JsonObject imageObject = images.get(j).getAsJsonObject();
            imageUrls.add(imageObject.get("src").getAsString());
        }

        return new Product(id, name, price, ratingCount, description, stockStatus, imageUrls);
    }
}
