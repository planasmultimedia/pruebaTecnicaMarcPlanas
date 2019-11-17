package com.example.planas.pruebatecnica_marcplanas.HttpUtils;

import android.util.Log;

import com.example.planas.pruebatecnica_marcplanas.Models.BaseStats;
import com.example.planas.pruebatecnica_marcplanas.Models.MainListItem;
import com.example.planas.pruebatecnica_marcplanas.Models.Move;
import com.example.planas.pruebatecnica_marcplanas.Models.PokemonDetails;
import com.example.planas.pruebatecnica_marcplanas.Models.PokemonType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParser {
    private static final String TAG = "JsonParser";


    public static ArrayList<MainListItem> parseList(String response) throws JSONException {
        Log.d(TAG, "parseList: starting ");
        ArrayList<MainListItem> out = new ArrayList<MainListItem>();
        JSONObject full_response = new JSONObject(response);
        JSONArray list = full_response.getJSONArray("results");
        for(int i=0; i<list.length(); i++) {

            MainListItem aux = parseListItem(list.getJSONObject(i));
            out.add(aux);
        }
        return out;
    }

    public static PokemonDetails parseDetails(String response) throws JSONException {
        Log.d(TAG, "parseDetails: starting " + response);
        PokemonDetails out = new PokemonDetails();

        JSONObject o = new JSONObject(response);

        out.setBaseExperience(o.getInt("base_experience"));
        out.setHeight(o.getInt("height"));
        out.setWeight(o.getInt("weight"));
        out.setBaseStats(parseBaseStats(o.getJSONArray("stats")));
        out.setImages(parseImages(o.getJSONObject("sprites")));
        out.setMoves(parseMoves(o.getJSONArray("moves")));
        out.setTypes(parseTypes(o.getJSONArray("types")));

        return out;
    }


    private static ArrayList<PokemonType> parseTypes(JSONArray arr) throws JSONException {
        ArrayList<PokemonType> out = new ArrayList<PokemonType>();
        for(int i=0; i<arr.length(); i++) {
            out.add(new PokemonType(arr.getJSONObject(i).getJSONObject("type").getString("name")));
        }
        return out;
    }


    private static ArrayList<BaseStats> parseBaseStats(JSONArray arr) throws JSONException {
        ArrayList<BaseStats> out = new ArrayList<BaseStats>();
        for(int i=0; i<arr.length(); i++) {
            out.add(parseBaseStat(arr.getJSONObject(i)));
        }
        return out;
    }

    private static BaseStats parseBaseStat(JSONObject o) throws JSONException {
        BaseStats out = new BaseStats();
        out.setName(o.getJSONObject("stat").getString("name"));
        out.setValue(o.getInt("base_stat"));
        return out;
    }

    private static MainListItem parseListItem(JSONObject item) throws JSONException {
        MainListItem out = new MainListItem(item.getString("name"), item.getString("url"));
        return  out;
    }

    private static ArrayList<String> parseImages(JSONObject o) throws JSONException {
        ArrayList<String> out = new ArrayList<String>();
        out.add(o.getString("front_default"));
        out.add(o.getString ("back_default"));

        return out;
    }

    private static ArrayList<Move> parseMoves(JSONArray arr) throws JSONException {
        ArrayList<Move> out = new ArrayList<Move>();
        for(int i=0; i<arr.length(); i++) {

            Move aux = parseMove(arr.getJSONObject(i));
            out.add(aux);
        }
        return out;
    }

    private static Move parseMove(JSONObject o) throws JSONException {

        return new Move(o.getJSONObject("move").getString("name"));
    }

}
