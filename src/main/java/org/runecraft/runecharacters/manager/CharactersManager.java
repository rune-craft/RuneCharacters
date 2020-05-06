package org.runecraft.runecharacters.manager;

import org.runecraft.runecharacters.Character;
import org.runecraft.runecore.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CharactersManager {
    private Set<Character> characters = new HashSet<>();

    public Set<Character> getCharacters(User user){
        Set<Character> chars = new HashSet<>();
        characters.forEach(u -> {
            if(u.getOwner().equals(user)){
                chars.add(u);
            }
        });
        return chars;
    }
}
