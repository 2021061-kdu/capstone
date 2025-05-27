package com.example.myapplication;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class IngredientRepository {

    public static List<Ingredient> getAllIngredients() {
        List<Ingredient> result = new ArrayList<>();
        result.addAll(convertArrayToList(IngredientData.FRUITS));
        result.addAll(convertArrayToList(IngredientData.VEGETABLES));
        result.addAll(convertArrayToList(IngredientData.DAIRY));
        result.addAll(convertArrayToList(IngredientData.MEAT));
        result.addAll(convertArrayToList(IngredientData.SEAFOOD));
        result.addAll(convertArrayToList(IngredientData.CONDIMENTS));
        result.addAll(convertArrayToList(IngredientData.ETC));
        return result;
    }

    private static List<Ingredient> convertArrayToList(String[] array) {
        List<Ingredient> list = new ArrayList<>();
        Calendar today = Calendar.getInstance();

        for (int i = 0; i < array.length; i += 2) {
            String korean = array[i];
            int imageResId = IngredientData.getImageResource(korean);

            Ingredient ingredient = new Ingredient(
                    korean,
                    1,
                    "개",
                    "오늘",
                    (Calendar) today.clone(),
                    "냉장",      // 임시 설정값
                    imageResId
            );

            list.add(ingredient);
        }

        return list;
    }
}