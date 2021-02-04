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

public class ProductListDeserializer implements JsonDeserializer<List<Product>> {
    @Override
    public List<Product> deserialize(JsonElement json, Type typeOfT,
                                     JsonDeserializationContext context) throws JsonParseException {

        List<Product> products = new ArrayList<>();
        JsonArray bodyArray = json.getAsJsonArray();
        for (int i = 0; i < bodyArray.size(); i++) {
            JsonObject productObject = bodyArray.get(i).getAsJsonObject();

            int id = productObject.get("id").getAsInt();
            String name = productObject.get("name").getAsString();
            String price = productObject.get("regular_price").getAsString();
            String averageRating = productObject.get("average_rating").getAsString();
            String description = Html.fromHtml(
                    productObject.get("description").getAsString()).toString();
            String stockStatus = productObject.get("stock_status").getAsString();

            JsonArray images = productObject.get("images").getAsJsonArray();
            List<String> imageUrls = new ArrayList<>();
            for (int j = 0; j < images.size(); j++) {
                JsonObject imageObject = images.get(j).getAsJsonObject();
                imageUrls.add(imageObject.get("src").getAsString());
            }

            Product product = new Product(
                    id, name, price, averageRating, description, stockStatus, imageUrls);
            products.add(product);
        }
        return products;
    }
}

