package com.molot.developer.graphqloverview.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.molot.developer.graphqloverview.entities.Attack;
import com.molot.developer.graphqloverview.entities.Pokemon;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Component
public class PokemonResolver implements GraphQLResolver<Pokemon> {

    public List<Attack> getAttacks(Pokemon pokemon) {
        return !CollectionUtils.isEmpty(pokemon.getAttacks()) ? pokemon.getAttacks() : Collections.singletonList(new Attack("Unknown"));
    }
}
