package Ressources.GameClasses;

import java.util.ArrayList;
import java.util.Random;

public class MajorArcanaCard extends Card{
    private Effect activeEffect;
    private Effect youEffect;
    private Effect eventEffect;
    private Effect personEffect;


    //ENUM WITH VALUES
    private enum MajorArcana{
        THE_FOOL("The Fool", "Blissful Ignorance",3, Effect.DODGE, Effect.SACRIFICE, Effect.B_MP),
        THE_MAGICIAN("The Magician", "Surpassing the plausible",4, Effect.B_WAND, Effect.PROTECT, Effect.B_WAND),
        THE_HIGH_PRIESTESS("The High Priestess", "Hidden influence",5, Effect.N_RAND, Effect.N_MP, Effect.B_MP),
        THE_EMPRESS("The Empress", "Gentle Power",5, Effect.B_RAND, Effect.N_DMG, Effect.DAMAGE),
        THE_EMPEROR("The Emperor", "Supreme Ruler",6, Effect.B_SWORD, Effect.B_DMG, Effect.B_HEAL),
        THE_HIEROPHANT("The Hierophant", "Priest",4, Effect.B_PENT, Effect.STEAL, Effect.ALONE),
        THE_LOVERS("The Lovers", "Love at First Sight",5, Effect.B_CUPS, Effect.SPAWN_LOVER, Effect.ASSISTED_DEATH),
        THE_CHARIOT("The Chariot", "Conqueror",5, Effect.STEAL, Effect.DRAW, Effect.DAMAGE),
        STRENGTH("Strength", "Hero",6, Effect.B_ALL, Effect.SPAWN_STR, Effect.B_ALL),
        THE_HERMIT("The Hermit","Solitude",4, Effect.B_MP, Effect.SPAWN_HERMIT, Effect.SOLITUDE),
        THE_WHEEL_OF_FORTUNE("The Wheel of Fortune", "Fate",3, Effect.REPLACE, Effect.DRAW_Maj, Effect.STEAL),
        JUSTICE("Justice", "Retribution",5, Effect.PARRY, Effect.STEAL, Effect.PARRY),
        THE_HANGED_MAN("The Hanged Man", "Martyr",4, Effect.N_RAND, Effect.DEFLECT, Effect.SUICIDE),
        DEATH("Death", "Grim Reaper",7, Effect.DEATH_DEAL, Effect.GRAVE_ROB, Effect.DAMAGE_ALL),
        TEMPERANCE("Temperance", "Patience",5, Effect.B_TEMP, Effect.CUMULATE_MP, Effect.REFLECT),  
        THE_DEVIL("The Devil","Archfiend",4, Effect.DEFLECT, Effect.DEVIL_DEAL, Effect.ENEMY_ALLY),
        THE_TOWER("The Tower","Collapse",6, Effect.CRUMBLE, Effect.REMEMBER, Effect.B_TOWER),
        THE_STAR("The Star","Distance",7, Effect.DISTANCE, Effect.N_DMG, Effect.STAR_VUL),
        THE_MOON("The Moon","Soul",4, Effect.CONFUSE, Effect.SPAWN_MOON, Effect.MOON_HEAL),
        THE_SUN("The Sun","Ability",6, Effect.B_SUN, Effect.FREE_SPAWN, Effect.BURNING_SURFACE),
        JUDGEMENT("Judgement","Judgement Day",8, Effect.B_HEAL, Effect.DISCARD, Effect.PEEP),
        THE_WORLD("The World","Completion",9, Effect.B_WORLD, Effect.EMPTY_DRAW, Effect.REVIVE);

        private String name;
        private String destiny;
        private int cost;
        private Effect youEffect;
        private Effect eventEffect;
        private Effect personEffect;
        MajorArcana(String name, String destiny, int cost, Effect youEffect, Effect eventEffect, Effect personEffect){
            this.name = name;
            this.destiny = destiny;
            this.cost = cost;
            this.youEffect= youEffect;
            this.eventEffect= eventEffect;
            this.personEffect= personEffect;
        }
    }

    public MajorArcanaCard(MajorArcana majorArc){
        super(majorArc.name,majorArc.destiny,majorArc.cost,0, true, true);
        this.youEffect= majorArc.youEffect;
        this.eventEffect= majorArc.eventEffect;
        this.personEffect= majorArc.personEffect;
    }

    //CONSTRUCTOR
    public static MajorArcanaCard[] populateWithMajCards(){
        MajorArcanaCard[] cards = new MajorArcanaCard[22];
        for(int i=0;i<22;i++){
            for (MajorArcana majorArc : MajorArcana.values()){
                cards[i] = new MajorArcanaCard(majorArc);
            }
        }
        return cards;

    }


    // FONCTION ABSTRAITE D'ACTIVATION
    @Override
    public void placeCard(Slot placementSlot){
        if(placementSlot.isEmpty()){
            placementSlot.addCard(this);
            this.addStats(Effect.STUN);
            if (placementSlot.getslotType() == "You"){
                this.activeEffect = this.youEffect;
            }
            else if (placementSlot.getslotType() == "Event"){
                this.activeEffect = this.eventEffect;
            }
            else if (placementSlot.getslotType() == "Person"){
                this.activeEffect = this.personEffect;
            }
        }
    }

    @Override
    public void useSkill(Card targetCard, Game game, PlayerHalf player){
        Random random = new Random();
        PlayerHalf opponent = game.player1.equals(player) ? game.player2 : game.player1;
        int chance;
        switch(activeEffect){
            case Effect.DODGE:
            {
                chance = random.nextInt(10)/10;
                if(chance==1){
                    this.addStats(activeEffect);
                }
                chance = 0;
                break;
            }
            case Effect.PROTECT:
            {
                player.getYouCard().addStats(activeEffect);
                break;
            }
            case Effect.PARRY:
            {
                chance = random.nextInt(20)/20;
                if(chance==1){
                this.addStats(activeEffect);
                }
                chance = 0;
                break;
            }
            case Effect.ALONE:
            {
                if(!this.isCardAlone(player)){
                    this.addStats(Effect.IMMUNE);
                }
                else{
                    if(this.status.contains(Effect.IMMUNE)){
                        this.removeStats(Effect.IMMUNE);
                    }
                }
                break;
            }
            case Effect.SOLITUDE:
            {
                if(this.isCardAlone(player)){
                    this.varyDamage(1);
                }
                break;
            }
            case Effect.DEFLECT:
            {
                chance = random.nextInt(15)/15;
                if(chance==1){
                this.addStats(activeEffect);
                }
                chance = 0;
                break;
            }
            case Effect.PEEP:
            {
                //Ajouter logique pour voir les cartes ennemies
                break;
            }
            case Effect.REFLECT:
            {
                this.addStats(activeEffect);
                break;
            }
            case Effect.ENEMY_ALLY:
            {
                this.addStats(activeEffect);
                break;
            }
            case Effect.DAMAGE:
            {
                int dmg = random.nextInt(5);
                opponent.getPersons_Field()[random.nextInt(opponent.getOccupiedPersons()-1)].getCard().hurt(dmg,this);
                break;
            }
            case Effect.DAMAGE_ALL:
            {
                opponent.damageAll(this.damage*2, this);
                break;
            }
            case Effect.BURNING_SURFACE:
            {
                break;
            }
            case Effect.B_HEAL:
            {
                break;
            }
            case Effect.B_MP:
            {
                break;
            }
            case Effect.B_DMG:
            {
                break;
            }
            case Effect.B_RAND:
            {
                break;
            }
            case Effect.B_WAND:
            {
                break;
            }
            case Effect.B_SWORD:
            {
                break;
            }
            case Effect.B_PENT:
            {
                break;
            }
            case Effect.B_CUPS:
            {
                break;
            }
            case Effect.B_ALL:
            {
                break;
            }
            case Effect.B_WORLD:
            {
                break;
            }
            case Effect.B_SUN:
            {
                break;
            }
            case Effect.B_TEMP:
            {
                break;
            }
            case Effect.DISTANCE:
            {
                break;
            }
            case Effect.CRUMBLE:
            {
                break;
            }
            case Effect.CUMULATE_MP:
            {
                break;
            }
            case Effect.DEVIL_DEAL:
            {
                break;
            }
            case Effect.MOON_HEAL:
            {
                break;
            }
            case Effect.REMEMBER:
            {
                break;
            }
            case Effect.B_TOWER:
            {
                break;
            }
            case Effect.N_HEAL:
            {
                break;
            }
            case Effect.N_MP:{
                break;
            }
            case Effect.N_DMG:
            {
                break;
            }
            case Effect.N_RAND:
            {
                break;
            }
            case Effect.STAR_VUL:
            {
                break;
            }
            case Effect.EMPTY_DRAW:
           {
                break;
            }
            case Effect.DRAW:
            {
                break;
            }
            case Effect.DRAW_Min:
            {
                break;
            }
            case Effect.DRAW_Maj:
            {
                break;
            }
            case Effect.STEAL:
            {
                break;
            }
            case Effect.RETURN:
            {
                break;
            }
            case Effect.DISCARD:
            {
                break;
            }
            case Effect.REPLACE:
            {
                break;
            }
            case Effect.FREE_SPAWN:
            {
                break;
            }
            case Effect.SPAWN_SELF:
            {
                break;
            }
            case Effect.SPAWN_LOVER:
            {
                break;
            }
            case Effect.SPAWN_STR:
            {
                break;
            }
            case Effect.SPAWN_HERMIT:
            {
                break;
            }
            case Effect.SPAWN_MOON:
            {
                break;
            }
            case Effect.REVIVE:{
                break;
            }
            case Effect.GRAVE_ROB:
            {
                break;
            }
            case Effect.STUN:
            {
                break;
            }
            case Effect.CONFUSE:
            {
                break;
            }
            case Effect.SACRIFICE:
            {
                break;
            }
            case Effect.DEATH_DEAL:
            {
                break;
            }
            case Effect.SUICIDE:{
                break;
            }
            default:
            {
                break;
            }
                }
    }

    @Override
    public void attackCard(Slot slotTarget, Game game, PlayerHalf player){
        PlayerHalf opponent = game.player1.equals(player) ? game.player2 : game.player1;
        if (!slotTarget.isEmpty()) {
            
        }
    }
}



