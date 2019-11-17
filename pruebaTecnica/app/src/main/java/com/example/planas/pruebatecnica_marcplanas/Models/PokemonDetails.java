package com.example.planas.pruebatecnica_marcplanas.Models;

import java.util.ArrayList;

public class PokemonDetails {

    private ArrayList<BaseStats> baseStats;
    private ArrayList<PokemonType> types;
    private int weight;
    private int baseExperience;
    private int height;
    private ArrayList<Move> moves;
    private ArrayList<String> images;

    public PokemonDetails(ArrayList<BaseStats> baseStats, ArrayList<PokemonType> types, int weight, int baseExperience, int height, ArrayList<Move> moves, ArrayList<String> images) {
        this.baseStats = baseStats;
        this.types = types;
        this.weight = weight;
        this.baseExperience = baseExperience;
        this.height = height;
        this.moves = moves;
        this.images = images;
    }

    public PokemonDetails(){

    }

    public ArrayList<BaseStats> getBaseStats() {
        return baseStats;
    }

    public void setBaseStats(ArrayList<BaseStats> baseStats) {
        this.baseStats = baseStats;
    }

    public String getTypes() {
        String a = "";
        if(types != null){
            for(int i=0; i<this.types.size(); i++) {
                if(i != types.size()-1) {
                    a += types.get(i).getTypeName() + " , ";
                }else{
                    a += types.get(i).getTypeName();
                }
            }
        }
        return a;
    }

    public void setTypes(ArrayList<PokemonType> types) {
        this.types = types;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getMoves(){
            String a = "";
            if(types != null){

                for(int i=0; i<this.moves.size(); i++) {
                    if(i != moves.size()-1){
                        a += moves.get(i).getMoveName() + " , ";
                    }else{
                        a += moves.get(i).getMoveName();
                    }
                }
            }
            return a;
    }

    public void setMoves(ArrayList<Move> moves) {
        this.moves = moves;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
}
