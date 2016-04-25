package me.martelli.lab.mybimby.recipes;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;

import com.commonsware.cwac.provider.StreamProvider;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import me.martelli.lab.mybimby.R;

public class RecipeUtils {
    public static List<Recipe> getDummyRecipes() {
        return Arrays.asList(
                new Recipe.Builder("Ciambella della nonna", "file:///android_asset/images/ciambella.jpg")
                        .setBaseInfo(10, 40, BaseInfo.DIFFICULTY_MEDIUM, 1)
                        .addIngredientsBlock("350 g di farina 00 + q.b.",
                                "300 g di zucchero semolato + q.b.", "1 bustina di lievito per dolci",
                                "½ cucchiaino di sale", "100 g di olio di semi di girasole + q.b.",
                                "3 uova", "Latte q.b.")
                        .addUtensil("Tortiera a ciambella (⌀ 26cm)").addUtensil("Pennellino")
                        .addStepsBlock("Accendere il forno a 180 °C.",
                                "Mettere farina, zucchero, lievito e sale nel boccale e far " +
                                        "mescolare per <b>3 sec./vel. 1</b>.",
                                "Mettere l'olio e frullare <b>5 min./vel. 3</b>, aggiungendo le uova " +
                                        "una alla volta. Aggiungere latte finché il composto non " +
                                        "diventa abbastanza tenero. Montare <b>1 min./vel. 5</b>.",
                                "Ungere la tortiera con un po' di olio aiutandosi col pennellino, " +
                                        "e rimuovere l'olio in eccesso. Infarinare e aggiungere " +
                                        "il composto, spolverando con zucchero.",
                                "Mettere la rola nel forno preriscaldato e far cuocere 15 minuti, " +
                                        "poi mettere a 150 °C ventilato, e far cuocere per altri " +
                                        "15 minuti.")
                        .addVariant("Per una versione dietetica, mettere 200 g di zucchero e 2 uova, " +
                                "e un po' più di latte.")
                        .build(),
                new Recipe.Builder("Gnocchi di patate", "file:///android_asset/images/gnocchi.jpg")
                        .setBaseInfo(40, 75, BaseInfo.DIFFICULTY_MEDIUM, 6)
                        .addIngredientsBlock("50 g di Parmigiano reggiano a pezzi (3 cm)",
                                "800 g di patate farinose a fette", "1000 g di acqua",
                                "300-350 g di farina + q.b.", "1 uovo (da 60 g circa)",
                                "1½-2 cucchiaini di sale, a seconda del gusto personale")
                        .addUtensil("Spianatoia").addUtensil("Forchetta")
                        .addUtensil("Pentola capiente").addUtensil("Mestolo forato")
                        .addStepsBlock("Mettere nel boccale il Parmigiano e grattugiare " +
                                "<b>15 sec./vel. 10</b>. Trasferire in una ciotola e tenere da parte.",
                                "Mettere nel boccale l'acqua, posizionare il Varoma, distribuire " +
                                        "nel recipiente del Varoma le patate a fette e cuocere: " +
                                        "<b>30 min./Varoma/vel. 5</b>.",
                                "Nel boccale svuotato dall'acqua mettere 50 g di farina e le " +
                                        "patate, frullare: <b>20 sec./vel. 5</b>.",
                                "Aggiungere il resto della farina, l'uovo, il Parmigiano reggiano " +
                                        "grattugiato e il sale, impastare: <b>20 sec./vel. 3</b>.",
                                "Trasferire l'impasto sulla spianatoia infarinata, formare una " +
                                        "palla con le mani infarinate.",
                                "Prendere piccole porzioni di impasto e, lavorando sulla spianatoia " +
                                        "infarinata, formare dei rotolini di pasta di ⌀ 2 cm. " +
                                        "Tagliare dei pezzetti di 1,5cm formando in questo modo " +
                                        "tanti piccoli gnocchi. Passare gli gnocchi sui rebbi di " +
                                        "una forchetta formando al centro una fossetta e dando loro " +
                                        "la caratteristica decorazione rigata.",
                                "Cuocere gli gnocchi in abbondante acqua salata, fino a quando " +
                                        "affiorano in superficie, quindi scolare con un mestolo " +
                                        "forato e appoggiarli nel recipiente del Varoma. Servire " +
                                        "caldi e condire a piacere.")
                        .addAdvice("Si consiglia di usare patate farinose (tipo Bologna) e di " +
                                "adattare comunque la quantità di farina in base al grado di " +
                                "umidità delle patate utilizzate.")
                        .addAdvice("Condire in una pirofila con sugo di pomodoro oppure burro fuso, " +
                                "salvia e Parmigiano reggiano grattugiato o altro condimento a piacere.")
                        .addVariant("<b>Gnocchi verdi:</b> diminuire la quantità di patate a 600 g " +
                                "e aggiungere 200 g di foglie di spinaci o erbette cotte e ben " +
                                "strizzate. Dopo il passaggio 1 frullare gli spinaci 10 sec./vel. 8. " +
                                "Riunire sul fondo con la spatola e procedere come descritto dal " +
                                "passaggio 2, aumentando il quantitativo totale della farina a 400 g.")
                        .addVariant("<b>Gnocchi di zucca:</b> diminuire la quantità di patate a " +
                                "500 g e aggiungere 300 g di zucca cotta al forno o al vapore. " +
                                "Dopo il passaggio 1 frullare la zucca 10 sec./vel. 8. Riunire " +
                                "sul fondo con la spatola e procedere come descritto dal passagio 2, " +
                                "aumentando il quantitativo totale di farina a 400 g.")
                        .build()
        );
    }

    public static String toJson(List<Recipe> recipes) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Recipe>>() {}.getType();
        return gson.toJson(recipes, type);
    }

    public static List<Recipe> fromJson(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Recipe>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static String formatMinutes(int minutes) {
        int min = minutes % 60;
        int hrs = minutes / 60;

        String ret = "";

        if(hrs != 0) {
            ret += hrs + " h ";
        }

        if(min != 0) {
            ret += min + " min.";
        }

        return ret.trim();
    }

    public static String formatDifficulty(@BaseInfo.Difficulty int difficulty) {
        switch (difficulty) {
            case BaseInfo.DIFFICULTY_EASY: return "Easy";
            case BaseInfo.DIFFICULTY_MEDIUM: return "Medium";
            case BaseInfo.DIFFICULTY_HARD: return "Hard";
            default: return "Undefined";
        }
    }

    public static Spanned formatHtml(String string) {
        return Html.fromHtml(string);
    }

    public static Intent shareRecipe(Context ctx, Recipe recipe) {
        String text = "";

        for(IngredientsList ingredientsList : recipe.getIngredientsBlocks()) {
            String heading = ingredientsList.getTitle();
            if(heading == null) {
                heading = ctx.getString(R.string.ingredients);
            }

            text += appendText(ingredientsList.getIngredients(), heading);
        }

        if(recipe.getUtensils().size() != 0) {
            text += appendText(recipe.getUtensils(), ctx.getString(R.string.utensils));
        }

        for(StepsList stepsList : recipe.getStepsBlocks()) {
            String heading = stepsList.getTitle();
            if(heading == null) {
                heading = ctx.getString(R.string.steps);
            }

            text += appendText(stepsList.getSteps(), heading);
        }

        if(recipe.getAdvices().size() != 0) {
            text += appendText(recipe.getAdvices(), ctx.getString(R.string.advices));
        }

        if(recipe.getVariants().size() != 0) {
            text += appendText(recipe.getVariants(), ctx.getString(R.string.variants));
        }

        text += "<br>\n#mybimby";

        Spanned html = Html.fromHtml(text);

        String image = recipe.getImage();
        String base = "file:///android_asset/images/";

        Uri stream = Uri.parse("content://me.martelli.lab.mybimby").buildUpon()
                .appendPath(StreamProvider.getUriPrefix("me.martelli.lab.mybimby"))
                .appendPath("assets/" + TextUtils.substring(image, base.length(), image.length()))
                .build();

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, recipe.getName());
        sharingIntent.putExtra(Intent.EXTRA_TEXT, html.toString());
        sharingIntent.putExtra(Intent.EXTRA_HTML_TEXT, html);
        sharingIntent.putExtra(Intent.EXTRA_STREAM, stream);
        sharingIntent.setType("text/plain");
        sharingIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        return sharingIntent;
    }

    private static String appendText(List<String> list, String title) {
        String text = title + ":<br>\n";
        for(String s : list) {
            text += s + "<br>\n";
        }
        return text + "<br>\n";
    }
}
