package com.molot.developer.graphqloverview.resolvers;


import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.molot.developer.graphqloverview.entities.Attack;
import com.molot.developer.graphqloverview.entities.Pokemon;
import com.molot.developer.graphqloverview.entities.Type;
import com.molot.developer.graphqloverview.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class RootMutationResolver implements GraphQLMutationResolver {
    private final PokemonRepository pokemonRepository;

    @Autowired
    public RootMutationResolver(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public Pokemon newPokemon(String name, Type type, Integer age, List<Map<String, String>> attacksInput) {
        Pokemon pokemon = new Pokemon(name);
        pokemon.setAge(age);
        pokemon.setType(type);

        if (attacksInput != null) {
            List<Attack> attacks = new ArrayList<>();
            for (Map<String, String> input : attacksInput) {
                Attack attack = new Attack(input.get("name"));
                if (input.get("damage") != null) {
                    attack.setDamage(Integer.valueOf(input.get("damage")));
                }
                attack.setPokemon(pokemon);
                attacks.add(attack);
            }
            pokemon.setAttacks(attacks);
        }

        pokemonRepository.save(pokemon);

        return pokemon;
    }

    public Pokemon newPokemonWithAttacks(Map<String, Object> input) {
        Pokemon pokemon = new Pokemon((String) input.get("name"));
        pokemon.setAge((Integer) input.get("age"));
        pokemon.setType(Type.valueOf((String) input.get("type")));
        List<?> attacksInput = (List) input.get("attacksInput");
        if (attacksInput != null) {
            List<Attack> attacks = new ArrayList<>();
            for (Object element : attacksInput) {
                if (element instanceof Map<?, ?>) {
                    Map<?, ?> map = (Map<?, ?>) element;
                    Attack attack = new Attack((String) map.get("name"));
                    attack.setPokemon(pokemon);
                    attack.setDamage((Integer) map.get("damage"));
                    attacks.add(attack);
                }
            }
            pokemon.setAttacks(attacks);
        }

        pokemonRepository.save(pokemon);

        return pokemon;
    }
}
