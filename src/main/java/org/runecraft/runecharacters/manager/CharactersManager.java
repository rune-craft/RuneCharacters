package org.runecraft.runecharacters.manager;

import org.runecraft.runecharacters.Character;
import org.runecraft.runecharacters.atributes.Abiliity;
import org.runecraft.runecharacters.atributes.Stat;
import org.runecraft.runecharacters.atributes.enums.AbiliityType;
import org.runecraft.runecharacters.atributes.enums.StatType;
import org.runecraft.runecharacters.enums.CharacterClass;
import org.runecraft.runecore.User;
import org.runecraft.runecore.db.Atribute;
import org.runecraft.runecore.db.DataBase;
import org.runecraft.runecore.db.enums.DatabaseOperation;
import org.runecraft.runecore.db.enums.Table;
import org.runecraft.runeelements.HiddenElement;
import org.runecraft.runeelements.PrimalElement;
import org.spongepowered.api.scheduler.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.function.Consumer;

public class CharactersManager {
    private Set<Character> characters = new HashSet<>();

    public Set<Character> getCharacters() { return characters; }

    public Set<Character> getCharacters(User user){
        Set<Character> chars = new HashSet<>();
        characters.forEach(u -> {
            if(u.getOwner().equals(user)){
                chars.add(u);
            }
        });
        return chars;
    }

    public void downloadCharacters(){
        try{
            Statement st = DataBase.getConnection().createStatement();
            st.executeQuery("SELECT * FROM" + Table.CHARACTERS.getName());

            characters.clear();

            ResultSet rs = st.executeQuery("SELECT * FROM " + Table.CHARACTERS.getName());

            while(rs.next()){
                Character.Builder builder = Character.builder()
                        .setCharacterClass(CharacterClass.by(rs.getInt(Atribute.CharactersAtributes.CLASS.getName())).get())
                        .setLevel(rs.getInt(Atribute.CharactersAtributes.LEVEL.getName()))
                        .setOwner(User.by(UUID.fromString(rs.getString(Atribute.CharactersAtributes.USER.getName()))).get())
                        .setPrimalElement(PrimalElement.by(rs.getInt(Atribute.CharactersAtributes.PRIMARY_ELEMENT.getName())))
                        //.setHiddenElement(HiddenElement.)
                        .setUUID(UUID.fromString(rs.getString(Atribute.CharactersAtributes.UUID.getName())))
                        .setStat(StatType.DEXTERITY, rs.getInt(Atribute.CharactersAtributes.DEXTERITY.getName()))
                        .setStat(StatType.STRENGHT, rs.getInt(Atribute.CharactersAtributes.STRENGHT.getName()))
                        .setStat(StatType.DEFENSE, rs.getInt(Atribute.CharactersAtributes.DEFENSE.getName()))
                        .setStat(StatType.INTELLIGENCE, rs.getInt(Atribute.CharactersAtributes.INTELLIGENCE.getName()))
                        .setStat(StatType.AGILITY, rs.getInt(Atribute.CharactersAtributes.AGILITY.getName()));

                characters.add(builder.build());

            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void createCharacter(User owner, CharacterClass clazz, PrimalElement primalElement){
        Map<StatType, Stat> stats = new HashMap<>();
        Arrays.stream(StatType.values()).map(x -> stats.put(x,new Stat(0)));
        Map<AbiliityType, Abiliity> abilities = new HashMap<>();
        Arrays.asList(AbiliityType.values()).stream().map(x -> abilities.put(x,new Abiliity(0,0)));

        final UUID uid = UUID.randomUUID();

        Character.Builder charBuilder = Character.builder();

        characters.add(charBuilder.setRegister(true).setOwner(owner).setCharacterClass(clazz).setPrimalElement(primalElement).build());

        /*Task.Builder task = Task.builder().async().execute(
                () ->{
                    try{
                        PreparedStatement ps = DataBase.getConnection().prepareStatement(Table.CHARACTERS.getBuildString(DatabaseOperation.INSERT));
                        ps.setString(1, owner.getUUID().toString());
                        ps.setString(2, uid.toString());
                        ps.setInt(3, clazz.getId());
                        ps.setInt(4, primalElement.getId());
                        ps.setInt(5, 0);
                        ps.setInt(6, 1);
                        ps.setInt(7, 1);
                        ps.setInt(8, 1);
                        ps.setInt(9, 1);
                        ps.setInt(10, 1);
                        ps.setInt(11, 1);

                        ps.executeUpdate();
                    }catch (SQLException ex){
                        ex.printStackTrace();
                    }
                }
        );*/
    }
}
