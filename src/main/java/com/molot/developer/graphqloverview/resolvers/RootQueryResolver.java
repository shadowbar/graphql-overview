package com.molot.developer.graphqloverview.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.molot.developer.graphqloverview.entities.Attack;
import com.molot.developer.graphqloverview.entities.Pokemon;
import com.molot.developer.graphqloverview.repositories.AttackRepository;
import com.molot.developer.graphqloverview.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RootQueryResolver implements GraphQLQueryResolver {

    private final PokemonRepository pokemonRepository;
    private final AttackRepository attackRepository;

    @Autowired
    public RootQueryResolver(PokemonRepository pokemonRepository, AttackRepository attackRepository) {
        this.pokemonRepository = pokemonRepository;
        this.attackRepository = attackRepository;
    }

    public List<Pokemon> getPokemons() {
        return pokemonRepository.findAll();
    }

    public Pokemon getPokemon(String name) {
        Pokemon pokemon = pokemonRepository.findByName(name);
        return pokemon != null ? pokemon : new Pokemon("Unknown");
    }

    public Attack getAttack(String name) {
        Attack attack = attackRepository.findByName(name);
        return attack != null ? attack : new Attack("Unknown");
    }
}
